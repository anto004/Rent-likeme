package app.rent_likeme.com.rent_likeme.map;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by anto004 on 3/10/18.
 */

public class GeocoderAddressService extends IntentService {
    public static final String LOG_TAG = GeocoderAddressService.class.getSimpleName();
    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final int ADDRESS_INPUT_NAME_KEY = 10;
    public static final int ADDRESS_INPUT_LOCATION_KEY = 11 ;
    public static final String RETURN_RECEIVER_KEY = "return_receiver";
    public static final String RESULT_DATA_KEY = "result_data";
    public static final String RESULT_ADDRESS_KEY = "result_address";
    public static final String ADDRESS_NAME_KEY = "address_name";
    public static final String INPUT_TYPE_KEY = "input_type";
    public static final String LOCATION_LATITUDE_KEY = "location_latitude";
    public static final String LOCATION_LONGITUDE_KEY = "location_longitude";
    public ResultReceiver mResultReceiver;

    public GeocoderAddressService() {
        super("GeocoderAddressService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String errorMessage = "";
        List<Address> addressResult = null;

        int inputType = intent.getIntExtra(INPUT_TYPE_KEY, 0);
        if(inputType == ADDRESS_INPUT_NAME_KEY) {
            String name = intent.getStringExtra(ADDRESS_NAME_KEY);

            try {
                addressResult = geocoder.getFromLocationName(name, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(inputType == ADDRESS_INPUT_LOCATION_KEY) {
            double latitude = intent.getDoubleExtra(LOCATION_LATITUDE_KEY, 0);
            double longitude = intent.getDoubleExtra(LOCATION_LONGITUDE_KEY, 0);

            try {
                addressResult = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mResultReceiver = intent.getParcelableExtra(RETURN_RECEIVER_KEY);
        if(addressResult == null || addressResult.size() == 0){
            if(errorMessage.isEmpty()){
                errorMessage = "Not Found";
                Log.e(LOG_TAG, errorMessage);
            }
            deliverResultToReceiver(FAILURE_RESULT, errorMessage, null);
        }
        else{
            //Sending result address to activity
            Address address = addressResult.get(0);
            List<String> addressFragments = new ArrayList<>();
            for(int i = 0; i <address.getMaxAddressLineIndex(); i++){
                addressFragments.add(address.getAddressLine(i));
            }
            deliverResultToReceiver(SUCCESS_RESULT,
                    TextUtils.join(System.getProperty("line.separator"),addressFragments), address);
        }
    }

    private void deliverResultToReceiver(int resultCode, String message, Address address) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RESULT_ADDRESS_KEY, address);
        bundle.putString(RESULT_DATA_KEY, message);
        //Using the same result receiver to send data back to calling activity
        mResultReceiver.send(resultCode, bundle);
    }
}
