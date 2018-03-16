package app.rent_likeme.com.rent_likeme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.rent_likeme.com.rent_likeme.model.Result;
import app.rent_likeme.com.rent_likeme.util.Utility;

/**
 * Created by anto004 on 3/3/18.
 */

public class RentalAdapter extends
        RecyclerView.Adapter<RentalAdapter.ViewHolder> {
    private List<Result> mResultList;
    private Context mContext;
    private boolean mTwoPane;

    public RentalAdapter(Context context, Boolean twoPane, List<Result> items) {
        this.mContext = context;
        this.mResultList = items;
        this.mTwoPane = twoPane;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rental_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mResult = mResultList.get(position);
        holder.mCompanyNameTextView.setText(holder.mResult.provider.companyName);
        holder.mDistanceTextView.setText(Utility.getFriendlyDistFormat(mContext, holder.mResult.distance()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
//                    arguments.putString(RentalDetailFragment.ARG_ITEM_ID, holder.mResult.id);
                    RentalDetailFragment fragment = new RentalDetailFragment();
                    fragment.setArguments(arguments);

                    FragmentActivity activity = (FragmentActivity) mContext;
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.rental_detail_container, fragment)
                            .commit();
                } else {
                    Intent intent = new Intent(mContext, RentalDetailActivity.class);
                    intent.putExtra(RentalDetailActivity.PROVIDER_KEY, holder.mResult.provider);
                    intent.putExtra(RentalDetailActivity.ADDRESS_KEY, holder.mResult.address);
                    intent.putParcelableArrayListExtra(RentalDetailActivity.CAR_KEY, (ArrayList<? extends Parcelable>) holder.mResult.getCars());
                    mContext.startActivity(intent);

                    Activity activity = (Activity) mContext;
                    activity.overridePendingTransition(R.anim.come_in_from_right, R.anim.go_out_left);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

    public void swapRentals(List<Result> results){
        this.mResultList.clear();
        this.mResultList.addAll(results); // swap
        notifyDataSetChanged(); // notify
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCompanyNameTextView;
        public final TextView mDistanceTextView;
        public final ImageView mImageView;
        public Result mResult;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCompanyNameTextView = (TextView) view.findViewById(R.id.company_name_textView);
            mDistanceTextView = (TextView) view.findViewById(R.id.distance_textView);
            mImageView = (ImageView) view.findViewById(R.id.detail_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDistanceTextView.getText() + "'";
        }
    }
}
