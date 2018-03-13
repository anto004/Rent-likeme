package app.rent_likeme.com.rent_likeme.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by anto004 on 3/9/18.
 */

public class Utility {
    public static SimpleDateFormat getDateFormat(){
        return new SimpleDateFormat("EEE, MMM d, ''yy", Locale.US);
    }

    public static SimpleDateFormat getCurrentDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    }

    //Using Haversine formula
    //return distance in miles
    public static double calculateDistance(double lat1, double long1,
                                           double lat2, double long2){
        double R = 3958.756; //earth's radius in miles
        double φ1 = Math.toRadians(lat1);
        double φ2 = Math.toRadians(lat2);
        double Δφ = Math.toRadians(lat2-lat1);
        double Δλ = Math.toRadians(long2-long1);

        double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
                Math.cos(φ1) * Math.cos(φ2) *
                        Math.sin(Δλ/2) * Math.sin(Δλ/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return R * c;
    }
}
