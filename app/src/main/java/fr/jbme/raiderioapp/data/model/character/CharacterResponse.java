package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterResponse {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("race")
    @Expose
    public String race;
    @SerializedName("class")
    @Expose
    public String _class;
    @SerializedName("active_spec_name")
    @Expose
    public String activeSpecName;
    @SerializedName("active_spec_role")
    @Expose
    public String activeSpecRole;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("faction")
    @Expose
    public String faction;
    @SerializedName("achievement_points")
    @Expose
    public Integer achievementPoints;
    @SerializedName("honorable_kills")
    @Expose
    public Integer honorableKills;
    @SerializedName("thumbnail_url")
    @Expose
    public String thumbnailUrl;
    @SerializedName("region")
    @Expose
    public String region;
    @SerializedName("realm")
    @Expose
    public String realm;
    @SerializedName("profile_url")
    @Expose
    public String profileUrl;
    @SerializedName("profile_banner")
    @Expose
    public String profileBanner;
    @SerializedName("mythic_plus_ranks")
    @Expose
    public MythicPlusRanks mythicPlusRanks;
    @SerializedName("mythic_plus_best_runs")
    @Expose
    public List<MythicPlusBestRun> mythicPlusBestRuns = null;
    @SerializedName("gear")
    @Expose
    public Gear gear;
    @SerializedName("raid_progression")
    @Expose
    public RaidProgression raidProgression;
    @SerializedName("guild")
    @Expose
    public Guild guild;

}
