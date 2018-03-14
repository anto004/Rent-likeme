package app.rent_likeme.com.rent_likeme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anto004 on 3/3/18.
 */

public class VehicleInfo implements Parcelable {

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

    protected VehicleInfo(Parcel in) {
        acrissCode = in.readString();
        transmission = in.readString();
        fuel = in.readString();
        byte tmpAirConditioning = in.readByte();
        airConditioning = tmpAirConditioning == 0 ? null : tmpAirConditioning == 1;
        category = in.readString();
        type = in.readString();
    }

    public static final Creator<VehicleInfo> CREATOR = new Creator<VehicleInfo>() {
        @Override
        public VehicleInfo createFromParcel(Parcel in) {
            return new VehicleInfo(in);
        }

        @Override
        public VehicleInfo[] newArray(int size) {
            return new VehicleInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(acrissCode);
        parcel.writeString(transmission);
        parcel.writeString(fuel);
        parcel.writeByte((byte) (airConditioning == null ? 0 : airConditioning ? 1 : 2));
        parcel.writeString(category);
        parcel.writeString(type);
    }
}