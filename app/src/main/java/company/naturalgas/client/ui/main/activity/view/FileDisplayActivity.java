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
import com.yuan.devlibrary._11___Widget.promptBox.BaseProgressDialog;

public class FileDisplayActivity extends BaseAct
{
    private TbsListener mTbsListener;
    private FileReaderView mFileReaderView;
    private BaseProgressDialog mBaseProgressDialog;

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
                dismissLoadingDialog(mBaseProgressDialog);
                mFileReaderView.show(getIntent().getStringExtra("path"));
            }

            public void onDownloadProgress(int i)
            {

            }
        };
    }

    protected void initDatas()
    {
        mBaseProgressDialog = null;
    }

    protected void initLogic()
    {
        QbSdk.preInit(this, new QbSdk.PreInitCallback()
        {
            public void onCoreInitFinished()
            {

            }

            public void onViewInitFinished(boolean b)
            {
                dismissLoadingDialog(mBaseProgressDialog);
                mFileReaderView.show(getIntent().getStringExtra("path"));
            }
        });
        /******************************************************************************************/
        boolean needDownload = TbsDownloader.needDownload(this, TbsDownloader.DOWNLOAD_OVERSEA_TBS);
        if (needDownload && NetUtils.WhetherConnectNet(this))
        {
            QbSdk.setDownloadWithoutWifi(true);
            TbsDownloader.startDownload(this);
            mBaseProgressDialog = showLoadingDialog();
        }
        else
        {
            dismissLoadingDialog(mBaseProgressDialog);
            mFileReaderView.show(getIntent().getStringExtra("path"));
        }
        QbSdk.setTbsListener(mTbsListener);
    }

    public void onDestroy()
    {
        if (mFileReaderView != null)
            mFileReaderView.stop();
        super.onDestroy();
    }
}