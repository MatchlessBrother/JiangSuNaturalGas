package company.naturalgas.client.ui.main.activity.view_v;

import company.naturalgas.client.bean.main.ZlglBean;
import company.naturalgas.client.ui.base.BaseMvp_View;

public interface ZlglAct_V extends BaseMvp_View
{
    void finishRefresh();
    void finishLoadMore();
    void refreshDatas(ZlglBean zlglBean);
    void loadMoreDatas(ZlglBean zlglBean);
}