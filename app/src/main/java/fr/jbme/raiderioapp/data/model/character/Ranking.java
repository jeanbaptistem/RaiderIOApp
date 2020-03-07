package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ranking {

    @SerializedName("world")
    @Expose
    public Integer world;
    @SerializedName("region")
    @Expose
    public Integer region;
    @SerializedName("realm")
    @Expose
    public Integer realm;

}
