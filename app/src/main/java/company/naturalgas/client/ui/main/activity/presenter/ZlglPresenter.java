package company.naturalgas.client.ui.main.activity.presenter;

import company.naturalgas.client.bean.main.ZlglBean;
import company.naturalgas.client.bean.BaseReturnData;
import company.naturalgas.client.ui.base.BaseMvp_Presenter;
import company.naturalgas.client.ui.base.BaseMvp_EntranceOfModel;
import company.naturalgas.client.ui.base.BaseMvp_LocalObjCallBack;
import company.naturalgas.client.ui.main.activity.model.ZlglModel;
import company.naturalgas.client.ui.main.activity.view_v.ZlglAct_V;

public class ZlglPresenter extends BaseMvp_Presenter<ZlglAct_V>
{
    private int currentPageOfIndex;

    public ZlglPresenter()
    {
        currentPageOfIndex = 1;
    }

    public void refreshDatas(String filterContent)
    {
        if(isAttachContextAndViewLayer())
        {
            currentPageOfIndex = 1;
            BaseMvp_EntranceOfModel.requestDatas(ZlglModel.class).putForm("select",filterContent).putForm("page",currentPageOfIndex + "").convertForms().
            executeOfNet(getContext(),ZlglModel.GetZlglDatas,new BaseMvp_LocalObjCallBack<BaseReturnData<ZlglBean>>(this)
            {
                public void onSuccess(BaseReturnData<ZlglBean> zlglBean)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        currentPageOfIndex++;
                        getViewLayer().finishRefresh();
                        getViewLayer().refreshDatas(zlglBean.getData());
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishRefresh();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishRefresh();
                    }
                }
            });
        }
    }

    public void loadMoreDatas(String filterContent)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(ZlglModel.class).putForm("select",filterContent).putForm("page",currentPageOfIndex + "").convertForms().
            executeOfNet(getContext(),ZlglModel.GetZlglDatas,new BaseMvp_LocalObjCallBack<BaseReturnData<ZlglBean>>(this)
            {
                public void onSuccess(BaseReturnData<ZlglBean> zlglBean)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        currentPageOfIndex++;
                        getViewLayer().finishLoadMore();
                        getViewLayer().loadMoreDatas(zlglBean.getData());
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishLoadMore();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishLoadMore();
                    }
                }
            });
        }
    }
}