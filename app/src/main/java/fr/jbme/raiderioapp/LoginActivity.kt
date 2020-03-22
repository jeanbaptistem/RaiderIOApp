package fr.jbme.raiderioapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.jbme.raiderioapp.model.*
import fr.jbme.raiderioapp.model.login.Result
import fr.jbme.raiderioapp.ui.login.LoginViewModel
import fr.jbme.raiderioapp.ui.login.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var sharedPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharedPref = getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        val loginViewModel = LoginViewModelFactory().create(LoginViewModel::class.java)

        val sharedPrefToken = sharedPref?.getString(BEARER_TOKEN_KEY, null)

        if (!sharedPrefToken.isNullOrEmpty() && intent?.action == "android.intent.action.MAIN") {
            loginViewModel.checkToken(sharedPrefToken)
            loginViewModel.tokenCheck.observe(this, Observer {
                if (it is Result.Success) {
                    Log.i("tokenCheck", " success")
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    it as Result.Error
                    Log.i("Check failed", it.exception.message.toString())
                    loginWebView.loadUrl("https://${REGION}.battle.net/oauth/authorize?response_type=code&client_id=$CLIENT_ID&redirect_uri=$REDIRECTED_URI&scope=wow.profile")
                }
            })
        } else {
            login.isEnabled = true
            login.setOnClickListener {
                loginWebView.loadUrl("https://${REGION}.battle.net/oauth/authorize?response_type=code&client_id=$CLIENT_ID&redirect_uri=$REDIRECTED_URI&scope=wow.profile")
            }
        }

        if (intent?.action == "android.intent.action.VIEW" &&
            !intent?.data?.getQueryParameter("code").isNullOrEmpty()
        ) {
            val code = intent?.data?.getQueryParameter("code")
            loginViewModel.login(code)
            loginViewModel.loggedUser.observe(this, Observer {
                startActivity(Intent(this, MainActivity::class.java))
            })
        }
    }
}
