package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MythicPlusRanks {

    @SerializedName("overall")
    @Expose
    public Ranking overall;
    @SerializedName("class")
    @Expose
    public Ranking _class;
    @SerializedName("faction_overall")
    @Expose
    public Ranking factionOverall;
    @SerializedName("faction_class")
    @Expose
    public Ranking factionClass;
    @SerializedName("healer")
    @Expose
    public Ranking healer;
    @SerializedName("class_healer")
    @Expose
    public Ranking classHealer;
    @SerializedName("faction_healer")
    @Expose
    public Ranking factionHealer;
    @SerializedName("faction_class_healer")
    @Expose
    public Ranking factionClassHealer;
    @SerializedName("dps")
    @Expose
    public Ranking dps;
    @SerializedName("class_dps")
    @Expose
    public Ranking classDps;
    @SerializedName("faction_dps")
    @Expose
    public Ranking factionDps;
    @SerializedName("faction_class_dps")
    @Expose
    public Ranking factionClassDps;
    @SerializedName("spec_262")
    @Expose
    public Ranking spec262;
    @SerializedName("faction_spec_262")
    @Expose
    public Ranking factionSpec262;
    @SerializedName("spec_263")
    @Expose
    public Ranking spec263;
    @SerializedName("faction_spec_263")
    @Expose
    public Ranking factionSpec263;
    @SerializedName("spec_264")
    @Expose
    public Ranking spec264;
    @SerializedName("faction_spec_264")
    @Expose
    public Ranking factionSpec264;

}
