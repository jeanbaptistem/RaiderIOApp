package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeartOfAzeroth {

    @SerializedName("essences")
    @Expose
    public List<Essence> essences = null;
    @SerializedName("level")
    @Expose
    public Integer level;
    @SerializedName("progress")
    @Expose
    public Float progress;

}
