package company.naturalgas.client.ui.main.activity.presenter;

import java.util.Arrays;
import company.naturalgas.client.bean.BaseReturnData;
import company.naturalgas.client.bean.main.SgaqyBean;
import company.naturalgas.client.bean.main.SgjlyBean;
import company.naturalgas.client.bean.BaseReturnListData;
import company.naturalgas.client.ui.base.BaseMvp_Presenter;
import company.naturalgas.client.bean.main.DangerDetailBean;
import company.naturalgas.client.ui.base.BaseMvp_EntranceOfModel;
import company.naturalgas.client.ui.base.BaseMvp_LocalObjCallBack;
import company.naturalgas.client.ui.base.BaseMvp_LocalListCallBack;
import company.naturalgas.client.ui.main.activity.model.DangerDetailModel;
import company.naturalgas.client.ui.main.activity.view_v.DangerDetailAct_V;

public class DangerDetailPresenter extends BaseMvp_Presenter<DangerDetailAct_V>
{
    public void getDetailDatas(String id)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(DangerDetailModel.class).putForm("id",id).convertForms().
            executeOfNet(getContext(),DangerDetailModel.GetDetailDatas,new BaseMvp_LocalObjCallBack<BaseReturnData<DangerDetailBean>>(this)
            {
                public void onSuccess(BaseReturnData<DangerDetailBean> dangerDetailBeans)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfDangerDetailDatas(dangerDetailBeans.getData());
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfDangerDetailDatas();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfDangerDetailDatas();
                    }
                }
            });
        }
    }

    public void getSgaqyDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(DangerDetailModel.class).
            executeOfNet(getContext(),DangerDetailModel.GetSgaqyDatas,new BaseMvp_LocalListCallBack<BaseReturnListData<SgaqyBean>>(this)
            {
                public void onSuccess(BaseReturnListData<SgaqyBean> sgaqyBeans)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfSgaqyDatas(Arrays.asList(sgaqyBeans.getData()));
                     }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfSgaqyDatas();
                     }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfSgaqyDatas();
                    }
                }
            });
        }
    }

    public void getSgjlrDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(DangerDetailModel.class).
            executeOfNet(getContext(),DangerDetailModel.GetSgjlyDatas,new BaseMvp_LocalListCallBack<BaseReturnListData<SgjlyBean>>(this)
            {
                public void onSuccess(BaseReturnListData<SgjlyBean> sgjlyBeans)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfSgjlrDatas(Arrays.asList(sgjlyBeans.getData()));
                        }
                    }

                    public void onFailure(String msg)
                    {
                        super.onFailure(msg);
                        if(isAttachContextAndViewLayer())
                        {
                            getViewLayer().getFailOfSgjlrDatas();
                        }
                    }

                    public void onError(String msg)
                    {
                        super.onError(msg);
                        if(isAttachContextAndViewLayer())
                        {
                            getViewLayer().getFailOfSgjlrDatas();
                        }
                    }
            });
        }
    }

    public void chooseSgaqy(String transmitDealId,String dangerId)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(DangerDetailModel.class).putForm("transmitDealId",transmitDealId).putForm("dangerId",dangerId).convertForms().
            executeOfNet(getContext(),DangerDetailModel.ChooseSgaqy,new BaseMvp_LocalObjCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData baseReturnData)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().chooseSuccessOfSgaqy();
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().chooseFailOfSgaqy();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().chooseFailOfSgaqy();
                    }
                }
            });
        }
    }

    public void chooseSgjlr(String transmitDealId,String dangerId)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(DangerDetailModel.class).putForm("transmitDealId",transmitDealId).putForm("dangerId",dangerId).convertForms().
            executeOfNet(getContext(),DangerDetailModel.ChooseSgjlr,new BaseMvp_LocalObjCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData baseReturnData)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().chooseSuccessOfSgjlr();
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().chooseFailOfSgjlr();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().chooseFailOfSgjlr();
                    }
                }
            });
        }
    }
}