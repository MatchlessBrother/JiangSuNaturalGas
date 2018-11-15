package company.naturalgas.client.bean.main;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class DangerDetailBean implements Parcelable
{
    /**
     * record : [{"dealContent":"asdasdas","createTime":"2018-11-07 01:14:40","sAccount":"13008114676","rAccount":"13008114674","id":"","dangerId":"","rPhone":"13008114674","dealType":"","sPhone":"13008114676","fileList":[{"id":"","dataId":"","dealType":"","filePath":"http://47.101.133.144:8033/file//test/key"}]},{"dealContent":"sadasda","createTime":"2018-11-07 21:57:58","sAccount":"13008114676","rAccount":"13008114674","id":2,"dangerId":1,"rPhone":"13008114674","dealType":2,"sPhone":"13008114676","fileList":[]}]
     * sgms : {"createTime":"2018-26-01  01:26:36","description":"撒大大","type":"人","fileList":[{"id":"","dataId":"","dealType":"","filePath":"http://47.101.133.144:8033/file//test/key"}]}
     */

    private SgmsBean sgms;
    private List<RecordBean> record;

    public SgmsBean getSgms() {
        return sgms;
    }

    public void setSgms(SgmsBean sgms) {
        this.sgms = sgms;
    }

    public List<RecordBean> getRecord() {
        return record;
    }

    public void setRecord(List<RecordBean> record) {
        this.record = record;
    }

    public static class SgmsBean implements Parcelable{
        /**
         * createTime : 2018-26-01  01:26:36
         * description : 撒大大
         * type : 人
         * fileList : [{"id":"","dataId":"","dealType":"","filePath":"http://47.101.133.144:8033/file//test/key"}]
         */

        private String createTime;
        private String description;
        private String type;
        private List<RecordBean.FileListBeanX> fileList;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<RecordBean.FileListBeanX> getFileList() {
            return fileList;
        }

        public void setFileList(List<RecordBean.FileListBeanX> fileList) {
            this.fileList = fileList;
        }

        public static class FileListBean implements Parcelable{
            /**
             * id :
             * dataId :
             * dealType :
             * filePath : http://47.101.133.144:8033/file//test/key
             */

            private String id;
            private String dataId;
            private String dealType;
            private String filePath;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getDealType() {
                return dealType;
            }

            public void setDealType(String dealType) {
                this.dealType = dealType;
            }

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.dataId);
                dest.writeString(this.dealType);
                dest.writeString(this.filePath);
            }

            public FileListBean() {
            }

            protected FileListBean(Parcel in) {
                this.id = in.readString();
                this.dataId = in.readString();
                this.dealType = in.readString();
                this.filePath = in.readString();
            }

            public static final Creator<FileListBean> CREATOR = new Creator<FileListBean>() {
                @Override
                public FileListBean createFromParcel(Parcel source) {
                    return new FileListBean(source);
                }

                @Override
                public FileListBean[] newArray(int size) {
                    return new FileListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.createTime);
            dest.writeString(this.description);
            dest.writeString(this.type);
            dest.writeTypedList(this.fileList);
        }

        public SgmsBean() {
        }

        protected SgmsBean(Parcel in) {
            this.createTime = in.readString();
            this.description = in.readString();
            this.type = in.readString();
            this.fileList = in.createTypedArrayList(RecordBean.FileListBeanX.CREATOR);
        }

        public static final Creator<SgmsBean> CREATOR = new Creator<SgmsBean>() {
            @Override
            public SgmsBean createFromParcel(Parcel source) {
                return new SgmsBean(source);
            }

            @Override
            public SgmsBean[] newArray(int size) {
                return new SgmsBean[size];
            }
        };
    }

    public static class RecordBean implements Parcelable{
        /**
         * dealContent : asdasdas
         * createTime : 2018-11-07 01:14:40
         * sAccount : 13008114676
         * rAccount : 13008114674
         * id :
         * dangerId :
         * rPhone : 13008114674
         * dealType :
         * sPhone : 13008114676
         * fileList : [{"id":"","dataId":"","dealType":"","filePath":"http://47.101.133.144:8033/file//test/key"}]
         */

        private String dealContent;
        private String createTime;
        private String sAccount;
        private String rAccount;
        private String id;
        private String dangerId;
        private String rPhone;
        private String dealType;
        private String sPhone;
        private String description;
        private String type;
        private List<FileListBeanX> fileList;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDealContent() {
            return dealContent;
        }

        public void setDealContent(String dealContent) {
            this.dealContent = dealContent;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getSAccount() {
            return sAccount;
        }

        public void setSAccount(String sAccount) {
            this.sAccount = sAccount;
        }

        public String getRAccount() {
            return rAccount;
        }

        public void setRAccount(String rAccount) {
            this.rAccount = rAccount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDangerId() {
            return dangerId;
        }

        public void setDangerId(String dangerId) {
            this.dangerId = dangerId;
        }

        public String getRPhone() {
            return rPhone;
        }

        public void setRPhone(String rPhone) {
            this.rPhone = rPhone;
        }

        public String getDealType() {
            return dealType;
        }

        public void setDealType(String dealType) {
            this.dealType = dealType;
        }

        public String getSPhone() {
            return sPhone;
        }

        public void setSPhone(String sPhone) {
            this.sPhone = sPhone;
        }

        public List<FileListBeanX> getFileList() {
            return fileList;
        }

        public void setFileList(List<FileListBeanX> fileList) {
            this.fileList = fileList;
        }

        public static class FileListBeanX implements Parcelable {
            /**
             * id :
             * dataId :
             * dealType :
             * filePath : http://47.101.133.144:8033/file//test/key
             */

            private String id;
            private String dataId;
            private String dealType;
            private String filePath;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getDealType() {
                return dealType;
            }

            public void setDealType(String dealType) {
                this.dealType = dealType;
            }

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.dataId);
                dest.writeString(this.dealType);
                dest.writeString(this.filePath);
            }

            public FileListBeanX() {
            }

            protected FileListBeanX(Parcel in) {
                this.id = in.readString();
                this.dataId = in.readString();
                this.dealType = in.readString();
                this.filePath = in.readString();
            }

            public static final Creator<FileListBeanX> CREATOR = new Creator<FileListBeanX>() {
                @Override
                public FileListBeanX createFromParcel(Parcel source) {
                    return new FileListBeanX(source);
                }

                @Override
                public FileListBeanX[] newArray(int size) {
                    return new FileListBeanX[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.dealContent);
            dest.writeString(this.createTime);
            dest.writeString(this.sAccount);
            dest.writeString(this.rAccount);
            dest.writeString(this.id);
            dest.writeString(this.dangerId);
            dest.writeString(this.rPhone);
            dest.writeString(this.dealType);
            dest.writeString(this.sPhone);
            dest.writeString(this.description);
            dest.writeString(this.type);
            dest.writeTypedList(this.fileList);
        }

        public RecordBean() {
        }

        protected RecordBean(Parcel in) {
            this.dealContent = in.readString();
            this.createTime = in.readString();
            this.sAccount = in.readString();
            this.rAccount = in.readString();
            this.id = in.readString();
            this.dangerId = in.readString();
            this.rPhone = in.readString();
            this.dealType = in.readString();
            this.sPhone = in.readString();
            this.description = in.readString();
            this.type = in.readString();
            this.fileList = in.createTypedArrayList(FileListBeanX.CREATOR);
        }

        public static final Creator<RecordBean> CREATOR = new Creator<RecordBean>() {
            @Override
            public RecordBean createFromParcel(Parcel source) {
                return new RecordBean(source);
            }

            @Override
            public RecordBean[] newArray(int size) {
                return new RecordBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.sgms, flags);
        dest.writeTypedList(this.record);
    }

    public DangerDetailBean() {
    }

    protected DangerDetailBean(Parcel in) {
        this.sgms = in.readParcelable(SgmsBean.class.getClassLoader());
        this.record = in.createTypedArrayList(RecordBean.CREATOR);
    }

    public static final Creator<DangerDetailBean> CREATOR = new Creator<DangerDetailBean>() {
        @Override
        public DangerDetailBean createFromParcel(Parcel source) {
            return new DangerDetailBean(source);
        }

        @Override
        public DangerDetailBean[] newArray(int size) {
            return new DangerDetailBean[size];
        }
    };
}