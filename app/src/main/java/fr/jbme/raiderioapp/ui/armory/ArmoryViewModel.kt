package fr.jbme.raiderioapp.ui.armory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArmoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is armory Fragment"
    }
    val text: LiveData<String> = _text
}