package fr.jbme.raiderioapp.view.activity.mainEmpty

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.jbme.raiderioapp.BEARER_TOKEN_EXTRA
import fr.jbme.raiderioapp.BEARER_TOKEN_KEY
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.SHARED_PREF_KEY
import fr.jbme.raiderioapp.view.activity.login.LoginActivity
import fr.jbme.raiderioapp.view.activity.main.MainActivity

class MainEmptyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_empty)

        val sharedPref: SharedPreferences? =
            getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        val sharedPrefToken = sharedPref?.getString(BEARER_TOKEN_KEY, null)
        if (sharedPrefToken.isNullOrEmpty()) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, MainActivity::class.java).apply {
                putExtra(BEARER_TOKEN_EXTRA, sharedPrefToken)
            })
        }
    }
}
