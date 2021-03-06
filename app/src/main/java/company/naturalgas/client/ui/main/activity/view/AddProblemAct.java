package company.naturalgas.client.ui.main.activity.view;

import java.io.File;
import java.util.List;
import android.view.View;
import java.util.ArrayList;
import java.util.LinkedList;
import android.widget.Button;
import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.TextView;
import io.reactivex.Observable;
import android.widget.LinearLayout;
import company.naturalgas.client.R;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import com.luck.picture.lib.PictureSelector;
import android.support.v7.widget.RecyclerView;
import android.graphics.drawable.ColorDrawable;
import com.iceteck.silicompressorr.SiliCompressor;
import company.naturalgas.client.bean.main.FzrBean;
import company.naturalgas.client.base.BasePhotoAct;
import android.support.v7.widget.GridLayoutManager;
import company.naturalgas.client.bean.main.SjlxBean;
import com.bigkoo.pickerview.view.OptionsPickerView;
import android.support.v7.widget.LinearLayoutManager;
import company.naturalgas.client.bean.main.JlfzrBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yuan.devlibrary._12_______Utils.MemoryUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import com.yuan.devlibrary._12_______Utils.PromptBoxUtils;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import company.naturalgas.client.adapter.main.AddProblemAdapter;
import com.yuan.devlibrary._11___Widget.promptBox.BaseProgressDialog;
import company.naturalgas.client.ui.main.activity.view_v.AddProblemAct_V;
import company.naturalgas.client.ui.main.activity.presenter.AddProblemPresenter;

public class AddProblemAct extends BasePhotoAct implements AddProblemAct_V, View.OnClickListener
{
    private String mDangerid;
    private String mProcesstype;
    private Button mAddproblemBtn;
    private int mProcessTypeValue;
    private EditText mAddproblemEt;
    private int mTotalUploadedFiles;
    private List<String> mUploadedFilesList;
    private String mPicturesCachePath = null;
    private PictureSelector mPicturesSelector;
    private AddProblemAdapter mAddProblemAdapter;
    private RecyclerView mAddproblemRecyclerview;
    private BaseProgressDialog mBaseProgressDialog;
    private AddProblemPresenter mAddProblemPresenter;

    private TextView mAddproblemFzr;
    private List<FzrBean> mFzrOptionBeans;
    private LinearLayout mAddproblemFzrAll;
    private OptionsPickerView mFzrOptionsPickerView;
    private int mCurrentSelectedFzrOptionItemOfIndex;

    private TextView mAddproblemSjlx;
    private List<SjlxBean> mSjlxOptionBeans;
    private LinearLayout mAddproblemSjlxAll;
    private OptionsPickerView mSjlxOptionsPickerView;
    private int mCurrentSelectedSjlxOptionItemOfIndex;

    private TextView mAddproblemJlfzr;
    private List<JlfzrBean> mJlfzrOptionBeans;
    private LinearLayout mAddproblemJlfzrAll;
    private OptionsPickerView mJlfzrOptionsPickerView;
    private int mCurrentSelectedJlfzrOptionItemOfIndex;

    protected int setLayoutResID()
    {
        return R.layout.activity_addproblem;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        mPicturesSelector = PictureSelector.create(this);
        mAddproblemBtn = (Button)rootView.findViewById(R.id.addproblem_btn);
        mAddproblemEt = (EditText)rootView.findViewById(R.id.addproblem_et);
        mAddproblemRecyclerview =(RecyclerView)rootView.findViewById(R.id.addproblem_recyclerview);
        /******************************************************************************************/
        mAddproblemFzr = (TextView)rootView.findViewById(R.id.addproblem_fzr);
        mAddproblemFzrAll = (LinearLayout)rootView.findViewById(R.id.addproblem_fzr_all);
        mAddproblemSjlx = (TextView)rootView.findViewById(R.id.addproblem_sjlx);
        mAddproblemSjlxAll = (LinearLayout)rootView.findViewById(R.id.addproblem_sjlx_all);
        mAddproblemJlfzr = (TextView)rootView.findViewById(R.id.addproblem_jlfzr);
        mAddproblemJlfzrAll = (LinearLayout)rootView.findViewById(R.id.addproblem_jlfzr_all);
        /******************************************************************************************/
        List dataList = new ArrayList<String>();dataList.add("");
        mAddProblemAdapter =  new AddProblemAdapter(mActivity,dataList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity,3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAddproblemRecyclerview.setLayoutManager(gridLayoutManager);
        mAddproblemRecyclerview.setNestedScrollingEnabled(false);
        mAddproblemRecyclerview.setFocusableInTouchMode(false);
        mAddproblemRecyclerview.setAdapter(mAddProblemAdapter);
        /******************************************************************************************/
        mFzrOptionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener()
        {
            public void onOptionsSelect(int options1Index, int options2Index, int options3Index, View view)
            {
                mCurrentSelectedFzrOptionItemOfIndex = options1Index;
                mFzrOptionsPickerView.setSelectOptions(mCurrentSelectedFzrOptionItemOfIndex);
                mAddproblemFzr.setText(mCurrentSelectedFzrOptionItemOfIndex > -1 && mCurrentSelectedFzrOptionItemOfIndex < mFzrOptionBeans.size() ? mFzrOptionBeans.get(mCurrentSelectedFzrOptionItemOfIndex).getPickerViewText().trim() : "");
            }
        }) .setTitleText("选择负责人").setLabels("","","").setTitleSize(33)
                .setSubmitText("确定") .setCancelText("取消")
                .setSubCalSize(28).setContentTextSize(18)
                .setBgColor(getResources().getColor(R.color.white))
                .setTitleColor(getResources().getColor(R.color.white))
                .setSubmitColor(getResources().getColor(R.color.white))
                .setCancelColor(getResources().getColor(R.color.white))
                .setBackgroundId(getResources().getColor(R.color.white))
                .setTitleBgColor(getResources().getColor(R.color.colorPrimary))
                .setDividerColor(getResources().getColor(R.color.default_font_gray))
                .setOutSideCancelable(true).isRestoreItem(false).isCenterLabel(false)
                .setCyclic(false,false,false).setSelectOptions(0, 0, 0).build();
        /******************************************************************************************/
        mSjlxOptionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener()
        {
            public void onOptionsSelect(int options1Index, int options2Index, int options3Index, View view)
            {
                mCurrentSelectedSjlxOptionItemOfIndex = options1Index;
                mSjlxOptionsPickerView.setSelectOptions(mCurrentSelectedSjlxOptionItemOfIndex);
                mAddproblemSjlx.setText(mCurrentSelectedSjlxOptionItemOfIndex > -1 && mCurrentSelectedSjlxOptionItemOfIndex < mSjlxOptionBeans.size() ? mSjlxOptionBeans.get(mCurrentSelectedSjlxOptionItemOfIndex).getPickerViewText().trim() : "");
            }
        }) .setTitleText("选择事件类型").setLabels("","","").setTitleSize(33)
                .setSubmitText("确定") .setCancelText("取消")
                .setSubCalSize(28).setContentTextSize(18)
                .setBgColor(getResources().getColor(R.color.white))
                .setTitleColor(getResources().getColor(R.color.white))
                .setSubmitColor(getResources().getColor(R.color.white))
                .setCancelColor(getResources().getColor(R.color.white))
                .setBackgroundId(getResources().getColor(R.color.white))
                .setTitleBgColor(getResources().getColor(R.color.colorPrimary))
                .setDividerColor(getResources().getColor(R.color.default_font_gray))
                .setOutSideCancelable(true).isRestoreItem(false).isCenterLabel(false)
                .setCyclic(false,false,false).setSelectOptions(0, 0, 0).build();
        /******************************************************************************************/
        mJlfzrOptionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener()
        {
            public void onOptionsSelect(int options1Index, int options2Index, int options3Index, View view)
            {
                mCurrentSelectedJlfzrOptionItemOfIndex = options1Index;
                mJlfzrOptionsPickerView.setSelectOptions(mCurrentSelectedJlfzrOptionItemOfIndex);
                mAddproblemJlfzr.setText(mCurrentSelectedJlfzrOptionItemOfIndex > -1 && mCurrentSelectedJlfzrOptionItemOfIndex < mJlfzrOptionBeans.size() ? mJlfzrOptionBeans.get(mCurrentSelectedJlfzrOptionItemOfIndex).getPickerViewText().trim() : "");
            }
        }) .setTitleText("选择监理负责人").setLabels("","","").setTitleSize(33)
                .setSubmitText("确定") .setCancelText("取消")
                .setSubCalSize(28).setContentTextSize(18)
                .setBgColor(getResources().getColor(R.color.white))
                .setTitleColor(getResources().getColor(R.color.white))
                .setSubmitColor(getResources().getColor(R.color.white))
                .setCancelColor(getResources().getColor(R.color.white))
                .setBackgroundId(getResources().getColor(R.color.white))
                .setTitleBgColor(getResources().getColor(R.color.colorPrimary))
                .setDividerColor(getResources().getColor(R.color.default_font_gray))
                .setOutSideCancelable(true).isRestoreItem(false).isCenterLabel(false)
                .setCyclic(false,false,false).setSelectOptions(0, 0, 0).build();
    }

    protected void initDatas()
    {
        mPicturesCachePath = MemoryUtils.getBestFilesPath(this) + File.separator + "pictures";
        mAddProblemPresenter = new AddProblemPresenter();
        bindBaseMvpPresenter(mAddProblemPresenter);
        mUploadedFilesList = new ArrayList<>();
        mJlfzrOptionBeans = new ArrayList<>();
        mSjlxOptionBeans = new ArrayList<>();
        mFzrOptionBeans = new ArrayList<>();
        mBaseProgressDialog = null;
        mTotalUploadedFiles = 0;
    }

    protected void initLogic()
    {
        mProcesstype = (null != getIntent() && null != getIntent().getStringExtra("processtype") ? getIntent().getStringExtra("processtype").trim() : "");
        mDangerid = (null != getIntent() && null != getIntent().getStringExtra("dangerid") ? getIntent().getStringExtra("dangerid").trim() : "");
        switch(mProcesstype)
        {
            case "sgcl":
            {
                mProcessTypeValue = 2;
                setTitleContent("任务处理");
                mAddproblemFzrAll.setVisibility(View.GONE);
                mAddproblemSjlxAll.setVisibility(View.GONE);
                mAddproblemJlfzrAll.setVisibility(View.VISIBLE);
                break;
            }
            case "yscl":
            {
                mProcessTypeValue = 6;
                setTitleContent("验收处理");
                mAddproblemFzrAll.setVisibility(View.GONE);
                mAddproblemSjlxAll.setVisibility(View.GONE);
                mAddproblemJlfzrAll.setVisibility(View.GONE);
                break;
            }
            case "jjcl":
            {
                mProcessTypeValue = 7;
                setTitleContent("拒绝处理");
                mAddproblemFzrAll.setVisibility(View.GONE);
                mAddproblemSjlxAll.setVisibility(View.GONE);
                mAddproblemJlfzrAll.setVisibility(View.GONE);
                break;
            }
            default:
            {
                mProcessTypeValue = 1;
                setTitleContent("添加问题");
                mAddproblemFzrAll.setVisibility(View.VISIBLE);
                mAddproblemSjlxAll.setVisibility(View.VISIBLE);
                mAddproblemJlfzrAll.setVisibility(View.GONE);
                break;
            }
        }
        /******************************************************************************************/
        mAddProblemPresenter.getFzrDatas();
        mAddProblemPresenter.getSjlxDatas();
        mAddProblemPresenter.getJlfzrDatas();
        mAddproblemBtn.setOnClickListener(this);
        mAddproblemFzrAll.setOnClickListener(this);
        mAddproblemSjlxAll.setOnClickListener(this);
        mAddproblemJlfzrAll.setOnClickListener(this);
        mAddProblemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                if(null != mAddProblemAdapter.getData().get(position) && "".equals(mAddProblemAdapter.getData().get(position).trim()))
                {
                    int maxSizePicturesOfGallery = 9 - mAddProblemAdapter.getData().size();
                    for(int index = 0;index < mAddProblemAdapter.getData().size();index++)
                    {
                        if(null != mAddProblemAdapter.getData().get(index) && "".equals(mAddProblemAdapter.getData().get(index)))
                        {
                            maxSizePicturesOfGallery++;
                            break;
                        }
                    }
                    showSelectPicturesDialog(30f, TypedValue.COMPLEX_UNIT_SP,R.color.colorPrimary,maxSizePicturesOfGallery);
                }
                else if(null != mAddProblemAdapter.getData().get(position) && !"".equals(mAddProblemAdapter.getData().get(position).trim()))
                {
                    if(mAddProblemAdapter.getData().get(position).toLowerCase().trim().contains("jpeg") || mAddProblemAdapter.getData().get(position).toLowerCase().trim().contains("jpg") || mAddProblemAdapter.getData().get(position).toLowerCase().trim().contains("png"))
                    {
                        Intent intent = new Intent(AddProblemAct.this,PreviewPhotoAct.class);
                        intent.putExtra("imgPath",mAddProblemAdapter.getData().get(position).trim());
                        startActivity(intent);
                    }
                    else
                    {
                        PictureSelector.create(AddProblemAct.this).externalPictureVideo(mAddProblemAdapter.getData().get(position).trim());
                    }
                }
            }
        });

        mAddProblemAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener()
        {
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position)
            {
                if(null != mAddProblemAdapter.getData().get(position) && "".equals(mAddProblemAdapter.getData().get(position).trim()))
                {
                    int maxSizePicturesOfGallery = 9 - mAddProblemAdapter.getData().size();
                    for(int index = 0;index < mAddProblemAdapter.getData().size();index++)
                    {
                        if(null != mAddProblemAdapter.getData().get(index) && "".equals(mAddProblemAdapter.getData().get(index)))
                        {
                            maxSizePicturesOfGallery++;
                            break;
                        }
                    }
                    showSelectPicturesDialog(30f, TypedValue.COMPLEX_UNIT_SP,R.color.colorPrimary,maxSizePicturesOfGallery);
                }
                else if(null != mAddProblemAdapter.getData().get(position) && !"".equals(mAddProblemAdapter.getData().get(position).trim()))
                {
                    PromptBoxUtils.showPromptDialog(AddProblemAct.this,"删除提示：", Color.parseColor("#FF333333"),30,TypedValue.COMPLEX_UNIT_SP, new ColorDrawable(0xffffffff), View.VISIBLE,
                            "确定要删除这个照片/视频吗？",Color.parseColor("#FF666666"), 26, TypedValue.COMPLEX_UNIT_SP, new ColorDrawable(0xffffffff),
                            "我想想", Color.parseColor("#FF3B6DB9"),28, TypedValue.COMPLEX_UNIT_SP, new ColorDrawable(0xffffffff), View.VISIBLE,
                            "是的！", Color.parseColor("#FFFF0000"), 28, TypedValue.COMPLEX_UNIT_SP, new ColorDrawable(0xffffffff), View.VISIBLE,
                            true, new View.OnClickListener()
                            {
                                public void onClick(View v)
                                {
                                    mAddProblemAdapter.remove(position);
                                    if(mAddProblemAdapter.getData().size() < 9)
                                    {
                                        for(int index = mAddProblemAdapter.getData().size() - 1;index >= 0;index--)
                                        {
                                            if(null != mAddProblemAdapter.getData().get(index) && "".equals(mAddProblemAdapter.getData().get(index).trim()))
                                            {
                                                break;
                                            }
                                            if(index == 0)
                                            {
                                                mAddProblemAdapter.getData().add("");
                                            }
                                        }
                                    }
                                    mAddProblemAdapter.notifyDataSetChanged();
                                }
                            },null,null);
                }
                return false;
            }
        });
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.addproblem_fzr_all:
            {
                if(null != mFzrOptionBeans && mFzrOptionBeans.size() > 0 && mCurrentSelectedFzrOptionItemOfIndex < mFzrOptionBeans.size())
                {
                    mFzrOptionsPickerView.setSelectOptions(mCurrentSelectedFzrOptionItemOfIndex);
                    mFzrOptionsPickerView.show();
                }
                else
                    showToast("没有可以选择的负责人");
                break;
            }

            case R.id.addproblem_sjlx_all:
            {
                if(null != mSjlxOptionBeans && mSjlxOptionBeans.size() > 0 && mCurrentSelectedSjlxOptionItemOfIndex < mSjlxOptionBeans.size())
                {
                    mSjlxOptionsPickerView.setSelectOptions(mCurrentSelectedSjlxOptionItemOfIndex);
                    mSjlxOptionsPickerView.show();
                }
                else
                    showToast("没有可以选择的事件类型");
                break;
            }
            case R.id.addproblem_jlfzr_all:
            {
                if(null != mJlfzrOptionBeans && mJlfzrOptionBeans.size() > 0 && mCurrentSelectedJlfzrOptionItemOfIndex < mJlfzrOptionBeans.size())
                {
                    mJlfzrOptionsPickerView.setSelectOptions(mCurrentSelectedJlfzrOptionItemOfIndex);
                    mJlfzrOptionsPickerView.show();
                }
                else
                    showToast("没有可以选择的监理负责人");
                break;
            }
            case R.id.addproblem_btn:
            {
                boolean isContainEmptyView = false;
                for(int index = 0;index < mAddProblemAdapter.getData().size();index++)
                {
                    if("".equals(mAddProblemAdapter.getData().get(index).trim()))
                    {
                        isContainEmptyView = true;
                    }
                }
                if((isContainEmptyView && mAddProblemAdapter.getData().size() > 1) || (!isContainEmptyView && mAddProblemAdapter.getData().size() > 0))
                {
                    mTotalUploadedFiles = 0;
                    mUploadedFilesList.clear();
                    String videoPath = mAddProblemAdapter.getData().get(mTotalUploadedFiles);
                    String videoType = videoPath.substring(videoPath.lastIndexOf(".") + 1,videoPath.length()).toLowerCase().trim();
                    if(videoType.contains("mp4") || videoType.contains("3gp") || videoType.contains("mkv") || videoType.contains("m4a") ||
                            videoType.contains("aac") || videoType.contains("ts") || videoType.contains("flac") || videoType.contains("wav") || videoType.contains("ogg"))
                    {
                        if(videoPath.substring(videoPath.lastIndexOf("/") + 1,videoPath.length()).startsWith("VIDEO_"))
                        {
                            mAddProblemPresenter.uploadFile(videoPath,String.valueOf(mProcessTypeValue));
                        }
                        else
                        {
                            mBaseProgressDialog = showLoadingDialog();
                            Observable.just(videoPath).map(new Function<String, String>()
                            {
                                public String apply(String videoPath) throws Exception
                                {
                                    return SiliCompressor.with(AddProblemAct.this).compressVideo(videoPath,mPicturesCachePath,0,0,1100000);
                                }
                            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>()
                            {
                                public void accept(String newVideoPath) throws Exception
                                {
                                    System.gc();
                                    dismissLoadingDialog(mBaseProgressDialog);
                                    mAddProblemPresenter.uploadFile(newVideoPath,String.valueOf(mProcessTypeValue));
                                }
                            });
                        }
                    }
                    else
                    {
                        mAddProblemPresenter.uploadFile(videoPath,String.valueOf(mProcessTypeValue));
                    }
                }
                else
                {
                    switch(mProcesstype)
                    {
                        case "sgcl":
                        {
                            mAddProblemPresenter.processDanger(mCurrentSelectedJlfzrOptionItemOfIndex > -1 && mCurrentSelectedJlfzrOptionItemOfIndex < mJlfzrOptionBeans.size() ?
                                  mJlfzrOptionBeans.get(mCurrentSelectedJlfzrOptionItemOfIndex).getId().trim() : "",mAddproblemEt.getText().toString().trim(),mDangerid,mUploadedFilesList);
                            break;
                        }
                        case "yscl":
                        {
                            mAddProblemPresenter.acceptDanger(mAddproblemEt.getText().toString().trim(),mDangerid,mUploadedFilesList);
                            break;
                        }
                        case "jjcl":
                        {
                            mAddProblemPresenter.refuseDanger(mAddproblemEt.getText().toString().trim(),mDangerid,mUploadedFilesList);
                            break;
                        }
                        default:
                        {
                            mAddProblemPresenter.uploadDanger(mCurrentSelectedFzrOptionItemOfIndex > -1 && mCurrentSelectedFzrOptionItemOfIndex < mFzrOptionBeans.size() ?
                                                mFzrOptionBeans.get(mCurrentSelectedFzrOptionItemOfIndex).getId().trim() : "", mAddproblemEt.getText().toString().trim(),
                                                mCurrentSelectedSjlxOptionItemOfIndex > -1 && mCurrentSelectedSjlxOptionItemOfIndex < mSjlxOptionBeans.size() ?
                                                mSjlxOptionBeans.get(mCurrentSelectedSjlxOptionItemOfIndex).getCode().trim() : "",mUploadedFilesList);
                            break;
                        }
                    }
                }
                break;
            }
        }
    }

    public void getFailOfFzrDatas()
    {

    }

    public void getFailOfSjlxDatas()
    {

    }

    public void getFailOfJlfzrDatas()
    {

    }

    public void getFailOfUploadFile()
    {
        showToast("上传图片/视频失败，请重新上传");

    }

    public void getFailOfUploadDanger()
    {

    }

    public void getSuccessOfUploadDanger()
    {
        finish();

    }

    public void getFailOfAcceptDanger()
    {

    }

    public void getSuccessOfAcceptDanger()
    {
        finish();

    }

    public void getFailOfProcessDanger()
    {

    }

    public void getSuccessOfProcessDanger()
    {
        finish();

    }

    public void getFailOfRefuseDanger()
    {

    }

    public void getSuccessOfRefuseDanger()
    {
        finish();

    }

    public void getSuccessOfUploadFile(String filePath)
    {
        mTotalUploadedFiles++;
        mUploadedFilesList.add(filePath);
        boolean isContainEmptyView = false;
        for(int index = 0;index < mAddProblemAdapter.getData().size();index++)
        {
            if("".equals(mAddProblemAdapter.getData().get(index).trim()))
            {
                isContainEmptyView = true;
            }
        }
        if((isContainEmptyView && mTotalUploadedFiles <= mAddProblemAdapter.getData().size() - 2) || (!isContainEmptyView && mTotalUploadedFiles <= mAddProblemAdapter.getData().size() - 1))
        {
            System.gc();
            //mAddProblemPresenter.uploadFile(mAddProblemAdapter.getData().get(mTotalUploadedFiles),String.valueOf(mProcessTypeValue));
            String videoPath = mAddProblemAdapter.getData().get(mTotalUploadedFiles);
            String videoType = videoPath.substring(videoPath.lastIndexOf(".") + 1,videoPath.length()).toLowerCase().trim();
            if(videoType.contains("mp4") || videoType.contains("3gp") || videoType.contains("mkv") || videoType.contains("m4a") ||
                    videoType.contains("aac") || videoType.contains("ts") || videoType.contains("flac") || videoType.contains("wav") || videoType.contains("ogg"))
            {
                if(videoPath.substring(videoPath.lastIndexOf("/") + 1,videoPath.length()).startsWith("VIDEO_"))
                {
                    mAddProblemPresenter.uploadFile(videoPath,String.valueOf(mProcessTypeValue));
                }
                else
                {
                    mBaseProgressDialog = showLoadingDialog();
                    Observable.just(videoPath).map(new Function<String, String>()
                    {
                        public String apply(String videoPath) throws Exception
                        {
                            return SiliCompressor.with(AddProblemAct.this).compressVideo(videoPath,mPicturesCachePath,0,0,1100000);
                        }
                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>()
                    {
                        public void accept(String newVideoPath) throws Exception
                        {
                            System.gc();
                            dismissLoadingDialog(mBaseProgressDialog);
                            mAddProblemPresenter.uploadFile(newVideoPath,String.valueOf(mProcessTypeValue));
                        }
                    });
                }
            }
            else
            {
                mAddProblemPresenter.uploadFile(videoPath,String.valueOf(mProcessTypeValue));
            }
        }
        else
        {
            switch(mProcesstype)
            {
                case "sgcl":
                {
                    mAddProblemPresenter.processDanger(mCurrentSelectedJlfzrOptionItemOfIndex > -1 && mCurrentSelectedJlfzrOptionItemOfIndex < mJlfzrOptionBeans.size() ?
                         mJlfzrOptionBeans.get(mCurrentSelectedJlfzrOptionItemOfIndex).getId().trim() : "",mAddproblemEt.getText().toString().trim(),mDangerid,mUploadedFilesList);
                    break;
                }
                case "yscl":
                {
                    mAddProblemPresenter.acceptDanger(mAddproblemEt.getText().toString().trim(),mDangerid,mUploadedFilesList);
                    break;
                }
                case "jjcl":
                {
                    mAddProblemPresenter.refuseDanger(mAddproblemEt.getText().toString().trim(),mDangerid,mUploadedFilesList);
                    break;
                }
                default:
                {
                    mAddProblemPresenter.uploadDanger(mCurrentSelectedFzrOptionItemOfIndex > -1 && mCurrentSelectedFzrOptionItemOfIndex < mFzrOptionBeans.size() ?
                                    mFzrOptionBeans.get(mCurrentSelectedFzrOptionItemOfIndex).getId().trim() : "", mAddproblemEt.getText().toString().trim(),
                                    mCurrentSelectedSjlxOptionItemOfIndex > -1 && mCurrentSelectedSjlxOptionItemOfIndex < mSjlxOptionBeans.size() ?
                                    mSjlxOptionBeans.get(mCurrentSelectedSjlxOptionItemOfIndex).getCode().trim() : "",mUploadedFilesList);
                    break;
                }
            }
        }
    }

    public void getSuccessOfFzrDatas(List<FzrBean> fzrBeans)
    {
        mFzrOptionBeans = fzrBeans;
        mCurrentSelectedFzrOptionItemOfIndex = mFzrOptionBeans.size() > 0 ? -1 : -1;
        mFzrOptionsPickerView.setNPicker(mFzrOptionBeans,null,null);
        if(mCurrentSelectedFzrOptionItemOfIndex > -1 && mCurrentSelectedFzrOptionItemOfIndex < mFzrOptionBeans.size())mFzrOptionsPickerView.setSelectOptions(mCurrentSelectedFzrOptionItemOfIndex);
        mAddproblemFzr.setText(mCurrentSelectedFzrOptionItemOfIndex > -1 && mCurrentSelectedFzrOptionItemOfIndex < mFzrOptionBeans.size() ? mFzrOptionBeans.get(mCurrentSelectedFzrOptionItemOfIndex).getPickerViewText().trim() : "");
    }

    public void getSuccessOfSjlxDatas(List<SjlxBean> sjlxBeans)
    {
        mSjlxOptionBeans = sjlxBeans;
        mCurrentSelectedSjlxOptionItemOfIndex = mSjlxOptionBeans.size() > 0 ? -1 : -1;
        mSjlxOptionsPickerView.setNPicker(mSjlxOptionBeans,null,null);
        if(mCurrentSelectedSjlxOptionItemOfIndex > -1 && mCurrentSelectedSjlxOptionItemOfIndex < mSjlxOptionBeans.size())mSjlxOptionsPickerView.setSelectOptions(mCurrentSelectedSjlxOptionItemOfIndex);
        mAddproblemSjlx.setText(mCurrentSelectedSjlxOptionItemOfIndex > -1 && mCurrentSelectedSjlxOptionItemOfIndex < mSjlxOptionBeans.size() ? mSjlxOptionBeans.get(mCurrentSelectedSjlxOptionItemOfIndex).getPickerViewText().trim() : "");
    }

    public void getSuccessOfJlfzrDatas(List<JlfzrBean> jlfzrBeans)
    {
        mJlfzrOptionBeans = jlfzrBeans;
        mCurrentSelectedJlfzrOptionItemOfIndex = mJlfzrOptionBeans.size() > 0 ? -1 : -1;
        mJlfzrOptionsPickerView.setNPicker(mJlfzrOptionBeans,null,null);
        if(mCurrentSelectedJlfzrOptionItemOfIndex > -1 && mCurrentSelectedJlfzrOptionItemOfIndex < mJlfzrOptionBeans.size())mJlfzrOptionsPickerView.setSelectOptions(mCurrentSelectedJlfzrOptionItemOfIndex);
        mAddproblemJlfzr.setText(mCurrentSelectedJlfzrOptionItemOfIndex > -1 && mCurrentSelectedJlfzrOptionItemOfIndex < mJlfzrOptionBeans.size() ? mJlfzrOptionBeans.get(mCurrentSelectedJlfzrOptionItemOfIndex).getPickerViewText().trim() : "");
    }

    protected void setOnNewImgPathListener(LinkedList<String> bitmapPaths)
    {
        if(null != bitmapPaths && bitmapPaths.size() > 0)
        {
            for(int index = 0;index < bitmapPaths.size();index++)
            {
                if(null != bitmapPaths.get(index) && !"".equals(bitmapPaths.get(index).trim()))
                    mAddProblemAdapter.addData(0,bitmapPaths.get(index).trim());
            }
            if(mAddProblemAdapter.getData().size() > 9)
            {
                for(int index = mAddProblemAdapter.getData().size() - 1;index >= 0;index--)
                {
                    if(null != mAddProblemAdapter.getData().get(index) && "".equals(mAddProblemAdapter.getData().get(index).trim()))
                    {
                        mAddProblemAdapter.getData().remove(index);
                        break;
                    }
                }
            }
            mAddProblemAdapter.notifyDataSetChanged();
        }
    }
}