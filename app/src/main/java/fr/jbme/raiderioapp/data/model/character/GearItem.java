package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GearItem {

    @SerializedName("item_id")
    @Expose
    public Integer itemId;
    @SerializedName("item_level")
    @Expose
    public Integer itemLevel;
    @SerializedName("item_quality")
    @Expose
    public Integer itemQuality;
    @SerializedName("is_legion_legendary")
    @Expose
    public Boolean isLegionLegendary;
    @SerializedName("is_azerite_armor")
    @Expose
    public Boolean isAzeriteArmor;
    @SerializedName("azerite_powers")
    @Expose
    public List<AzeritePower> azeritePowers = null;
    @SerializedName("corruption")
    @Expose
    public Corruption corruption;
    @SerializedName("gems")
    @Expose
    public List<Integer> gems = null;
    @SerializedName("bonuses")
    @Expose
    public List<Integer> bonuses = null;

}
