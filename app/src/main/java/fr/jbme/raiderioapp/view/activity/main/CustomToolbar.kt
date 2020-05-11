package fr.jbme.raiderioapp.view.activity.main

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.mikhaellopez.circularimageview.CircularImageView
import com.squareup.picasso.Picasso
import fr.jbme.raiderioapp.R

class CustomToolbar {
    private lateinit var charThumbnail: CircularImageView
    private lateinit var charName: TextView
    private lateinit var profileButton: Button

    private lateinit var homeButton: Button
    private lateinit var toolbarTitle: TextView

    fun setCharThumbnail(imgUrl: String?) {
        Picasso.get().load(imgUrl)
            .placeholder(R.color.primaryColor)
            .error(R.color.errorColor)
            .into(charThumbnail)
    }

    fun setCharName(charName: CharSequence?) {
        this.charName.text = charName
    }

    fun setOnProfileClickListener(l: (View) -> Unit) {
        profileButton.setOnClickListener(l)
    }

    fun setOnHomeButtonClickListener(l: (View) -> Unit) {
        homeButton.setOnClickListener(l)
    }


    fun setToolbarTitle(title: CharSequence?) {
        toolbarTitle.text = title
    }

    companion object {
        fun setupCustomToolbar(layout: CollapsingToolbarLayout): CustomToolbar {
            return CustomToolbar().run {
                charThumbnail = layout.findViewById(R.id.toolbarCharThumbnail)
                charName = layout.findViewById(R.id.charName)
                profileButton = layout.findViewById(R.id.toolbarProfileButton)
                homeButton = layout.findViewById(R.id.toolbarHomeButton)
                toolbarTitle = layout.findViewById(R.id.toolbarTitle)
                this
            }
        }
    }
}