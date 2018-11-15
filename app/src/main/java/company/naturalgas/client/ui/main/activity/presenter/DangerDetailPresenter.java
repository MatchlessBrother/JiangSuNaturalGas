package company.naturalgas.client.ui.main.activity.presenter;

import company.naturalgas.client.bean.BaseReturnData;
import company.naturalgas.client.ui.base.BaseMvp_Presenter;
import company.naturalgas.client.bean.main.DangerDetailBean;
import company.naturalgas.client.ui.base.BaseMvp_EntranceOfModel;
import company.naturalgas.client.ui.base.BaseMvp_LocalObjCallBack;
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
}