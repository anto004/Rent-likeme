package app.rent_likeme.com.rent_likeme.Utility;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by anto004 on 3/9/18.
 */

public class Utility {
    public static SimpleDateFormat getDateFormat(){
        return new SimpleDateFormat("EEE, MMM d, ''yy", Locale.US);
    }
}
