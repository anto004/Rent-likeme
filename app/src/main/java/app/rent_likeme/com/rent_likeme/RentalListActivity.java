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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import android.widget.RadioGroup;
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

import app.rent_likeme.com.rent_likeme.map.RentalIntentService;
import app.rent_likeme.com.rent_likeme.model.Result;
import app.rent_likeme.com.rent_likeme.util.Utility;


/**
 * created by anto004
 */
public class RentalListActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int FREQ_INTERVAL = 5000; //5 secs
    private static final int FAST_INTERVAL = 1000; //1 sec
    public static final int LOC_REQ_CODE = 200;
    public static final long TWO_WEEK_PICK_UP = 3600 * 24 * 7 * 1000 * 2; //2 week ahead
    public static final long ONE_MONTH_DROP_OFF = TWO_WEEK_PICK_UP * 4; //4 week ahead
    public static final String GLOBAL_PREFS = "rental_prefs";
    public static final String PICK_UP_DATE_PREF = "pick_up_date";
    public static final String DROP_OFF_DATE_PREF = "drop_off_date";
    public static final String DIALOG_TAG = "date_picker";
    public static final String FRAG_VIEW_INFO = "frag_info";
    private RecyclerView mRecyclerView;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private Double mLatitude;
    private Double mLongitude;
    private PopupWindow mPopUpWindow;
    private EditText mAddressTv;
    private RadioButton mCurrentLocButton;
    private RadioGroup mPopUpGroup;
    private Calendar mCalendar;
    private List<Result> mResults;
    private View mPopUpView;
    private RentalAdapter mRentalAdapter;
    public ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        mPopUpView = inflater.inflate(R.layout.pop_up_window, null);

        mPopUpWindow = new PopupWindow(mPopUpView, RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);

        mPopUpGroup = (RadioGroup)mPopUpView.findViewById(R.id.pop_up_sort_group);
        ImageButton closeButton = mPopUpView.findViewById(R.id.close_pop_up_button);
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

        final FrameLayout rentalFrameLayout = (FrameLayout) findViewById(R.id.rental_frame_layout);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        //editText click listener won't respond on single click with OnClickListener
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

        //setting current date by default to pickUp and dropOff
        mCalendar = Calendar.getInstance();
        SharedPreferences sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        Long pickUpDateLong = sharedPreferences.getLong(PICK_UP_DATE_PREF, 0L);

        if(pickUpDateLong > 0){
            String pickUpDate = Utility.convertLongToDate(pickUpDateLong);
            pickUpTv.setText(pickUpDate);
        }
        else{
            setCurrentDateOnView(pickUpTv.getId());
        }

        TextView dropOffTv = findViewById(R.id.drop_off_textview);
        dropOffTv.setOnClickListener(this);

        Long dropOffDateLong= sharedPreferences.getLong(DROP_OFF_DATE_PREF, 0L);
        if(dropOffDateLong > 0){
            String dropOffDate = Utility.convertLongToDate(dropOffDateLong);
            dropOffTv.setText(dropOffDate);
        }
        else{
            setCurrentDateOnView(dropOffTv.getId());
        }

        mRecyclerView = findViewById(R.id.rental_list);
        assert mRecyclerView != null;
        setupRecyclerView((RecyclerView) mRecyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        mResults = new ArrayList<Result>();
        mRentalAdapter = new RentalAdapter(this, mResults);
        recyclerView.setAdapter(mRentalAdapter);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rental_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh_item:
                mProgressBar.setVisibility(View.VISIBLE);
                startIntentToFetchLatLong();
                break;
        }
        return super.onOptionsItemSelected(item);
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
            case R.id.close_pop_up_button:
                sortRentalsOnRadioButtonChecked();
                closePopUpWindow();
                break;
            case R.id.pick_up_textview:
                callDatePickerFragment(R.id.pick_up_textview);
                break;
            case R.id.drop_off_textview:
                callDatePickerFragment(R.id.drop_off_textview);
                break;
            case R.id.rental_search_button:
                mProgressBar.setVisibility(View.VISIBLE);
                mAddressTv.clearFocus();
                hideKeyboard();
                startIntentToFetchLatLong();
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
            return RentalIntentService.ADDRESS_INPUT_LOCATION_KEY;
        }
        String address = mAddressTv.getText().toString();
        if(address.trim().length() == 0){
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Address?", Toast.LENGTH_SHORT).show();
            return null;
        }
        return RentalIntentService.ADDRESS_INPUT_NAME_KEY;
    }
    public void startIntentToFetchLatLong(){
        Integer inputType = getInputTypeToFetchLatLong();
        if(inputType == null){
            return;
        }
        Intent intent = new Intent(this, RentalIntentService.class);
        RentalResultReceiver resultReceiver = new RentalResultReceiver(new Handler());
        intent.putExtra(RentalIntentService.RETURN_RECEIVER_KEY, resultReceiver);
        //using Radio button current location
        if(inputType == RentalIntentService.ADDRESS_INPUT_LOCATION_KEY){
            intent.putExtra(RentalIntentService.INPUT_TYPE_KEY, RentalIntentService.ADDRESS_INPUT_LOCATION_KEY);
            intent.putExtra(RentalIntentService.LOCATION_LATITUDE_KEY, mLocation.getLatitude());
            intent.putExtra(RentalIntentService.LOCATION_LONGITUDE_KEY, mLocation.getLongitude());
        }
        //using typed address
        if(inputType == RentalIntentService.ADDRESS_INPUT_NAME_KEY) {
            intent.putExtra(RentalIntentService.INPUT_TYPE_KEY, RentalIntentService.ADDRESS_INPUT_NAME_KEY);
            String address = mAddressTv.getText().toString();
            intent.putExtra(RentalIntentService.ADDRESS_NAME_KEY, address);
        }
        startService(intent);
    }

    public void setCurrentDateOnView(int viewId) {
        TextView textView = (TextView) findViewById(viewId);
        long pickUpCurrentTimeInMillis = mCalendar.getTimeInMillis() + TWO_WEEK_PICK_UP;
        long dropOffCurrentTimeInMillis = mCalendar.getTimeInMillis() + ONE_MONTH_DROP_OFF;
        //Set current date for pickUp one week ahead and two weeks later for dropOff
        textView.setText(viewId == R.id.pick_up_textview ? Utility.getDateFormat().format(new Date(pickUpCurrentTimeInMillis))
                                    : Utility.getDateFormat().format(new Date(dropOffCurrentTimeInMillis)));
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

    class RentalResultReceiver extends ResultReceiver {
        public RentalResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(final int resultCode, final Bundle resultData) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    switch (resultCode) {
                        case RentalIntentService.RUNNING_RESULT:
                            mProgressBar.setVisibility(View.VISIBLE);
                            break;

                        case RentalIntentService.SUCCESS_RESULT:
                            mProgressBar.setVisibility(View.GONE);

                            Address address = resultData.getParcelable(RentalIntentService.RESULT_ADDRESS_KEY);
                            mLatitude = address.getLatitude();
                            mLongitude = address.getLongitude();

                            mResults = resultData.getParcelableArrayList(RentalIntentService.RESULT_DATA_KEY);
                            displayRentalsByDistance(mResults);
                            break;

                        case RentalIntentService.FAILURE_RESULT:
                            mProgressBar.setVisibility(View.GONE);
                            String errorMsg = resultData.getString(RentalIntentService.RESULT_MESSAGE_KEY);
                            assert errorMsg != null;
                            if(errorMsg.equals(RentalIntentService.INVALID_ADDRESS_MSG)) {
                                Toast.makeText(RentalListActivity.this, "Invalid Address!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(RentalListActivity.this, "Choose a much later pick up, drop off date!", Toast.LENGTH_LONG).show();
                            }
                            break;
                    }

                }
            });
        }
    }

    private void displayRentalsByDistance(List<Result> results){
        if(mLatitude == null && mLongitude == null) {
            return;
        }

        for(Result result: results){
            result.latitude = mLatitude;
            result.longitude = mLongitude;
        }
        //Sort by distance using provided location as reference
        Collections.sort(results);
        mRentalAdapter.swapRentals(results);
    }

    private void displayRentalsByCompany(List<Result> results){
        Result.sortByCompanyName(results);
        mRentalAdapter.swapRentals(results);
    }

    private void displayRentalsByPriceLowToHigh(List<Result> results){
        Result.sortByPriceLowToHigh(results);
        mRentalAdapter.swapRentals(mResults);
    }

    private void displayRentalsByPriceHighToLow(List<Result> results){
        Result.sortByPriceHighToLow(results);
        mRentalAdapter.swapRentals(mResults);
    }

    private void sortRentalsOnRadioButtonChecked(){
        if(mResults == null){
            return;
        }
        int popUpSelected = mPopUpGroup.getCheckedRadioButtonId();
        RadioButton popUpButton = (RadioButton)mPopUpView.findViewById(popUpSelected);
        switch(popUpButton.getId()){
            case R.id.distance_checkBox:
                displayRentalsByDistance(mResults);
                break;
            case R.id.company_checkBox:
                displayRentalsByCompany(mResults);
                break;
            case R.id.price_lowToHigh_checkBox:
                displayRentalsByPriceLowToHigh(mResults);
                break;
            case R.id.price_highToLow_checkBox:
                displayRentalsByPriceHighToLow(mResults);
                break;
        }
    }
}

