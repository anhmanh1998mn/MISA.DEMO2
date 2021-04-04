package vn.com.misa.demo2.View;

/**
 * ‐ Mục đích Class thực hiện công việc hiển thị vị trí các cửa hàng trên bản đồ
 *
 * ‐ {@link vn.com.misa.demo2.Adapter.RestaurantAdapter}
 * - {@link vn.com.misa.demo2.Model.Restaurant}
 *
 * ‐ @created_by cvmanh on 01/04/2021
 */

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.com.misa.demo2.Adapter.LocationRestaurantAdapter;
import vn.com.misa.demo2.MainActivity;
import vn.com.misa.demo2.Model.Restaurant;
import vn.com.misa.demo2.R;
import vn.com.misa.demo2.until.AndroidDeviceHelper;

import static android.content.Context.LOCATION_SERVICE;

public class FragmentLocation extends Fragment implements OnMapReadyCallback {

    private MapView mvMarkerLocation;

    private GoogleMap gmLocation;

    private RecyclerView rcvRestaurantDetail;

    private LocationRestaurantAdapter mAdapter;

    private List<Restaurant> mListRestaurant;

    private FrameLayout frmLocation;

    private SnapHelper mSnapHelper;

    private LinearLayoutManager llManager;

    private ArrayList<LatLng> mListLatLng;

    private Marker nameMarker;

    private ImageButton ibCurrentLocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        initView(view);

        setHeightScreenMapView();

//        showMap();

        showMapFragment();

        getDataRestaurant();

        getPositionItemCenter();

        onCLickCurrentLocation();

        return view;
    }

    /**
     * Mục đích: Hiện bản đồ
     *
     * Created by cvmanh on 01/02/2021
     */
    private void showMapFragment() {
        try {
            SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                    .findFragmentById(R.id.frmMapLocation);
            mapFragment.getMapAsync(this::onMapReady);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Mục đích: Khởi tạo các view và đặt chiều dài của bản đồ bằng chiều dài kích cỡ màn hình
     *
     * Created by cvmanh on 01/02/2021
     */
    private void initView(View view) {
        try {
            rcvRestaurantDetail = view.findViewById(R.id.rcvRestaurantDetail);
            frmLocation = view.findViewById(R.id.frmLocation);
            frmLocation.getLayoutParams().height = AndroidDeviceHelper.getHeightScreen(getContext()) * 15 / 16;
            frmLocation.requestLayout();
            ibCurrentLocation=view.findViewById(R.id.ibCurrentLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích:
     *
     * Created by cvmanh on 12/30/2020
     */
    private void showMap() {
        try {
            mvMarkerLocation.onCreate(null);
            mvMarkerLocation.getMapAsync(FragmentLocation.this::onMapReady);
            MapsInitializer.initialize(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích: Hiển thị danh sách địa điểm các cửa hàng lên bản đồ
     *
     * @param googleMap
     *
     * @created_by cvmanh on 01/04/2021
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            gmLocation = googleMap;
            if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                gmLocation.setMyLocationEnabled(true);
            }
            else {
            }
            Bitmap bitmap1 = getBitmapFromVectorDrawable(getContext(), R.drawable.ic_marker_blue);
            BitmapDescriptor descriptor1 = BitmapDescriptorFactory.fromBitmap(bitmap1);

//            gmLocation.setMyLocationEnabled(true);
            gmLocation.getUiSettings().setMyLocationButtonEnabled(false);
            mListLatLng = new ArrayList<>();
            mListLatLng.add(new LatLng(21.0303901, 105.8374124));
            mListLatLng.add(new LatLng(21.0339319, 105.832951));
            mListLatLng.add(new LatLng(21.0279167, 105.8256869));
            mListLatLng.add(new LatLng(21.036356, 105.834682));
            mListLatLng.add(new LatLng(21.036116, 105.834463));
            mListLatLng.add(new LatLng(21.035395, 105.834745));
            mListLatLng.add(new LatLng(21.034973, 105.834594));
            mListLatLng.add(new LatLng(21.0335833, 105.8327653));
            mListLatLng.add(new LatLng(21.0386095, 105.8238746));
            mListLatLng.add(new LatLng(21.035235, 105.832418));
            mListLatLng.add(new LatLng(21.0302315, 105.8277867));
            mListLatLng.add(new LatLng(21.0326058, 105.827636));
            mListLatLng.add(new LatLng(21.0386095, 105.8238746));
            mListLatLng.add(new LatLng(21.0279167, 105.8256869));
            mListLatLng.add(new LatLng(21.0225167, 105.8275464));
            mListLatLng.add(new LatLng(21.0231378, 105.8238064));
            Bitmap bitmap = getBitmapFromVectorDrawable(getContext(), R.drawable.ic_marker_red);
            BitmapDescriptor descriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
            for (int i = 0; i < mListLatLng.size(); i++) {
                gmLocation.moveCamera(CameraUpdateFactory.newLatLngZoom(mListLatLng.get(i), 15));
                gmLocation.addMarker(new MarkerOptions().position(mListLatLng.get(i)).icon(descriptor));
            }
            gmLocation.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.0303901, 105.8374124), 15));
            nameMarker = gmLocation.addMarker(new MarkerOptions().position(new LatLng(21.0303901, 105.8374124)).icon(descriptor1).zIndex(1f));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert icon vector to bitmap
     *
     * @param context
     * @param drawableId là một icon vector
     *
     * @return Trả về bitmap
     * Created by cvmanh on 12/30/2020
     */
    public Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Bitmap bitmap = null;
        try {
            Drawable drawable = AppCompatResources.getDrawable(context, drawableId);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                drawable = (DrawableCompat.wrap(drawable)).mutate();
            }

            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * Mục đích: Đặt chiều dài cho mapview bằng chiều dài của màn hình
     *
     * Created by CVManh on 12/30/2020
     */
    private void setHeightScreenMapView() {
        try {
            mvMarkerLocation.getLayoutParams().height = AndroidDeviceHelper.getHeightScreen(getContext()) * 15 / 16;
            mvMarkerLocation.requestLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích: Đổ dữ liệu các cửa hàng lên recycleview
     *
     * Created by CVManh on 12/30/2020
     */
    private void getDataRestaurant() {
        try {
            llManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
            rcvRestaurantDetail.setLayoutManager(llManager);
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
            mListRestaurant.add(new Restaurant("https://oms.hotdeal.vn/images/editors/sources/000350331590/350331-350331-body%20(6).jpg", "Nhà hàng Quẩy quán",
                    "1 đánh giá", "HN", "32,92m", 3));
            mListRestaurant.add(new Restaurant("https://oms.hotdeal.vn/images/editors/sources/000350331590/350331-350331-body%20(6).jpg", "Nhà hàng Quẩy quán",
                    "1 đánh giá", "HN", "32,92m", 3));
            mListRestaurant.add(new Restaurant("https://oms.hotdeal.vn/images/editors/sources/000350331590/350331-350331-body%20(6).jpg", "Nhà hàng Quẩy quán",
                    "1 đánh giá", "HN", "32,92m", 3));

            mAdapter = new LocationRestaurantAdapter(getActivity(), R.layout.item_location_restaurant, mListRestaurant);
            rcvRestaurantDetail.setAdapter(mAdapter);
            scrollItemCenter();
            setDividerDecoration();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích: Đặt khoảng cách giữa các item trong recycleView
     *
     * Created by CVManh on 01/02/2021
     */
    private void setDividerDecoration() {
        try {
            DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL);
            itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.view_recycleview_location));
            rcvRestaurantDetail.addItemDecoration(itemDecorator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích: Cho item trong recycleview luôn hiển thị chính giữa khi scroll
     *
     * Created by CVManh on 01/02/2021
     */
    private void scrollItemCenter() {
        try {
            mSnapHelper = new PagerSnapHelper();
            mSnapHelper.attachToRecyclerView(rcvRestaurantDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích: hiển thị location tại vị trí item chính giữa recycleview khi scroll
     *
     * Created by CVManh on 12/31/2020
     */
    private void getPositionItemCenter() {
        try {
            Bitmap bitmap = getBitmapFromVectorDrawable(getContext(), R.drawable.ic_marker_blue);
            Bitmap bitmap1 = getBitmapFromVectorDrawable(getContext(), R.drawable.ic_marker_red);
            BitmapDescriptor descriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
            BitmapDescriptor descriptor1 = BitmapDescriptorFactory.fromBitmap(bitmap1);
            rcvRestaurantDetail.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    for (int i = 0; i < mListLatLng.size(); i++) {
                        gmLocation.addMarker(new MarkerOptions().position(mListLatLng.get(i)).icon(descriptor1));
                    }
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) { //Nếu scroll kết thúc
                        nameMarker.remove();
                        View centerView = mSnapHelper.findSnapView(llManager);
                        int positionItemCenter = llManager.getPosition(centerView);
//                  Log.d("Snapped:", positionItemCenter + "");
                        gmLocation.animateCamera(CameraUpdateFactory.newLatLngZoom(mListLatLng.get(positionItemCenter), 15));
                        nameMarker = gmLocation.addMarker(new MarkerOptions().position(mListLatLng.get(positionItemCenter)).icon(descriptor).zIndex(1f));
                        return;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Dịch chuyển camera dến vị trí hiện tại
     *
     * @created_by cvmanh on 01/05/2021
     */
    private void onCLickCurrentLocation(){
        ibCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    gmLocation.setMyLocationEnabled(true);
                }
                else {
                }
                LocationManager service = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                String provider = service.getBestProvider(criteria, false);
                Location location = service.getLastKnownLocation(provider);
                gmLocation.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()),15));
            }
        });
    }

}
