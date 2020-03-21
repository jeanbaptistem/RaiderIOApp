package fr.jbme.raiderioapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputLayout
import fr.jbme.raiderioapp.model.CHARACTER_NAME_KEY
import fr.jbme.raiderioapp.model.REALM_NAME_KEY
import fr.jbme.raiderioapp.model.REGION_KEY
import fr.jbme.raiderioapp.model.SHARED_PREF_KEY
import fr.jbme.raiderioapp.model.login.LoggedInUser
import fr.jbme.raiderioapp.ui.login.LoginViewModel
import fr.jbme.raiderioapp.ui.login.LoginViewModelFactory
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private var sharedPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharedPref = getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        loginViewModel = LoginViewModelFactory().create(LoginViewModel::class.java)

        val realmNameLayout = findViewById<TextInputLayout>(R.id.realmNameLayout)
        val characterNameLayout = findViewById<TextInputLayout>(R.id.characterNameLayout)
        val regionSelectionAutoCompleteTextView =
            findViewById<AutoCompleteTextView>(R.id.regionSelection)
        val loginButton = findViewById<Button>(R.id.login)
        val loadingProgressBar = findViewById<ProgressBar>(R.id.loading)

        val regionItemAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            R.layout.dropdown_region_selection_popup,
            resources.getStringArray(R.array.region_options)
        )
        regionSelectionAutoCompleteTextView.setText(resources.getStringArray(R.array.region_options)[0])
        regionSelectionAutoCompleteTextView.setAdapter(regionItemAdapter)

        sharedPref?.let {
            realmNameLayout.editText!!.setText(it.getString(REALM_NAME_KEY, ""))
            characterNameLayout.editText!!.setText(it.getString(CHARACTER_NAME_KEY, ""))
            val spinnerRegionOptions = resources.getStringArray(R.array.region_options)
            regionSelectionAutoCompleteTextView.setSelection(
                spinnerRegionOptions.indexOf(
                    it.getString(REGION_KEY, "eu")?.toUpperCase(Locale.ROOT)
                )
            )
            loginViewModel.loginDataChanged(
                realmNameLayout.editText!!.text.toString(),
                characterNameLayout.editText!!.text.toString()
            )
        }

        loginViewModel.loginFormState.observe(this,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                loginFormState.realmNameError?.let {
                    realmNameLayout.error = getString(it)
                }
                loginFormState.characterNameError?.let {
                    characterNameLayout.error = getString(it)
                }
            })

        loginViewModel.loginResult.observe(this,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it)
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
                    realmNameLayout.editText!!.text.toString(),
                    characterNameLayout.editText!!.text.toString()
                )
            }
        }

        realmNameLayout.editText!!.addTextChangedListener(afterTextChangedListener)
        realmNameLayout.editText!!.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                characterNameLayout.editText!!.requestFocus()
            }
            false
        }

        characterNameLayout.editText!!.addTextChangedListener(afterTextChangedListener)
        characterNameLayout.editText!!.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loadingProgressBar.visibility = View.VISIBLE
                loginViewModel.login(
                    realmNameLayout.editText!!.text.toString(),
                    characterNameLayout.editText!!.text.toString(),
                    regionSelectionAutoCompleteTextView.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.login(
                realmNameLayout.editText!!.text.toString(),
                characterNameLayout.editText!!.text.toString(),
                regionSelectionAutoCompleteTextView.text.toString()
            )
        }
    }

    private fun updateUiWithUser(model: LoggedInUser) {
        val welcome =
            getString(R.string.welcome) + " " +
                    model.characterName + " - " +
                    model.realmName +
                    model.region.toUpperCase(Locale.ROOT)
        Toast.makeText(this, welcome, Toast.LENGTH_LONG).show()
        // Go to MainActivity
        Intent(this, MainActivity::class.java).run {
            startActivity(this, null)
        }
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(this, errorString, Toast.LENGTH_LONG).show()
    }
}
