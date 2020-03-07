package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RaidProgression {

    @SerializedName("battle-of-dazaralor")
    @Expose
    public Raid battleOfDazaralor;
    @SerializedName("crucible-of-storms")
    @Expose
    public Raid crucibleOfStorms;
    @SerializedName("nyalotha-the-waking-city")
    @Expose
    public Raid nyalothaTheWakingCity;
    @SerializedName("the-eternal-palace")
    @Expose
    public Raid theEternalPalace;
    @SerializedName("uldir")
    @Expose
    public Raid uldir;

}
