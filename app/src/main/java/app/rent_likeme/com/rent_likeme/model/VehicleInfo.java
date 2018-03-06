package app.rent_likeme.com.rent_likeme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anto004 on 3/3/18.
 */

public class VehicleInfo {

    @SerializedName("acriss_code")
    @Expose
    public String acrissCode;
    @SerializedName("transmission")
    @Expose
    public String transmission;
    @SerializedName("fuel")
    @Expose
    public String fuel;
    @SerializedName("air_conditioning")
    @Expose
    public Boolean airConditioning;
    @SerializedName("category")
    @Expose
    public String category;
    @SerializedName("type")
    @Expose
    public String type;

}