package app.rent_likeme.com.rent_likeme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anto004 on 3/3/18.
 */

public class Result {

    @SerializedName("provider")
    @Expose
    public Provider provider;
    @SerializedName("branch_id")
    @Expose
    public String branchId;
    @SerializedName("location")
    @Expose
    public Location location;
    @SerializedName("airport")
    @Expose
    public String airport;
    @SerializedName("address")
    @Expose
    public Address address;
    @SerializedName("cars")
    @Expose
    public List<Car> cars = null;

}
