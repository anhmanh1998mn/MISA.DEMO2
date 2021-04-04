package vn.com.misa.demo2.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.com.misa.demo2.Model.Food;
import vn.com.misa.demo2.Model.Service;
import vn.com.misa.demo2.R;

/**
 * ‐ Mục đích Class quy định dữ liệu và cách hiển thị lên view
 *
 * ‐ {@link vn.com.misa.demo2.Model.Food}
 * ‐ {@link vn.com.misa.demo2.View.FragmentListRestaurant}
 *
 * ‐ @created_by cvmanh on 01/04/2021
 */

public class FoodMenuAdapter extends RecyclerView.Adapter<FoodMenuAdapter.ViewHolder> {

    private Activity mContext;

    private int mLayout;

    private List<Food> mListFood;

    private TextView tvItemClick = null;

    public FoodMenuAdapter(Activity mContext, int mLayout, List<Food> mListFood) {
        this.mContext = mContext;
        this.mLayout = mLayout;
        this.mListFood = mListFood;
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
            holder.tvFood.setText(mListFood.get(position).getmFoodName());
            Drawable drawableTop = mContext.getResources().getDrawable(mListFood.get(position).getmFoodImage());
            holder.tvFood.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mListFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                tvFood = itemView.findViewById(R.id.tvFood);
                tvFood.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View v) {
                        if (tvItemClick != null) {
                            tvItemClick.setTextColor(mContext.getResources().getColor(R.color.foot_gray));
                            tvItemClick.setCompoundDrawableTintList(mContext.getResources().getColorStateList(R.color.foot_gray));
                        }
                        tvFood.setTextColor(mContext.getResources().getColor(R.color.purple_500));
                        tvFood.setCompoundDrawableTintList(mContext.getResources().getColorStateList(R.color.purple_500));
                        tvItemClick = tvFood;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
