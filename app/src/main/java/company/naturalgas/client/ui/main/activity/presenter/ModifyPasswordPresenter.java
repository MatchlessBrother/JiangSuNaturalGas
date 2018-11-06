package company.naturalgas.client.ui.main.activity.presenter;

import company.naturalgas.client.bean.BaseReturnData;
import company.naturalgas.client.ui.base.BaseMvp_Presenter;
import company.naturalgas.client.ui.base.BaseMvp_EntranceOfModel;
import company.naturalgas.client.ui.base.BaseMvp_LocalObjCallBack;
import company.naturalgas.client.ui.main.activity.model.ModifyPasswordModel;
import company.naturalgas.client.ui.main.activity.view_v.ModifyPasswordAct_V;

public class ModifyPasswordPresenter extends BaseMvp_Presenter<ModifyPasswordAct_V>
{
    public void modifyPassword(String oldPassword,String newPassword)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(ModifyPasswordModel.class).
            putForm("oldPassword",oldPassword).putForm("newPassword",newPassword).convertForms().executeOfNet(getContext(),ModifyPasswordModel.ModifyPassword,new BaseMvp_LocalObjCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData baseReturnData)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().successOfModifyPassword();
                    }
                }
            });
        }
    }
}