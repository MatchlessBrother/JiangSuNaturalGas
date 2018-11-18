package company.naturalgas.client.ui.main.activity.presenter;

import company.naturalgas.client.bean.main.SjtjBean;
import company.naturalgas.client.bean.BaseReturnData;
import company.naturalgas.client.ui.base.BaseMvp_Presenter;
import company.naturalgas.client.ui.base.BaseMvp_EntranceOfModel;
import company.naturalgas.client.ui.base.BaseMvp_LocalObjCallBack;
import company.naturalgas.client.ui.main.activity.model.SjtjModel;
import company.naturalgas.client.ui.main.activity.view_v.SjtjActivity_V;

public class SjtjPresenter extends BaseMvp_Presenter<SjtjActivity_V>
{
    public void getHztjDatas(String startTime,String endTime)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(SjtjModel.class).putForm("startTime",startTime).putForm("endTime",endTime).convertForms().
            executeOfNet(getContext(),SjtjModel.GetHztjDatas,new BaseMvp_LocalObjCallBack<BaseReturnData<SjtjBean>>(this)
            {
                public void onSuccess(BaseReturnData<SjtjBean> sjtjBeanBases)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfSjtjDatas(sjtjBeanBases.getData());
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfSjtjDatas();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfSjtjDatas();
                    }
                }
            });
        }
    }
}