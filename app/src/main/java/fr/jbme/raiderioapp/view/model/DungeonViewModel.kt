package fr.jbme.raiderioapp.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DungeonViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dungeon Fragment"
    }
    val text: LiveData<String> = _text
}