package app.rent_likeme.com.rent_likeme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anto004 on 3/3/18.
 */

public class Price {

    @SerializedName("amount")
    @Expose
    public String amount;
    @SerializedName("currency")
    @Expose
    public String currency;
}
