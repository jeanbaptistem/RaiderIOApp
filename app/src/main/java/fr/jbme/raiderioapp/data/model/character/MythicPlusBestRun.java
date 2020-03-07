package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MythicPlusBestRun {

    @SerializedName("dungeon")
    @Expose
    public String dungeon;
    @SerializedName("short_name")
    @Expose
    public String shortName;
    @SerializedName("mythic_level")
    @Expose
    public Integer mythicLevel;
    @SerializedName("completed_at")
    @Expose
    public String completedAt;
    @SerializedName("clear_time_ms")
    @Expose
    public Integer clearTimeMs;
    @SerializedName("num_keystone_upgrades")
    @Expose
    public Integer numKeystoneUpgrades;
    @SerializedName("map_challenge_mode_id")
    @Expose
    public Integer mapChallengeModeId;
    @SerializedName("score")
    @Expose
    public Float score;
    @SerializedName("affixes")
    @Expose
    public List<Affix> affixes = null;
    @SerializedName("url")
    @Expose
    public String url;

}
