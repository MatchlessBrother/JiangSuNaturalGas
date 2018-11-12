package company.naturalgas.client.ui.main.activity.view;

import android.view.View;
import android.view.Window;
import company.naturalgas.client.base.BaseAct;

public class YhtjAct extends BaseAct
{
    @Override
    protected void initStatusBarAddTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected int setLayoutResID() {
        return 0;
    }

    @Override
    protected void initWidgets(View rootView) {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initLogic() {

    }
}