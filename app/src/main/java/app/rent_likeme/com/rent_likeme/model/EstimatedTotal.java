package app.rent_likeme.com.rent_likeme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anto004 on 3/3/18.
 */

public class EstimatedTotal implements Parcelable {

    @SerializedName("amount")
    @Expose
    public String amount;
    @SerializedName("currency")
    @Expose
    public String currency;

    protected EstimatedTotal(Parcel in) {
        amount = in.readString();
        currency = in.readString();
    }

    public static final Creator<EstimatedTotal> CREATOR = new Creator<EstimatedTotal>() {
        @Override
        public EstimatedTotal createFromParcel(Parcel in) {
            return new EstimatedTotal(in);
        }

        @Override
        public EstimatedTotal[] newArray(int size) {
            return new EstimatedTotal[size];
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