package vn.com.misa.demo2.Model;

import android.graphics.drawable.Drawable;

/**
 * ‐ Mục đích Class thực hiện việc định nghĩa đối tượng food
 *
 * ‐ {@link vn.com.misa.demo2.Adapter.FoodMenuAdapter}
 * ‐ {@link vn.com.misa.demo2.View.FragmentListRestaurant}
 *
 * ‐ @created_by cvmanh on 01/04/2021
 */

public class Food {

    private String mFoodName;

    private Integer mFoodImage;

    private boolean mState;

    public String getmFoodName() {
        return mFoodName;
    }

    public void setmFoodName(String mFoodName) {
        this.mFoodName = mFoodName;
    }

    public Integer getmFoodImage() {
        return mFoodImage;
    }

    public void setmFoodImage(Integer mFoodImage) {
        this.mFoodImage = mFoodImage;
    }

    public boolean ismState() {
        return mState;
    }

    public void setmState(boolean mState) {
        this.mState = mState;
    }

    public Food(String mFoodName, Integer mFoodImage, boolean mState) {
        this.mFoodName = mFoodName;
        this.mFoodImage = mFoodImage;
        this.mState = mState;
    }
}
