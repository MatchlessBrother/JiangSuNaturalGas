package company.naturalgas.client.ui.main.activity.view;

import android.view.View;
import java.util.ArrayList;
import android.content.Intent;
import company.naturalgas.client.R;
import company.naturalgas.client.base.BaseAct;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import company.naturalgas.client.bean.main.DangerBean;
import company.naturalgas.client.adapter.main.ListOfDangersAdapter;
import company.naturalgas.client.ui.main.activity.view_v.ListOfDangersAct_V;
import company.naturalgas.client.ui.main.activity.presenter.ListOfDangersPresenter;

public class ListOfDangersAct extends BaseAct implements ListOfDangersAct_V
{
    private RecyclerView mListOfDangersRecycler;
    private ListOfDangersAdapter mListOfDangersAdapter;
    private ListOfDangersPresenter mListOfDangersPresenter;
    private SwipeRefreshLayout mListOfDangersSwiperefreshlayout;
    public static final int StartOtherActivity = 0x0001;

    protected int setLayoutResID()
    {
        return R.layout.activity_listofdangers;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("隐患列表");
        if(getIntent().getStringExtra("authurl").equals("yhsb"))
        {
            setTitleMoreFont("添加");
            setTitleMoreFontVisible(View.VISIBLE);
        }
        mListOfDangersSwiperefreshlayout = (SwipeRefreshLayout)rootView.findViewById(R.id.listofdangers_swiperefreshlayout);
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
                startActivityForResult(intent,StartOtherActivity);
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
        if(null != getIntent() && null != getIntent().getStringExtra("authurl") && "yhsb".equals(getIntent().getStringExtra("authurl").toLowerCase().trim()))
        {
            Intent intent = new Intent(this,AddProblemAct.class);
            startActivityForResult(intent,StartOtherActivity);
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
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case StartOtherActivity:
            {
                mListOfDangersPresenter.refreshDatas();
                break;
            }
        }
    }
}