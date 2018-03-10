package app.rent_likeme.com.rent_likeme;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
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
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Calendar;
import java.util.GregorianCalendar;

import app.rent_likeme.com.rent_likeme.Utility.Utility;
import app.rent_likeme.com.rent_likeme.dummy.DummyContent;

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
    private boolean mTwoPane;
    private GoogleApiClient mGoogleApiClient;
    private PopupWindow mPopUpWindow;
    public static final String GLOBAL_PREFS = "rental_prefs";
    public static final String ADDRESS_PREF = "address";
    public static final String PICK_UP_DATE_PREF = "pick_up_date";
    public static final String DROP_OFF_DATE_PREF = "drop_off_date";
    public static final String DIALOG_TAG = "date_picker";
    public static final String FRAG_VIEW_INFO = "frag_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        final FrameLayout rentalLayout = (FrameLayout) findViewById(R.id.rental_frame_layout);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popUpView = inflater.inflate(R.layout.pop_up_window, null);
                mPopUpWindow = new PopupWindow(popUpView, RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT);

                TextView distanceTv = popUpView.findViewById(R.id.distance_textview);
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
                mPopUpWindow.showAtLocation(rentalLayout, Gravity.BOTTOM, 0, 0);

            }
        });

        checkPermissions();
        buildGoogleApiClient();

        TextView pickUpTv = findViewById(R.id.pick_up_textview);
        pickUpTv.setOnClickListener(this);
        SharedPreferences sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        String pickUpDate = sharedPreferences.getString(PICK_UP_DATE_PREF, null);
        if(pickUpDate != null){
            pickUpTv.setText(pickUpDate);
        }
        else{
            setCurrentDateOnView(pickUpTv.getId());
        }

        TextView dropOffTv = findViewById(R.id.drop_off_textview);
        dropOffTv.setOnClickListener(this);
        String dropOffDate = sharedPreferences.getString(DROP_OFF_DATE_PREF, null);
        if(dropOffDate!= null){
            dropOffTv.setText(dropOffDate);
        }
        else{
            setCurrentDateOnView(dropOffTv.getId());
        }

        View recyclerView = findViewById(R.id.rental_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.rental_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        RentalFetcher.FetchRentalsTask fetchData = new RentalFetcher.FetchRentalsTask();
        fetchData.execute();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new RentalAdapter(this, mTwoPane, DummyContent.ITEMS));
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

    private void closePopUpWindow(){
        mPopUpWindow.dismiss();
    }

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
            case R.id.distance_textview:
                closePopUpWindow();
                break;
            case R.id.close_pop_up_button:
                closePopUpWindow();
                break;
            case R.id.pick_up_textview:
                callDatePickerFragment(R.id.pick_up_textview);
                break;
            case R.id.drop_off_textview:
                callDatePickerFragment(R.id.drop_off_textview);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    public void setCurrentDateOnView(int viewId) {
        Calendar c = Calendar.getInstance();
        TextView textView = (TextView) findViewById(viewId);
        textView.setText(Utility.getDateFormat().format(c.getTime()));
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
                    Log.v(LOG_TAG, "Got location: " + location.toString());
                }
            });
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
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Your location is required", Toast.LENGTH_SHORT).show();
                }
            }
        }
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
                    .getSharedPreferences(RentalListActivity.GLOBAL_PREFS, MODE_PRIVATE).edit();
            editor.putString(viewId == R.id.pick_up_textview ? RentalListActivity.PICK_UP_DATE_PREF
                            : RentalListActivity.DROP_OFF_DATE_PREF, formattedDate);
            editor.apply();
        }
    }
}

