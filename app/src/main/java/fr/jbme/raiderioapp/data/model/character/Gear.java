package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gear {

    @SerializedName("item_level_equipped")
    @Expose
    public Integer itemLevelEquipped;
    @SerializedName("item_level_total")
    @Expose
    public Integer itemLevelTotal;
    @SerializedName("artifact_traits")
    @Expose
    public Float artifactTraits;
    @SerializedName("corruption")
    @Expose
    public Corruption corruption;
    @SerializedName("items")
    @Expose
    public Items items;

}
