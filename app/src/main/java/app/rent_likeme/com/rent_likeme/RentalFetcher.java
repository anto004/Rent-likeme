package app.rent_likeme.com.rent_likeme;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static app.rent_likeme.com.rent_likeme.RentalListActivity.LOG_TAG;

/**
 * Created by anto004 on 3/3/18.
 */

public class RentalFetcher {

    static class FetchRentalsTask extends AsyncTask<Void, Void, Void> {

        private URL getUrl() throws IOException {
            final String RENTAL_BASE_URL =
                    "https://api.sandbox.amadeus.com/v1.2/cars/search-circle?";
            final String APPID_PARAM = "apikey";
            final String LAT_PARAM = "latitude";
            final String LONG_PARAM = "longitude";
            final String RADIUS_PARAM = "radius";
            final String PICK_UP_PARAM = "pick_up";
            final String DROP_OFF_PARAM = "drop_off";

            Uri builtUri = Uri.parse(RENTAL_BASE_URL).buildUpon()
                    .appendQueryParameter(APPID_PARAM, BuildConfig.RENTAL_API_KEY)
                    .appendQueryParameter(LAT_PARAM, "35.15")
                    .appendQueryParameter(LONG_PARAM, "-114.57")
                    .appendQueryParameter(RADIUS_PARAM, "20")
                    .appendQueryParameter(PICK_UP_PARAM, "2018-06-07")
                    .appendQueryParameter(DROP_OFF_PARAM, "2018-06-08")
                    .build();
            return new URL(builtUri.toString());
        }

        private String getJsonString(URL url) throws IOException {
            HttpURLConnection urlConnection =  null;
            BufferedReader reader = null;
            StringBuffer buffer = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }
                reader.close();
                return buffer.toString();
            } finally {
                if(urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //"https://api.sandbox.amadeus.com/v1.2/cars/search-circle?
            // apikey=8ziGGBD5UYagqCGpPGKAk0tCg7BHpnhg
            // &latitude=35.1504
            // &longitude=-114.57632
            // &radius=42
            // &pick_up=2018-06-07
            // &drop_off=2018-06-08&lang=EN&currency=USD"
            try {
                String rentalJsonStr = getJsonString(getUrl());
                Log.d(LOG_TAG, "rentalJsonStr:"+rentalJsonStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
