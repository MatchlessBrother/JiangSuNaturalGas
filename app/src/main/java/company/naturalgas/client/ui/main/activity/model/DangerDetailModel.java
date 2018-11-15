package company.naturalgas.client.ui.main.activity.model;

import android.content.Context;
import io.reactivex.schedulers.Schedulers;
import company.naturalgas.client.network.NetClient;
import company.naturalgas.client.ui.base.BaseMvp_PVModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import company.naturalgas.client.ui.base.BaseMvp_NetObjCallBack;
import company.naturalgas.client.ui.base.BaseMvp_LocalObjCallBack;

public class DangerDetailModel extends BaseMvp_PVModel
{
    public static final int GetDetailDatas = 0x0001;
    public void executeOfNet(Context context, int netRequestCode, BaseMvp_LocalObjCallBack localCallBack)
    {
        localCallBack.onStart();
        switch(netRequestCode)
        {
            case GetDetailDatas: NetClient.getInstance(context).getNetUrl().getDangerDetailDatas(getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetObjCallBack(context,localCallBack));break;
        }
    }

    public void executeOfLocal(Context context, int localRequestCode, BaseMvp_LocalObjCallBack localCallBack)
    {
        localCallBack.onStart();
        localCallBack.onFinish();
    }
}