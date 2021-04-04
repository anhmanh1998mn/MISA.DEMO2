package vn.com.misa.demo2.View;

/**
 * ‐ Mục đích Class thực hiện công việc: hiển thỉ toàn bộ danh sách cửa hàng và các món ăn
 *
 * ‐ {@link vn.com.misa.demo2.Adapter.RestaurantAdapter}
 * ‐ {@link vn.com.misa.demo2.Adapter.FoodMenuAdapter}
 * ‐ {@link vn.com.misa.demo2.Model.Food}
 * - {@link vn.com.misa.demo2.Model.Restaurant}
 *
 * ‐ @created_by cvmanh on 01/04/2021
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.demo2.Adapter.FoodMenuAdapter;
import vn.com.misa.demo2.Adapter.RestaurantAdapter;
import vn.com.misa.demo2.Model.Food;
import vn.com.misa.demo2.Model.Restaurant;
import vn.com.misa.demo2.R;

public class FragmentListRestaurant extends Fragment {

    private RecyclerView rcvFoodMeu, rcvFoodList;

    private FoodMenuAdapter mAdapter;

    private List<Food> mListFood;

    private RestaurantAdapter mRestaurantAdapter;

    private List<Restaurant> mListRestaurant;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_restaurant, container, false);

        initView(view);

        showDataListFood();

        showListRestaurant();

        return view;
    }

    /**
     * Mục đích: Khởi tạo các view
     *
     * Created by CVManh on 01/02/2021
     */
    private void initView(View view) {
        try {
            rcvFoodMeu = view.findViewById(R.id.rcvFoodMenu);
            rcvFoodList = view.findViewById(R.id.rcvFoodList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích: Hiện dữ liệu danh sách loại món ăn lên recycleview
     *
     * Created by CVManh on 01/02/2021
     */
    private void showDataListFood() {
        try {
            LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
            mListFood = new ArrayList<>();
            mListFood.add(new Food("Cafe giải khát", R.drawable.ic_tea, false));
            mListFood.add(new Food("Sang trọng", R.drawable.ic_bottle, false));
            mListFood.add(new Food("Buffet", R.drawable.ic_tray, false));
            mListFood.add(new Food("Kem, trà sữa", R.drawable.ic_milk_tea, false));
            mListFood.add(new Food("Bít tết", R.drawable.ic_bottle, false));
            mListFood.add(new Food("Nướng, lẩu", R.drawable.ic_tray, false));
            mListFood.add(new Food("Món Nhật", R.drawable.ic_tea, false));
            mListFood.add(new Food("Món Hàn", R.drawable.ic_bottle, false));
            mListFood.add(new Food("Món Âu", R.drawable.ic_tray, false));
            mAdapter = new FoodMenuAdapter(getActivity(), R.layout.item_foot_menu, mListFood);
            rcvFoodMeu.setLayoutManager(manager);
            rcvFoodMeu.setAdapter(mAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hiển thị dữ liệu các nhà hàng lên recycleview
     *
     * Created by CVManh on 12/29/2020
     */
    private void showListRestaurant() {
        try {
            LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            rcvFoodList.setLayoutManager(manager);
            mListRestaurant = new ArrayList<>();
            mListRestaurant.add(new Restaurant("https://bom.to/itJpWXYs", "Nhà hàng Quẩy quán",
                    "1 đánh giá", "HN", "32,92m", 1));
            mListRestaurant.add(new Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/15/c5/84/65/khong-gian-nha-hang-vu.jpg", "Ẩm thực Dương Dê",
                    "2 đánh giá", "Tầng 1, N02T3, Quang Minh Tower,...", "0,13km", 2));
            mListRestaurant.add(new Restaurant("https://nesthome.vn/wp-content/uploads/2020/07/thiet-ke-nha-hang-do-an-nhanh-02.jpg", "T - Space",
                    "1 đánh giá", "Tòa N03 - T5", "0,19km", 3));
            mListRestaurant.add(new Restaurant("https://dichoihanquoc.com/wp-content/uploads/2019/12/southside-parlor-min.jpg", "Nhà hàng Quẩy quán",
                    "1 đánh giá", "HN", "32,92m", 2));
            mListRestaurant.add(new Restaurant("https://channel.mediacdn.vn/thumb_w/640/2019/3/22/photo-1-15532477725961129884695.jpg", "Nhà hàng Quẩy quán",
                    "1 đánh giá", "HN", "32,92m", 2));
            mListRestaurant.add(new Restaurant("https://healthgold.com.vn/wp-content/uploads/2020/09/qilin-quan-bar-hong-kong-cuc-chat-tai-sai-gon-healthgold-5-1024x934.jpg", "Nhà hàng Quẩy quán",
                    "1 đánh giá", "HN", "32,92m", 1));
            mListRestaurant.add(new Restaurant("https://we25.vn/media/images/6-san-quay-goi-moi-hang-dau-doi-voi-gioi-tre-sanh-an-choi-sai-gon-1.jpg", "Nhà hàng Quẩy quán",
                    "1 đánh giá", "HN", "32,92m", 1));
            mListRestaurant.add(new Restaurant("https://oms.hotdeal.vn/images/editors/sources/000350331590/350331-350331-body%20(6).jpg", "Nhà hàng Quẩy quán",
                    "1 đánh giá", "HN", "32,92m", 3));
            mListRestaurant.add(new Restaurant("https://channel.mediacdn.vn/thumb_w/640/2019/3/22/photo-1-15532477725961129884695.jpg", "Nhà hàng Quẩy quán",
                    "1 đánh giá", "HN", "32,92m", 2));
            mListRestaurant.add(new Restaurant("https://healthgold.com.vn/wp-content/uploads/2020/09/qilin-quan-bar-hong-kong-cuc-chat-tai-sai-gon-healthgold-5-1024x934.jpg", "Nhà hàng Quẩy quán",
                    "1 đánh giá", "HN", "32,92m", 1));
            mListRestaurant.add(new Restaurant("https://we25.vn/media/images/6-san-quay-goi-moi-hang-dau-doi-voi-gioi-tre-sanh-an-choi-sai-gon-1.jpg", "Nhà hàng Quẩy quán",
                    "1 đánh giá", "HN", "32,92m", 1));
            mListRestaurant.add(new Restaurant("https://oms.hotdeal.vn/images/editors/sources/000350331590/350331-350331-body%20(6).jpg", "Nhà hàng Quẩy quán",
                    "1 đánh giá", "HN", "32,92m", 3));
            mRestaurantAdapter = new RestaurantAdapter(getActivity(), R.layout.item_food_list, mListRestaurant);
            rcvFoodList.setAdapter(mRestaurantAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
