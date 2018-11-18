package company.naturalgas.client.ui.main.activity.view_v;

import company.naturalgas.client.bean.main.SjtjBean;
import company.naturalgas.client.ui.base.BaseMvp_View;

public interface SjtjActivity_V extends BaseMvp_View
{
    void getFailOfSjtjDatas();
    void getSuccessOfSjtjDatas(SjtjBean sjtjBean);
}