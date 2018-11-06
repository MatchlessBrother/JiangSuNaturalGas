package company.naturalgas.client.ui.main.activity.view_v;

import company.naturalgas.client.bean.main.MsgBean;
import company.naturalgas.client.ui.base.BaseMvp_View;

public interface MainAct_V extends BaseMvp_View
{
    void signOutSuccess();
    void signOutFailure();
    void finishRefresh();
    void finishLoadMore();
    void refreshDatas(MsgBean msgPageInfo);
    void loadMoreDatas(MsgBean msgPageInfo);
}