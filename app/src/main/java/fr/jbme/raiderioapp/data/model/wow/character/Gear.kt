package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Gear(

    @SerializedName("back") val back: Back,
    @SerializedName("chest") val chest: Chest,
    @SerializedName("foot") val foot: Foot,
    @SerializedName("hand") val hand: Hand,
    @SerializedName("head") val head: Head,
    @SerializedName("leftFinger") val leftFinger: Finger,
    @SerializedName("leftTrinket") val leftTrinket: Finger,
    @SerializedName("leg") val leg: Leg,
    @SerializedName("neck") val neck: Neck,
    @SerializedName("rightFinger") val rightFinger: Trinket,
    @SerializedName("rightTrinket") val rightTrinket: Trinket,
    @SerializedName("shoulder") val shoulder: Shoulder,
    @SerializedName("waist") val waist: Waist,
    @SerializedName("weapon") val mainHand: Weapon,
    @SerializedName("offhand") val offHand: Weapon,
    @SerializedName("wrist") val wrist: Wrist
)