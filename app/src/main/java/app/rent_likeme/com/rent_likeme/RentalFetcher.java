package app.rent_likeme.com.rent_likeme;

import android.location.Address;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import app.rent_likeme.com.rent_likeme.util.JSONHelper;
import app.rent_likeme.com.rent_likeme.util.JSONHelper.Results;

/**
 * Created by anto004 on 3/3/18.
 */

public class RentalFetcher extends AsyncTask<Address, Integer, Results> {
    private String pickUpDate;
    private String dropOffDate;
    public RentalFetcher(String pickUpDate, String dropOffDate){
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Results doInBackground(Address... addresses) {
        try {
            Address address = addresses[0];
            double latitude = address.getLatitude();
            double longitude = address.getLongitude();

            String rentalJsonStr = getJsonString(getUrl(latitude, longitude));

            if(rentalJsonStr != null) {
                JSONHelper.Results results = JSONHelper.parseJSONRental(rentalJsonStr);
                return results;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Results results) {
        super.onPostExecute(results);
    }

    private URL getUrl(double latitude, double longitude) throws IOException {
        final String RENTAL_BASE_URL =
                "https://api.sandbox.amadeus.com/v1.2/cars/search-circle?";
        final String APPID_PARAM = "apikey";
        final String LAT_PARAM = "latitude";
        final String LONG_PARAM = "longitude";
        final String RADIUS_PARAM = "radius";
        final String PICK_UP_PARAM = "pick_up";
        final String DROP_OFF_PARAM = "drop_off";
        final String RADIUS = "20";
        Uri builtUri = Uri.parse(RENTAL_BASE_URL).buildUpon()
                .appendQueryParameter(APPID_PARAM, BuildConfig.RENTAL_API_KEY)
                .appendQueryParameter(LAT_PARAM, String.valueOf(latitude))
                .appendQueryParameter(LONG_PARAM, String.valueOf(longitude))
                .appendQueryParameter(RADIUS_PARAM, RADIUS)
                .appendQueryParameter(PICK_UP_PARAM, pickUpDate)
                .appendQueryParameter(DROP_OFF_PARAM, dropOffDate)
                .build();
//
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

            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();
                buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                if (buffer.length() == 0) {
                    return null;
                }
                reader.close();
                return buffer.toString();
            }
            else{
                return null;
            }
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
