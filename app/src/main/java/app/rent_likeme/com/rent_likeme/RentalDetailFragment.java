package app.rent_likeme.com.rent_likeme;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.rent_likeme.com.rent_likeme.model.Address;
import app.rent_likeme.com.rent_likeme.model.Car;
import app.rent_likeme.com.rent_likeme.model.Provider;
import app.rent_likeme.com.rent_likeme.model.Result;

/**
 * A fragment representing a single Rental detail screen.
 * This fragment is either contained in a {@link RentalListActivity}
 * in two-pane mode (on tablets) or a {@link RentalDetailActivity}
 * on handsets.
 */
public class RentalDetailFragment extends Fragment {
    public static final String LOG_TAG = RentalDetailFragment.class.getSimpleName();
    public static final String ARG_ITEM= "item";
    public static final String ARG_ITEM_PROVIDER = "arg_item_provider";
    public static final String ARG_ITEM_ADDRESS = "arg_item_address";
    public static final String ARG_ITEM_CAR = "arg_item_car";
    private Result mResult;
    private Provider mProvider;
    private Address mAddress;
    private List<Car> mCars;
    private RecyclerView mRecyclerView;

    public RentalDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_PROVIDER) &&
                getArguments().containsKey(ARG_ITEM_ADDRESS)) {
            mProvider = getArguments().getParcelable(ARG_ITEM_PROVIDER);
            mAddress = getArguments().getParcelable(ARG_ITEM_ADDRESS);
            mCars = getArguments().getParcelableArrayList(ARG_ITEM_CAR);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mProvider.companyName);
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.car_list, container, false);

        mRecyclerView = rootView.findViewById(R.id.car_recyclerView);
        mRecyclerView.setAdapter(new CarAdapter(getActivity(), mCars != null ? mCars : new ArrayList<Car>()));

        return rootView;
    }
}
