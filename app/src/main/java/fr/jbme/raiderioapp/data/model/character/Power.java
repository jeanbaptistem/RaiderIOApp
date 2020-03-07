package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Power {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("essence")
    @Expose
    public Essence essence;
    @SerializedName("tierId")
    @Expose
    public Integer tierId;
    @SerializedName("majorPowerSpell")
    @Expose
    public Spell majorPowerSpell;
    @SerializedName("minorPowerSpell")
    @Expose
    public Spell minorPowerSpell;

}
