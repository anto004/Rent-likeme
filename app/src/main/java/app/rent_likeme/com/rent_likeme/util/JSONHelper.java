package app.rent_likeme.com.rent_likeme.util;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import app.rent_likeme.com.rent_likeme.model.Result;

/**
 * Created by anto004 on 3/5/18.
 */

public class JSONHelper {
    private static final String LOG_TAG = JSONHelper.class.getSimpleName();
    public static Results parseJSONRental(String rentalJsonStr){
        Gson gson = new Gson();
        Results results = gson.fromJson(rentalJsonStr, Results.class);

        if(results != null){
            Log.v(LOG_TAG, "Car found " + results.toString());
        }
        return results;
    }

    public class Results{
        List<Result> results;

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for(Result result: results){
                sb.append(result.provider.companyName).append("-");
                sb.append(result.address.city).append(", ");
            }
            return sb.toString();
        }
    }
}
