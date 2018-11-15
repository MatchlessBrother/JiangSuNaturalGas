package company.naturalgas.client.adapter.main;

import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import company.naturalgas.client.R;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import company.naturalgas.client.bean.main.MainInfo;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;

public class MainYhpcAdapter extends BaseQuickAdapter<MainInfo.MenuBean.YhpcBean,BaseViewHolder>
{
    private Context mContext;
    public MainYhpcAdapter(Context context,@Nullable List<MainInfo.MenuBean.YhpcBean> data)
    {
        super(R.layout.item_mainyhpc, data);
        mContext = context;
    }

    protected void convert(BaseViewHolder helper,MainInfo.MenuBean.YhpcBean yhpcBean)
    {
        switch(yhpcBean.getAuthUrl())
        {
            case "yhsb":
            {
                Drawable bgDrawable = mContext.getResources().getDrawable(R.drawable.shape_round_green_yhsb);
                helper.getView(R.id.item_mainyhpc_imgbg).setBackgroundDrawable(bgDrawable);
                Bitmap fgBitmap = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.yhsb);
                ((ImageView)helper.getView(R.id.item_mainyhpc_imgfg)).setImageBitmap(fgBitmap);
                helper.setText(R.id.item_mainyhpc_tv,null != yhpcBean.getAuthName() ? yhpcBean.getAuthName().trim() : "");
                break;
            }
            case "yhzg":
            {
                Drawable bgDrawable = mContext.getResources().getDrawable(R.drawable.shape_round_green_yhzg);
                helper.getView(R.id.item_mainyhpc_imgbg).setBackgroundDrawable(bgDrawable);
                Bitmap fgBitmap = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.yhzg);
                ((ImageView)helper.getView(R.id.item_mainyhpc_imgfg)).setImageBitmap(fgBitmap);
                break;
            }
            case "zgys":
            {
                Drawable bgDrawable = mContext.getResources().getDrawable(R.drawable.shape_round_green_zgys);
                helper.getView(R.id.item_mainyhpc_imgbg).setBackgroundDrawable(bgDrawable);
                Bitmap fgBitmap = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.zgys);
                ((ImageView)helper.getView(R.id.item_mainyhpc_imgfg)).setImageBitmap(fgBitmap);
                break;
            }
            case "yhtj":
            {
                Drawable bgDrawable = mContext.getResources().getDrawable(R.drawable.shape_round_green_yhhz);
                helper.getView(R.id.item_mainyhpc_imgbg).setBackgroundDrawable(bgDrawable);
                Bitmap fgBitmap = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.yhhz);
                ((ImageView)helper.getView(R.id.item_mainyhpc_imgfg)).setImageBitmap(fgBitmap);
                break;
            }
            case "qhse":
            {
                Drawable bgDrawable = mContext.getResources().getDrawable(R.drawable.shape_round_red_yhgb);
                helper.getView(R.id.item_mainyhpc_imgbg).setBackgroundDrawable(bgDrawable);
                Bitmap fgBitmap = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.yhgb);
                ((ImageView)helper.getView(R.id.item_mainyhpc_imgfg)).setImageBitmap(fgBitmap);
                break;
            }
            case "zlgl":
            {
                Drawable bgDrawable = mContext.getResources().getDrawable(R.drawable.shape_round_yellow_zlgl);
                helper.getView(R.id.item_mainyhpc_imgbg).setBackgroundDrawable(bgDrawable);
                Bitmap fgBitmap = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.zlgl);
                ((ImageView)helper.getView(R.id.item_mainyhpc_imgfg)).setImageBitmap(fgBitmap);
                break;
            }
            default:
            {
                Drawable bgDrawable = mContext.getResources().getDrawable(R.drawable.shape_round_green_yhzg);
                helper.getView(R.id.item_mainyhpc_imgbg).setBackgroundDrawable(bgDrawable);
                Bitmap fgBitmap = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.yhzg);
                ((ImageView)helper.getView(R.id.item_mainyhpc_imgfg)).setImageBitmap(fgBitmap);
                break;
            }
        }
        helper.setText(R.id.item_mainyhpc_tv,null != yhpcBean.getAuthName() ? yhpcBean.getAuthName().trim() : "");
    }
}