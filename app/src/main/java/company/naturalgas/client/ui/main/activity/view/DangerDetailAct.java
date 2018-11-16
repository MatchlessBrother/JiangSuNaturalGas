package company.naturalgas.client.ui.main.activity.view;

import java.util.List;
import android.view.View;
import java.util.ArrayList;
import android.widget.Button;
import android.content.Intent;
import company.naturalgas.client.R;
import android.support.v7.widget.RecyclerView;
import company.naturalgas.client.base.BaseAct;
import company.naturalgas.client.bean.main.MainInfo;
import android.support.v4.widget.SwipeRefreshLayout;
import com.bigkoo.pickerview.view.OptionsPickerView;
import android.support.v7.widget.LinearLayoutManager;
import company.naturalgas.client.bean.main.SgaqyBean;
import company.naturalgas.client.bean.main.SgjlyBean;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import company.naturalgas.client.bean.main.DangerDetailBean;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import company.naturalgas.client.adapter.main.DangerDetailAdapter;
import company.naturalgas.client.ui.main.activity.view_v.DangerDetailAct_V;
import company.naturalgas.client.ui.main.activity.presenter.DangerDetailPresenter;

public class DangerDetailAct extends BaseAct implements DangerDetailAct_V, View.OnClickListener
{
    private Button mDangerdetailBtnSgcl;
    private Button mDangerdetailBtnTrue;
    private Button mDangerdetailBtnFalse;
    /************************************************/
    private List<SgaqyBean> mSgaqyOptionBeans;
    private List<SgjlyBean> mSgjlrOptionBeans;
    private OptionsPickerView mSgaqyOrSgjlrOptionsPickerView;
    private int mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex;
    /************************************************/
    private DangerDetailBean mDangerDetailBeans;
    private RecyclerView mDangerdetailRecyclerview;
    private DangerDetailAdapter mDangerDetailAdapter;
    private SwipeRefreshLayout mDangerdetailSwiperefresh;
    private DangerDetailPresenter mDangerDetailPresenter;
    public static final int StartAddProblemAct = 0x0001;

    protected int setLayoutResID()
    {
        return R.layout.activity_dangerdetail;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("详情");
        mDangerdetailBtnSgcl = (Button)rootView.findViewById(R.id.dangerdetail_btn_sgcl);
        mDangerdetailBtnTrue = (Button)rootView.findViewById(R.id.dangerdetail_btn_true);
        mDangerdetailBtnFalse = (Button)rootView.findViewById(R.id.dangerdetail_btn_false);
        mDangerdetailSwiperefresh = (SwipeRefreshLayout) findViewById(R.id.dangerdetail_swiperefresh);
        mDangerdetailRecyclerview = (RecyclerView)rootView.findViewById(R.id.dangerdetail_recyclerview);
        mDangerDetailAdapter = new DangerDetailAdapter(this,new ArrayList<DangerDetailBean.RecordBean>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDangerdetailRecyclerview.setLayoutManager(linearLayoutManager);
        mDangerdetailRecyclerview.setAdapter(mDangerDetailAdapter);
        mDangerdetailSwiperefresh.setEnabled(true);
        mDangerDetailAdapter.setEnableLoadMore(false);
    }

    protected void initDatas()
    {
        mDangerDetailPresenter = new DangerDetailPresenter();
        bindBaseMvpPresenter(mDangerDetailPresenter);
        mSgaqyOptionBeans = new ArrayList<>();
        mSgjlrOptionBeans = new ArrayList<>();
        mDangerDetailBeans = null;
    }

    protected void initLogic()
    {
        mDangerdetailBtnTrue.setOnClickListener(this);
        mDangerdetailBtnFalse.setOnClickListener(this);
        mDangerdetailBtnSgcl.setOnClickListener(this);
        mDangerDetailPresenter.getDetailDatas(getIntent().getStringExtra("id"));
        mDangerdetailSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                mDangerDetailPresenter.getDetailDatas(getIntent().getStringExtra("id"));
            }
        });
    }

    public void onClick(View view)
    {
        super.onClick(view);
        Intent intent = new Intent(this,AddProblemAct.class);
        switch(view.getId())
        {
            case R.id.dangerdetail_btn_sgcl:
            {
                intent.putExtra("processtype","sgcl");
                break;
            }
            case R.id.dangerdetail_btn_true:
            {
                intent.putExtra("processtype","yscl");
                break;
            }
            case R.id.dangerdetail_btn_false:
            {
                intent.putExtra("processtype","jjcl");
                break;
            }
        }
        startActivityForResult(intent,StartAddProblemAct);
    }

    public void getFailOfDangerDetailDatas()
    {

    }

    protected void onTitleMoreFontClick()
    {
        super.onTitleMoreFontClick();
        if(null != mDangerDetailBeans)
        {
            switch(mDangerDetailBeans.getSgms().getStatus())
            {
                case "1000":
                case "4001"://施工安全员
                {
                    if(null != mSgaqyOptionBeans && mSgaqyOptionBeans.size() > 0)
                    {
                        mSgaqyOrSgjlrOptionsPickerView.setSelectOptions(mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex);
                        mSgaqyOrSgjlrOptionsPickerView.show();
                    }
                    else
                        mDangerDetailPresenter.getSgaqyDatas();
                    break;
                }
                case "2000"://施工监理员
                {
                    if(null != mSgjlrOptionBeans && mSgjlrOptionBeans.size() > 0)
                    {
                        mSgaqyOrSgjlrOptionsPickerView.setSelectOptions(mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex);
                        mSgaqyOrSgjlrOptionsPickerView.show();
                    }
                    else
                        mDangerDetailPresenter.getSgjlrDatas();
                    break;
                }
            }
        }
    }

    public void chooseFailOfSgjlr()
    {

    }

    public void chooseFailOfSgaqy()
    {

    }

    public void chooseSuccessOfSgaqy()
    {
        showToast("派发成功");
        setTitleMoreFont("");
        setTitleMoreFontVisible(View.GONE);
        mDangerdetailBtnSgcl.setVisibility(View.GONE);
        mDangerdetailBtnTrue.setVisibility(View.GONE);
        mDangerdetailBtnFalse.setVisibility(View.GONE);
        mDangerDetailPresenter.getDetailDatas(getIntent().getStringExtra("id"));
    }

    public void chooseSuccessOfSgjlr()
    {
        showToast("派发成功");
        setTitleMoreFont("");
        setTitleMoreFontVisible(View.GONE);
        mDangerdetailBtnSgcl.setVisibility(View.GONE);
        mDangerdetailBtnTrue.setVisibility(View.GONE);
        mDangerdetailBtnFalse.setVisibility(View.GONE);
        mDangerDetailPresenter.getDetailDatas(getIntent().getStringExtra("id"));
    }

    public void getFailOfSgjlrDatas()
    {

    }

    public void getFailOfSgaqyDatas()
    {

    }

    public void getSuccessOfSgjlrDatas(List<SgjlyBean> sgjlyBeans)
    {
        mSgaqyOrSgjlrOptionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener()
        {
            public void onOptionsSelect(int options1Index, int options2Index, int options3Index, View view)
            {
                mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex = options1Index;
                mSgaqyOrSgjlrOptionsPickerView.setSelectOptions(mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex);
                mDangerDetailPresenter.chooseSgjlr(mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex > -1 && mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex < mSgjlrOptionBeans.size() ?
                mSgjlrOptionBeans.get(mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex).getId().trim() : "",(null != mDangerDetailBeans && null != mDangerDetailBeans.getSgms() ? mDangerDetailBeans.getSgms().getId() : ""));
            }
        }) .setTitleText("选择施工监理人").setLabels("","","").setTitleSize(33)
                .setSubmitText("确定") .setCancelText("取消")
                .setSubCalSize(28).setContentTextSize(18)
                .setBgColor(getResources().getColor(R.color.white))
                .setTitleColor(getResources().getColor(R.color.white))
                .setSubmitColor(getResources().getColor(R.color.white))
                .setCancelColor(getResources().getColor(R.color.white))
                .setBackgroundId(getResources().getColor(R.color.white))
                .setTitleBgColor(getResources().getColor(R.color.colorPrimary))
                .setDividerColor(getResources().getColor(R.color.default_font_gray))
                .setOutSideCancelable(true).isRestoreItem(false).isCenterLabel(false)
                .setCyclic(false,false,false).setSelectOptions(0, 0, 0).build();

        mSgjlrOptionBeans = sgjlyBeans;
        mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex = mSgjlrOptionBeans.size() > 0 ? -1 : -1;
        mSgaqyOrSgjlrOptionsPickerView.setNPicker(mSgjlrOptionBeans,null,null);
        if(mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex > -1 && mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex < mSgjlrOptionBeans.size())mSgaqyOrSgjlrOptionsPickerView.setSelectOptions(mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex);
        mSgaqyOrSgjlrOptionsPickerView.show();
    }

    public void getSuccessOfSgaqyDatas(List<SgaqyBean> sgaqyBeans)
    {
        mSgaqyOrSgjlrOptionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener()
        {
            public void onOptionsSelect(int options1Index, int options2Index, int options3Index, View view)
            {
                mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex = options1Index;
                mSgaqyOrSgjlrOptionsPickerView.setSelectOptions(mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex);
                mDangerDetailPresenter.chooseSgaqy(mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex > -1 && mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex < mSgaqyOptionBeans.size() ?
                mSgaqyOptionBeans.get(mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex).getId().trim() : "",(null != mDangerDetailBeans && null != mDangerDetailBeans.getSgms() ? mDangerDetailBeans.getSgms().getId() : ""));
            }
        }) .setTitleText("选择施工安全员").setLabels("","","").setTitleSize(33)
                .setSubmitText("确定") .setCancelText("取消")
                .setSubCalSize(28).setContentTextSize(18)
                .setBgColor(getResources().getColor(R.color.white))
                .setTitleColor(getResources().getColor(R.color.white))
                .setSubmitColor(getResources().getColor(R.color.white))
                .setCancelColor(getResources().getColor(R.color.white))
                .setBackgroundId(getResources().getColor(R.color.white))
                .setTitleBgColor(getResources().getColor(R.color.colorPrimary))
                .setDividerColor(getResources().getColor(R.color.default_font_gray))
                .setOutSideCancelable(true).isRestoreItem(false).isCenterLabel(false)
                .setCyclic(false,false,false).setSelectOptions(0, 0, 0).build();

        mSgaqyOptionBeans = sgaqyBeans;
        mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex = mSgaqyOptionBeans.size() > 0 ? -1 : -1;
        mSgaqyOrSgjlrOptionsPickerView.setNPicker(mSgaqyOptionBeans,null,null);
        if(mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex > -1 && mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex < mSgaqyOptionBeans.size())mSgaqyOrSgjlrOptionsPickerView.setSelectOptions(mCurrentSelectedSgaqyOrSgjlrOptionItemOfIndex);
        mSgaqyOrSgjlrOptionsPickerView.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(null != data)
        {
            switch(requestCode)
            {
                case StartAddProblemAct:
                {
                    setTitleMoreFont("");
                    setTitleMoreFontVisible(View.GONE);
                    mDangerdetailBtnSgcl.setVisibility(View.GONE);
                    mDangerdetailBtnTrue.setVisibility(View.GONE);
                    mDangerdetailBtnFalse.setVisibility(View.GONE);
                    mDangerDetailPresenter.getDetailDatas(getIntent().getStringExtra("id"));
                }
            }
        }
    }

    public void getSuccessOfDangerDetailDatas(DangerDetailBean dangerDetailBeans)
    {
        mDangerDetailBeans = dangerDetailBeans;
        mDangerdetailSwiperefresh.setRefreshing(false);
        if(null != dangerDetailBeans.getRecord() && dangerDetailBeans.getRecord().size() > 0)
           dangerDetailBeans.getRecord().get(0).setFileList(new ArrayList<DangerDetailBean.RecordBean.FileListBeanX>());
        DangerDetailBean.SgmsBean sgmsBean = dangerDetailBeans.getSgms();
        DangerDetailBean.RecordBean recordBean = new DangerDetailBean.RecordBean();
        recordBean.setDealContent(sgmsBean.getDescription());
        recordBean.setCreateTime(sgmsBean.getCreateTime());
        recordBean.setType(sgmsBean.getType());
        recordBean.setFileList(sgmsBean.getFileList());
        recordBean.setDealType("0");
        dangerDetailBeans.getRecord().add(0,recordBean);
        mDangerDetailAdapter.setNewData(dangerDetailBeans.getRecord());
        /******************************************************************************************/
        StringBuffer stringBuffer = new StringBuffer();
        List<MainInfo.MenuBean.YhpcBean> yhpcBeans = getBaseApp().getMainInfo().getMenu().getYhpc();
        List<MainInfo.MenuBean.YhpcBean> qtywBeans = getBaseApp().getMainInfo().getMenu().getQtyw();
        for(int index = 0;index < yhpcBeans.size();index++)
            stringBuffer.append(yhpcBeans.get(index).getFunctionAuths());
        for(int index = 0;index < qtywBeans.size();index++)
            stringBuffer.append(qtywBeans.get(index).getFunctionAuths());
        String functions = stringBuffer.toString();
        switch(dangerDetailBeans.getSgms().getStatus())
        {
            case "1000":
            {
                if(functions.contains("sgcl"))
                {
                    mDangerdetailBtnSgcl.setVisibility(View.VISIBLE);
                    mDangerdetailBtnTrue.setVisibility(View.VISIBLE);
                    mDangerdetailBtnFalse.setVisibility(View.VISIBLE);
                    if(functions.contains("sgpf"))
                    {
                        setTitleMoreFont("派发");
                        setTitleMoreFontVisible(View.VISIBLE);
                    }
                }
                else
                {
                    if(functions.contains("sgpf"))
                    {
                        setTitleMoreFont("派发");
                        setTitleMoreFontVisible(View.VISIBLE);
                    }
                    mDangerdetailBtnSgcl.setVisibility(View.GONE);
                    mDangerdetailBtnTrue.setVisibility(View.GONE);
                    mDangerdetailBtnFalse.setVisibility(View.GONE);
                }
                break;
            }
            case "1001":
            {
                if(functions.contains("sgcl"))
                {
                    if(functions.contains("sgpf"))
                    {
                        mDangerdetailBtnSgcl.setVisibility(View.GONE);
                        mDangerdetailBtnTrue.setVisibility(View.GONE);
                        mDangerdetailBtnFalse.setVisibility(View.GONE);
                    }
                    else
                    {
                        mDangerdetailBtnSgcl.setVisibility(View.VISIBLE);
                        mDangerdetailBtnTrue.setVisibility(View.VISIBLE);
                        mDangerdetailBtnFalse.setVisibility(View.VISIBLE);
                    }
                }
                else
                {
                    mDangerdetailBtnSgcl.setVisibility(View.GONE);
                    mDangerdetailBtnTrue.setVisibility(View.GONE);
                    mDangerdetailBtnFalse.setVisibility(View.GONE);
                }
                break;
            }
            case "2000":
            {
                if(functions.contains("yscl"))
                {
                    mDangerdetailBtnSgcl.setVisibility(View.GONE);
                    mDangerdetailBtnTrue.setVisibility(View.VISIBLE);
                    mDangerdetailBtnFalse.setVisibility(View.VISIBLE);
                    if(functions.contains("yspf"))
                    {
                        setTitleMoreFont("派发");
                        setTitleMoreFontVisible(View.VISIBLE);
                    }
                }
                else
                {
                    if(functions.contains("yspf"))
                    {
                        setTitleMoreFont("派发");
                        setTitleMoreFontVisible(View.VISIBLE);
                    }
                    mDangerdetailBtnSgcl.setVisibility(View.GONE);
                    mDangerdetailBtnTrue.setVisibility(View.GONE);
                    mDangerdetailBtnFalse.setVisibility(View.GONE);
                }
                break;
            }
            case "3001":
            {
                if(functions.contains("yscl"))
                {
                    mDangerdetailBtnSgcl.setVisibility(View.GONE);
                    mDangerdetailBtnTrue.setVisibility(View.VISIBLE);
                    mDangerdetailBtnFalse.setVisibility(View.VISIBLE);
                }
                if(functions.contains("yspf"))
                {
                    mDangerdetailBtnSgcl.setVisibility(View.GONE);
                    mDangerdetailBtnTrue.setVisibility(View.GONE);
                    mDangerdetailBtnFalse.setVisibility(View.GONE);
                }
                break;
            }
            case "4001":
            {
                if(functions.contains("sgcl"))
                {
                    mDangerdetailBtnSgcl.setVisibility(View.VISIBLE);
                    mDangerdetailBtnTrue.setVisibility(View.VISIBLE);
                    mDangerdetailBtnFalse.setVisibility(View.VISIBLE);
                    if(functions.contains("sgpf"))
                    {
                        setTitleMoreFont("派发");
                        setTitleMoreFontVisible(View.VISIBLE);
                    }
                }
                else
                {
                    if(functions.contains("sgpf"))
                    {
                        setTitleMoreFont("派发");
                        setTitleMoreFontVisible(View.VISIBLE);
                    }
                    mDangerdetailBtnSgcl.setVisibility(View.GONE);
                    mDangerdetailBtnTrue.setVisibility(View.GONE);
                    mDangerdetailBtnFalse.setVisibility(View.GONE);
                }
                break;
            }
            case "5000":
            {
                if(functions.contains("qhsegb"))
                {
                    mDangerdetailBtnSgcl.setText("关闭");
                    mDangerdetailBtnSgcl.setVisibility(View.VISIBLE);
                    mDangerdetailBtnTrue.setVisibility(View.VISIBLE);
                    mDangerdetailBtnFalse.setVisibility(View.VISIBLE);
                }
                else
                {
                    mDangerdetailBtnSgcl.setVisibility(View.GONE);
                    mDangerdetailBtnTrue.setVisibility(View.GONE);
                    mDangerdetailBtnFalse.setVisibility(View.GONE);
                }
                break;
            }
            case "6000":
            {
                mDangerdetailBtnSgcl.setVisibility(View.GONE);
                mDangerdetailBtnTrue.setVisibility(View.GONE);
                mDangerdetailBtnFalse.setVisibility(View.GONE);
                break;
            }
        }
    }
}