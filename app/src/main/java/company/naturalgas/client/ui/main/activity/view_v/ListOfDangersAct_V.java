package company.naturalgas.client.ui.main.activity.view_v;

import company.naturalgas.client.bean.main.DangerBean;
import company.naturalgas.client.ui.base.BaseMvp_View;

public interface ListOfDangersAct_V extends BaseMvp_View
{
    void finishRefresh();
    void finishLoadMore();
    void refreshDatas(DangerBean dangerBeans);
    void loadMoreDatas(DangerBean dangerBeans);
}