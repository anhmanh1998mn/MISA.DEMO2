package vn.com.misa.demo2.Adapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.com.misa.demo2.Model.Service;
import vn.com.misa.demo2.R;

/**
 * ‐ Mục đích Class quy định dữ liệu và cách hiển thị lên view
 * <p>
 * ‐ {@link vn.com.misa.demo2.Model.Service}
 * - {@link vn.com.misa.demo2.MainActivity}
 * <p>
 * ‐ @created_by cvmanh on 01/04/2021
 */

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private Activity mContext;

    private int mLayout;

    private List<Service> mListService;

    private IOnClickItem iOnClickItem;

    public IOnClickItem getiOnClickItem() {
        return iOnClickItem;
    }

    private int mCountService = 0;

    public void setiOnClickItem(IOnClickItem iOnClickItem) {
        this.iOnClickItem = iOnClickItem;
    }

    public ServiceAdapter(Activity mContext, int mLayout, List<Service> mListService) {
        this.mContext = mContext;
        this.mLayout = mLayout;
        this.mListService = mListService;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater liInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = liInflater.inflate(mLayout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (!mListService.get(position).ismState()) {
                mListService.get(position).setmState(false);
                holder.tvNameService.setBackgroundResource(R.drawable.view_service_name);
                holder.tvNameService.setTextColor(Color.GRAY);
            } else {
                holder.tvNameService.setBackgroundResource(R.drawable.view_service_selection);
                holder.tvNameService.setTextColor(Color.WHITE);
                mListService.get(position).setmState(true);
            }

            holder.tvNameService.setText(mListService.get(position).getmServiceName());
            holder.tvNameService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListService.get(position).ismState()) {
                        mCountService--;
                        mListService.get(position).setmState(false);
                        holder.tvNameService.setBackgroundResource(R.drawable.view_service_name);
                        holder.tvNameService.setTextColor(Color.GRAY);
                        iOnClickItem.onClickItemAgain(mCountService);
                        return;
                    }
                    if (iOnClickItem != null) {
                        mCountService++;
                        holder.tvNameService.setBackgroundResource(R.drawable.view_service_selection);
                        holder.tvNameService.setTextColor(Color.WHITE);
                        mListService.get(position).setmState(true);
                        iOnClickItem.onClickItem(mCountService);
                        return;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mListService.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameService;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                tvNameService = itemView.findViewById(R.id.tvServiceName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Mục đích: Nhận sự kiện khi người dùng click item service
     *
     * Created by CVManh on 01/04/2021
     */
    public interface IOnClickItem {
        public void onClickItem(int countService);

        public void onClickItemAgain(int countService);
    }
}
