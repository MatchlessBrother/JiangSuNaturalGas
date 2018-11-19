package company.naturalgas.client.bean.main;

import android.os.Parcel;
import android.os.Parcelable;
import com.contrarywind.interfaces.IPickerViewData;

public class JlfzrBean implements Parcelable,IPickerViewData
{
    /**
     * id :
     * account : 大哥
     */
    private String id;
    private String account;

    public String getPickerViewText() {
        return account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.account);
    }

    public JlfzrBean() {
    }

    protected JlfzrBean(Parcel in) {
        this.id = in.readString();
        this.account = in.readString();
    }

    public static final Creator<JlfzrBean> CREATOR = new Creator<JlfzrBean>() {
        @Override
        public JlfzrBean createFromParcel(Parcel source) {
            return new JlfzrBean(source);
        }

        @Override
        public JlfzrBean[] newArray(int size) {
            return new JlfzrBean[size];
        }
    };
}