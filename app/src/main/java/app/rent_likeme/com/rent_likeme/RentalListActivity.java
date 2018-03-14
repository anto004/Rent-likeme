package app.rent_likeme.com.rent_likeme;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import app.rent_likeme.com.rent_likeme.map.GeocoderAddressService;
import app.rent_likeme.com.rent_likeme.model.Result;
import app.rent_likeme.com.rent_likeme.util.JSONHelper.Results;
import app.rent_likeme.com.rent_likeme.util.Utility;


/**
 * An activity representing a list of Rentals. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RentalDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RentalListActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String LOG_TAG = RentalListActivity.class.getSimpleName();
    private static final int FREQ_INTERVAL = 5000; //5 secs
    private static final int FAST_INTERVAL = 1000; //1 sec
    public static final int LOC_REQ_CODE = 200;
    public static final String GLOBAL_PREFS = "rental_prefs";
    public static final String ADDRESS_PREF = "address_prefs";
    public static final String PICK_UP_DATE_PREF = "pick_up_date";
    public static final String DROP_OFF_DATE_PREF = "drop_off_date";
    public static final String DIALOG_TAG = "date_picker";
    public static final String FRAG_VIEW_INFO = "frag_info";
    private boolean mTwoPane;
    private RecyclerView mRecyclerView;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private Double mLatitude;
    private Double mLongitude;
    private PopupWindow mPopUpWindow;
    private ProgressBar mProgressBar;
    private EditText mAddressTv;
    private RadioButton mCurrentLocButton;
    private Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        final FrameLayout rentalFrameLayout = (FrameLayout) findViewById(R.id.rental_frame_layout);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popUpView = inflater.inflate(R.layout.pop_up_window, null);
                mPopUpWindow = new PopupWindow(popUpView, RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT);

                TextView distanceTv = popUpView.findViewById(R.id.pop_up_distance_textview);
                distanceTv.setOnClickListener(RentalListActivity.this);
                ImageButton closeButton = popUpView.findViewById(R.id.close_pop_up_button);
                closeButton.setOnClickListener(RentalListActivity.this);
                mPopUpWindow.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if(motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE){
                            closePopUpWindow();
                            return true;
                        }
                        return false;
                    }
                });
                mPopUpWindow.setOutsideTouchable(true);
                mPopUpWindow.setAnimationStyle(R.style.pop_up_window_animation);
                mPopUpWindow.showAtLocation(rentalFrameLayout, Gravity.BOTTOM, 0, 0);

            }
        });

        checkPermissions();
        buildGoogleApiClient();

        mCurrentLocButton = (RadioButton) findViewById(R.id.current_loc_radio_button);
        mCurrentLocButton.setOnClickListener(this);
        mAddressTv = findViewById(R.id.address_editText);
        //click listener won't respond to single click
        mAddressTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mCurrentLocButton.isChecked()) {
                    mCurrentLocButton.setChecked(false);
                    return true;
                }
                return false;
            }

        });
        //Clear editText focus when click anywhere else on the screen
        RelativeLayout rentalRelLayout = findViewById(R.id.rental_list_relativeLayout);
        rentalRelLayout.setOnClickListener(this);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        Button searchButton = (Button) findViewById(R.id.rental_search_button);
        searchButton.setOnClickListener(this);
        TextView pickUpTv = findViewById(R.id.pick_up_textview);
        pickUpTv.setOnClickListener(this);

        mCalendar = Calendar.getInstance();
        SharedPreferences sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        Long pickUpDateLong = sharedPreferences.getLong(PICK_UP_DATE_PREF, mCalendar.getTimeInMillis());
        String pickUpDate = Utility.convertLongToDate(pickUpDateLong);
        if(pickUpDate != null){
            pickUpTv.setText(pickUpDate);
        }
        else{
            setCurrentDateOnView(pickUpTv.getId());
        }

        TextView dropOffTv = findViewById(R.id.drop_off_textview);
        dropOffTv.setOnClickListener(this);
        Long dropOffDateLong= sharedPreferences.getLong(PICK_UP_DATE_PREF, mCalendar.getTimeInMillis());
        String dropOffDate = Utility.convertLongToDate(dropOffDateLong);
        if(dropOffDate!= null){
            dropOffTv.setText(dropOffDate);
        }
        else{
            setCurrentDateOnView(dropOffTv.getId());
        }

        mRecyclerView = findViewById(R.id.rental_list);
        assert mRecyclerView != null;
        setupRecyclerView((RecyclerView) mRecyclerView);

        if (findViewById(R.id.rental_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new RentalAdapter(this, mTwoPane, new ArrayList<Result>()));
    }

    private void hideKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager)
                this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputMethodManager != null && this.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 2);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(LOG_TAG, "onResume Called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rental_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void closePopUpWindow(){
        mPopUpWindow.dismiss();
    }

    //Either for pickup or dropoff
    private void callDatePickerFragment(int viewId){
        Bundle bundle = new Bundle();
        bundle.putInt(FRAG_VIEW_INFO, viewId);
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(bundle);
        datePickerFragment.show(getSupportFragmentManager(), DIALOG_TAG);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.address_editText:
                break;
            case R.id.current_loc_radio_button:
                if(mCurrentLocButton.isChecked()){
                    if(mLocation == null){
                        getLocation();
                        mCurrentLocButton.setChecked(false);
                        return;
                    }
                    mAddressTv.clearFocus();
                    mAddressTv.getText().clear();
                }
                break;
            case R.id.pop_up_distance_textview:
                closePopUpWindow();
                break;
            case R.id.close_pop_up_button:
                closePopUpWindow();
                onResume();
                break;
            case R.id.pick_up_textview:
                callDatePickerFragment(R.id.pick_up_textview);
                break;
            case R.id.drop_off_textview:
                callDatePickerFragment(R.id.drop_off_textview);
                break;
            case R.id.rental_search_button:
                startIntentToFetchLatLong();
                mProgressBar.setVisibility(View.VISIBLE);
                mAddressTv.clearFocus();
                hideKeyboard();
                break;
            case R.id.rental_list_relativeLayout:
                mAddressTv.clearFocus();
                hideKeyboard();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    public Integer getInputTypeToFetchLatLong(){
        if(mCurrentLocButton.isChecked()){
            return GeocoderAddressService.ADDRESS_INPUT_LOCATION_KEY;
        }
        String address = mAddressTv.getText().toString();
        if(address.trim().length() == 0){
            Toast.makeText(this, "Address?", Toast.LENGTH_SHORT).show();
            return null;
        }
        return GeocoderAddressService.ADDRESS_INPUT_NAME_KEY;
    }
    public void startIntentToFetchLatLong(){
        Integer inputType = getInputTypeToFetchLatLong();
        if(inputType == null){
            return;
        }
        Intent intent = new Intent(this, GeocoderAddressService.class);
        AddressResultReceiver resultReceiver = new AddressResultReceiver(null);
        intent.putExtra(GeocoderAddressService.RETURN_RECEIVER_KEY, resultReceiver);
        //using Radio button current location
        if(inputType == GeocoderAddressService.ADDRESS_INPUT_LOCATION_KEY){
            intent.putExtra(GeocoderAddressService.INPUT_TYPE_KEY, GeocoderAddressService.ADDRESS_INPUT_LOCATION_KEY);
            intent.putExtra(GeocoderAddressService.LOCATION_LATITUDE_KEY, mLocation.getLatitude());
            intent.putExtra(GeocoderAddressService.LOCATION_LONGITUDE_KEY, mLocation.getLongitude());
        }
        //using typed address
        if(inputType == GeocoderAddressService.ADDRESS_INPUT_NAME_KEY) {
            intent.putExtra(GeocoderAddressService.INPUT_TYPE_KEY, GeocoderAddressService.ADDRESS_INPUT_NAME_KEY);
            String address = mAddressTv.getText().toString();
            intent.putExtra(GeocoderAddressService.ADDRESS_NAME_KEY, address);
        }
        mProgressBar.setVisibility(View.VISIBLE);
        startService(intent);
    }

    public void setCurrentDateOnView(int viewId) {
        TextView textView = (TextView) findViewById(viewId);
        textView.setText(Utility.getDateFormat().format(mCalendar.getTime()));
    }

    private void checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, LOC_REQ_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOC_REQ_CODE: {
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Required Access to your Current Location", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void getLocation() {
        LocationRequest request = LocationRequest.create();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setInterval(FREQ_INTERVAL);
        request.setFastestInterval(FAST_INTERVAL);

        LocationSettingsRequest locationSettingRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(request)
                .build();
        //Checking locationRequestSettings
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingRequest);

        FusedLocationProviderClient fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(
                    new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    mLocation = location;
                    Log.v(LOG_TAG, "Got location: " + mLocation.toString());
                }
            });
        }
        else{
            checkPermissions();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        getLocation();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .build();
    }

    private static String mCal;

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            int viewId = getArguments().getInt(RentalListActivity.FRAG_VIEW_INFO);

            GregorianCalendar cal = new GregorianCalendar(year, month, day);
            String formattedDate = Utility.getDateFormat().format(cal.getTime());

            TextView textView = (TextView) getActivity().findViewById(viewId);
            textView.setText(formattedDate);

            SharedPreferences.Editor editor = getActivity()
                    .getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE).edit();
            editor.putLong(viewId == R.id.pick_up_textview ? PICK_UP_DATE_PREF
                            : DROP_OFF_DATE_PREF, cal.getTimeInMillis());
            editor.apply();
        }
    }

    class AddressResultReceiver extends ResultReceiver {

        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if(resultCode == GeocoderAddressService.SUCCESS_RESULT){
                final Address address = resultData.getParcelable(GeocoderAddressService.RESULT_ADDRESS_KEY);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v(LOG_TAG, "Receive latitude and longitude: " + address.getLatitude()
                                    +address.getLongitude() +" "+ address.getCountryName());
                        mLatitude = address.getLatitude();
                        mLongitude = address.getLongitude();

                        SharedPreferences prefs = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
                        long pickUpLong = prefs.getLong(PICK_UP_DATE_PREF, mCalendar.getTimeInMillis());
                        long dropOffLong = prefs.getLong(DROP_OFF_DATE_PREF, mCalendar.getTimeInMillis());

                        Date date = new Date(pickUpLong);
                        String pickUpDate = Utility.getCurrentDateFormat().format(date);
                        date = new Date(dropOffLong);
                        String dropOffDate = Utility.getCurrentDateFormat().format(date);

                        if(address.hasLatitude() && address.hasLongitude()) {
                            RentalFetcher.FetchRentalsTask fetchData = new RentalFetcher.FetchRentalsTask(
                                                                            pickUpDate, dropOffDate);
                            AsyncTask<Address, Void, Results> results = fetchData.execute(address);
                            try {
                                mProgressBar.setVisibility(View.GONE);
                                if(results.get() != null) {
                                    displayValuesByDistance(results.get());
                                }
                                else{
                                    Toast.makeText(RentalListActivity.this, "Try a different date!",
                                                        Toast.LENGTH_SHORT).show();
                                }

                            } catch (InterruptedException | ExecutionException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
            if(resultCode == GeocoderAddressService.FAILURE_RESULT){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(RentalListActivity.this, "Invalid Address!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
    }

    private void displayValuesByDistance(Results results){
        List<Result> resultList = results.getResults();
        for(Result result: resultList){
            if(mLatitude != null && mLongitude != null) {
                result.latitude = mLatitude;
                result.longitude = mLongitude;
            }
            String d = Utility.getDistanceFormat().format(result.distance());
            Log.v(LOG_TAG, "Distance: "+Utility.getFriendlyDistFormat(this, result.distance()));
        }

        //Sort by distance using provided location as reference
        Collections.sort(resultList);
        mRecyclerView.swapAdapter(new RentalAdapter(this, mTwoPane, resultList), false);
    }
}

