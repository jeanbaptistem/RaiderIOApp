package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Corruption {

    @SerializedName("added")
    @Expose
    public Integer added;
    @SerializedName("resisted")
    @Expose
    public Integer resisted;
    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("cloakRank")
    @Expose
    public Integer cloakRank;
    @SerializedName("spells")
    @Expose
    public List<Spell> spells = null;

}
