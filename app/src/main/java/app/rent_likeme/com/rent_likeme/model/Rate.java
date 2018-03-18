package app.rent_likeme.com.rent_likeme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anto004 on 3/3/18.
 */

public class Rate implements Parcelable {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("price")
    @Expose
    public Price price;

    public Rate() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeParcelable(this.price, flags);
    }

    protected Rate(Parcel in) {
        this.type = in.readString();
        this.price = in.readParcelable(Price.class.getClassLoader());
    }

    public static final Parcelable.Creator<Rate> CREATOR = new Parcelable.Creator<Rate>() {
        @Override
        public Rate createFromParcel(Parcel source) {
            return new Rate(source);
        }

        @Override
        public Rate[] newArray(int size) {
            return new Rate[size];
        }
    };
}