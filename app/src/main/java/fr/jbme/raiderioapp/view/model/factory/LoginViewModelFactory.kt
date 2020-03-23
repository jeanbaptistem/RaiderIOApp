package fr.jbme.raiderioapp.view.model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.view.model.LoginViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = RaiderIOApp.loginRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
