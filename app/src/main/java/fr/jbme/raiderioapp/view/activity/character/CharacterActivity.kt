package fr.jbme.raiderioapp.view.activity.character

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
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
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.MAIN_SCREEN_BG
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.service.model.blizzard.characterMedia.CharacterMedia
import fr.jbme.raiderioapp.service.model.blizzard.profileInfo.Characters
import fr.jbme.raiderioapp.service.model.blizzard.profileInfo.ProfileInfo
import fr.jbme.raiderioapp.view.activity.login.LoginActivity
import fr.jbme.raiderioapp.view.activity.settings.SettingsActivity
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.nav_header_character.*


@SuppressLint("DefaultLocale")
class CharacterActivity : AppCompatActivity() {
    private val characterActivityViewModel: CharacterActivityViewModel by viewModels()

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navHeaderView: View
    private lateinit var navController: NavController

    private val characterList = mutableListOf<Characters>()
    private val selectedCharacter = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navHeaderView = nav_view.inflateHeaderView(R.layout.nav_header_character)
        nav_view.inflateMenu(R.menu.activity_character_drawer)
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_armory,
                R.id.nav_raid,
                R.id.nav_dungeon,
                R.id.nav_search
            ), drawer_layout
        )
        nav_view.setupWithNavController(navController)

        observeViewModel(characterActivityViewModel)
    }

    fun onSelectionButtonClick(view: View) {
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

    private fun observeViewModel(viewModel: CharacterActivityViewModel) {
        selectedCharacter.observe(this, Observer {
            viewModel.selectedCharacter(it)
        })
        viewModel.profileInfoData.observe(this, Observer {
            populateCharacterSelectionPopup(it)
        })
        viewModel.characterData.observe(this, Observer {
        })
        viewModel.characterMedia.observe(this, Observer {
            setupNavHeader(it)
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

    private fun setupNavHeader(characterMedia: CharacterMedia) {
        //TODO: fix null value on configuration change :: Fixed
        //But header disappear on configuration change
        characterMedia.let { media ->
            navHeaderTitle?.text = media.character.name
            navHeaderDescription?.text = media.character.realm.name
            navHeaderThumbnail?.let {
                Picasso.get()
                    .load(media.avatar_url)
                    .placeholder(R.color.design_default_color_background)
                    .resize(180, 180)
                    .into(it)
            }
            navHeaderLayout?.let {
                Picasso.get()
                    .load(MAIN_SCREEN_BG)
                    .placeholder(R.color.design_default_color_background)
                    .resize(
                        navHeaderView.measuredWidth,
                        resources.getDimension(R.dimen.nav_header_height).toInt()
                    )
                    .centerCrop(Gravity.START)
                    .into(it)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                RaiderIOApp.loginRepository.logout()
                Toast.makeText(this, "Goodbye", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
