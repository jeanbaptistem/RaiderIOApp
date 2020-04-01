package fr.jbme.raiderioapp.view.activity.mainEmpty

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.jbme.raiderioapp.BEARER_TOKEN_KEY
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.SHARED_PREF_KEY
import fr.jbme.raiderioapp.utils.network.Result
import fr.jbme.raiderioapp.view.activity.login.LoginActivity
import fr.jbme.raiderioapp.view.activity.login.LoginViewModel
import fr.jbme.raiderioapp.view.activity.main.MainActivity


class MainEmptyActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_empty)

        if (intent.action == Intent.ACTION_MAIN) {
            val sharedPref: SharedPreferences? =
                getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
            val sharedPrefToken = sharedPref?.getString(BEARER_TOKEN_KEY, null)
            if (sharedPrefToken.isNullOrEmpty()) {
                startActivity(Intent(this, LoginActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
                })
            } else {
                loginViewModel.checkToken(sharedPrefToken).observe(this, Observer {
                    if (it is Result.Success) {
                        startActivity(Intent(this, MainActivity::class.java).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
                        })
                    } else {
                        startActivity(Intent(this, LoginActivity::class.java).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
                        })
                    }
                })
            }
        }
    }
}
