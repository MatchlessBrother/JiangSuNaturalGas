package company.naturalgas.client.ui.main.activity.presenter;

import java.util.List;
import okhttp3.Interceptor;
import company.naturalgas.client.network.NetClient;
import company.naturalgas.client.bean.BaseReturnData;
import company.naturalgas.client.ui.base.BaseMvp_Presenter;
import company.naturalgas.client.ui.base.BaseMvp_EntranceOfModel;
import company.naturalgas.client.ui.base.BaseMvp_LocalObjCallBack;
import company.naturalgas.client.ui.main.activity.model.MainModel;
import company.naturalgas.client.ui.main.activity.view_v.MainAct_V;
import com.yuan.devlibrary._9__Network.okhttp.Http3Interceptions.TokenInterceptor_PersistentStore;

public class MainPresenter extends BaseMvp_Presenter<MainAct_V>
{
    public void signOut()
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(MainModel.class).executeOfNet(getContext(),MainModel.SignOut,new BaseMvp_LocalObjCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData baseReturnData)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        List<Interceptor> interceptorList = NetClient.getInstance(getContext().getApplicationContext()).getOkHttpClient().interceptors();
                        for(int index = 0;index<interceptorList.size();index++)
                        {
                            if(interceptorList.get(index) instanceof TokenInterceptor_PersistentStore)
                            {
                                TokenInterceptor_PersistentStore interceptor = (TokenInterceptor_PersistentStore) interceptorList.get(index);
                                interceptor.updateToken(NetClient.getInstance(getContext().getApplicationContext()).getRetrofit().baseUrl().host().trim(),"");
                                getViewLayer().signOutSuccess();
                                return;
                            }
                            if(index == interceptorList.size() -1)
                            {
                                getViewLayer().showToast("退出登录失败，请稍后再试，谢谢！");
                            }
                        }
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().signOutFailure();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().signOutFailure();
                    }
                }
            });
        }
    }

    public void getDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(MainModel.class).executeOfNet(getContext(),MainModel.GetDatas,new BaseMvp_LocalObjCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData baseReturnData)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfDatas();
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfDatas();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfDatas();
                    }
                }
            });
        }
    }
}