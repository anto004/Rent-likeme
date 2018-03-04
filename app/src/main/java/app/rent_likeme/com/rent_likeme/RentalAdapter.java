package app.rent_likeme.com.rent_likeme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.rent_likeme.com.rent_likeme.dummy.DummyContent;

/**
 * Created by anto004 on 3/3/18.
 */

public class RentalAdapter
        extends RecyclerView.Adapter<RentalAdapter.ViewHolder> {

    private final List<DummyContent.DummyItem> mValues;
    private Context mContext;
    private boolean mTwoPane;

    public RentalAdapter(Context context, Boolean twoPane, List<DummyContent.DummyItem> items) {
        this.mContext = context;
        this.mValues = items;
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
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(RentalDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                    RentalDetailFragment fragment = new RentalDetailFragment();
                    fragment.setArguments(arguments);

                    FragmentActivity activity = (FragmentActivity) mContext;
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.rental_detail_container, fragment)
                            .commit();
                } else {
                    Intent intent = new Intent(mContext, RentalDetailActivity.class);
                    intent.putExtra(RentalDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                    mContext.startActivity(intent);
                    Activity activity = (Activity) mContext;
                    activity.overridePendingTransition(R.anim.come_in_from_right, R.anim.go_out_left);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView mImageView;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mImageView = (ImageView) view.findViewById(R.id.detail_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
