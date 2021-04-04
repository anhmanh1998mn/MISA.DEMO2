package vn.com.misa.demo2;

/**
 * ‐ Mục đích Class thực hiện những công việc
 *
 * ‐ {@link vn.com.misa.demo2.Adapter.ServiceAdapter}
 * ‐ {@link vn.com.misa.demo2.Model.Service}
 *
 * ‐ @created_by cvmanh on 01/04/2021
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.demo2.Adapter.ServiceAdapter;
import vn.com.misa.demo2.Model.Service;
import vn.com.misa.demo2.View.FragmentListRestaurant;
import vn.com.misa.demo2.View.FragmentLocation;

public class MainActivity extends AppCompatActivity implements ServiceAdapter.IOnClickItem, View.OnClickListener {

    private Toolbar tbActionBar;

    private RecyclerView rcvService;

    private ServiceAdapter mAdapter;

    private List<Service> mListService;

    private TextView tvCountService;

    private ImageButton ibLocationMarker, ibListRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        showDataService();

        showListRetaurant();

        eventClickImageButton();
    }

    /**
     * Mục đích: Khởi tạo các view
     *
     * Created by CVManh on 12/30/2020
     */
    private void initView() {
        try {
            tbActionBar = findViewById(R.id.tbToolbar);
            setSupportActionBar(tbActionBar);
            rcvService = findViewById(R.id.rcvService);
            tvCountService = findViewById(R.id.tvCountService);
            ibLocationMarker = findViewById(R.id.ibLocationMarker);
            ibListRestaurant = findViewById(R.id.ibListRestaurant);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích: Đổ dữ liệu từ mảng danh sách lên recycleView
     *
     * Created by CVManh on 12/30/2020
     */
    private void showDataService() {
        try {
            LinearLayoutManager llmManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
            rcvService.setLayoutManager(llmManager);
            mListService = new ArrayList<>();
            mListService.add(new Service("Đang phục vụ", false));
            mListService.add(new Service("Có ưu đãi", false));
            mListService.add(new Service("Tự gọi món", false));
            mListService.add(new Service("Thẻ thành viên", false));
            mListService.add(new Service("Có điều hòa", false));
            mListService.add(new Service("Có wifi", false));
            mListService.add(new Service("Có chỗ để ô tô", false));
            mListService.add(new Service("Có phòng riêng", false));
            mListService.add(new Service("Thanh toán thẻ", false));
            mAdapter = new ServiceAdapter(this, R.layout.item_service, mListService);
            rcvService.setAdapter(mAdapter);
            mAdapter.setiOnClickItem(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích: Hiển thị fragment danh sách nhà hàng
     *
     * Created by CVManh on 12/30/2020
     */
    private void showListRetaurant() {
        try {
            Fragment fragment = new FragmentListRestaurant();
            setFragment(fragment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích: Hiển thị fragment
     *
     * @param fragment
     *
     * Created by CVManh on 12/30/2020
     */
    private void setFragment(Fragment fragment) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmContent, fragment);
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích: Hiển thị tổng dịch vụ khi người dùng click dịch vụ
     *
     * @param serviceTotal Tổng số lượng dịch vụ được chọn
     *
     * Created by CVManh on 01/02/2021
     */
    @Override
    public void onClickItem(int serviceTotal) {
        try {
            tvCountService.setVisibility(View.VISIBLE);
            tvCountService.setText(serviceTotal + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích: Hiển thị sô dịch vụ khi người dùng bỏ click dịch vụ
     *
     * @param serviceTotal Tổng số lượng dịch vụ được chọn
     *
     * Created by CVManh on 01/02/2021
     */
    @Override
    public void onClickItemAgain(int serviceTotal) {
        try {
            if (serviceTotal != 0) {
                tvCountService.setVisibility(View.VISIBLE);
                tvCountService.setText(serviceTotal + "");
                return;
            }
            tvCountService.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích: xự kiện click imageButton
     *
     * Created by CVManh on 12/30/2020
     */
    private void eventClickImageButton() {
        try {
            ibLocationMarker.setOnClickListener(this);
            ibListRestaurant.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibLocationMarker: {
                ibLocationMarker.setVisibility(View.GONE);
                ibListRestaurant.setVisibility(View.VISIBLE);
                setFragment(new FragmentLocation());
                break;
            }
            case R.id.ibListRestaurant: {
                ibLocationMarker.setVisibility(View.VISIBLE);
                ibListRestaurant.setVisibility(View.GONE);
                setFragment(new FragmentListRestaurant());
                break;
            }
        }
    }
}