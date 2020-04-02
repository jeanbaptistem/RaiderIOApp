package fr.jbme.raiderioapp.view.activity.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ListPopupWindow
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import fr.jbme.raiderioapp.R
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
    private lateinit var searchBar: SearchView

    private val selectedCharacter = MutableLiveData<PopupCharacterItem>()

    private lateinit var popupWindow: ListPopupWindow
    private lateinit var popupListAdapter: PopupListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Init nav controller
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

        // Init toolbar
        customToolbar = CustomToolbar.setupCustomToolbar(toolbar_layout)
        setupToolbar(customToolbar)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            customToolbar.setToolbarTitle(destination.label)
        }

        // Init character popup list
        popupListAdapter = PopupListAdapter(this, listOf())
        popupWindow = ListPopupWindow(this).apply {
            setAdapter(popupListAdapter)
            setDropDownGravity(Gravity.END)
            setOnItemClickListener { _, _, position, _ ->
                selectedCharacter.value = popupListAdapter.charList[position]
                val bundle =
                    bundleOf("self" to bundleOf("character" to popupListAdapter.charList[position]))
                navController.navigate(R.id.nav_character_page, bundle)
                dismiss()
            }
        }

        searchBar = findViewById(R.id.searchView)
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchBar.clearFocus()
                if (query.isNotEmpty() && navController.currentDestination != navController.graph.findNode(
                        R.id.nav_search
                    )
                ) {
                    navController.navigate(R.id.nav_search)
                }
                mainActivityViewModel.performCharacterSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty() && navController.currentDestination != navController.graph.findNode(
                        R.id.nav_search
                    )
                ) {
                    navController.navigate(R.id.nav_search)
                }
                mainActivityViewModel.performCharacterSearch(newText)
                return true
            }
        })
        findViewById<ImageView>(R.id.search_close_btn).setOnClickListener {
            searchBar.setQuery("", true)
        }

        observeViewModel(mainActivityViewModel)
    }

    private fun setupToolbar(customToolbar: CustomToolbar?) {
        customToolbar?.run {
            setOnHomeButtonClickListener { navController.navigateUp(appBarConfiguration) }
            setOnProfileClickListener { openCharacterSelection(it) }
        }
    }

    private fun openCharacterSelection(view: View) {
        if (popupWindow.isShowing) popupWindow.dismiss() //TODO: fix this
        else {
            popupWindow.run {
                anchorView = view
                setContentWidth(popupListAdapter.measureContentWidth())
                show()
            }
        }
    }

    private fun observeViewModel(viewModel: MainActivityViewModel) {
        selectedCharacter.observe(this, Observer {
            viewModel.selectedCharacter(it)
            customToolbar.setCharName(it.name)
            customToolbar.setCharThumbnail(it.thumbnailUrl)
        })
        viewModel.toolbarCharactersList.observe(this, Observer {
            popupListAdapter.run {
                this.charList = it
                notifyDataSetChanged()
            }
        })
        viewModel.updateSharedPref.observe(this, Observer {})
        viewModel.updateSelectedChar.observe(this, Observer {
            selectedCharacter.value = it
        })
        viewModel.searchQuery.observe(this, Observer {})
    }
}
