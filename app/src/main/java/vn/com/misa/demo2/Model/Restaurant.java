package vn.com.misa.demo2.Model;

/**
 * ‐ Mục đích Class thực hiện những việc định nghĩa đối tượng restaurant
 *
 * ‐ {@link vn.com.misa.demo2.Adapter.RestaurantAdapter}
 * ‐ {@link vn.com.misa.demo2.Adapter.LocationRestaurantAdapter}
 * ‐ {@link vn.com.misa.demo2.View.FragmentListRestaurant}
 * - {@link vn.com.misa.demo2.View.FragmentLocation}
 *
 * ‐ @created_by cvmanh on 01/04/2021
 */

public class Restaurant {

    private String mRestaurantImage,mRestaurantName,mRate,mLocation,mSpaceRestaurant;

    private int mRestaurantState;

    public String getmRestaurantImage() {
        return mRestaurantImage;
    }

    public void setmRestaurantImage(String mRestaurantImage) {
        this.mRestaurantImage = mRestaurantImage;
    }

    public String getmRestaurantName() {
        return mRestaurantName;
    }

    public void setmRestaurantName(String mRestaurantName) {
        this.mRestaurantName = mRestaurantName;
    }

    public String getmRate() {
        return mRate;
    }

    public void setmRate(String mRate) {
        this.mRate = mRate;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getmSpaceRestaurant() {
        return mSpaceRestaurant;
    }

    public void setmSpaceRestaurant(String mSpaceRestaurant) {
        this.mSpaceRestaurant = mSpaceRestaurant;
    }

    public int getmRestaurantState() {
        return mRestaurantState;
    }

    public void setmRestaurantState(int mRestaurantState) {
        this.mRestaurantState = mRestaurantState;
    }

    public Restaurant(String mRestaurantImage, String mRestaurantName, String mRate, String mLocation, String mSpaceRestaurant, int mRestaurantState) {
        this.mRestaurantImage = mRestaurantImage;
        this.mRestaurantName = mRestaurantName;
        this.mRate = mRate;
        this.mLocation = mLocation;
        this.mSpaceRestaurant = mSpaceRestaurant;
        this.mRestaurantState = mRestaurantState;
    }
}
