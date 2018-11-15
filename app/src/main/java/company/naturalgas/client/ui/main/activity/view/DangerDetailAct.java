package company.naturalgas.client.ui.main.activity.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import company.naturalgas.client.R;
import company.naturalgas.client.base.BaseAct;
import company.naturalgas.client.ui.main.activity.view_v.DangerDetailAct_V;
import company.naturalgas.client.ui.main.activity.presenter.DangerDetailPresenter;

public class DangerDetailAct extends BaseAct implements DangerDetailAct_V, View.OnClickListener
{
    private Button mDangerdetailBtnTrue;
    private Button mDangerdetailBtnFalse;
    private RecyclerView mDangerdetailRecyclerview;
    private SwipeRefreshLayout mDangerdetailSwiperefresh;
    private DangerDetailPresenter mDangerDetailPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_dangerdetail;
    }

    protected void initWidgets(View rootView)
    {
        mDangerdetailBtnTrue = (Button)rootView.findViewById(R.id.dangerdetail_btn_true);
        mDangerdetailBtnFalse = (Button)rootView.findViewById(R.id.dangerdetail_btn_false);
        mDangerdetailSwiperefresh = (SwipeRefreshLayout) findViewById(R.id.dangerdetail_swiperefresh);
        mDangerdetailRecyclerview = (RecyclerView)rootView.findViewById(R.id.dangerdetail_recyclerview);
        mDangerdetailSwiperefresh.setEnabled(true);

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
        mDangerdetailSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {

            }
        });
    }
}