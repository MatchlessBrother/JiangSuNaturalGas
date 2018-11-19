package company.naturalgas.client.ui.main.activity.presenter;

import java.util.List;
import java.util.Arrays;
import company.naturalgas.client.bean.main.FzrBean;
import company.naturalgas.client.bean.main.SjlxBean;
import company.naturalgas.client.bean.BaseReturnData;
import company.naturalgas.client.bean.main.JlfzrBean;
import company.naturalgas.client.bean.BaseReturnListData;
import company.naturalgas.client.ui.base.BaseMvp_Presenter;
import company.naturalgas.client.ui.base.BaseMvp_EntranceOfModel;
import company.naturalgas.client.ui.base.BaseMvp_LocalObjCallBack;
import company.naturalgas.client.ui.base.BaseMvp_LocalListCallBack;
import company.naturalgas.client.ui.main.activity.model.AddProblemModel;
import company.naturalgas.client.ui.main.activity.view_v.AddProblemAct_V;

public class AddProblemPresenter extends BaseMvp_Presenter<AddProblemAct_V>
{
    public void getFzrDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(AddProblemModel.class).
            executeOfNet(getContext(),AddProblemModel.GetFzrDatas,new BaseMvp_LocalListCallBack<BaseReturnListData<FzrBean>>(this)
            {
                public void onSuccess(BaseReturnListData<FzrBean> jcDataInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfFzrDatas(Arrays.asList(jcDataInfo.getData()));
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfFzrDatas();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfFzrDatas();
                    }
                }
            });
        }
    }

    public void getSjlxDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(AddProblemModel.class).
            executeOfNet(getContext(),AddProblemModel.GetSjlxDatas,new BaseMvp_LocalListCallBack<BaseReturnListData<SjlxBean>>(this)
            {
                public void onSuccess(BaseReturnListData<SjlxBean> sjlxBeans)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfSjlxDatas(Arrays.asList(sjlxBeans.getData()));
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfSjlxDatas();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfSjlxDatas();
                    }
                }
            });
        }
    }

    public void getJlfzrDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(AddProblemModel.class).
            executeOfNet(getContext(),AddProblemModel.GetJlfzrDatas,new BaseMvp_LocalListCallBack<BaseReturnListData<JlfzrBean>>(this)
            {
                public void onSuccess(BaseReturnListData<JlfzrBean> jlfzrBeans)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfJlfzrDatas(Arrays.asList(jlfzrBeans.getData()));
                    }
                }

                 public void onFailure(String msg)
                 {
                     super.onFailure(msg);
                     if(isAttachContextAndViewLayer())
                     {
                         getViewLayer().getFailOfJlfzrDatas();
                     }
                 }

                 public void onError(String msg)
                 {
                     super.onError(msg);
                     if(isAttachContextAndViewLayer())
                     {
                         getViewLayer().getFailOfJlfzrDatas();
                     }
                 }
            });
        }
    }

    public void uploadFile(String filePath,String dealType)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(AddProblemModel.class).putForm("dealType",dealType).convertForms().putFilePath(filePath).convertFiles().
            executeOfNet(getContext(),AddProblemModel.UploadFile,new BaseMvp_LocalObjCallBack<BaseReturnData<String>>(this)
            {
                public void onSuccess(BaseReturnData<String> baseReturnData)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfUploadFile(baseReturnData.getData());
                    }
                }

                public void onFailure(String msg)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfUploadFile();
                    }
                }

                public void onError(String msg)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfUploadFile();
                    }
                }
            });
        }
    }

    public void acceptDanger(String description,String dangerId,List<String> filesPath)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(AddProblemModel.class).putForm("description",description).putForm("dangerId",dangerId).
            convertForms().putImagesPath(filesPath).executeOfNet(getContext(),AddProblemModel.AcceptDanger,new BaseMvp_LocalObjCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData baseReturnData)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfAcceptDanger();
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfAcceptDanger();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfAcceptDanger();
                    }
                }
            });
        }
    }

    public void refuseDanger(String description,String dangerId,List<String> filesPath)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(AddProblemModel.class).putForm("description",description).putForm("dangerId",dangerId).
            convertForms().putImagesPath(filesPath).executeOfNet(getContext(),AddProblemModel.RefuseDanger,new BaseMvp_LocalObjCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData baseReturnData)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfRefuseDanger();
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfRefuseDanger();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfRefuseDanger();
                    }
                }
            });
        }
    }

    public void processDanger(String transmitDealId,String description,String dangerId,List<String> filesPath)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(AddProblemModel.class).putForm("transmitDealId",transmitDealId).putForm("description",description).putForm("dangerId",dangerId).
            convertForms().putImagesPath(filesPath).executeOfNet(getContext(),AddProblemModel.ProcessDanger,new BaseMvp_LocalObjCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData baseReturnData)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfProcessDanger();
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfProcessDanger();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfProcessDanger();
                    }
                }
            });
        }
    }

    public void uploadDanger(String transmitDealId,String description,String sjlx,List<String> filesPath)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(AddProblemModel.class).putForm("transmitDealId",transmitDealId).putForm("description",description).putForm("dangerType",sjlx).
            convertForms().putImagesPath(filesPath).executeOfNet(getContext(),AddProblemModel.UpLoadDanger,new BaseMvp_LocalObjCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData baseReturnData)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfUploadDanger();
                    }
                }

                 public void onFailure(String msg)
                 {
                     super.onFailure(msg);
                     if(isAttachContextAndViewLayer())
                     {
                         getViewLayer().getFailOfUploadDanger();
                     }
                 }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfUploadDanger();
                    }
                }
            });
        }
    }
}