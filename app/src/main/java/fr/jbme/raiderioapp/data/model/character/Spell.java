package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Spell {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("school")
    @Expose
    public Integer school;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("rank")
    @Expose
    public String rank;

}
