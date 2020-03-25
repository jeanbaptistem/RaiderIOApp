package fr.jbme.raiderioapp.view.activity.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.jbme.raiderioapp.*
import fr.jbme.raiderioapp.service.model.login.Result
import fr.jbme.raiderioapp.view.activity.character.CharacterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref: SharedPreferences? =
            getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        val sharedPrefToken = sharedPref?.getString(BEARER_TOKEN_KEY, null)

        // Token is already know in the sharedPref
        if (!sharedPrefToken.isNullOrEmpty() && intent?.action == "android.intent.action.MAIN") {
            loginViewModel.checkToken(sharedPrefToken).observe(this, Observer {
                // Valid Token
                if (it is Result.Success) {
                    startActivity(Intent(this, CharacterActivity::class.java).apply {
                        putExtra(BEARER_TOKEN_EXTRA, sharedPrefToken)
                    })
                }
                // Invalid Token
                else {
                    loginWebView.loadUrl(
                        "https://$REGION.battle.net/oauth/authorize?response_type=code&client_id=$CLIENT_ID&redirect_uri=$REDIRECTED_URI&scope=wow.profile"
                    )
                }
            })
        } else {
            loginWebView.loadUrl(
                "https://$REGION.battle.net/oauth/authorize?response_type=code&client_id=$CLIENT_ID&redirect_uri=$REDIRECTED_URI&scope=wow.profile"
            )
        }

        if (!intent?.data?.getQueryParameter("code").isNullOrEmpty()) {
            // Return from web browser
            val code = intent?.data?.getQueryParameter("code")
            if (code != null) {
                loginViewModel.loginWithCode(code).observe(this, Observer {
                    // We got the right code and a new bearer token
                    if (it is Result.Success) {
                        with(sharedPref?.edit()) {
                            this?.putString(BEARER_TOKEN_KEY, it.data.access_token)
                            this?.apply()
                        }
                        startActivity(Intent(this, CharacterActivity::class.java).apply {
                            putExtra(BEARER_TOKEN_EXTRA, it.data.access_token)
                        })
                    }
                    // Something goes wrong
                    else {
                        Log.i("LoginActivity", (it as Result.Error).exception.message.toString())
                    }
                })
            } else {
                //TODO: should never happened
                Log.i("LoginActivity", "Query parameter code is null")
            }

        }
    }
}
