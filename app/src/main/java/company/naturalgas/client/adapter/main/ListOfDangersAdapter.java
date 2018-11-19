package company.naturalgas.client.adapter.main;

import java.util.Date;
import java.util.List;
import android.content.Context;
import java.text.SimpleDateFormat;
import company.naturalgas.client.R;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import company.naturalgas.client.bean.main.DangerBean;

public class ListOfDangersAdapter extends BaseQuickAdapter<DangerBean.RecordsBean,BaseViewHolder>
{
    private Date mDate;
    private Context mContext;
    private SimpleDateFormat mSimpleDateFormat;

    public ListOfDangersAdapter(Context context,@Nullable List<DangerBean.RecordsBean> data)
    {
        super(R.layout.item_danger, data);
        mDate = new Date();
        mContext = context;
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    protected void convert(BaseViewHolder helper, DangerBean.RecordsBean recordsBean)
    {
        switch(recordsBean.getCode())
        {
            case "5000":
            case "6000":helper.setTextColor(R.id.itemdanger_status,mContext.getResources().getColor(R.color.default_font_gray));break;
            default:helper.setTextColor(R.id.itemdanger_status,mContext.getResources().getColor(R.color.red));break;
        }
        /**mDate.setTime(Long.valueOf(recordsBean.getCreateTime().trim()));
        helper.setText(R.id.itemdanger_time,"时间 : " + mSimpleDateFormat.format(mDate));*/
        helper.setText(R.id.itemdanger_type,null != recordsBean.getType() ? recordsBean.getType().trim() : "");
        helper.setText(R.id.itemdanger_status,null != recordsBean.getStatusName() ? recordsBean.getStatusName().trim() : "");
        helper.setText(R.id.itemdanger_content,null != recordsBean.getDescription() ? recordsBean.getDescription().trim() : "");
        helper.setText(R.id.itemdanger_time,"时间 : " + (null != recordsBean.getCreateTime() ? recordsBean.getCreateTime().trim() : ""));
    }
}