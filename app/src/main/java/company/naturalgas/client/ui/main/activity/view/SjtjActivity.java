package company.naturalgas.client.ui.main.activity.view;

import java.util.Date;
import android.view.View;
import java.util.Calendar;
import com.google.gson.Gson;
import android.webkit.WebView;
import android.widget.TextView;
import android.content.Context;
import android.util.AttributeSet;
import java.text.SimpleDateFormat;
import java.util.List;

import company.naturalgas.client.R;
import android.widget.LinearLayout;
import me.jessyan.autosize.AutoSize;
import android.webkit.WebViewClient;
import company.naturalgas.client.base.BaseAct;
import android.support.v7.widget.RecyclerView;
import com.bigkoo.pickerview.view.TimePickerView;
import company.naturalgas.client.widget.EchartView;
import android.support.v4.widget.SwipeRefreshLayout;
import company.naturalgas.client.bean.main.SjtjBean;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import company.naturalgas.client.ui.main.activity.view_v.SjtjActivity_V;
import company.naturalgas.client.ui.main.activity.presenter.SjtjPresenter;

public class SjtjActivity extends BaseAct implements SjtjActivity_V,View.OnClickListener
{
    private Date mEndTimeDate;
    private Date mStartTimeDate;
    private TextView mSjtjKssjTv;
    private TextView mSjtjJssjTv;
    private EchartView mSjtjWebview;
    private TextView mSjtjWebviewTs;
    private TextView mSjtjYhpcYhNum;
    private LinearLayout mSjtjYhpcAll;
    private TextView mSjtjYhpcYhzgNum;
    private TextView mSjtjYhpcYhysNum;
    private LinearLayout mSjtjKssjAll;
    private LinearLayout mSjtjJssjAll;
    private LinearLayout mSjtjSearchBtn;
    private SjtjPresenter mSjtjPresenter;
    private RecyclerView mSjtjRecyclerview;
    private TimePickerView mEndTimePickerView;
    private SimpleDateFormat mSimpleDateFormat;
    private TimePickerView mStartTimePickerView;
    private SwipeRefreshLayout mSjtjSwiperefreshlayout;

    public View onCreateView(String name, Context context, AttributeSet attrs)
    {
        AutoSize.autoConvertDensityOfGlobal(this);
        return super.onCreateView(name, context, attrs);
    }

    protected int setLayoutResID()
    {
        return R.layout.activity_sjtj;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("隐患统计");
        mSjtjSwiperefreshlayout = (SwipeRefreshLayout)rootView.findViewById(R.id.sjtj_swiperefreshlayout);
        mSjtjRecyclerview = (RecyclerView)rootView.findViewById(R.id.sjtj_recyclerview);
        mSjtjKssjAll = (LinearLayout)rootView.findViewById(R.id.sjtj_kssj_all);
        mSjtjKssjTv = (TextView)rootView.findViewById(R.id.sjtj_kssj_tv);
        mSjtjJssjAll = (LinearLayout)rootView.findViewById(R.id.sjtj_jssj_all);
        mSjtjJssjTv = (TextView)rootView.findViewById(R.id.sjtj_jssj_tv);
        mSjtjSearchBtn = (LinearLayout)rootView.findViewById(R.id.sjtj_search_btn);
        mSjtjYhpcAll = (LinearLayout)rootView.findViewById(R.id.sjtj_yhpc_all);
        mSjtjYhpcYhNum = (TextView)rootView.findViewById(R.id.sjtj_yhpc_yh_num);
        mSjtjYhpcYhzgNum = (TextView)rootView.findViewById(R.id.sjtj_yhpc_yhzg_num);
        mSjtjYhpcYhysNum = (TextView)rootView.findViewById(R.id.sjtj_yhpc_yhys_num);
        mSjtjWebviewTs = (TextView)rootView.findViewById(R.id.sjtj_echartview_ts);
        mSjtjWebview = (EchartView)rootView.findViewById(R.id.sjtj_echartview);
        mSjtjSwiperefreshlayout.setEnabled(true);
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        /******************************************************************************************/
        Calendar startDateRange = Calendar.getInstance();
        startDateRange.set(2000,0,1);
        Calendar endDateRange = Calendar.getInstance();
        endDateRange.setTime(new Date());
        Calendar endTimeCalendar = Calendar.getInstance();
        endTimeCalendar.setTime(new Date());
        Calendar startTimeCalendar = Calendar.getInstance();
        startTimeCalendar.setTime(new Date());
        /******************************************************************************************/
        mEndTimePickerView = new TimePickerBuilder(mActivity,new OnTimeSelectListener()
        {
            public void onTimeSelect(Date date, View view)
            {
                mEndTimeDate = date;
                mSjtjJssjTv.setText(mSimpleDateFormat.format(mEndTimeDate));
            }
        }).setTitleText("结束日期").setTitleSize(33)
                .setSubmitText("确定") .setCancelText("取消")
                .setSubCalSize(28).setContentTextSize(18)
                .setBgColor(getResources().getColor(R.color.white))
                .setTitleColor(getResources().getColor(R.color.white))
                .setSubmitColor(getResources().getColor(R.color.white))
                .setCancelColor(getResources().getColor(R.color.white))
                .setBackgroundId(getResources().getColor(R.color.white))
                .setTitleBgColor(getResources().getColor(R.color.colorPrimary))
                .setDividerColor(getResources().getColor(R.color.default_font_gray))
                .setType(new boolean[]{ true , true , true , false , false , false })
                .setOutSideCancelable(true).isCenterLabel(false).setLabel("年","月","日","时","分","秒")
                .setRangDate(startDateRange,endDateRange).setDate(endTimeCalendar).isCyclic(true).build();

        mStartTimePickerView = new TimePickerBuilder(mActivity,new OnTimeSelectListener()
        {
            public void onTimeSelect(Date date, View view)
            {
                mStartTimeDate = date;
                mSjtjKssjTv.setText(mSimpleDateFormat.format(mStartTimeDate));
            }
        }).setTitleText("开始日期").setTitleSize(33)
                .setSubmitText("确定") .setCancelText("取消")
                .setSubCalSize(28).setContentTextSize(18)
                .setBgColor(getResources().getColor(R.color.white))
                .setTitleColor(getResources().getColor(R.color.white))
                .setSubmitColor(getResources().getColor(R.color.white))
                .setCancelColor(getResources().getColor(R.color.white))
                .setBackgroundId(getResources().getColor(R.color.white))
                .setTitleBgColor(getResources().getColor(R.color.colorPrimary))
                .setDividerColor(getResources().getColor(R.color.default_font_gray))
                .setType(new boolean[]{ true , true , true , false , false , false })
                .setOutSideCancelable(true).isCenterLabel(false).setLabel("年","月","日","时","分","秒")
                .setRangDate(startDateRange,endDateRange).setDate(startTimeCalendar).isCyclic(true).build();
    }

    protected void initDatas()
    {
        mSjtjPresenter = new SjtjPresenter();
        bindBaseMvpPresenter(mSjtjPresenter);
    }

    protected void initLogic()
    {
        mEndTimeDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -5);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        mStartTimeDate = calendar.getTime();
        mSjtjJssjTv.setText(mSimpleDateFormat.format(mEndTimeDate));
        mSjtjKssjTv.setText(mSimpleDateFormat.format(mStartTimeDate));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(mEndTimeDate);
        mEndTimePickerView.setDate(endCalendar);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(mStartTimeDate);
        mStartTimePickerView.setDate(startCalendar);
        mSjtjWebviewTs.setText("隐患整改分析图");

        mSjtjKssjAll.setOnClickListener(this);
        mSjtjJssjAll.setOnClickListener(this);
        mSjtjSearchBtn.setOnClickListener(this);
        mSjtjSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                mEndTimeDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.MONTH, -5);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                mStartTimeDate = calendar.getTime();
                mSjtjJssjTv.setText(mSimpleDateFormat.format(mEndTimeDate));
                mSjtjKssjTv.setText(mSimpleDateFormat.format(mStartTimeDate));
                Calendar endCalendar = Calendar.getInstance();
                endCalendar.setTime(mEndTimeDate);
                mEndTimePickerView.setDate(endCalendar);
                Calendar startCalendar = Calendar.getInstance();
                startCalendar.setTime(mStartTimeDate);
                mStartTimePickerView.setDate(startCalendar);
                mSjtjPresenter.getHztjDatas(mSimpleDateFormat.format(mStartTimeDate),mSimpleDateFormat.format(mEndTimeDate));
            }
        });
        mSjtjPresenter.getHztjDatas(mSimpleDateFormat.format(mStartTimeDate),mSimpleDateFormat.format(mEndTimeDate));
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch(view.getId())
        {
            case R.id.sjtj_kssj_all:
            {
                Calendar calendar = Calendar.getInstance();
                if(null != mStartTimeDate)
                    calendar.setTime(mStartTimeDate);
                else
                    calendar.setTime(new Date(  ));
                mStartTimePickerView.setDate(calendar);
                mStartTimePickerView.show();
                break;
            }
            case R.id.sjtj_jssj_all:
            {
                Calendar calendar = Calendar.getInstance();
                if(null != mEndTimeDate)
                    calendar.setTime(mEndTimeDate);
                else
                    calendar.setTime(new Date(  ));
                mEndTimePickerView.setDate(calendar);
                mEndTimePickerView.show();
                break;
            }
            case R.id.sjtj_search_btn:
            {
                mSjtjPresenter.getHztjDatas(mSimpleDateFormat.format(mStartTimeDate),mSimpleDateFormat.format(mEndTimeDate));
                break;
            }
        }
    }

    public void getFailOfSjtjDatas()
    {
        mSjtjSwiperefreshlayout.setRefreshing(false);

    }

    public void initBarGraph(final List<SjtjBean.FxtBean> fxtBeans)
    {
        mSjtjWebview.loadUrl("file:///android_asset/stat.html");
        if(null != mSjtjWebview)
        {
            mSjtjWebview.setWebViewClient(new WebViewClient()
            {
                public void onPageFinished(WebView view, String url)
                {
                    super.onPageFinished(view, url);
                    mSjtjWebview.refreshEchartsViewWithDataJson(new Gson().toJson(fxtBeans));
                }
            });
        }
    }

    public void getSuccessOfSjtjDatas(SjtjBean sjtjBean)
    {
        mSjtjWebviewTs.setText(null != sjtjBean && null != sjtjBean.getFxt() && sjtjBean.getFxt().size() > 0 ? "近" + sjtjBean.getFxt().size() + "月隐患整改分析图" : "隐患整改分析图");
        mSjtjYhpcYhNum.setText(null != sjtjBean && null != sjtjBean.getYh() && !"".equals(sjtjBean.getYh().trim()) ? sjtjBean.getYh().trim() : "0");
        mSjtjYhpcYhzgNum.setText(null != sjtjBean && null != sjtjBean.getYzg() && !"".equals(sjtjBean.getYzg().trim()) ? sjtjBean.getYzg().trim() : "0");
        mSjtjYhpcYhysNum.setText(null != sjtjBean && null != sjtjBean.getYys() && !"".equals(sjtjBean.getYys().trim()) ? sjtjBean.getYys().trim() : "0");
        mSjtjSwiperefreshlayout.setRefreshing(false);
        initBarGraph(sjtjBean.getFxt());
    }
}