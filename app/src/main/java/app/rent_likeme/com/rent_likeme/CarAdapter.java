package app.rent_likeme.com.rent_likeme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.rent_likeme.com.rent_likeme.model.Car;

/**
 * Created by anto004 on 3/13/18.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarHolder> {
    private Context mContext;
    private List<Car> mCars;
    public CarAdapter(Context context, List<Car> cars) {
        this.mContext = context;
        this.mCars = cars;
    }

    @NonNull
    @Override
    public CarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.car_list_item, parent, false);
        return new CarHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarHolder holder, int position) {
        holder.mCar = mCars.get(position);
        holder.mCategory.setText(holder.mCar.vehicleInfo.category);
        holder.mType.setText(holder.mCar.vehicleInfo.type);
        holder.mPrice.setText(holder.mCar.rates.get(0).price.amount);

    }

    @Override
    public int getItemCount() {
        return mCars.size();
    }

    public class CarHolder extends RecyclerView.ViewHolder{
        private Car mCar;
        private TextView mCategory;
        private TextView mType;
        private TextView mPrice;
        private View mView;

        public CarHolder(View view) {
            super(view);
            mView = view;
            mCategory = (TextView) view.findViewById(R.id.category_textView);
            mType = (TextView) view.findViewById(R.id.type_textView);
            mPrice = (TextView) view.findViewById(R.id.price_textView);
        }
    }
}
