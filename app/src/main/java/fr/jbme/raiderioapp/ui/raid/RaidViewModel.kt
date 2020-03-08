package fr.jbme.raiderioapp.ui.raid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RaidViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is raid Fragment"
    }
    val text: LiveData<String> = _text
}