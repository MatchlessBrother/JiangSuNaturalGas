package company.naturalgas.client.adapter.main;

import java.util.List;
import android.content.Context;
import company.naturalgas.client.R;
import android.support.annotation.Nullable;
import company.naturalgas.client.bean.main.SjtjBean;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;

public class SjtjAdapter extends BaseQuickAdapter<SjtjBean,BaseViewHolder>
{
    public SjtjAdapter(Context context,@Nullable List<SjtjBean> sjtjBeans)
    {
        super(R.layout.item_sjtj,sjtjBeans);
        mContext = context;
    }

    protected void convert(BaseViewHolder helper,SjtjBean sjtjBean)
    {

    }
}