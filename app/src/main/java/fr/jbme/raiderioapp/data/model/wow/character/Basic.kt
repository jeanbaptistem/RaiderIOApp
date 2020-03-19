package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Basic(

    @SerializedName("primary") val primary: List<Primary>,
    @SerializedName("secondary") val secondary: List<Secondary>
)