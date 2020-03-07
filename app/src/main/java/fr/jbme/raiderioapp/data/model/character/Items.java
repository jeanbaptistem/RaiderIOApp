package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("head")
    @Expose
    public GearItem head;
    @SerializedName("neck")
    @Expose
    public GearItem neck;
    @SerializedName("shoulder")
    @Expose
    public GearItem shoulder;
    @SerializedName("back")
    @Expose
    public GearItem back;
    @SerializedName("chest")
    @Expose
    public GearItem chest;
    @SerializedName("waist")
    @Expose
    public GearItem waist;
    @SerializedName("wrist")
    @Expose
    public GearItem wrist;
    @SerializedName("hands")
    @Expose
    public GearItem hands;
    @SerializedName("legs")
    @Expose
    public GearItem legs;
    @SerializedName("feet")
    @Expose
    public GearItem feet;
    @SerializedName("finger1")
    @Expose
    public GearItem finger1;
    @SerializedName("finger2")
    @Expose
    public GearItem finger2;
    @SerializedName("trinket1")
    @Expose
    public GearItem trinket1;
    @SerializedName("trinket2")
    @Expose
    public GearItem trinket2;
    @SerializedName("mainhand")
    @Expose
    public GearItem mainhand;
    @SerializedName("offhand")
    @Expose
    public GearItem offhand;

}
