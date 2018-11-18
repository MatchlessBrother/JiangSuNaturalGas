package company.naturalgas.client.adapter.main;

import java.util.List;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import company.naturalgas.client.R;
import android.support.annotation.Nullable;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import company.naturalgas.client.bean.main.ZlglBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ZlglAdapter extends BaseQuickAdapter<ZlglBean.RecordsBean,BaseViewHolder>
{
    private Context mContext;
    public ZlglAdapter(Context context, @Nullable List<ZlglBean.RecordsBean> data)
    {
        super(R.layout.item_zlgl,data);
        mContext = context;
    }

    protected void convert(BaseViewHolder helper,ZlglBean.RecordsBean recordsBean)
    {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.defaultimage);
        options.placeholder(R.mipmap.defaultimage);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(null != recordsBean.getType() ? recordsBean.getType().trim() : "").apply(options).into((ImageView) helper.getView(R.id.item_zlgl_img));
        helper.setText(R.id.item_zlgl_code,null != recordsBean.getCode() ? recordsBean.getCode().trim() : "");
        helper.setText(R.id.item_zlgl_content,null != recordsBean.getName() ? recordsBean.getName().trim() : "");
    }
}
