package company.naturalgas.client.ui.main.activity.view_v;

import java.util.List;
import company.naturalgas.client.bean.main.SgaqyBean;
import company.naturalgas.client.bean.main.SgjlyBean;
import company.naturalgas.client.ui.base.BaseMvp_View;
import company.naturalgas.client.bean.main.DangerDetailBean;

public interface DangerDetailAct_V extends BaseMvp_View
{
    void closeFailOfDanger();
    void chooseFailOfSgjlr();
    void chooseFailOfSgaqy();
    void getFailOfSgjlrDatas();
    void getFailOfSgaqyDatas();
    void chooseSuccessOfSgaqy();
    void chooseSuccessOfSgjlr();
    void closeSuccessOfDanger();
    void getFailOfDangerDetailDatas();
    void getSuccessOfSgjlrDatas(List<SgjlyBean> sgjlyBeans);
    void getSuccessOfSgaqyDatas(List<SgaqyBean> sgaqyBeans);
    void getSuccessOfDangerDetailDatas(DangerDetailBean dangerDetailBeans);
}