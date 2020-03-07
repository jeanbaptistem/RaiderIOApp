package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AzeritePower {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("spell")
    @Expose
    public Spell spell;
    @SerializedName("tier")
    @Expose
    public Integer tier;

}
