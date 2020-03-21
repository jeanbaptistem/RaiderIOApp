package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Dungeon(

    @SerializedName("background") var background: Background,
    @SerializedName("bossEncounters") var bossEncounters: List<BossEncounter>,
    @SerializedName("category") var category: Category,
    @SerializedName("description") var description: String,
    @SerializedName("expansion") var expansion: String,
    @SerializedName("id") var id: Int,
    @SerializedName("levelMin") var levelMin: Int,
    @SerializedName("location") var location: String,
    @SerializedName("modes") var modes: List<String>,
    @SerializedName("name") var name: String,
    @SerializedName("screenshot") var screenshot: Screenshot,
    @SerializedName("slug") var slug: String,
    @SerializedName("thumbnail") var thumbnail: Thumbnail,
    @SerializedName("url") var url: String
)