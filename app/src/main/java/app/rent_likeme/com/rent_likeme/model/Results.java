package app.rent_likeme.com.rent_likeme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anto004 on 3/3/18.
 */

public class Results {

    @SerializedName("results")
    @Expose
    public List<Result> results = null;

}
