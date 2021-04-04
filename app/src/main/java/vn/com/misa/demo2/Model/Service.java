package vn.com.misa.demo2.Model;

/**
 * ‐ Mục đích Class thực hiện những việc định nghĩa đối tượng service
 *
 * ‐ {@link vn.com.misa.demo2.Adapter.ServiceAdapter}
 * ‐ {@link vn.com.misa.demo2.MainActivity}
 *
 * ‐ @created_by cvmanh on 01/04/2021
 */

public class Service {

    private String mServiceName;

    private boolean mState;

    public Service(String mServiceName, boolean mState) {
        this.mServiceName = mServiceName;
        this.mState = mState;
    }

    public String getmServiceName() {
        return mServiceName;
    }

    public void setmServiceName(String mServiceName) {
        this.mServiceName = mServiceName;
    }

    public boolean ismState() {
        return mState;
    }

    public void setmState(boolean mState) {
        this.mState = mState;
    }
}
