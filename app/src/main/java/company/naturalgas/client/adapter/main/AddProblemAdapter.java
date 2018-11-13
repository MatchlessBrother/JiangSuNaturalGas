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

public class AddProblemAdapter extends BaseQuickAdapter<String,BaseViewHolder>
{
    private Context mContext;
    public AddProblemAdapter(Context context, @Nullable List<String> data)
    {
        super(R.layout.item_addproblem,data);
        mContext = context;
    }

    protected void convert(BaseViewHolder helper,String imgPath)
    {
        if("".equals(imgPath))
        {
            helper.setGone(R.id.item_addproblem_smallimg,true);
            helper.setGone(R.id.item_addproblem_bigimg_all,false);
        }
        else
        {
            RequestOptions options = new RequestOptions();
            options.error(R.mipmap.defaultimage);
            options.placeholder(R.mipmap.defaultimage);
            options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            helper.setGone(R.id.item_addproblem_smallimg,false);
            helper.setGone(R.id.item_addproblem_bigimg_all,true);
            Glide.with(mContext).load(imgPath).apply(options).into((ImageView) helper.getView(R.id.item_addproblem_bigimg_img));
            if(imgPath.toLowerCase().trim().contains("jpeg") || imgPath.toLowerCase().trim().contains("jpg") || imgPath.toLowerCase().trim().contains("png"))
                helper.setGone(R.id.item_addproblem_bitimg_play,false);
            else
                helper.setGone(R.id.item_addproblem_bitimg_play,true);
        }
    }
}