package company.naturalgas.client.ui.main.activity.view;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import com.tencent.smtt.sdk.QbSdk;
import company.naturalgas.client.R;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.TbsDownloader;
import company.naturalgas.client.base.BaseAct;
import com.fanneng.android.web.file.FileReaderView;
import com.yuan.devlibrary._12_______Utils.NetUtils;

public class FileDisplayActivity extends BaseAct
{
    private TbsListener mTbsListener;
    private FileReaderView mFileReaderView;

    protected int setLayoutResID()
    {
        return R.layout.activity_filedisplay;
    }

    public static void show(Context context,String title,String url)
    {
        Intent intent = new Intent(context, FileDisplayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("path",url);
        bundle.putString("title",title);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent(null != getIntent().getStringExtra("title") ? getIntent().getStringExtra("title").trim() : "在线浏览");
        mFileReaderView = (FileReaderView)rootView.findViewById(R.id.filedisplay_readerview);
        mTbsListener = new TbsListener()
        {
            public void onDownloadFinish(int i)
            {

            }

            public void onInstallFinish(int i)
            {
                mFileReaderView.show(getIntent().getStringExtra("path"));
            }

            public void onDownloadProgress(int i)
            {

            }
        };
    }

    protected void initDatas()
    {

    }

    protected void initLogic()
    {
        boolean needDownload = TbsDownloader.needDownload(this, TbsDownloader.DOWNLOAD_OVERSEA_TBS);
        if (needDownload && NetUtils.WhetherConnectNet(this))
            TbsDownloader.startDownload(this);
        else
            mFileReaderView.show(getIntent().getStringExtra("path"));
        QbSdk.setTbsListener(mTbsListener);
    }

    public void onDestroy()
    {
        if (mFileReaderView != null)
            mFileReaderView.stop();
        super.onDestroy();
    }
}