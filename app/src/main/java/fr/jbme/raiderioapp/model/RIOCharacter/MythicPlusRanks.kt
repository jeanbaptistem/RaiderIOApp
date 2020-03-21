package fr.jbme.raiderioapp.model.RIOCharacter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MythicPlusRanks(
    @SerializedName("overall")
    @Expose
    var overall: Ranking? = null,

    @SerializedName("class")
    @Expose
    var _class: Ranking? = null,

    @SerializedName("faction_overall")
    @Expose
    var factionOverall: Ranking? = null,

    @SerializedName("faction_class")
    @Expose
    var factionClass: Ranking? = null,

    @SerializedName("healer")
    @Expose
    var healer: Ranking? = null,

    @SerializedName("class_healer")
    @Expose
    var classHealer: Ranking? = null,

    @SerializedName("faction_healer")
    @Expose
    var factionHealer: Ranking? = null,

    @SerializedName("faction_class_healer")
    @Expose
    var factionClassHealer: Ranking? = null,

    @SerializedName("dps")
    @Expose
    var dps: Ranking? = null,

    @SerializedName("class_dps")
    @Expose
    var classDps: Ranking? = null,

    @SerializedName("faction_dps")
    @Expose
    var factionDps: Ranking? = null,

    @SerializedName("faction_class_dps")
    @Expose
    var factionClassDps: Ranking? = null,

    @SerializedName("spec_262")
    @Expose
    var spec262: Ranking? = null,

    @SerializedName("faction_spec_262")
    @Expose
    var factionSpec262: Ranking? = null,

    @SerializedName("spec_263")
    @Expose
    var spec263: Ranking? = null,

    @SerializedName("faction_spec_263")
    @Expose
    var factionSpec263: Ranking? = null,

    @SerializedName("spec_264")
    @Expose
    var spec264: Ranking? = null,

    @SerializedName("faction_spec_264")
    @Expose
    var factionSpec264: Ranking? = null
)