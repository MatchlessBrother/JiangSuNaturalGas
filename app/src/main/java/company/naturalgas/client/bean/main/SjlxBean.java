package company.naturalgas.client.bean.main;

import android.os.Parcel;
import android.os.Parcelable;
import com.contrarywind.interfaces.IPickerViewData;

public class SjlxBean implements Parcelable,IPickerViewData
{
    private String id;
    private String code;
    private String type;

    public String getPickerViewText()
    {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.code);
        dest.writeString(this.type);
    }

    public SjlxBean() {
    }

    protected SjlxBean(Parcel in) {
        this.id = in.readString();
        this.code = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<SjlxBean> CREATOR = new Parcelable.Creator<SjlxBean>() {
        @Override
        public SjlxBean createFromParcel(Parcel source) {
            return new SjlxBean(source);
        }

        @Override
        public SjlxBean[] newArray(int size) {
            return new SjlxBean[size];
        }
    };
}