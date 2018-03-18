package app.rent_likeme.com.rent_likeme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anto004 on 3/3/18.
 */

public class Provider implements Parcelable {
    @SerializedName("company_code")
    @Expose
    public String companyCode;
    @SerializedName("company_name")
    @Expose
    public String companyName;

    public Provider(){
    }

    protected Provider(Parcel in) {
        companyCode = in.readString();
        companyName = in.readString();
    }

    public static final Creator<Provider> CREATOR = new Creator<Provider>() {
        @Override
        public Provider createFromParcel(Parcel in) {
            return new Provider(in);
        }

        @Override
        public Provider[] newArray(int size) {
            return new Provider[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(companyCode);
        parcel.writeString(companyName);
    }
}
