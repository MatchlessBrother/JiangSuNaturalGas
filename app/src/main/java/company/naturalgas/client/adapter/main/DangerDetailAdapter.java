package company.naturalgas.client.adapter.main;

import java.util.List;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import company.naturalgas.client.R;
import android.support.annotation.Nullable;
import com.luck.picture.lib.PictureSelector;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import com.chad.library.adapter.base.BaseViewHolder;
import android.support.v7.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import company.naturalgas.client.bean.main.DangerDetailBean;
import company.naturalgas.client.ui.main.activity.view.PreviewPhotoAct;

public class DangerDetailAdapter extends BaseQuickAdapter<DangerDetailBean.RecordBean,BaseViewHolder>
{
    private Context mContext;
    private GridLayoutManager mGridLayoutManager;
    private DangerDetailImgAdapter mDangerDetailImgAdapter;

    public DangerDetailAdapter(Context context,@Nullable List<DangerDetailBean.RecordBean> data)
    {
        super(R.layout.item_dangerdetail,data);
        mContext = context;
    }

    protected void convert(BaseViewHolder helper,DangerDetailBean.RecordBean recordBean)
    {
        switch(recordBean.getDealType())
        {
            case "0":
            {
                helper.setText(R.id.itemdangerdetail_one_left,"上报时间");
                helper.setText(R.id.itemdangerdetail_one_right,null != recordBean.getCreateTime() ? recordBean.getCreateTime().trim() : "");
                helper.setGone(R.id.itemdangerdetail_two_all,true);
                helper.setText(R.id.itemdangerdetail_two_left,"事件类型 : ");
                helper.setText(R.id.itemdangerdetail_two_right,null != recordBean.getType() ? recordBean.getType().trim() : "");
                helper.setGone(R.id.itemdangerdetail_three,true);
                helper.setText(R.id.itemdangerdetail_three,"问题描述");
                helper.setGone(R.id.itemdangerdetail_four,true);
                helper.setText(R.id.itemdangerdetail_four,null != recordBean.getDealContent() ? recordBean.getDealContent().trim() : "");
                break;
            }
            case "1":
            {
                helper.setText(R.id.itemdangerdetail_one_left,"隐患上报时间");
                helper.setText(R.id.itemdangerdetail_one_right,null != recordBean.getCreateTime() ? recordBean.getCreateTime().trim() : "");
                helper.setGone(R.id.itemdangerdetail_two_all,true);
                helper.setText(R.id.itemdangerdetail_two_left,(null != recordBean.getSAccount() ? recordBean.getSAccount().trim() : "") + "上报给:");
                helper.setText(R.id.itemdangerdetail_two_right,null != recordBean.getRAccount() ? recordBean.getRAccount().trim() : "");
                helper.setGone(R.id.itemdangerdetail_three,false);
                helper.setGone(R.id.itemdangerdetail_four,false);
                break;
            }
            case "2":
            {
                helper.setText(R.id.itemdangerdetail_one_left,"整改时间");
                helper.setText(R.id.itemdangerdetail_one_right,null != recordBean.getCreateTime() ? recordBean.getCreateTime().trim() : "");
                helper.setGone(R.id.itemdangerdetail_two_all,false);
                helper.setGone(R.id.itemdangerdetail_three,true);
                helper.setText(R.id.itemdangerdetail_three,"整改描述");
                helper.setGone(R.id.itemdangerdetail_four,true);
                helper.setText(R.id.itemdangerdetail_four,null != recordBean.getDealContent() ? recordBean.getDealContent().trim() : "");
                break;
            }
            case "3":
            {
                helper.setText(R.id.itemdangerdetail_one_left,"整改派发时间");
                helper.setText(R.id.itemdangerdetail_one_right,null != recordBean.getCreateTime() ? recordBean.getCreateTime().trim() : "");
                helper.setGone(R.id.itemdangerdetail_two_all,true);
                helper.setText(R.id.itemdangerdetail_two_left,(null != recordBean.getSAccount() ? recordBean.getSAccount().trim() : "") + "派发给:");
                helper.setText(R.id.itemdangerdetail_two_right,null != recordBean.getRAccount() ? recordBean.getRAccount().trim() : "");
                helper.setGone(R.id.itemdangerdetail_three,false);
                helper.setGone(R.id.itemdangerdetail_four,false);
                break;
            }
            case "4":
            {
                helper.setText(R.id.itemdangerdetail_one_left,"验收上报时间");
                helper.setText(R.id.itemdangerdetail_one_right,null != recordBean.getCreateTime() ? recordBean.getCreateTime().trim() : "");
                helper.setGone(R.id.itemdangerdetail_two_all,true);
                helper.setText(R.id.itemdangerdetail_two_left,(null != recordBean.getSAccount() ? recordBean.getSAccount().trim() : "") + "上报给:");
                helper.setText(R.id.itemdangerdetail_two_right,null != recordBean.getRAccount() ? recordBean.getRAccount().trim() : "");
                helper.setGone(R.id.itemdangerdetail_three,false);
                helper.setGone(R.id.itemdangerdetail_four,false);
                break;
            }
            case "5":
            {
                helper.setText(R.id.itemdangerdetail_one_left,"验收派发时间");
                helper.setText(R.id.itemdangerdetail_one_right,null != recordBean.getCreateTime() ? recordBean.getCreateTime().trim() : "");
                helper.setGone(R.id.itemdangerdetail_two_all,true);
                helper.setText(R.id.itemdangerdetail_two_left,(null != recordBean.getSAccount() ? recordBean.getSAccount().trim() : "") + "派发给:");
                helper.setText(R.id.itemdangerdetail_two_right,null != recordBean.getRAccount() ? recordBean.getRAccount().trim() : "");
                helper.setGone(R.id.itemdangerdetail_three,false);
                helper.setGone(R.id.itemdangerdetail_four,false);
                break;
            }
            case "6":
            {
                helper.setText(R.id.itemdangerdetail_one_left,"验收时间");
                helper.setText(R.id.itemdangerdetail_one_right,null != recordBean.getCreateTime() ? recordBean.getCreateTime().trim() : "");
                helper.setGone(R.id.itemdangerdetail_two_all,false);
                helper.setGone(R.id.itemdangerdetail_three,true);
                helper.setText(R.id.itemdangerdetail_three,"验收描述");
                helper.setGone(R.id.itemdangerdetail_four,true);
                helper.setText(R.id.itemdangerdetail_four,null != recordBean.getDealContent() ? recordBean.getDealContent().trim() : "");
                break;
            }
            case "7":
            {
                helper.setText(R.id.itemdangerdetail_one_left,"验收拒绝时间");
                helper.setText(R.id.itemdangerdetail_one_right,null != recordBean.getCreateTime() ? recordBean.getCreateTime().trim() : "");
                helper.setGone(R.id.itemdangerdetail_two_all,false);
                helper.setGone(R.id.itemdangerdetail_three,true);
                helper.setText(R.id.itemdangerdetail_three,"验收拒绝描述");
                helper.setGone(R.id.itemdangerdetail_four,true);
                helper.setText(R.id.itemdangerdetail_four,null != recordBean.getDealContent() ? recordBean.getDealContent().trim() : "");
                break;
            }
            default:
            {
                helper.setText(R.id.itemdangerdetail_one_left,"事故关闭时间");
                helper.setText(R.id.itemdangerdetail_one_right,null != recordBean.getCreateTime() ? recordBean.getCreateTime().trim() : "");
                helper.setGone(R.id.itemdangerdetail_two_all,true);
                helper.setText(R.id.itemdangerdetail_two_left,"关闭人 : ");
                helper.setText(R.id.itemdangerdetail_two_right,null != recordBean.getSAccount() ? recordBean.getSAccount().trim() : "");
                helper.setGone(R.id.itemdangerdetail_three,false);
                helper.setGone(R.id.itemdangerdetail_four,false);
                break;
            }
        }
        if(null != recordBean.getFileList() && recordBean.getFileList().size() > 0)
        {
            helper.setGone(R.id.itemdangerdetail_zpsh_all,true);
            mGridLayoutManager = new GridLayoutManager(mContext,3);
            mGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mDangerDetailImgAdapter = new DangerDetailImgAdapter(mContext,recordBean.getFileList());
            ((RecyclerView)helper.getView(R.id.itemdangerdetail_zpsh_recyclerview)).setLayoutManager(mGridLayoutManager);
            ((RecyclerView)helper.getView(R.id.itemdangerdetail_zpsh_recyclerview)).setAdapter(mDangerDetailImgAdapter);
        }
        else
            helper.setGone(R.id.itemdangerdetail_zpsh_all,false);
    }
}