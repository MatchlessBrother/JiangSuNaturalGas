package company.naturalgas.client.bean.main;

import android.os.Parcel;
import android.os.Parcelable;
import com.contrarywind.interfaces.IPickerViewData;

public class FzrBean implements Parcelable,IPickerViewData
{
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

    public FzrBean() {
    }

    protected FzrBean(Parcel in) {
        this.id = in.readString();
        this.account = in.readString();
    }

    public static final Creator<FzrBean> CREATOR = new Creator<FzrBean>() {
        @Override
        public FzrBean createFromParcel(Parcel source) {
            return new FzrBean(source);
        }

        @Override
        public FzrBean[] newArray(int size) {
            return new FzrBean[size];
        }
    };
}