package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Basic(

    @SerializedName("primary") val primary: List<Primary>,
    @SerializedName("secondary") val secondary: List<Secondary>
)