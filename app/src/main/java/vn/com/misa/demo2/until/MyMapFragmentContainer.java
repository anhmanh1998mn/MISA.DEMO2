package vn.com.misa.demo2.until;

/**
 * ‐ Mục đích Class thực hiện công việc tạo vùng chứa trong suốt cho google map fragment
 *
 * - {@link vn.com.misa.demo2.View.FragmentLocation}
 *
 * ‐ @created_by cvmanh on 01/04/2021
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MyMapFragmentContainer extends LinearLayout {

    public MyMapFragmentContainer(Context context) {
        super(context);
    }

    public MyMapFragmentContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Mục đích: Chặn các sự kiện chạm của scrollView
     *
     * @param motionEvent Kiểu hoạt động của sự kiện touch
     *
     * Created by cvmanh on 01/02/2021
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) //Nếu kiểu hoạt động sự kiện chạm xảy ra trên 1 view cùng với tọa độ của điểm được chạm
            {
                ViewParent viewParent = getParent();
                if (viewParent != null) {
                    viewParent.requestDisallowInterceptTouchEvent(true); //Chặn sự kiện chạm từ view cha
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public MyMapFragmentContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
