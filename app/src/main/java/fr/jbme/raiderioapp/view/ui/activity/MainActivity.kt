package fr.jbme.raiderioapp.view.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.*
import fr.jbme.raiderioapp.components.CustomConstraintLayout
import fr.jbme.raiderioapp.service.model.blizzard.characterMedia.CharacterMedia
import fr.jbme.raiderioapp.service.model.blizzard.characterProfile.CharacterProfile
import fr.jbme.raiderioapp.service.model.blizzard.profileInfo.Characters
import fr.jbme.raiderioapp.service.model.blizzard.profileInfo.ProfileInfo
import fr.jbme.raiderioapp.utils.LiveDataUtils
import fr.jbme.raiderioapp.view.model.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mainActivityViewModel: MainActivityViewModel

    private lateinit var navHeaderView: View
    private lateinit var navController: NavController

    private var profileInfo: ProfileInfo? = null
    private val selectedCharacter = MutableLiveData<Characters>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

        setSupportActionBar(toolbar)

        navHeaderView = nav_view.inflateHeaderView(R.layout.nav_header_main)
        nav_view.inflateMenu(R.menu.activity_main_drawer)

        navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_armory,
                R.id.nav_raid,
                R.id.nav_dungeon
            ), drawer_layout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            toolbar_layout.title = destination.label
            when (destination.id) {
                R.id.nav_armory -> {
                    app_bar.setExpanded(true)
                }
                R.id.nav_raid, R.id.nav_dungeon, R.id.nav_loading -> {
                    app_bar.setExpanded(false)
                }
            }
        }

        //TODO fix loading order
        mainActivityViewModel =
            ViewModelProvider.NewInstanceFactory().create(MainActivityViewModel::class.java)
        observeViewModel(mainActivityViewModel)

        observeSelectedCharacter()
    }

    private fun observeViewModel(mainActivityViewModel: MainActivityViewModel) {
        mainActivityViewModel.profileInfoObservable().observe(this, Observer { profileInfo ->
            this.profileInfo = profileInfo
            if (selectedCharacter.value != null) {
                val realm = sharedPref.getString(REALM_NAME_KEY, null)
                val name = sharedPref.getString(CHARACTER_NAME_KEY, null)
                selectedCharacter.value =
                    profileInfo.wow_accounts.firstOrNull { wowAccounts ->
                        wowAccounts.characters.any { characters ->
                            characters.name.toLowerCase(Locale.ROOT) == name && characters.realm.slug == realm
                        }
                    }?.characters?.firstOrNull { characters ->
                        characters.name.toLowerCase(Locale.ROOT) == name && characters.realm.slug == realm
                    }
            } else {
                selectedCharacter.value = profileInfo.wow_accounts[0].characters[0]
            }
        })
    }

    @SuppressLint("ApplySharedPref")
    private fun observeSelectedCharacter() {
        selectedCharacter.observe(this, Observer { characters ->
            if (sharedPref.getString(REALM_NAME_KEY, "") !=
                characters.realm.slug ||
                sharedPref.getString(CHARACTER_NAME_KEY, "") !=
                characters.name.toLowerCase(Locale.ROOT)
            ) {
                with(sharedPref.edit()) {
                    putString(REALM_NAME_KEY, characters.realm.slug)
                    putString(CHARACTER_NAME_KEY, characters.name.toLowerCase(Locale.ROOT))
                    commit()
                }
            }
            navController.setGraph(
                R.navigation.menu_navigation, bundleOf(
                    "realmSlug" to characters.realm.slug,
                    "characterName" to characters.name.toLowerCase(Locale.ROOT)
                )
            )

            val zippedLiveData = LiveDataUtils.zipPair(
                mainActivityViewModel.characterMediaObservable(),
                mainActivityViewModel.characterProfileObservable()
            )
            zippedLiveData.observe(this, Observer {
                setupNavHeader(it.first)
                setupToolbarBackground(it.first)
                setupToolbar(it.second)
            })
        })
    }

    private fun setupToolbarBackground(characterMedia: CharacterMedia?) {
        Picasso.get().load(characterMedia?.render_url)
            .resize(
                toolbar_layout.width,
                resources.getDimension(R.dimen.app_bar_expanded_height).toInt()
            ).centerCrop()
            .into(toolbar_layout)
    }

    private fun setupToolbar(characterProfile: CharacterProfile?) {
        charNameTextView.text = characterProfile?.name
        charNameTextView.setTextColor(
            when (characterProfile?.character_class?.id) {
                6 -> getColor(R.color.DEATHKNIGHT)
                12 -> getColor(R.color.DEMONHUNTER)
                11 -> getColor(R.color.DRUID)
                3 -> getColor(R.color.HUNTER)
                8 -> getColor(R.color.MAGE)
                10 -> getColor(R.color.MONK)
                2 -> getColor(R.color.PALADIN)
                5 -> getColor(R.color.PRIEST)
                4 -> getColor(R.color.ROGUE)
                7 -> getColor(R.color.SHAMAN)
                9 -> getColor(R.color.WARLOCK)
                1 -> getColor(R.color.WARRIOR)
                else -> getColor(R.color.colorText)
            }
        )
        charClassTextView.text = characterProfile?.character_class?.name
        charSpectextView.text = characterProfile?.active_spec?.name
        charRealmTextView.text = characterProfile?.realm?.name
        charTitleTextView.text = characterProfile?.active_title?.name?.capitalize()
    }

    private fun setupNavHeader(characterMedia: CharacterMedia) {
        characterMedia.let {
            navHeaderTitle.text = it.character.name
            navHeaderDescription.text = it.character.realm.name
            Picasso.get().load(it.avatar_url)
                .resize(180, 180)
                .into(navHeaderThumbnail)

            val customConstraintLayout: CustomConstraintLayout = findViewById(R.id.navHeaderLayout)
            Picasso.get().load(it.bust_url)
                .resize(
                    navHeaderView.measuredWidth,
                    resources.getDimension(R.dimen.nav_header_height).toInt()
                )
                .centerCrop(Gravity.START)
                .into(customConstraintLayout)
        }
    }


    fun showPopup(view: View) {
        val popup = PopupMenu(this, view)
        popup.gravity = Gravity.END
        profileInfo?.wow_accounts?.forEachIndexed { accountIndex, wowAccounts ->
            wowAccounts.characters
                .sortedByDescending { characters -> characters.level }
                .filter { characters -> characters.level == 120 }
                .forEachIndexed { charIndex, char ->
                    popup.menu.add(
                        accountIndex + charIndex,
                        char.id,
                        Menu.NONE,
                        char.name + "-" + char.realm.name
                    )
                }
        }
        popup.setOnMenuItemClickListener {
            selectedCharacter.value =
                profileInfo?.wow_accounts?.find { account ->
                    account.characters.any { character ->
                        character.name + "-" + character.realm.name == it.title
                    }
                }?.characters?.first { character ->
                    character.name + "-" + character.realm.name == it.title
                }
            drawer_layout.closeDrawers()
            true
        }

        popup.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                RaiderIOApp.loginRepository.logout()
                Toast.makeText(this, "Goodbye", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                this.startActivity(intent)
            }
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                this.startActivity(intent)
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setupToolbarBackground(mainActivityViewModel.characterMediaObservable().value)
    }
}
