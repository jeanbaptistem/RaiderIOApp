package fr.jbme.raiderioapp.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import fr.jbme.raiderioapp.R

class HeaderFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.header_preferences, rootKey)
    }
}