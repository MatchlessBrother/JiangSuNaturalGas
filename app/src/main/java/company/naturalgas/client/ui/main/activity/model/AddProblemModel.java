package company.naturalgas.client.ui.main.activity.model;

import java.util.List;
import java.util.ArrayList;
import okhttp3.MultipartBody;
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
    public static final int GetSjlxDatas = 0x0004;

    public void executeOfNet(Context context, int netRequestCode,BaseMvp_LocalObjCallBack localCallBack)
    {
        localCallBack.onStart();
        switch(netRequestCode)
        {
            case UploadFile:NetClient.getInstance(context).getNetUrl().uploadFile(getMultipartFiles(),getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetObjCallBack(context,localCallBack));break;
            case UpLoadDanger:
            {
                List<MultipartBody.Part> requestBodies = new ArrayList<>();
                for(int index = 0;index < getImagesPath().size();index++)
                    requestBodies.add(MultipartBody.Part.createFormData("files",(String)getImagesPath().get(index)));
                NetClient.getInstance(context).getNetUrl().upLoadDanger(getMultipartForms(),requestBodies).subscribeOn(Schedulers.io()).
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
            case GetSjlxDatas:NetClient.getInstance(context).getNetUrl().getSjlxDatas().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetListCallBack(context,localCallBack));break;
        }
    }

    public void executeOfLocal(Context context, int localRequestCode, BaseMvp_LocalObjCallBack localCallBack)
    {
        localCallBack.onStart();
        localCallBack.onFinish();
    }
}