package vn.com.misa.demo2.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.com.misa.demo2.Model.Restaurant;
import vn.com.misa.demo2.R;

/**
 * ‐ Mục đích Class quy định dữ liệu và cách hiển thị lên view
 *
 * ‐ {@link vn.com.misa.demo2.Model.Restaurant}
 * - {@link vn.com.misa.demo2.View.FragmentLocation}
 *
 * ‐ @created_by cvmanh on 01/04/2021
 */

public class LocationRestaurantAdapter extends RecyclerView.Adapter<LocationRestaurantAdapter.ViewHolder> {

    private Activity mContext;

    private int mLayout;

    private List<Restaurant> mListRestaurant;

    public LocationRestaurantAdapter(Activity mContext, int mLayout, List<Restaurant> mListRestaurant) {
        this.mContext = mContext;
        this.mLayout = mLayout;
        this.mListRestaurant = mListRestaurant;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mLayout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            Glide.with(mContext).load(mListRestaurant.get(position).getmRestaurantImage()).centerCrop().into(holder.ivImageRestaurant);
            holder.tvRestaurantName.setText(mListRestaurant.get(position).getmRestaurantName());
            holder.tvCountRate.setText(mListRestaurant.get(position).getmRate());
            holder.tvRestaurantLocation.setText(mListRestaurant.get(position).getmLocation());
            holder.tvSpace.setText(mListRestaurant.get(position).getmSpaceRestaurant());
            switch (mListRestaurant.get(position).getmRestaurantState()) {
                case 1: {
                    holder.ivState.setColorFilter(Color.RED);
                    break;
                }
                case 2: {
                    holder.ivState.setColorFilter(Color.GREEN);
                    break;
                }
                case 3: {
                    holder.ivState.setColorFilter(Color.GRAY);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return mListRestaurant.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImageRestaurant, ivMarker, ivState;

        TextView tvRestaurantName, tvCountRate, tvRestaurantLocation, tvSpace;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                ivImageRestaurant = itemView.findViewById(R.id.ivImageRestaurant);
                ivMarker = itemView.findViewById(R.id.ivMarker);
                tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
                tvCountRate = itemView.findViewById(R.id.tvCountRate);
                tvRestaurantLocation = itemView.findViewById(R.id.tvRestaurantLocation);
                tvSpace = itemView.findViewById(R.id.tvSpace);
                ivState = itemView.findViewById(R.id.ivState);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
