package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Raid {

    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("total_bosses")
    @Expose
    public Integer totalBosses;
    @SerializedName("normal_bosses_killed")
    @Expose
    public Integer normalBossesKilled;
    @SerializedName("heroic_bosses_killed")
    @Expose
    public Integer heroicBossesKilled;
    @SerializedName("mythic_bosses_killed")
    @Expose
    public Integer mythicBossesKilled;

}
