package company.naturalgas.client.adapter.main;

import java.util.List;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import com.bumptech.glide.Glide;
import android.widget.ImageView;
import company.naturalgas.client.R;
import android.support.annotation.Nullable;
import com.luck.picture.lib.PictureSelector;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import company.naturalgas.client.bean.main.DangerDetailBean;
import company.naturalgas.client.ui.main.activity.view.PreviewPhotoAct;

public class DangerDetailImgAdapter extends BaseQuickAdapter<DangerDetailBean.RecordBean.FileListBeanX,BaseViewHolder>
{
    private Context mContext;
    public DangerDetailImgAdapter(Context context, @Nullable List<DangerDetailBean.RecordBean.FileListBeanX> data)
    {
        super(R.layout.item_dangerdetailimg,data);
        mContext = context;
    }

    protected void convert(BaseViewHolder helper,final DangerDetailBean.RecordBean.FileListBeanX path)
    {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.defaultimage);
        options.placeholder(R.mipmap.defaultimage);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(null != path .getFilePath() ?  path.getFilePath().trim() : "").apply(options).into((ImageView) helper.getView(R.id.item_dangerdetailimg_img));
        if((null != path.getFilePath() && !"".equals(path.getFilePath().trim())) && (path.getFilePath().substring(path.getFilePath().lastIndexOf(".") + 1).toLowerCase().trim().contains("jpg") ||
            path.getFilePath().substring(path.getFilePath().lastIndexOf(".") + 1).toLowerCase().trim().contains("png") || path.getFilePath().substring(path.getFilePath().lastIndexOf(".") + 1).toLowerCase().trim().contains("jpeg")))
           helper.setGone(R.id.item_dangerdetailimg_play,false);
        else if((null != path.getFilePath() && !"".equals(path.getFilePath().trim())))
            helper.setGone(R.id.item_dangerdetailimg_play,true);
        helper.getView(R.id.item_dangerdetailimg_play).setEnabled(false);
        helper.getView(R.id.item_dangerdetailimg_img).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if((null != path.getFilePath() && !"".equals(path.getFilePath().trim())) &&
                                (path.getFilePath().substring(path.getFilePath().lastIndexOf(".") + 1).toLowerCase().trim().contains("jpg") ||
                                path.getFilePath().substring(path.getFilePath().lastIndexOf(".") + 1).toLowerCase().trim().contains("png") ||
                                path.getFilePath().substring(path.getFilePath().lastIndexOf(".") + 1).toLowerCase().trim().contains("jpeg")))
                {
                    Intent intent = new Intent(mContext,PreviewPhotoAct.class);
                    intent.putExtra("imgPath",path.getFilePath().trim());
                    mContext.startActivity(intent);
                }
                else if((null != path.getFilePath() && !"".equals(path.getFilePath().trim())))
                {
                    PictureSelector.create((Activity)mContext).externalPictureVideo(path.getFilePath().trim());
                }
            }
        });
    }
}