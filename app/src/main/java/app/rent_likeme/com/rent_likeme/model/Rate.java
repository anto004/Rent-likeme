package app.rent_likeme.com.rent_likeme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anto004 on 3/3/18.
 */

public class Rate {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("price")
    @Expose
    public Price price;

}