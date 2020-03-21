package fr.jbme.raiderioapp

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.components.CustomLinearLayout
import fr.jbme.raiderioapp.model.BG_DEFAULT_URL
import fr.jbme.raiderioapp.model.login.LoggedInUser
import fr.jbme.raiderioapp.ui.login.LoginViewModel
import fr.jbme.raiderioapp.ui.login.LoginViewModelFactory
import fr.jbme.raiderioapp.ui.navigation.navHeader.NavHeaderViewModel
import fr.jbme.raiderioapp.ui.navigation.toolbar.ToolbarViewModel
import fr.jbme.raiderioapp.utils.Whatever
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val user: LoggedInUser = RaiderIOApp.loginRepository.user!!

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var loginViewModel: LoginViewModel

    private lateinit var navHeaderView: View
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginViewModel = LoginViewModelFactory().create(LoginViewModel::class.java)

        setSupportActionBar(toolbar)

        navHeaderView = nav_view.inflateHeaderView(R.layout.nav_header_main)
        nav_view.inflateMenu(R.menu.activity_main_drawer)

        navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_armory, R.id.nav_raid, R.id.nav_dungeon
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
                R.id.nav_raid, R.id.nav_dungeon -> {
                    app_bar.setExpanded(false)
                }
            }
        }

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupNavHeader()
        setupToolbar()
    }

    private fun setupToolbar() {
        val toolbarViewModel: ToolbarViewModel =
            ViewModelProvider.NewInstanceFactory().create(ToolbarViewModel::class.java)

        try {
            toolbarViewModel.fetchData(
                Whatever.parseToSlug(user.region)!!,
                Whatever.parseToSlug(user.realmName)!!,
                Whatever.parseToSlug(user.characterName)!!
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        toolbarViewModel.character.observe(this, Observer {
            Picasso.get()
                .load(it.character.render.url)
                .resize(
                    toolbar_layout.width,
                    resources.getDimension(R.dimen.app_bar_expanded_height).toInt()
                )
                .centerCrop()
                .into(toolbar_layout)

            charTitleTextView.text =
                it.character.prefix?.capitalize() ?: it.character.suffix?.capitalize()
            charNameTextView.text = it.character.name
            charNameTextView.setTextColor(
                when (it.character._class.enum) {
                    "DEATHKNIGHT" -> getColor(R.color.DEATHKNIGHT)
                    "DEMONHUNTER" -> getColor(R.color.DEMONHUNTER)
                    "DRUID" -> getColor(R.color.DRUID)
                    "HUNTER" -> getColor(R.color.HUNTER)
                    "MAGE" -> getColor(R.color.MAGE)
                    "MONK" -> getColor(R.color.MONK)
                    "PALADIN" -> getColor(R.color.PALADIN)
                    "PRIEST" -> getColor(R.color.PRIEST)
                    "ROGUE" -> getColor(R.color.ROGUE)
                    "SHAMAN" -> getColor(R.color.SHAMAN)
                    "WARLOCK" -> getColor(R.color.WARLOCK)
                    "WARRIOR" -> getColor(R.color.WARRIOR)
                    else -> getColor(R.color.colorText)
                }
            )
            charClassTextView.text = it.character._class.name
            charSpectextView.text = it.character.spec.name
            charRealmTextView.text = it.character.realm.name

        })
    }


    private fun setupNavHeader() {
        val navViewModel =
            ViewModelProvider.NewInstanceFactory().create(NavHeaderViewModel::class.java)

        navViewModel.fetchData(user.region, user.realmName, user.characterName)

        navViewModel.character.observe(this, Observer {
            navHeaderTitle.text = it.name
            navHeaderDescription.text = String.format(
                "%s %s", it.realm, it.region!!.toUpperCase(
                    Locale.ROOT
                )
            )
            Picasso.get().load(it.thumbnailUrl)
                .resize(180, 180)
                .placeholder(getDrawable(R.mipmap.ic_launcher)!!)
                .error(getDrawable(R.mipmap.ic_launcher)!!)
                .into(navHeaderThumbnail)

            val customLinearLayout: CustomLinearLayout = findViewById(R.id.navHeaderLayout)
            Picasso.get().load(BG_DEFAULT_URL + it.profileBanner + ".jpg")
                .resize(
                    navHeaderView.measuredWidth,
                    resources.getDimension(R.dimen.nav_header_height).toInt()
                )
                .centerCrop(Gravity.START)
                .into(customLinearLayout)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                loginViewModel.logout()
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
}
