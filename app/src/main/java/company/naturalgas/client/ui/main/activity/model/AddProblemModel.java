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

public class AddProblemModel extends BaseMvp_PVModel
{
    public static final int GetFzrDatas = 0x0001;
    public static final int UploadFile = 0x0002;
    public static final int UpLoadDanger = 0x0003;


    public void executeOfNet(Context context, int netRequestCode,BaseMvp_LocalObjCallBack localCallBack)
    {
        localCallBack.onStart();
        switch(netRequestCode)
        {
            case UploadFile:NetClient.getInstance(context).getNetUrl().uploadFile(getMultipartFiles(),getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetObjCallBack(context,localCallBack));break;
            case UpLoadDanger:
            {
                String[] imagesPath = new String[getImagesPath().size()];
                for(int index = 0;index < imagesPath.length;index++)
                    imagesPath[index] = (String)getImagesPath().get(index);
                NetClient.getInstance(context).getNetUrl().upLoadDanger(getMultipartForms(),imagesPath).subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetObjCallBack(context,localCallBack));break;
            }
        }
    }

    public void executeOfNet(Context context, int netRequestCode,BaseMvp_LocalListCallBack localCallBack)
    {
        localCallBack.onStart();
        switch(netRequestCode)
        {
            case GetFzrDatas:NetClient.getInstance(context).getNetUrl().getFzrDatas().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetListCallBack(context,localCallBack));break;
        }
    }

    public void executeOfLocal(Context context, int localRequestCode, BaseMvp_LocalObjCallBack localCallBack)
    {
        localCallBack.onStart();
        localCallBack.onFinish();
    }
}