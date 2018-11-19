package company.naturalgas.client.bean.main;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class SjtjBean implements Parcelable
{
    /**
     * yys :
     * fxt : [{"count":["",""],"time":"2018-10"},{"count":["",""],"time":"2018-11"}]
     * yzg :
     * yh :
     */
    private String yys;
    private String yzg;
    private String yh;
    private List<FxtBean> fxt;

    public String getYys() {
        return yys;
    }

    public void setYys(String yys) {
        this.yys = yys;
    }

    public String getYzg() {
        return yzg;
    }

    public void setYzg(String yzg) {
        this.yzg = yzg;
    }

    public String getYh() {
        return yh;
    }

    public void setYh(String yh) {
        this.yh = yh;
    }

    public List<FxtBean> getFxt() {
        return fxt;
    }

    public void setFxt(List<FxtBean> fxt) {
        this.fxt = fxt;
    }

    public static class FxtBean implements Parcelable{
        /**
         * count : ["",""]
         * time : 2018-10
         */

        private String time;
        private List<String> count;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<String> getCount() {
            return count;
        }

        public void setCount(List<String> count) {
            this.count = count;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.time);
            dest.writeStringList(this.count);
        }

        public FxtBean() {
        }

        protected FxtBean(Parcel in) {
            this.time = in.readString();
            this.count = in.createStringArrayList();
        }

        public static final Creator<FxtBean> CREATOR = new Creator<FxtBean>() {
            @Override
            public FxtBean createFromParcel(Parcel source) {
                return new FxtBean(source);
            }

            @Override
            public FxtBean[] newArray(int size) {
                return new FxtBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.yys);
        dest.writeString(this.yzg);
        dest.writeString(this.yh);
        dest.writeTypedList(this.fxt);
    }

    public SjtjBean() {
    }

    protected SjtjBean(Parcel in) {
        this.yys = in.readString();
        this.yzg = in.readString();
        this.yh = in.readString();
        this.fxt = in.createTypedArrayList(FxtBean.CREATOR);
    }

    public static final Creator<SjtjBean> CREATOR = new Creator<SjtjBean>() {
        @Override
        public SjtjBean createFromParcel(Parcel source) {
            return new SjtjBean(source);
        }

        @Override
        public SjtjBean[] newArray(int size) {
            return new SjtjBean[size];
        }
    };
}