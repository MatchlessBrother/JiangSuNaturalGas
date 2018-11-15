package company.naturalgas.client.adapter.main;

import java.util.List;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import company.naturalgas.client.R;
import android.support.annotation.Nullable;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import company.naturalgas.client.bean.main.DangerDetailBean;

public class DangerDetailAdapter extends BaseQuickAdapter<DangerDetailBean.RecordBean,BaseViewHolder>
{
    private Context mContext;
    public DangerDetailAdapter(Context context,@Nullable List<DangerDetailBean.RecordBean> data)
    {
        super(R.layout.item_dangerdetail,data);
        mContext = context;
    }

    protected void convert(BaseViewHolder helper,DangerDetailBean.RecordBean recordBean)
    {

    }
}