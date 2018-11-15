package company.naturalgas.client.ui.main.activity.view_v;

import company.naturalgas.client.ui.base.BaseMvp_View;
import company.naturalgas.client.bean.main.DangerDetailBean;

public interface DangerDetailAct_V extends BaseMvp_View
{
    void getFailOfDangerDetailDatas();
    void getSuccessOfDangerDetailDatas(DangerDetailBean dangerDetailBeans);
}