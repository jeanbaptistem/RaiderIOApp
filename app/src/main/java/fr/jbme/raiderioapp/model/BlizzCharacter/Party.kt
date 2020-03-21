package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Party(

    @SerializedName("avatar") val avatar: Avatar,
    @SerializedName("averageItemLevel") val averageItemLevel: Int,
    @SerializedName("class") val _class: Class_,
    @SerializedName("name") val name: String,
    @SerializedName("race") val race: Race,
    @SerializedName("realm") val realm: Realm,
    @SerializedName("region") val region: String,
    @SerializedName("spec") val spec: Spec,
    @SerializedName("url") val url: String
)