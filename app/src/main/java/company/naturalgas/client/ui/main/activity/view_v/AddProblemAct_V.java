package company.naturalgas.client.ui.main.activity.view_v;

import java.util.List;
import company.naturalgas.client.bean.main.FzrBean;
import company.naturalgas.client.bean.main.SjlxBean;
import company.naturalgas.client.bean.main.JlfzrBean;
import company.naturalgas.client.ui.base.BaseMvp_View;

public interface AddProblemAct_V extends BaseMvp_View
{
    void getFailOfFzrDatas();
    void getFailOfSjlxDatas();
    void getFailOfUploadFile();
    void getFailOfJlfzrDatas();
    void getFailOfAcceptDanger();
    void getFailOfUploadDanger();
    void getFailOfRefuseDanger();
    void getFailOfProcessDanger();
    void getSuccessOfUploadDanger();
    void getSuccessOfAcceptDanger();
    void getSuccessOfRefuseDanger();
    void getSuccessOfProcessDanger();
    void getSuccessOfUploadFile(String filePath);
    void getSuccessOfFzrDatas(List<FzrBean> fzrBeans);
    void getSuccessOfSjlxDatas(List<SjlxBean> sjlxBeans);
    void getSuccessOfJlfzrDatas(List<JlfzrBean> jlfzrBeans);
}