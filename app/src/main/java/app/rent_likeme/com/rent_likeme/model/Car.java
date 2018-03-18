package app.rent_likeme.com.rent_likeme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anto004 on 3/3/18.
 */

public class Car implements Parcelable {
    @SerializedName("vehicle_info")
    @Expose
    public VehicleInfo vehicleInfo;
    @SerializedName("rates")
    @Expose
    public List<Rate> rates = null;
    @SerializedName("estimated_total")
    @Expose
    public EstimatedTotal estimatedTotal;

    public Car() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.vehicleInfo, flags);
        dest.writeList(this.rates);
        dest.writeParcelable(this.estimatedTotal, flags);
    }

    protected Car(Parcel in) {
        this.vehicleInfo = in.readParcelable(VehicleInfo.class.getClassLoader());
        this.rates = new ArrayList<Rate>();
        in.readList(this.rates, Rate.class.getClassLoader());
        this.estimatedTotal = in.readParcelable(EstimatedTotal.class.getClassLoader());
    }

    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel source) {
            return new Car(source);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };
}