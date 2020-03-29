package fr.jbme.raiderioapp.view.activity.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.blizzard.profileInfo.Characters
import fr.jbme.raiderioapp.service.model.blizzard.profileInfo.ProfileInfo
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_toolbar.*


@SuppressLint("DefaultLocale")
class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController: NavController

    private lateinit var customToolbar: CustomToolbar

    private val characterList = mutableListOf<Characters>()
    private val selectedCharacter = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        nav_view.inflateMenu(R.menu.activity_character_drawer)
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_armory,
                R.id.nav_raid,
                R.id.nav_dungeon,
                R.id.nav_character_page
            ), drawer_layout
        )
        nav_view.setupWithNavController(navController)

        customToolbar = CustomToolbar.setupCustomToolbar(toolbar_layout)
        setupToolbar(customToolbar)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            customToolbar.setToolbarTitle(destination.label)
        }

        observeViewModel(mainActivityViewModel)
    }

    private fun setupToolbar(customToolbar: CustomToolbar?) {
        customToolbar?.run {
            setOnBackPressedListener { navController.popBackStack() }
            setOnHomeButtonClickListener { navController.navigateUp(appBarConfiguration) }
            setOnProfileClickListener { openCharacterSelection(it) }
        }

    }

    fun openCharacterSelection(view: View) {
        val menu = PopupMenu(this, view)
        menu.apply {
            gravity = Gravity.END
            characterList.forEach { characters ->
                this.menu.add(characters.name + "-" + characters.realm.name)
            }
            setOnMenuItemClickListener {
                selectedCharacter.value = it.title.toString()
                drawer_layout.closeDrawers()
                true
            }
            show()
        }
    }

    private fun observeViewModel(viewModel: MainActivityViewModel) {
        selectedCharacter.observe(this, Observer {
            viewModel.selectedCharacter(it)
        })
        viewModel.profileInfoData.observe(this, Observer {
            populateCharacterSelectionPopup(it)
        })
        viewModel.characterData.observe(this, Observer {
            customToolbar.setCharName(it.name)
        })
        viewModel.characterMedia.observe(this, Observer {
            customToolbar.setCharThumbnail(it.avatar_url)
        })
        viewModel.updateSharedPref.observe(this, Observer { })
    }

    private fun populateCharacterSelectionPopup(profileInfo: ProfileInfo) {
        profileInfo.wow_accounts.forEach { wowAccounts ->
            wowAccounts.characters
                .sortedByDescending { characters -> characters.level }
                .filter { characters -> characters.level >= 100 }
                .forEach { char -> characterList.add(char) }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
