package fr.jbme.raiderioapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
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
import com.squareup.picasso.Target
import fr.jbme.raiderioapp.data.contants.*
import fr.jbme.raiderioapp.data.model.character.CharacterResponse
import fr.jbme.raiderioapp.network.RaiderIOService
import fr.jbme.raiderioapp.network.RetrofitInstance
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import fr.jbme.raiderioapp.ui.login.LoginViewModel
import fr.jbme.raiderioapp.ui.login.LoginViewModelFactory
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences
    private var raiderIOService: RaiderIOService? = null

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var loginViewModel: LoginViewModel

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        raiderIOService = RetrofitInstance.retrofitInstance?.create(RaiderIOService::class.java)
        loginViewModel =
            ViewModelProviders.of(this, LoginViewModelFactory()).get(LoginViewModel::class.java)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
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
        val headerView = navView.getHeaderView(0)
        val headerLayout = headerView.navHeaderLayout
        val navHeaderTitle: TextView = headerView.findViewById(R.id.navHeaderTitle)
        val navHeaderDescription: TextView = headerView.findViewById(R.id.navHeaderDescription)
        val navHeaderThumbnail: ImageView = headerView.findViewById(R.id.navHeaderThumbnail)

        val characterName: String = sharedPref.getString(CHARACTER_NAME_KEY, "")!!
        val realmName: String = sharedPref.getString(REALM_NAME_KEY, "")!!
        val region: String = sharedPref.getString(REGION_KEY, "")?.toUpperCase(Locale.ROOT)!!

        val call = raiderIOService?.getCharacterInfo(region, realmName, characterName)
        call?.enqueue(object : Callback<CharacterResponse> {
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                loginViewModel.logout()
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, LoginActivity::class.java)
                applicationContext.startActivity(intent)
            }

            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful) {
                    val character = response.body()!!
                    navHeaderTitle.text = character.name
                    navHeaderDescription.text = String.format(
                        "%s %s", character.realm, character.region.toUpperCase(
                            Locale.ROOT
                        )
                    )
                    // Fixme: There is not always a loaded image in the view or background
                    // 1rst login: only placeholder
                    // 2nd login: sometimes images are loaded
                    Picasso.get().setIndicatorsEnabled(RaiderIOApp.DEBUG)
                    Picasso.get().load(character.thumbnailUrl)
                        .resize(180, 180)
                        .placeholder(getDrawable(R.mipmap.ic_launcher)!!)
                        .error(getDrawable(R.mipmap.ic_launcher)!!)
                        .into(navHeaderThumbnail)

                    val bgUrl = when (character._class) {
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
                    //Fixme: same here
                    Picasso.get().setIndicatorsEnabled(RaiderIOApp.DEBUG)
                    Picasso.get().load(bgUrl)
                        .resize(headerLayout.measuredWidth, headerLayout.measuredHeight)
                        .centerCrop(Gravity.START)
                        .placeholder(getDrawable(R.drawable.side_nav_bar)!!)
                        .error(getDrawable(R.drawable.side_nav_bar)!!)
                        .into(object : Target {
                            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                                headerLayout.background = placeHolderDrawable
                            }

                            override fun onBitmapFailed(
                                e: java.lang.Exception?,
                                errorDrawable: Drawable?
                            ) {
                                headerLayout.background = errorDrawable
                            }

                            override fun onBitmapLoaded(
                                bitmap: Bitmap?,
                                from: Picasso.LoadedFrom?
                            ) {
                                headerLayout.background =
                                    BitmapDrawable(baseContext.resources, bitmap)
                            }
                        })
                } else {
                    val errorResponse =
                        NetworkErrorUtils.parseError(response)
                    onFailure(call, Exception(errorResponse.message))
                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
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
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
