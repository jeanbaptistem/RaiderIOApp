package fr.jbme.raiderioapp.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import fr.jbme.raiderioapp.MainActivity
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.data.contants.CHARACTER_NAME_KEY
import fr.jbme.raiderioapp.data.contants.REALM_NAME_KEY
import fr.jbme.raiderioapp.data.contants.REGION_KEY
import fr.jbme.raiderioapp.data.contants.SHARED_PREF_KEY
import fr.jbme.raiderioapp.data.model.login.LoggedInUser
import java.util.*


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var sharedPref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPref = context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        val realmNameEditText = view.findViewById<EditText>(R.id.realmName)
        val characterNameEditText = view.findViewById<EditText>(R.id.characterName)
        val regionSelectionSpinner = view.findViewById<Spinner>(R.id.regionSelection)
        val loginButton = view.findViewById<Button>(R.id.login)
        val loadingProgressBar = view.findViewById<ProgressBar>(R.id.loading)

        sharedPref?.let {
            realmNameEditText.setText(it.getString(REALM_NAME_KEY, ""))
            characterNameEditText.setText(it.getString(CHARACTER_NAME_KEY, ""))
            val spinnerRegionOptions = resources.getStringArray(R.array.regionOptions)
            regionSelectionSpinner.setSelection(
                spinnerRegionOptions.indexOf(
                    it.getString(REGION_KEY, "eu")?.toUpperCase(
                        Locale.ROOT
                    )
                )
            )
            loginViewModel.loginDataChanged(
                realmNameEditText.text.toString(),
                characterNameEditText.text.toString()
            )
        }

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                loginFormState.realmNameError?.let {
                    realmNameEditText.error = getString(it)
                }
                loginFormState.characterNameError?.let {
                    characterNameEditText.error = getString(it)
                }
            })

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it, view)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    realmNameEditText.text.toString(),
                    characterNameEditText.text.toString()
                )
            }
        }

        realmNameEditText.addTextChangedListener(afterTextChangedListener)
        characterNameEditText.addTextChangedListener(afterTextChangedListener)

        characterNameEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loadingProgressBar.visibility = View.VISIBLE
                loginViewModel.login(
                    realmNameEditText.text.toString(),
                    characterNameEditText.text.toString(),
                    regionSelectionSpinner.selectedItem.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.login(
                realmNameEditText.text.toString(),
                characterNameEditText.text.toString(),
                regionSelectionSpinner.selectedItem.toString()
            )
        }
    }

    private fun updateUiWithUser(model: LoggedInUser, view: View) {
        val welcome =
            getString(R.string.welcome) + " " +
                    model.characterName + " - " +
                    model.realmName +
                    model.region.toUpperCase(Locale.ROOT)
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
        // Go to MainActivity
        Intent(view.context, MainActivity::class.java).run {
            startActivity(this, null)
        }
    }

    private fun showLoginFailed(errorString: String) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}
