package company.naturalgas.client.ui.main.activity.model;

import android.content.Context;
import io.reactivex.schedulers.Schedulers;
import company.naturalgas.client.network.NetClient;
import company.naturalgas.client.ui.base.BaseMvp_PVModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import company.naturalgas.client.ui.base.BaseMvp_NetObjCallBack;
import company.naturalgas.client.ui.base.BaseMvp_NetListCallBack;
import company.naturalgas.client.ui.base.BaseMvp_LocalObjCallBack;
import company.naturalgas.client.ui.base.BaseMvp_LocalListCallBack;

public class DangerDetailModel extends BaseMvp_PVModel
{
    public static final int GetDetailDatas = 0x0001;
    public static final int GetSgaqyDatas = 0x0002;
    public static final int GetSgjlyDatas = 0x0003;
    public static final int ChooseSgaqy = 0x0004;
    public static final int ChooseSgjlr = 0x0005;

    public void executeOfNet(Context context, int netRequestCode, BaseMvp_LocalObjCallBack localCallBack)
    {
        localCallBack.onStart();
        switch(netRequestCode)
        {
            case GetDetailDatas:NetClient.getInstance(context).getNetUrl().getDangerDetailDatas(getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetObjCallBack(context,localCallBack));break;
            case ChooseSgaqy:NetClient.getInstance(context).getNetUrl().chooseSgaqy(getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetObjCallBack(context,localCallBack));break;
            case ChooseSgjlr:NetClient.getInstance(context).getNetUrl().chooseSgjlr(getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetObjCallBack(context,localCallBack));break;
        }
    }

    public void executeOfNet(Context context, int netRequestCode, BaseMvp_LocalListCallBack localCallBack)
    {
        localCallBack.onStart();
        switch(netRequestCode)
        {
            case GetSgaqyDatas:NetClient.getInstance(context).getNetUrl().getSgaqyDatas().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetListCallBack(context,localCallBack));break;
            case GetSgjlyDatas:NetClient.getInstance(context).getNetUrl().getSgjlyDatas().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetListCallBack(context,localCallBack));break;
        }
    }

    public void executeOfLocal(Context context, int localRequestCode, BaseMvp_LocalObjCallBack localCallBack)
    {
        localCallBack.onStart();
        localCallBack.onFinish();
    }
}