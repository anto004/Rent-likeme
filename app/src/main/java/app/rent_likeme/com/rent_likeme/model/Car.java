package app.rent_likeme.com.rent_likeme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anto004 on 3/3/18.
 */

public class Car {

    @SerializedName("vehicle_info")
    @Expose
    public VehicleInfo vehicleInfo;
    @SerializedName("rates")
    @Expose
    public List<Rate> rates = null;
    @SerializedName("estimated_total")
    @Expose
    public EstimatedTotal estimatedTotal;

}