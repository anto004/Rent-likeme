package app.rent_likeme.com.rent_likeme.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import app.rent_likeme.com.rent_likeme.util.Utility;

/**
 * Created by anto004 on 3/3/18.
 */

public class Result implements Comparable<Result>{
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
    public double latitude;
    public double longitude;

    Result(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    //With reference to provided location calculate the distance
    public double distance(){
        return Utility.calculateDistance(latitude, longitude,
                this.location.latitude, this.location.longitude);
    }
    //Sort by distance
    @Override
    public int compareTo(@NonNull Result o) {
        return Double.compare(this.distance(), o.distance());
    }

    public static void sortByCompanyName(List<Result> results){
        Collections.sort(results, new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o1.provider.companyName.compareTo(o2.provider.companyName);
            }
        });
    }

    public static void sortByPriceLowToHigh(List<Result> results){
        Collections.sort(results, new Comparator<Result>() {
            @Override
            public int compare(Result r1, Result r2) {
                Double r1Min = Double.MAX_VALUE, r2Min = Double.MAX_VALUE;
                List<Car> r1Cars = r1.getCars();
                for (Car r1Car : r1Cars) {
                    //min of r1
                    r1Min = r1Min <= Double.parseDouble(r1Car.estimatedTotal.amount) ? r1Min :
                            Double.parseDouble(r1Car.estimatedTotal.amount);
                }

                List<Car> r2Cars = r2.getCars();
                for (Car r2Car : r2Cars) {
                    //min of r2
                    r2Min = r2Min <= Double.parseDouble(r2Car.estimatedTotal.amount) ? r2Min :
                            Double.parseDouble(r2Car.estimatedTotal.amount);
                }
                return Double.compare(r1Min, r2Min);
            }
        });
    }

    public static void sortByPriceHighToLow(List<Result> results){
        Collections.sort(results, new Comparator<Result>() {
            @Override
            public int compare(Result r1, Result r2) {
                Double r1Min = Double.MAX_VALUE, r2Min = Double.MAX_VALUE;
                List<Car> r1Cars = r1.getCars();
                for (Car r1Car : r1Cars) {
                    //min of r1
                    r1Min = r1Min <= Double.parseDouble(r1Car.estimatedTotal.amount) ? r1Min :
                            Double.parseDouble(r1Car.estimatedTotal.amount);
                }

                List<Car> r2Cars = r2.getCars();
                for (Car r2Car : r2Cars) {
                    //min of r2
                    r2Min = r2Min <= Double.parseDouble(r2Car.estimatedTotal.amount) ? r2Min :
                            Double.parseDouble(r2Car.estimatedTotal.amount);
                }
                //Reverse
                Double d = r2Min - r1Min;
                return d.intValue();
            }
        });
    }
}
