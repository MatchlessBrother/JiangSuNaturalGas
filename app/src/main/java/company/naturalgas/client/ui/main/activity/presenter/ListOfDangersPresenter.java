package company.naturalgas.client.ui.main.activity.presenter;

import company.naturalgas.client.bean.BaseReturnData;
import company.naturalgas.client.bean.main.DangerBean;
import company.naturalgas.client.ui.base.BaseMvp_Presenter;
import company.naturalgas.client.ui.base.BaseMvp_EntranceOfModel;
import company.naturalgas.client.ui.base.BaseMvp_LocalObjCallBack;
import company.naturalgas.client.ui.main.activity.model.ListOfDangersModel;
import company.naturalgas.client.ui.main.activity.view_v.ListOfDangersAct_V;

public class ListOfDangersPresenter extends BaseMvp_Presenter<ListOfDangersAct_V>
{
    private int currentPageOfIndex;

    public ListOfDangersPresenter()
    {
        currentPageOfIndex = 1;
    }

    public void refreshDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            currentPageOfIndex = 1;
            BaseMvp_EntranceOfModel.requestDatas(ListOfDangersModel.class).
            putForm("index",currentPageOfIndex + "").convertForms().executeOfNet(getContext(),ListOfDangersModel.GetDangerDatas,new BaseMvp_LocalObjCallBack<BaseReturnData<DangerBean>>(this)
            {
                public void onSuccess(BaseReturnData<DangerBean> dangerBeans)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        currentPageOfIndex++;
                        getViewLayer().finishRefresh();
                        getViewLayer().refreshDatas(dangerBeans.getData());
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

    public void loadMoreDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(ListOfDangersModel.class).
            putForm("index",currentPageOfIndex + "").convertForms().executeOfNet(getContext(),ListOfDangersModel.GetDangerDatas,new BaseMvp_LocalObjCallBack<BaseReturnData<DangerBean>>(this)
            {
                public void onSuccess(BaseReturnData<DangerBean> dangerBeans)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        currentPageOfIndex++;
                        getViewLayer().finishLoadMore();
                        getViewLayer().loadMoreDatas(dangerBeans.getData());
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