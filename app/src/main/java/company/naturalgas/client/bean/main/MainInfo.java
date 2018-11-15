package company.naturalgas.client.bean.main;

import java.util.List;
import android.os.Parcel;
import java.util.ArrayList;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class MainInfo implements Parcelable
{
    /**
     * role : {"id":"","roleName":"施工负责人","code":""}
     * menu : {"隐患排查":[{"authUrl":"yhzg","functionAuth":[{"id":"","authName":"施工处理","authPic":"","authSort":7,"authLevel":3,"authUrl":"sgcl","parentId":""},{"id":10,"authName":"施工派发","authPic":null,"authSort":8,"authLevel":3,"authUrl":"sgpf","parentId":3}],"functionAuths":"functionAuths:sgcl,sgpf","serialVersionUID":"1","authName":"隐患整改","authSort":"3","id":"3","authLevel":"2","parentId":"1"},{"authUrl":"yhtj","functionAuth":[],"functionAuths":"functionAuths:","serialVersionUID":"1","authName":"隐患统计","authSort":"5","id":"5","authLevel":"2","parentId":"1"}],"其他":[{"authUrl":"zlgl","functionAuth":[],"functionAuths":"functionAuths:","serialVersionUID":"1","authName":"资料管理","authSort":"13","id":"15","authLevel":"2","parentId":"14"}]}
     * token : {"token":"cab49f3b67b54393a1af3dde7c950c38","expireTimestamp":"","userId":""}
     */

    private RoleBean role;
    private MenuBean menu;
    private TokenBean token;

    public RoleBean getRole() {
        return role;
    }

    public void setRole(RoleBean role) {
        this.role = role;
    }

    public MenuBean getMenu() {
        return menu;
    }

    public void setMenu(MenuBean menu) {
        this.menu = menu;
    }

    public TokenBean getToken() {
        return token;
    }

    public void setToken(TokenBean token) {
        this.token = token;
    }

    public static class RoleBean implements Parcelable{
        /**
         * id :
         * roleName : 施工负责人
         * code :
         */
        private String id;
        private String roleName;
        private String code;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.roleName);
            dest.writeString(this.code);
        }

        public RoleBean() {
        }

        protected RoleBean(Parcel in) {
            this.id = in.readString();
            this.roleName = in.readString();
            this.code = in.readString();
        }

        public static final Creator<RoleBean> CREATOR = new Creator<RoleBean>() {
            @Override
            public RoleBean createFromParcel(Parcel source) {
                return new RoleBean(source);
            }

            @Override
            public RoleBean[] newArray(int size) {
                return new RoleBean[size];
            }
        };
    }

    public static class MenuBean implements Parcelable
    {
        @SerializedName("隐患排查")
        private List<YhpcBean> yhpc;
        @SerializedName("其他")
        private List<YhpcBean> qtyw;

        public List<YhpcBean> getYhpc() {
            return yhpc;
        }

        public void setYhpc(List<YhpcBean> yhpc) {
            this.yhpc = yhpc;
        }

        public List<YhpcBean> getQtyw() {
            return qtyw;
        }

        public void setQtyw(List<YhpcBean> qtyw) {
            this.qtyw = qtyw;
        }

        public static class YhpcBean implements Parcelable,Comparable<YhpcBean>{
            /**
             * authUrl : yhzg
             * functionAuth : [{"id":"","authName":"施工处理","authPic":"","authSort":7,"authLevel":3,"authUrl":"sgcl","parentId":""},{"id":10,"authName":"施工派发","authPic":null,"authSort":8,"authLevel":3,"authUrl":"sgpf","parentId":3}]
             * functionAuths : functionAuths:sgcl,sgpf
             * serialVersionUID : 1
             * authName : 隐患整改
             * authSort : 3
             * id : 3
             * authLevel : 2
             * parentId : 1
             */

            private String authUrl;
            private String functionAuths;
            private String serialVersionUID;
            private String authName;
            private int authSort;
            private String id;
            private int authLevel;
            private String parentId;
            private List<FunctionAuthBean> functionAuth;

            public String getAuthUrl() {
                return authUrl;
            }

            public void setAuthUrl(String authUrl) {
                this.authUrl = authUrl;
            }

            public String getFunctionAuths() {
                return functionAuths;
            }

            public void setFunctionAuths(String functionAuths) {
                this.functionAuths = functionAuths;
            }

            public String getSerialVersionUID() {
                return serialVersionUID;
            }

            public void setSerialVersionUID(String serialVersionUID) {
                this.serialVersionUID = serialVersionUID;
            }

            public String getAuthName() {
                return authName;
            }

            public void setAuthName(String authName) {
                this.authName = authName;
            }

            public int getAuthSort() {
                return authSort;
            }

            public void setAuthSort(int authSort) {
                this.authSort = authSort;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getAuthLevel() {
                return authLevel;
            }

            public void setAuthLevel(int authLevel) {
                this.authLevel = authLevel;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public List<FunctionAuthBean> getFunctionAuth() {
                return functionAuth;
            }

            public void setFunctionAuth(List<FunctionAuthBean> functionAuth) {
                this.functionAuth = functionAuth;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.authUrl);
                dest.writeString(this.functionAuths);
                dest.writeString(this.serialVersionUID);
                dest.writeString(this.authName);
                dest.writeInt(this.authSort);
                dest.writeString(this.id);
                dest.writeInt(this.authLevel);
                dest.writeString(this.parentId);
                dest.writeTypedList(this.functionAuth);
            }

            public YhpcBean() {
            }

            protected YhpcBean(Parcel in) {
                this.authUrl = in.readString();
                this.functionAuths = in.readString();
                this.serialVersionUID = in.readString();
                this.authName = in.readString();
                this.authSort = in.readInt();
                this.id = in.readString();
                this.authLevel = in.readInt();
                this.parentId = in.readString();
                this.functionAuth = in.createTypedArrayList(FunctionAuthBean.CREATOR);
            }

            public static final Creator<YhpcBean> CREATOR = new Creator<YhpcBean>() {
                @Override
                public YhpcBean createFromParcel(Parcel source) {
                    return new YhpcBean(source);
                }

                @Override
                public YhpcBean[] newArray(int size) {
                    return new YhpcBean[size];
                }
            };

            public int compareTo(@NonNull YhpcBean yhpcBean)
            {
                return this.getAuthSort() - yhpcBean.getAuthSort();
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(this.yhpc);
            dest.writeTypedList(this.qtyw);
        }

        public MenuBean() {
        }

        protected MenuBean(Parcel in) {
            this.yhpc = in.createTypedArrayList(YhpcBean.CREATOR);
            this.qtyw = in.createTypedArrayList(YhpcBean.CREATOR);
        }

        public static final Creator<MenuBean> CREATOR = new Creator<MenuBean>() {
            @Override
            public MenuBean createFromParcel(Parcel source) {
                return new MenuBean(source);
            }

            @Override
            public MenuBean[] newArray(int size) {
                return new MenuBean[size];
            }
        };
    }

    public static class TokenBean implements Parcelable{
        /**
         * token : cab49f3b67b54393a1af3dde7c950c38
         * expireTimestamp :
         * userId :
         */

        private String token;
        private String expireTimestamp;
        private String userId;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExpireTimestamp() {
            return expireTimestamp;
        }

        public void setExpireTimestamp(String expireTimestamp) {
            this.expireTimestamp = expireTimestamp;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.token);
            dest.writeString(this.expireTimestamp);
            dest.writeString(this.userId);
        }

        public TokenBean() {
        }

        protected TokenBean(Parcel in) {
            this.token = in.readString();
            this.expireTimestamp = in.readString();
            this.userId = in.readString();
        }

        public static final Creator<TokenBean> CREATOR = new Creator<TokenBean>() {
            @Override
            public TokenBean createFromParcel(Parcel source) {
                return new TokenBean(source);
            }

            @Override
            public TokenBean[] newArray(int size) {
                return new TokenBean[size];
            }
        };
    }

    public static class FunctionAuthBean implements Parcelable{
        /**
         * id :
         * authName : 施工处理
         * authPic :
         * authSort : 7
         * authLevel : 3
         * authUrl : sgcl
         * parentId :
         */

        private String id;
        private String authName;
        private String authPic;
        private int authSort;
        private int authLevel;
        private String authUrl;
        private String parentId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthName() {
            return authName;
        }

        public void setAuthName(String authName) {
            this.authName = authName;
        }

        public String getAuthPic() {
            return authPic;
        }

        public void setAuthPic(String authPic) {
            this.authPic = authPic;
        }

        public int getAuthSort() {
            return authSort;
        }

        public void setAuthSort(int authSort) {
            this.authSort = authSort;
        }

        public int getAuthLevel() {
            return authLevel;
        }

        public void setAuthLevel(int authLevel) {
            this.authLevel = authLevel;
        }

        public String getAuthUrl() {
            return authUrl;
        }

        public void setAuthUrl(String authUrl) {
            this.authUrl = authUrl;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.authName);
            dest.writeString(this.authPic);
            dest.writeInt(this.authSort);
            dest.writeInt(this.authLevel);
            dest.writeString(this.authUrl);
            dest.writeString(this.parentId);
        }

        public FunctionAuthBean() {
        }

        protected FunctionAuthBean(Parcel in) {
            this.id = in.readString();
            this.authName = in.readString();
            this.authPic = in.readString();
            this.authSort = in.readInt();
            this.authLevel = in.readInt();
            this.authUrl = in.readString();
            this.parentId = in.readString();
        }

        public static final Creator<FunctionAuthBean> CREATOR = new Creator<FunctionAuthBean>() {
            @Override
            public FunctionAuthBean createFromParcel(Parcel source) {
                return new FunctionAuthBean(source);
            }

            @Override
            public FunctionAuthBean[] newArray(int size) {
                return new FunctionAuthBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.role, flags);
        dest.writeParcelable(this.menu, flags);
        dest.writeParcelable(this.token, flags);
    }

    public MainInfo() {
    }

    protected MainInfo(Parcel in) {
        this.role = in.readParcelable(RoleBean.class.getClassLoader());
        this.menu = in.readParcelable(MenuBean.class.getClassLoader());
        this.token = in.readParcelable(TokenBean.class.getClassLoader());
    }

    public static final Creator<MainInfo> CREATOR = new Creator<MainInfo>() {
        @Override
        public MainInfo createFromParcel(Parcel source) {
            return new MainInfo(source);
        }

        @Override
        public MainInfo[] newArray(int size) {
            return new MainInfo[size];
        }
    };
}