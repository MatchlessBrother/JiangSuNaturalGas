package company.naturalgas.client.ui.main.activity.presenter;

import company.naturalgas.client.bean.BaseReturnData;
import company.naturalgas.client.bean.main.MsgDetailBean;
import company.naturalgas.client.ui.base.BaseMvp_Presenter;
import company.naturalgas.client.ui.base.BaseMvp_EntranceOfModel;
import company.naturalgas.client.ui.base.BaseMvp_LocalObjCallBack;
import company.naturalgas.client.ui.main.activity.model.MsgDetailModel;
import company.naturalgas.client.ui.main.activity.view_v.MsgDetailAct_V;

public class MsgDetailPresenter extends BaseMvp_Presenter<MsgDetailAct_V>
{
    public void getMsgOfDetailDatas(String msgId)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(MsgDetailModel.class).
            putForm("id",msgId).convertForms().executeOfNet(getContext(),MsgDetailModel.GetDetailDatas,new BaseMvp_LocalObjCallBack<BaseReturnData<MsgDetailBean>>(this)
            {
                public void onSuccess(BaseReturnData<MsgDetailBean> msgDetailBean)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().successOfGetDatas(msgDetailBean.getData());
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().failOfGetDatas();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().failOfGetDatas();
                    }
                }
            });
        }
    }
}