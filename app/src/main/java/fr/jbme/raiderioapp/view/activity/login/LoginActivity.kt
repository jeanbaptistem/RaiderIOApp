package fr.jbme.raiderioapp.view.activity.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.jbme.raiderioapp.*
import fr.jbme.raiderioapp.service.model.login.Result
import fr.jbme.raiderioapp.view.activity.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val LOGIN_URL =
        "https://$REGION.battle.net/oauth/authorize?response_type=code&client_id=${BuildConfig.CLIENT_ID}&redirect_uri=${BuildConfig.REDIRECTED_URL}&scope=wow.profile"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref: SharedPreferences? =
            getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

        if (!intent?.data?.getQueryParameter("code").isNullOrEmpty()) {
            // Return from web browser
            loginInfoTextView.text = getString(R.string.login_auth_response)
            val code = intent?.data?.getQueryParameter("code")
            if (code != null) {
                loginViewModel.loginWithCode(code).observe(this, Observer {
                    loginInfoTextView.text = getString(R.string.login_fetching_token)
                    if (it is Result.Success) {
                        // We got the right code and a new bearer token
                        with(sharedPref?.edit()) {
                            this?.putString(BEARER_TOKEN_KEY, it.data.access_token)
                            this?.apply()
                        }
                        startActivity(Intent(this, MainActivity::class.java).apply {
                            putExtra(BEARER_TOKEN_EXTRA, it.data.access_token)
                        })
                    } else {
                        // Something goes wrong
                        loginInfoTextView.text = (it as Result.Error).exception.message
                    }
                })
            } else {
                loginInfoTextView.text = getString(R.string.login_no_response)
            }
        } else {
            loginInfoTextView.text = getString(R.string.login_need_auth)
            loginWebView.loadUrl(LOGIN_URL)
        }
    }
}
