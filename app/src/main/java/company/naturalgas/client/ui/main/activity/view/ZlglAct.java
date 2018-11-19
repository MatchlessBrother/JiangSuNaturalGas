package company.naturalgas.client.ui.main.activity.view;

import java.io.File;
import java.util.Map;
import android.net.Uri;
import android.os.Build;
import java.util.HashMap;
import android.view.View;
import java.io.IOException;
import java.util.ArrayList;
import android.widget.Button;
import android.os.StrictMode;
import android.content.Intent;
import android.widget.EditText;
import company.naturalgas.client.R;
import android.support.annotation.NonNull;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.DownloadTask;
import android.support.v7.widget.RecyclerView;
import company.naturalgas.client.base.BaseAct;
import company.naturalgas.client.bean.main.ZlglBean;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yuan.devlibrary._12_______Utils.MemoryUtils;
import company.naturalgas.client.adapter.main.ZlglAdapter;
import com.yuan.devlibrary._12_______Utils.SoftKeyboardUtils;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener3;
import company.naturalgas.client.ui.main.activity.view_v.ZlglAct_V;
import com.yuan.devlibrary._11___Widget.promptBox.BaseProgressDialog;

import company.naturalgas.client.ui.main.activity.presenter.ZlglPresenter;

public class ZlglAct extends BaseAct implements ZlglAct_V,View.OnClickListener
{
    private Button mZlglBtn;
    private EditText mZlglEt;
    private String mfilterContent;
    private ZlglAdapter mZlglAdapter;
    private ZlglPresenter mZlglPresenter;
    private RecyclerView mZlglRecyclerview;
    private SwipeRefreshLayout mZlglSwiperefreshlayout;
    /*******************下载文件模块******************/
    private String mDisplayTitle;
    private String mDownloadPath;
    private DownloadListener3 mDownloadListener;
    private Map<String,DownloadTask> mDownloadTaskMap;
    private BaseProgressDialog mDownloadProgressDialog;

    protected int setLayoutResID()
    {
        return R.layout.activity_zlgl;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("资料管理");
        mZlglEt = (EditText)rootView.findViewById(R.id.zlgl_et);
        mZlglBtn = (Button)rootView.findViewById(R.id.zlgl_btn);
        mZlglRecyclerview = (RecyclerView)rootView.findViewById(R.id.zlgl_recyclerview);
        mZlglSwiperefreshlayout = (SwipeRefreshLayout)rootView.findViewById(R.id.zlgl_swiperefreshlayout);
        mZlglAdapter = new ZlglAdapter(mActivity,new ArrayList<ZlglBean.RecordsBean>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mZlglRecyclerview.setLayoutManager(linearLayoutManager);
        mZlglRecyclerview.setAdapter(mZlglAdapter);
        mZlglSwiperefreshlayout.setEnabled(true);
        mZlglAdapter.setEnableLoadMore(true);
        /******************************************************************************************/
        mDownloadPath = MemoryUtils.getBestFilesPath(this);
        mDownloadTaskMap = new HashMap<String,DownloadTask>();
        mDownloadListener = new DownloadListener3()
        {
            protected void started(@NonNull DownloadTask task)
            {
                mDownloadProgressDialog = showLoadingDialog();
            }

            protected void warn(@NonNull DownloadTask task)
            {
                showToast("下载文件遇见错误！");
                dismissLoadingDialog(mDownloadProgressDialog);
            }

            protected void canceled(@NonNull DownloadTask task)
            {
                showToast("取消文件下载！");
                dismissLoadingDialog(mDownloadProgressDialog);
            }

            protected void completed(@NonNull DownloadTask task)
            {
                if(!task.getFile().exists())
                {
                    try
                    {
                        task.getFile().createNewFile();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                showToast("下载文件成功！");
                dismissLoadingDialog(mDownloadProgressDialog);
                openFile(task.getFile().getAbsolutePath(),task.getFile().getAbsolutePath().trim().substring(task.getFile().getAbsolutePath().trim().lastIndexOf(".") + 1,task.getFile().getAbsolutePath().trim().length()));
            }

            protected void error(@NonNull DownloadTask task, @NonNull Exception e)
            {
                showToast("下载文件失败！");
                dismissLoadingDialog(mDownloadProgressDialog);
            }

            public void retry(@NonNull DownloadTask task, @NonNull ResumeFailedCause cause)
            {

            }

            public void progress(@NonNull DownloadTask task, long currentOffset, long totalLength)
            {

            }

            public void connected(@NonNull DownloadTask task, int blockCount, long currentOffset, long totalLength)
            {

            }
        };
    }

    protected void initDatas()
    {
        mZlglPresenter = new ZlglPresenter();
        bindBaseMvpPresenter(mZlglPresenter);
        mfilterContent = "";
        mDisplayTitle = "";
    }

    protected void initLogic()
    {
        mZlglPresenter.refreshDatas(mfilterContent);
        mZlglBtn.setOnClickListener(this);
        mZlglSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                mZlglEt.setText("");
                mfilterContent = mZlglEt.getText().toString().trim();
                mZlglPresenter.refreshDatas(mfilterContent);
                SoftKeyboardUtils.hideKeyboard(ZlglAct.this,mZlglEt);
                //mZlglPresenter.refreshDatas(mfilterContent);
            }
        });

        mZlglAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener()
        {
            public void onLoadMoreRequested()
            {
                mZlglPresenter.loadMoreDatas(mfilterContent);
            }
        },mZlglRecyclerview);

        /******************************************************************************************/
        mZlglAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                mDisplayTitle = (null != mZlglAdapter.getData().get(position) && null != mZlglAdapter.getData().get(position).getName() ? mZlglAdapter.getData().get(position).getName().trim() : "");
                if(mZlglAdapter.getData().get(position).getFilePath().lastIndexOf(".")  == -1)
                {
                    showToast("文件为无效格式，因此无法下载");
                }
                else if(mZlglAdapter.getData().get(position).getFilePath().substring(mZlglAdapter.getData().get(position).getFilePath().lastIndexOf("."),mZlglAdapter.getData().get(position).getFilePath().length()).contains("png") ||
                        mZlglAdapter.getData().get(position).getFilePath().substring(mZlglAdapter.getData().get(position).getFilePath().lastIndexOf("."),mZlglAdapter.getData().get(position).getFilePath().length()).contains("jpg") ||
                        mZlglAdapter.getData().get(position).getFilePath().substring(mZlglAdapter.getData().get(position).getFilePath().lastIndexOf("."),mZlglAdapter.getData().get(position).getFilePath().length()).contains("jpeg"))
                {
                    Intent intent = new Intent(ZlglAct.this,PreviewPhotoAct.class);
                    intent.putExtra("imgPath",mZlglAdapter.getData().get(position).getFilePath().trim());
                    startActivity(intent);
                }
                else
                {
                    String fileName = null;
                    String fileIntegrityPath = null;
                    DownloadTask downloadTask = null;
                    if(mDownloadTaskMap.containsKey(mZlglAdapter.getData().get(position).getFilePath().trim()))
                    {
                        downloadTask = mDownloadTaskMap.get(mZlglAdapter.getData().get(position).getFilePath().trim());
                        fileName = downloadTask.getFilename();
                        fileIntegrityPath = mDownloadPath + File.separator + fileName;
                    }
                    else
                    {
                        fileName = mZlglAdapter.getData().get(position).getFilePath().trim().substring(
                        mZlglAdapter.getData().get(position).getFilePath().trim().lastIndexOf("/") + 1,mZlglAdapter.getData().get(position).getFilePath().trim().length());
                        downloadTask = new DownloadTask.Builder(mZlglAdapter.getData().get(position).getFilePath().trim(),mDownloadPath,fileName).
                        setMinIntervalMillisCallbackProcess(30).setPassIfAlreadyCompleted(true).build();
                        mDownloadTaskMap.put(mZlglAdapter.getData().get(position).getFilePath().trim(),downloadTask);
                        fileIntegrityPath = mDownloadPath + File.separator + fileName;
                    }
                    StatusUtil.Status downloadStatus = StatusUtil.getStatus(downloadTask);
                    if(downloadStatus == StatusUtil.Status.PENDING)
                        showToast("当前文件正在等待下载，请稍等...");
                    else if(downloadStatus == StatusUtil.Status.RUNNING)
                        showToast("正在下载当前文件，请稍等...");
                    else if(downloadStatus == StatusUtil.Status.COMPLETED)
                        openFile(fileIntegrityPath,fileIntegrityPath.trim().substring(fileIntegrityPath.trim().lastIndexOf(".") + 1,fileIntegrityPath.trim().length()));
                    else if(downloadStatus ==  StatusUtil.Status.UNKNOWN)
                        downloadTask.enqueue(mDownloadListener);
                }
            }
        });
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch(view.getId())
        {
            case R.id.zlgl_btn:
            {
                mfilterContent = mZlglEt.getText().toString().trim();
                mZlglPresenter.refreshDatas(mfilterContent);
                SoftKeyboardUtils.hideKeyboard(this,mZlglEt);
                break;
            }
        }
    }

    public void finishRefresh()
    {
        mZlglSwiperefreshlayout.setRefreshing(false);

    }

    public void finishLoadMore()
    {
        mZlglAdapter.loadMoreComplete();

    }

    public void refreshDatas(ZlglBean zlglBean)
    {
        mZlglAdapter.setNewData(zlglBean.getRecords());
        if(zlglBean.getRecords().size() < zlglBean.getSize())
            mZlglAdapter.setEnableLoadMore(false);
        else
            mZlglAdapter.setEnableLoadMore(true);
    }

    public void loadMoreDatas(ZlglBean zlglBean)
    {
        mZlglAdapter.addData(zlglBean.getRecords());
        mZlglAdapter.notifyDataSetChanged();
        if(zlglBean.getRecords().size() < zlglBean.getSize())
            mZlglAdapter.setEnableLoadMore(false);
        else
            mZlglAdapter.setEnableLoadMore(true);
    }

    public boolean canScanOfFile(String type)
    {
        switch(type.toLowerCase().trim())
        {
            case "pdf":
            case "doc":
            case "docx":
            case "wps":
            case "xls":
            case "et":
            case "xlsx":
            case "ppt":
            case "html":
            case "htm":
            case "txt":return true;
            default:return false;
        }
    }

    public String getFileType(String type)
    {
        switch(type.toLowerCase().trim())
        {
            case "rar":return "application/x-rar-compressed";
            case "jpg":return "image/jpeg";
            case "zip":return "application/zip";
            case "pdf":return "application/pdf";
            case "doc":return "application/msword";
            case "docx":return "application/msword";
            case "wps":return "application/msword";
            case "xls":return "application/vnd.ms-excel";
            case "et":return "application/vnd.ms-excel";
            case "xlsx":return "application/vnd.ms-excel";
            case "ppt":return "application/vnd.ms-powerpoint";
            case "html":return "text/html";
            case "htm":return "text/html";
            case "txt":return "text/html";
            case "mp3":return "audio/mpeg";
            case "mp4":return "video/mp4";
            case "3gp":return "video/3gpp";
            case "wav":return "audio/x-wav";
            case "avi":return "video/x-msvideo";
            case "flv":return "flv-application/octet-stream";
            default: return "*/*";
        }
    }

    public void openFile(String filePath, String fileType)
    {
        if(canScanOfFile(fileType))
            FileDisplayActivity.show(this,mDisplayTitle,filePath);
        else
        {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
            }
            Uri data = Uri.fromFile(new File(filePath));
            intent.setDataAndType(data,getFileType(fileType));
            if(getPackageManager().queryIntentActivities(intent,0).size() > 0)
                startActivity(intent);
            else
                showToast("未找到可以打开该文件的APP！");
        }
    }
}