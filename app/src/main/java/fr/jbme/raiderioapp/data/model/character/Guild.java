package fr.jbme.raiderioapp.data.model.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Guild {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("realm")
    @Expose
    public String realm;

}
