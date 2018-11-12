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

    protected int setLayoutResID()
    {
        return R.layout.activity_listofdangers;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        switch(getIntent().getStringExtra("code"))
        {
            case "1":
            {
                setTitleMoreFont("添加");
                setTitleContent("检查列表");
                setTitleMoreFontVisible(View.VISIBLE);
                break;
            }
            case "2":setTitleContent("任务列表");break;
            case "3":setTitleContent("任务列表");break;
            case "4":setTitleContent("验收列表");break;
            case "5":setTitleContent("验收列表");break;
            default:setTitleContent("验收列表");break;
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
                switch(getIntent().getStringExtra("code"))
                {
                    case "1":
                    {
                        setTitleMoreFont("添加");
                        setTitleContent("检查列表");
                        setTitleMoreFontVisible(View.VISIBLE);
                        break;
                    }
                    case "2":setTitleContent("任务列表");break;
                    case "3":setTitleContent("任务列表");break;
                    case "4":setTitleContent("验收列表");break;
                    case "5":setTitleContent("验收列表");break;
                    default:setTitleContent("验收列表");break;
                }
                showToast("position : " + position);
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

    protected void onTitleMoreFontClick()
    {
        super.onTitleMoreFontClick();
        if(null != getIntent() && null != getIntent().getStringExtra("code") && "1".equals(getIntent().getStringExtra("code").trim()))
        {
            Intent intent = new Intent(this,AddProblemAct.class);
            startActivity(intent);
        }
    }
}