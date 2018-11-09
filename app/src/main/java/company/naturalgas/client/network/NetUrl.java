package company.naturalgas.client.network;

import java.util.Map;
import okhttp3.RequestBody;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import io.reactivex.Observable;
import retrofit2.http.Multipart;
import company.naturalgas.client.bean.main.MainInfo;
import company.naturalgas.client.bean.BaseReturnData;
import company.naturalgas.client.bean.main.MsgDetailBean;
import company.naturalgas.client.bean.BaseReturnListData;
import company.naturalgas.client.bean.main.RefreshMsgBean;

public interface NetUrl
{
    @POST("/sUser/logout")
    Observable<BaseReturnData> signOut();

    @POST("/sUser/changePwd")
    @Multipart
    Observable<BaseReturnData> modifyPassword(@PartMap Map<String, RequestBody> params);

    @POST("/sUser/login")
    @Multipart
    Observable<BaseReturnData<MainInfo>> signIn(@PartMap Map<String, RequestBody> params);



    @POST("/yjfb/notify/newMessage.app")
    Observable<BaseReturnListData<RefreshMsgBean>> refreshMsg();

    @POST("/yjfb/notify/finishAction.app")
    @Multipart
    Observable<BaseReturnData> refreshMsgEnd(@PartMap Map<String, RequestBody> params);

    @POST("/yjfb/notify/detail.app")
    @Multipart
    Observable<BaseReturnData<MsgDetailBean>> getMsgOfDetailDats(@PartMap Map<String, RequestBody> params);
}