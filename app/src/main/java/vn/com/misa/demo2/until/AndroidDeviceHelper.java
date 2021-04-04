package vn.com.misa.demo2.until;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * ‐ Mục đích Class thực hiện lấy kích thước của màn hình
 *
 * ‐ {@link vn.com.misa.demo2.View.FragmentListRestaurant}
 * - {@link vn.com.misa.demo2.View.FragmentLocation}
 *
 * ‐ @created_by cvmanh on 01/04/2021
 */

public class AndroidDeviceHelper {

    /**
     * Mục đích: Lấy chiều rộng màn hình hiện tại của thiết bị
     *
     * @return Trả về kích thước chiều rộng của màn hình
     *
     * Created by cvmanh on 12/30/2020
     */
    public static int getWidthScreen(Context context){
        try {
            WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display=windowManager.getDefaultDisplay();
            Point point=new Point();
            display.getSize(point);
            return point.x;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Mục đích: Lấy chiều dài màn hình hiện tại của thiết bị
     *
     * @return Trả về kích thước chiều dài của màn hình
     *
     * Created by CVManh on 12/30/2020
     */
    public static int getHeightScreen(Context context){
        try {
            WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display=windowManager.getDefaultDisplay();
            Point point=new Point();
            display.getSize(point);
            return point.y;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
