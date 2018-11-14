package company.naturalgas.client.network;

import java.util.Map;
import java.util.List;
import retrofit2.http.Part;
import retrofit2.http.POST;
import okhttp3.RequestBody;
import retrofit2.http.Query;
import okhttp3.MultipartBody;
import retrofit2.http.PartMap;
import io.reactivex.Observable;
import retrofit2.http.Multipart;
import company.naturalgas.client.bean.main.FzrBean;
import company.naturalgas.client.bean.main.SjlxBean;
import company.naturalgas.client.bean.main.MainInfo;
import company.naturalgas.client.bean.BaseReturnData;
import company.naturalgas.client.bean.main.DangerBean;
import company.naturalgas.client.bean.main.MsgDetailBean;
import company.naturalgas.client.bean.BaseReturnListData;
import company.naturalgas.client.bean.main.RefreshMsgBean;

public interface NetUrl
{
    @POST("/sUser/logout")
    Observable<BaseReturnData> signOut();

    @POST("/sUser/findSgfzr")
    Observable<BaseReturnListData<FzrBean>> getFzrDatas();

    @POST("/dDangerType/getDDangerTypeList")
    Observable<BaseReturnListData<SjlxBean>> getSjlxDatas();

    @POST("/yjfb/notify/newMessage.app")
    Observable<BaseReturnListData<RefreshMsgBean>> refreshMsg();

    @POST("/yjfb/notify/finishAction.app")
    @Multipart
    Observable<BaseReturnData> refreshMsgEnd(@PartMap Map<String, RequestBody> params);

    @POST("/sUser/changePwd")
    @Multipart
    Observable<BaseReturnData> modifyPassword(@PartMap Map<String, RequestBody> params);

    @POST("/sUser/login")
    @Multipart
    Observable<BaseReturnData<MainInfo>> signIn(@PartMap Map<String, RequestBody> params);

    @POST("/cUpDanger/getDangerList")
    @Multipart
    Observable<BaseReturnData<DangerBean>> getDangerDatas(@PartMap Map<String, RequestBody> params);

    @POST("/yjfb/notify/detail.app")
    @Multipart
    Observable<BaseReturnData<MsgDetailBean>> getMsgOfDetailDats(@PartMap Map<String, RequestBody> params);

    @POST("/cUpDanger/upLoadDanger")
    @Multipart
    Observable<BaseReturnData> upLoadDanger(@PartMap Map<String, RequestBody> params,@Query("files[]") String[] filesParams);

    @POST("/cDangerFile/uploadFile")
    @Multipart
    Observable<BaseReturnData<String>> uploadFile(@Part List<MultipartBody.Part> fileParams,@PartMap Map<String, RequestBody> params);

}