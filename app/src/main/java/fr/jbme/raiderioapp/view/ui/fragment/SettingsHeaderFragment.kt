package fr.jbme.raiderioapp.view.ui.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import fr.jbme.raiderioapp.R

class SettingsHeaderFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.header_preferences, rootKey)
    }
}