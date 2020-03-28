package fr.jbme.raiderioapp.view.activity.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.jbme.raiderioapp.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        backButton.setOnClickListener {
            onBackPressed()
        }
    }

}