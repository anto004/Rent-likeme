package app.rent_likeme.com.rent_likeme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anto004 on 3/3/18.
 */

public class Price implements Parcelable {
    @SerializedName("amount")
    @Expose
    public String amount;
    @SerializedName("currency")
    @Expose
    public String currency;

    protected Price(Parcel in) {
        amount = in.readString();
        currency = in.readString();
    }

    public static final Creator<Price> CREATOR = new Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel in) {
            return new Price(in);
        }

        @Override
        public Price[] newArray(int size) {
            return new Price[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(amount);
        parcel.writeString(currency);
    }
}
