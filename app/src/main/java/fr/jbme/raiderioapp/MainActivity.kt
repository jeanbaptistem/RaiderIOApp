package fr.jbme.raiderioapp

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.data.*
import fr.jbme.raiderioapp.data.model.login.LoggedInUser
import fr.jbme.raiderioapp.network.RetrofitRaiderIOInstance
import fr.jbme.raiderioapp.network.services.RaiderIOService
import fr.jbme.raiderioapp.ui.drawer.navHeader.CustomHeaderLayout
import fr.jbme.raiderioapp.ui.drawer.navHeader.NavHeaderViewModel
import fr.jbme.raiderioapp.ui.login.LoginViewModel
import fr.jbme.raiderioapp.ui.login.LoginViewModelFactory
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val user: LoggedInUser = RaiderIOApp.loginRepository.user!!
    private var raiderIOService: RaiderIOService? = null

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var loginViewModel: LoginViewModel

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var navHeaderView: View
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        raiderIOService =
            RetrofitRaiderIOInstance.retrofitInstance?.create(RaiderIOService::class.java)
        loginViewModel = LoginViewModelFactory().create(LoginViewModel::class.java)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        drawerLayout = findViewById(R.id.drawer_layout)

        navView = findViewById(R.id.nav_view)
        navHeaderView = navView.inflateHeaderView(R.layout.nav_header_main)
        navView.inflateMenu(R.menu.activity_main_drawer)

        navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_armory, R.id.nav_dungeon, R.id.nav_raid
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupNavHeader()
    }

    private fun setupNavHeader() {
        val navHeaderTitle: TextView = navHeaderView.findViewById(R.id.navHeaderTitle)
        val navHeaderDescription: TextView = navHeaderView.findViewById(R.id.navHeaderDescription)
        val navHeaderThumbnail: ImageView = navHeaderView.findViewById(R.id.navHeaderThumbnail)

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

            val bgUrl = when (it._class) {
                "Shaman" -> SHAMAN_BG
                "Death Knight" -> DK_BG
                "Demon Hunter" -> DH_BG
                "Druid" -> DRUID_BG
                "Hunter" -> HUNTER_BG
                "Mage" -> MAGE_BG
                "Monk" -> MONK_BG
                "Paladin" -> PALADIN_BG
                "Priest" -> PRIEST_BG
                "Rogue" -> ROGUE_BG
                "Warlock" -> WARLOCK_BG
                "Warrior" -> WARRIOR_BG
                else -> DEFAULT_BG
            }
            val customHeaderLayout: CustomHeaderLayout = findViewById(R.id.navHeaderLayout)
            Picasso.get().load(bgUrl)
                .resize(
                    navHeaderView.measuredWidth,
                    resources.getDimension(R.dimen.nav_header_height).toInt()
                )
                .centerCrop(Gravity.START)
                .placeholder(getDrawable(R.drawable.side_nav_bar)!!)
                .error(getDrawable(R.drawable.side_nav_bar)!!).into(customHeaderLayout)
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
                Snackbar.make(fab, "Setting action click", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
