package company.naturalgas.client.ui.main.activity.view_v;

import company.naturalgas.client.ui.base.BaseMvp_View;
import company.naturalgas.client.bean.main.MsgDetailBean;

public interface MsgDetailAct_V extends BaseMvp_View
{
    void failOfGetDatas();
    void successOfGetDatas(MsgDetailBean msgDetailBean);
}