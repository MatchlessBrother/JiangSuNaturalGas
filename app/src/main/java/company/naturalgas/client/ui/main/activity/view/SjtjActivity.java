package company.naturalgas.client.ui.main.activity.view;

import android.view.View;
import java.util.ArrayList;
/**setTitleContent("汇总统计");*/
import android.content.Intent;
import company.naturalgas.client.R;
import company.naturalgas.client.base.BaseAct;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import company.naturalgas.client.bean.main.DangerBean;
import company.naturalgas.client.adapter.main.ListOfDangersAdapter;
import company.naturalgas.client.ui.main.activity.view_v.ListOfDangersAct_V;
import company.naturalgas.client.ui.main.activity.presenter.ListOfDangersPresenter;

public class SjtjActivity extends BaseAct implements ListOfDangersAct_V
{
    private LinearLayout mSjtjKssjAll;
    private TextView mSjtjKssjTv;
    private LinearLayout mSjtjJssjAll;
    private TextView mSjtjJssjTv;
    private LinearLayout mSjtjSearchBtn;
    private RecyclerView mSjtjRecyclerview;
    private TextView mSjtjWebviewTs;
    private WebView mSjtjWebview;
    private
    private SwipeRefreshLayout mSjtjSwiperefreshlayout;

    protected int setLayoutResID()
    {
        return R.layout.activity_sjtj;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        mSjtjSwiperefreshlayout = (SwipeRefreshLayout)rootView.findViewById(R.id.sjtj_swiperefreshlayout);
        mSjtjKssjAll = (LinearLayout)rootView.findViewById(R.id.sjtj_kssj_all);
        mSjtjKssjTv = (TextView)rootView.findViewById(R.id.sjtj_kssj_tv);
        mSjtjJssjAll = (LinearLayout)rootView.findViewById(R.id.sjtj_jssj_all);
        mSjtjJssjTv = (TextView)rootView.findViewById(R.id.sjtj_jssj_tv);
        mSjtjSearchBtn = (LinearLayout)rootView.findViewById(R.id.sjtj_search_btn);
        mSjtjRecyclerview = (RecyclerView)rootView.findViewById(R.id.sjtj_recyclerview);
        mSjtjWebviewTs = (TextView)rootView.findViewById(R.id.sjtj_webview_ts);
        mSjtjWebview = (WebView)rootView.findViewById(R.id.sjtj_webview);

        mSjtjSwiperefreshlayout.setEnabled(true);



        mListOfDangersAdapter = new ListOfDangersAdapter(mActivity,new ArrayList<DangerBean.RecordsBean>());
        mListOfDangersRecycler = (RecyclerView)rootView.findViewById(R.id.listofdangers_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListOfDangersRecycler.setLayoutManager(linearLayoutManager);
        mListOfDangersRecycler.setAdapter(mListOfDangersAdapter);
        mListOfDangersSwiperefreshlayout.setEnabled(true);
        mListOfDangersAdapter.setEnableLoadMore(true);
    }

    protected void initDatas()
    {
        mListOfDangersPresenter = new ListOfDangersPresenter();
        bindBaseMvpPresenter(mListOfDangersPresenter);
    }

    protected void initLogic()
    {
        mListOfDangersPresenter.refreshDatas();
        mListOfDangersSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                mListOfDangersPresenter.refreshDatas();
            }
        });

        mListOfDangersAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener()
        {
            public void onLoadMoreRequested()
            {
                mListOfDangersPresenter.loadMoreDatas();
            }
        },mListOfDangersRecycler);

        mListOfDangersAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                Intent intent = new Intent(ListOfDangersAct.this,DangerDetailAct.class);
                intent.putExtra("code",getBaseApp().getMainInfo().getRole().getCode());
                intent.putExtra("id",mListOfDangersAdapter.getData().get(position).getId());
                startActivity(intent);
            }
        });
    }

    public void finishRefresh()
    {
        mListOfDangersSwiperefreshlayout.setRefreshing(false);

    }

    public void finishLoadMore()
    {
        mListOfDangersAdapter.loadMoreComplete();

    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch(view.getId())
        {

        }
    }

    protected void onTitleMoreFontClick()
    {
        super.onTitleMoreFontClick();
        if(null != getIntent() && null != getIntent().getStringExtra("code") && "1".equals(getIntent().getStringExtra("code").trim()))
        {
            Intent intent = new Intent(this,AddProblemAct.class);
            startActivity(intent);
        }
    }

    public void refreshDatas(DangerBean dangerBeans)
    {
        mListOfDangersAdapter.setNewData(dangerBeans.getRecords());
        if(dangerBeans.getRecords().size() < dangerBeans.getSize())
            mListOfDangersAdapter.setEnableLoadMore(false);
        else
            mListOfDangersAdapter.setEnableLoadMore(true);
    }

    public void loadMoreDatas(DangerBean dangerBeans)
    {
        mListOfDangersAdapter.addData(dangerBeans.getRecords());
        mListOfDangersAdapter.notifyDataSetChanged();
        if(dangerBeans.getRecords().size() < dangerBeans.getSize())
            mListOfDangersAdapter.setEnableLoadMore(false);
        else
            mListOfDangersAdapter.setEnableLoadMore(true);
    }*/
}
}