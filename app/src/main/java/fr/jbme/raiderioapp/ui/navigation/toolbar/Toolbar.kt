package fr.jbme.raiderioapp.ui.navigation.toolbar

import androidx.fragment.app.Fragment

class Toolbar : Fragment() {


    //customToolbar = layoutInflater.inflate(R.layout.collapsing_toolbar_main, container, false)
    //setupCustomToolbar()

    /*
        private fun setupCustomToolbar() {
        val dynamicHeightImageView: DynamicHeightImageView =
            customToolbar.findViewById(R.id.dynamicHeightImageView)
        val charNameTextView: TextView = customToolbar.findViewById(R.id.charNameTextView)
        val charClassTextView: TextView = customToolbar.findViewById(R.id.charClassTextView)
        val charSpecTextView: TextView = customToolbar.findViewById(R.id.charSpecTextView)
        val charIlvlTetView: TextView = customToolbar.findViewById(R.id.charIlvlTetView)
        val charRaceTextView: TextView = customToolbar.findViewById(R.id.charRaceTextView)

        val toolbarViewModel =
            ViewModelProvider.NewInstanceFactory().create(ToolbarViewModel::class.java)

        val regionSlug = Whatever.parseToSlug(user.region)
        val realmSlut = Whatever.parseToSlug(user.realmName)
        val characterNameSlug = Whatever.parseToSlug(user.characterName)
        try {
            toolbarViewModel.fetchData(regionSlug!!, realmSlut!!, characterNameSlug!!)
        } catch (e: Exception) {
            // TODO: handle exception
        }
        toolbarViewModel.character.observe(this, Observer {
            Picasso.get().load(it.character.bust.url).into(dynamicHeightImageView)
            charNameTextView.text = it.character.name
            charClassTextView.text = it.character._class.name
            charSpecTextView.text = it.character.spec.name
            charIlvlTetView.text = "Ilvl: " + it.character.averageItemLevel.toString()
            charRaceTextView.text = it.character.race.name

        })
    }
     */
}