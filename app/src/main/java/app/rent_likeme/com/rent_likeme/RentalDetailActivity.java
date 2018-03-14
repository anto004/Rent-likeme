package app.rent_likeme.com.rent_likeme;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import app.rent_likeme.com.rent_likeme.dummy.DummyRentalContent;
import app.rent_likeme.com.rent_likeme.model.Address;
import app.rent_likeme.com.rent_likeme.model.Car;
import app.rent_likeme.com.rent_likeme.model.Provider;

/**
 * An activity representing a single Rental detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link RentalListActivity}.
 */
public class RentalDetailActivity extends AppCompatActivity {

    public static final String CAR_DETAILS_KEY = "car_details";
    public static final String PROVIDER_KEY = "provider";
    public static final String ADDRESS_KEY = "address";
    public static final String CAR_KEY = "car";
    private CollapsingToolbarLayout mCollapsingToolbar;
    private LinearLayout mParentLayout;
    private List<Car> mCars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(RentalDetailFragment.ARG_ITEM_PROVIDER,
                    getIntent().getParcelableExtra(PROVIDER_KEY));
            arguments.putParcelable(RentalDetailFragment.ARG_ITEM_ADDRESS,
                    getIntent().getParcelableExtra(ADDRESS_KEY));
            arguments.putParcelableArrayList(RentalDetailFragment.ARG_ITEM_CAR,
                    getIntent().getParcelableArrayListExtra(CAR_KEY));
            RentalDetailFragment fragment = new RentalDetailFragment();
            fragment.setArguments(arguments);

//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.rental_detail_container, fragment)
//                    .commit();
        }

        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        displayImageOnToolbar();

        Provider mProvider = getIntent().getParcelableExtra(PROVIDER_KEY);
        Address mAddress = getIntent().getParcelableExtra(ADDRESS_KEY);
        mCars = getIntent().getParcelableArrayListExtra(CAR_KEY);

        TextView companyNameTv = (TextView) findViewById(R.id.company_name_detail_textView);
        companyNameTv.setText(mProvider.companyName);

        TextView addressTv = (TextView) findViewById(R.id.detail_address_textView);
        addressTv.setText(mAddress.line1 + ", "+ mAddress.city);

        mParentLayout = (LinearLayout) findViewById(R.id.car_list_parent);

        if(mCars != null) {
            createMyCarAdapter();
        }
    }

    //dynamically adding view to scroll view
    public void createMyCarAdapter(){
        for(int i = 0; i < mCars.size(); i++){
            CarHolder carHolder = createCarItemView();
            addCarListItem(carHolder, i);
        }
    }

    public CarHolder createCarItemView(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.car_list_item, null);
        return new CarHolder(view);
    }

    public void addCarListItem(CarHolder holder, int position) {
        holder.mCar = mCars.get(position);
        holder.mCategory.setText(holder.mCar.vehicleInfo.category);
        holder.mType.setText(holder.mCar.vehicleInfo.type);
        holder.mPrice.setText(holder.mCar.rates.get(0).price.amount);

        mParentLayout.addView(holder.mView, mParentLayout.getChildCount() - 1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        slideActivityLeft();
    }

    public void slideActivityLeft(){
        overridePendingTransition(R.anim.come_in_from_left, R.anim.go_out_right);
    }

    public void displayImageOnToolbar(){
        Random r = new Random();
        InputStream inputStream = null;
        List<String> names = DummyRentalContent.getImageNames();

        try {
            String imageName = names.get(r.nextInt(10));
            inputStream = getAssets().open(imageName);
            Bitmap b = BitmapFactory.decodeStream(inputStream);

            ImageView imageView = findViewById(R.id.detail_image);
            imageView.setImageBitmap(b);

            Palette.from(b).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(@NonNull Palette palette) {
                    int mutedColor = palette.getMutedColor(R.attr.colorPrimary);
                    mCollapsingToolbar.setContentScrimColor(mutedColor);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class CarHolder {
        private Car mCar;
        private TextView mCategory;
        private TextView mType;
        private TextView mPrice;
        private View mView;

        public CarHolder(View view) {
            mView = view;
            mCategory = (TextView) view.findViewById(R.id.category_textView);
            mType = (TextView) view.findViewById(R.id.type_textView);
            mPrice = (TextView) view.findViewById(R.id.price_textView);
        }
    }
}
