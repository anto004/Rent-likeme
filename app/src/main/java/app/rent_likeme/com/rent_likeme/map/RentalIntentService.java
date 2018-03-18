package app.rent_likeme.com.rent_likeme.map;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import app.rent_likeme.com.rent_likeme.RentalFetcher;
import app.rent_likeme.com.rent_likeme.RentalListActivity;
import app.rent_likeme.com.rent_likeme.model.Result;
import app.rent_likeme.com.rent_likeme.util.JSONHelper;
import app.rent_likeme.com.rent_likeme.util.Utility;

import static app.rent_likeme.com.rent_likeme.RentalListActivity.DROP_OFF_DATE_PREF;
import static app.rent_likeme.com.rent_likeme.RentalListActivity.PICK_UP_DATE_PREF;


/**
 * Created by anto004 on 3/10/18.
 */

public class RentalIntentService extends IntentService {
    public static final int RUNNING_RESULT = 0;
    public static final int SUCCESS_RESULT = 1;
    public static final int FAILURE_RESULT = 2;
    public static final int ADDRESS_INPUT_NAME_KEY = 10;
    public static final int ADDRESS_INPUT_LOCATION_KEY = 11 ;
    public static final String RETURN_RECEIVER_KEY = "return_receiver";
    public static final String RESULT_DATA_KEY = "result_data";
    public static final String RESULT_MESSAGE_KEY = "result_message";
    public static final String RESULT_ADDRESS_KEY = "result_address";
    public static final String INVALID_ADDRESS_MSG = "invalid_address";
    public static final String ADDRESS_NAME_KEY = "address_name";
    public static final String INPUT_TYPE_KEY = "input_type";
    public static final String LOCATION_LATITUDE_KEY = "location_latitude";
    public static final String LOCATION_LONGITUDE_KEY = "location_longitude";
    public ResultReceiver mResultReceiver;

    public RentalIntentService() {
        super("RentalIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addressResult;

        mResultReceiver = intent.getParcelableExtra(RETURN_RECEIVER_KEY);
        //Informing UI that service is running
        mResultReceiver.send(RUNNING_RESULT, Bundle.EMPTY);

        int inputType = intent.getIntExtra(INPUT_TYPE_KEY, 0);
        //Getting address using either by address name or location
        addressResult = getAddressUsingGeocoder(inputType, geocoder, intent);

        if(addressResult == null || addressResult.size() == 0){
            deliverFailedResultToReceiver(FAILURE_RESULT, INVALID_ADDRESS_MSG);
        }
        else{

            Address address = addressResult.get(0);
            try {
                //Fetching data from API using retrieved address
                List<Result> asyncTaskResults = callAsyncTask(address);

                if(asyncTaskResults == null || asyncTaskResults.size() == 0){
                    deliverFailedResultToReceiver(FAILURE_RESULT, "Not Found");
                }
                else {
                    deliverSuccessResultToReceiver(SUCCESS_RESULT,
                            "Found Rentals Using Address Found Using Geocoder", address, asyncTaskResults);
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.stopSelf();
    }

    private List<Address> getAddressUsingGeocoder(int inputType, Geocoder geocoder, Intent intent) {
        List<Address> addressResult = null;
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
        return addressResult;
    }

    private List<Result> callAsyncTask(Address address)
            throws ExecutionException, InterruptedException {

        Calendar cal = Calendar.getInstance();
        SharedPreferences prefs = getSharedPreferences(RentalListActivity.GLOBAL_PREFS, MODE_PRIVATE);
        long pickUpLong = prefs.getLong(PICK_UP_DATE_PREF, cal.getTimeInMillis() + RentalListActivity.TWO_WEEK_PICK_UP);
        long dropOffLong = prefs.getLong(DROP_OFF_DATE_PREF, cal.getTimeInMillis() + RentalListActivity.ONE_MONTH_DROP_OFF);

        Date date = new Date(pickUpLong);
        String pickUpDate = Utility.getCurrentDateFormat().format(date);
        date = new Date(dropOffLong);
        String dropOffDate = Utility.getCurrentDateFormat().format(date);

        if(address.hasLatitude() && address.hasLongitude()) {

            RentalFetcher fetchData = new RentalFetcher(pickUpDate, dropOffDate);
            AsyncTask<Address, Integer, JSONHelper.Results> results = fetchData.execute(address);

            if(results != null && results.get() != null){
                return results.get().getResults();
            }
        }

        return null;
    }

    private void deliverSuccessResultToReceiver(int resultCode, String message, Address address,
                                                List<Result> results) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RESULT_ADDRESS_KEY, address);
        bundle.putParcelableArrayList(RESULT_DATA_KEY, (ArrayList<? extends Parcelable>) results);
        bundle.putString(RESULT_MESSAGE_KEY, message);

        mResultReceiver.send(resultCode, bundle);
    }

    private void deliverFailedResultToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();
        bundle.putString(RESULT_MESSAGE_KEY, message);
        mResultReceiver.send(resultCode, bundle);
    }
}
