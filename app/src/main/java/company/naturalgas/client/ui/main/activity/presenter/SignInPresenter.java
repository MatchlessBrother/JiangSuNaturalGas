package company.naturalgas.client.ui.main.activity.presenter;

import java.util.List;
import okhttp3.Interceptor;
import company.naturalgas.client.network.NetClient;
import company.naturalgas.client.bean.main.MainInfo;
import company.naturalgas.client.bean.BaseReturnData;
import company.naturalgas.client.ui.base.BaseMvp_Presenter;
import com.yuan.devlibrary._12_______Utils.SharepreferenceUtils;
import company.naturalgas.client.ui.base.BaseMvp_EntranceOfModel;
import company.naturalgas.client.ui.base.BaseMvp_LocalObjCallBack;
import company.naturalgas.client.ui.main.activity.model.SignInModel;
import company.naturalgas.client.ui.main.activity.view_v.SignInAct_V;
import com.yuan.devlibrary._9__Network.okhttp.Http3Interceptions.TokenInterceptor_PersistentStore;

public class SignInPresenter extends BaseMvp_Presenter<SignInAct_V>
{
    public void signIn(final String phone,final String password)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(SignInModel.class).
            putForm("phone",phone).putForm("password",password).convertForms().executeOfNet(getContext(),SignInModel.SignIn,new BaseMvp_LocalObjCallBack<BaseReturnData<MainInfo>>(this)
            {
                public void onSuccess(BaseReturnData<MainInfo> mainInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        List<Interceptor> interceptorList = NetClient.getInstance(getContext().getApplicationContext()).getOkHttpClient().interceptors();
                        for(int index = 0;index<interceptorList.size();index++)
                        {
                            if(interceptorList.get(index) instanceof TokenInterceptor_PersistentStore)
                            {
                                TokenInterceptor_PersistentStore interceptor = (TokenInterceptor_PersistentStore) interceptorList.get(index);
                                interceptor.updateToken(NetClient.getInstance(getContext().getApplicationContext()).getRetrofit().baseUrl().host().trim(), mainInfo.getData().getToken().getToken().trim());
                                SharepreferenceUtils.storageObject(getContext(),"password",password);
                                SharepreferenceUtils.storageObject(getContext(),"phone",phone);
                                getViewLayer().getBaseApp().setMainInfo(mainInfo.getData());
                                getViewLayer().signInSuccess();
                                return;
                            }

                            if(index == interceptorList.size() -1)
                            {
                                getViewLayer().showToast("登录失败，请稍后再试，谢谢！");
                            }
                        }
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().signInFailure();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().signInFailure();
                    }
                }
            });
        }
    }
}