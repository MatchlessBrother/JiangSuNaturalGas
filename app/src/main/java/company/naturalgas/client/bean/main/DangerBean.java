package company.naturalgas.client.bean.main;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class DangerBean implements Parcelable
{
    /**
     * total : 21
     * size : 10
     * current : 1
     * records : [{"code":"","createTime":"2018-11-11 12:32:43","statusName":"整改待派发","description":"测试视频压缩，假的批量上传","id":""},{"code":1000,"createTime":"2018-11-10 01:23:33","statusName":"整改待派发","description":"嗯嘛\u2026\u2026","id":21},{"code":1000,"createTime":"2018-11-10 01:21:33","statusName":"整改待派发","description":"是否意味着","id":20},{"code":1000,"createTime":"2018-11-10 01:19:55","statusName":"整改待派发","description":"嗯是时候","id":19},{"code":1000,"createTime":"2018-11-10 01:14:26","statusName":"整改待派发","description":"是的吧。","id":18},{"code":1000,"createTime":"2018-11-10 01:00:50","statusName":"整改待派发","description":"是","id":17},{"code":1000,"createTime":"2018-11-09 23:53:17","statusName":"整改待派发","description":"嗯哼","id":16},{"code":1000,"createTime":"2018-11-09 23:48:48","statusName":"整改待派发","description":"测试路径","id":15},{"code":1000,"createTime":"2018-11-09 23:46:33","statusName":"整改待派发","description":"视频图片上传","id":14},{"code":1000,"createTime":"2018-11-09 22:12:37","statusName":"整改待派发","description":"视频上传测试","id":13}]
     * pages : 3
     */
    private int total;
    private int size;
    private int current;
    private int pages;
    private List<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean  implements Parcelable{
        /**
         * code :
         * createTime : 2018-11-11 12:32:43
         * statusName : 整改待派发
         * description : 测试视频压缩，假的批量上传
         * id :
         */

        private String code;
        private String createTime;
        private String statusName;
        private String description;
        private String id;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.code);
            dest.writeString(this.createTime);
            dest.writeString(this.statusName);
            dest.writeString(this.description);
            dest.writeString(this.id);
        }

        public RecordsBean() {
        }

        protected RecordsBean(Parcel in) {
            this.code = in.readString();
            this.createTime = in.readString();
            this.statusName = in.readString();
            this.description = in.readString();
            this.id = in.readString();
        }

        public static final Creator<RecordsBean> CREATOR = new Creator<RecordsBean>() {
            @Override
            public RecordsBean createFromParcel(Parcel source) {
                return new RecordsBean(source);
            }

            @Override
            public RecordsBean[] newArray(int size) {
                return new RecordsBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeInt(this.size);
        dest.writeInt(this.current);
        dest.writeInt(this.pages);
        dest.writeTypedList(this.records);
    }

    public DangerBean() {
    }

    protected DangerBean(Parcel in) {
        this.total = in.readInt();
        this.size = in.readInt();
        this.current = in.readInt();
        this.pages = in.readInt();
        this.records = in.createTypedArrayList(RecordsBean.CREATOR);
    }

    public static final Creator<DangerBean> CREATOR = new Creator<DangerBean>() {
        @Override
        public DangerBean createFromParcel(Parcel source) {
            return new DangerBean(source);
        }

        @Override
        public DangerBean[] newArray(int size) {
            return new DangerBean[size];
        }
    };
}