package company.naturalgas.client.ui.main.activity.view;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

import android.widget.Button;
import company.naturalgas.client.R;
import android.support.v7.widget.RecyclerView;
import company.naturalgas.client.base.BaseAct;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import company.naturalgas.client.bean.main.DangerDetailBean;
import company.naturalgas.client.adapter.main.DangerDetailAdapter;
import company.naturalgas.client.ui.main.activity.view_v.DangerDetailAct_V;
import company.naturalgas.client.ui.main.activity.presenter.DangerDetailPresenter;

public class DangerDetailAct extends BaseAct implements DangerDetailAct_V, View.OnClickListener
{
    private Button mDangerdetailBtnTrue;
    private Button mDangerdetailBtnFalse;
    private RecyclerView mDangerdetailRecyclerview;
    private DangerDetailAdapter mDangerDetailAdapter;
    private SwipeRefreshLayout mDangerdetailSwiperefresh;
    private DangerDetailPresenter mDangerDetailPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_dangerdetail;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("详情");
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
    }

    protected void initLogic()
    {
        mDangerdetailBtnTrue.setOnClickListener(this);
        mDangerdetailBtnFalse.setOnClickListener(this);
        mDangerDetailPresenter.getDetailDatas(getIntent().getStringExtra("id"));
        mDangerdetailSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                mDangerDetailPresenter.getDetailDatas(getIntent().getStringExtra("id"));
            }
        });
    }

    public void getFailOfDangerDetailDatas()
    {

    }

    public void getSuccessOfDangerDetailDatas(DangerDetailBean dangerDetailBeans)
    {
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
    }
}