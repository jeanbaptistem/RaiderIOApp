package fr.jbme.raiderioapp.view.activity.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ListPopupWindow
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.service.model.blizzard.profileInfo.ProfileInfo
import fr.jbme.raiderioapp.view.activity.main.popupWindow.PopupCharacterItem
import fr.jbme.raiderioapp.view.activity.main.popupWindow.PopupListAdapter
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_toolbar.*


@SuppressLint("DefaultLocale")
class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController: NavController

    private lateinit var customToolbar: CustomToolbar

    private val characterList = mutableListOf<PopupCharacterItem>()
    private val selectedCharacter = MutableLiveData<PopupCharacterItem>()

    private lateinit var popupWindow: ListPopupWindow
    private lateinit var popupListAdapter: PopupListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        nav_view.inflateMenu(R.menu.activity_main_drawer)
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

        popupListAdapter =
            PopupListAdapter(
                this,
                characterList
            )
        popupWindow = ListPopupWindow(this).apply {
            setAdapter(popupListAdapter)
            setOnItemClickListener { _, _, position, _ ->
                selectedCharacter.value = popupListAdapter.charList[position]
                dismiss()
            }
        }

        observeViewModel(mainActivityViewModel)
    }

    private fun setupToolbar(customToolbar: CustomToolbar?) {
        customToolbar?.run {
            setOnBackPressedListener { onBackPressed() }
            setOnHomeButtonClickListener { navController.navigateUp(appBarConfiguration) }
            setOnProfileClickListener { openCharacterSelection(it) }
        }

    }

    private fun openCharacterSelection(view: View) {
        if (popupWindow.isShowing) popupWindow.dismiss()
        else {
            popupWindow.run {
                anchorView = view
                show()
            }
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
                .filter { characters -> characters.level >= 100 }
                .sortedByDescending { characters -> characters.level }
                .sortedByDescending { characters -> characters.realm.slug }
                .forEach { char ->
                    characterList.add(
                        PopupCharacterItem(
                            char
                        )
                    )
                }
        }
        popupListAdapter.run {
            this.charList = characterList
            notifyDataSetChanged()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
