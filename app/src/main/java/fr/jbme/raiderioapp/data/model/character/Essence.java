package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Essence {

    @SerializedName("slot")
    @Expose
    public Integer slot;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("rank")
    @Expose
    public Integer rank;
    @SerializedName("power")
    @Expose
    public Power power;

}
