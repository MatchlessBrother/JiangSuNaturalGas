package company.naturalgas.client.ui.main.activity.view;

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
import android.widget.LinearLayout;
import company.naturalgas.client.R;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.luck.picture.lib.PictureSelector;
import android.support.v7.widget.RecyclerView;
import android.graphics.drawable.ColorDrawable;
import company.naturalgas.client.base.BasePhotoAct;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yuan.devlibrary._12_______Utils.PromptBoxUtils;
import company.naturalgas.client.adapter.main.AddProblemAdapter;
import company.naturalgas.client.bean.main.FzrBean;
import company.naturalgas.client.ui.main.activity.view_v.AddProblemAct_V;
import company.naturalgas.client.ui.main.activity.presenter.AddProblemPresenter;

public class AddProblemAct extends BasePhotoAct implements AddProblemAct_V, View.OnClickListener
{
    private Button mAddproblemBtn;
    private EditText mAddproblemEt;
    private TextView mAddproblemFzr;
    private int mTotalUploadedFiles;
    private List<FzrBean> mFzrOptionBeans;
    private LinearLayout mAddproblemFzrAll;
    private List<String> mUploadedFilesList;
    private PictureSelector mPicturesSelector;
    private AddProblemAdapter mAddProblemAdapter;
    private RecyclerView mAddproblemRecyclerview;
    private OptionsPickerView mFzrOptionsPickerView;
    private int mCurrentSelectedFzrOptionItemOfIndex;
    private AddProblemPresenter mAddProblemPresenter;

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
        mAddproblemFzr = (TextView)rootView.findViewById(R.id.addproblem_fzr);
        mAddproblemFzrAll = (LinearLayout)rootView.findViewById(R.id.addproblem_fzr_all);
        mAddproblemRecyclerview =(RecyclerView)rootView.findViewById(R.id.addproblem_recyclerview);
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
    }

    protected void initDatas()
    {
        mAddProblemPresenter = new AddProblemPresenter();
        bindBaseMvpPresenter(mAddProblemPresenter);
        mUploadedFilesList = new ArrayList<>();
        mFzrOptionBeans = new ArrayList<>();
        mTotalUploadedFiles = 0;
    }

    protected void initLogic()
    {
        mAddProblemPresenter.getFzrDatas();
        mAddproblemBtn.setOnClickListener(this);
        mAddproblemFzrAll.setOnClickListener(this);
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
                if(null != mFzrOptionBeans && mFzrOptionBeans.size() > 0)
                {
                    mFzrOptionsPickerView.setSelectOptions(mCurrentSelectedFzrOptionItemOfIndex);
                    mFzrOptionsPickerView.show();
                }
                else
                    showToast("没有可以选择的负责人");
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
                    mAddProblemPresenter.uploadFile(mAddProblemAdapter.getData().get(mTotalUploadedFiles),"1");
                }
                else
                {
                    mAddProblemPresenter.uploadDanger(mCurrentSelectedFzrOptionItemOfIndex > -1 && mCurrentSelectedFzrOptionItemOfIndex < mFzrOptionBeans.size() ?
                                    mFzrOptionBeans.get(mCurrentSelectedFzrOptionItemOfIndex).getPickerViewText().trim() : "",
                            mAddproblemEt.getText().toString().trim(),mUploadedFilesList);
                }
                break;
            }
        }
    }

    public void getFailOfFzrDatas()
    {

    }

    public void getFailOfUploadFile()
    {
        showToast("上传图片/视频失败，请重新上传");

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
            mAddProblemPresenter.uploadFile(mAddProblemAdapter.getData().get(mTotalUploadedFiles),"1");
        }
        else
        {
            mAddProblemPresenter.uploadDanger(mCurrentSelectedFzrOptionItemOfIndex > -1 && mCurrentSelectedFzrOptionItemOfIndex < mFzrOptionBeans.size() ?
                            mFzrOptionBeans.get(mCurrentSelectedFzrOptionItemOfIndex).getId().trim() : "",
                            mAddproblemEt.getText().toString().trim(),mUploadedFilesList);
        }
    }

    public void getFailOfUploadDanger()
    {

    }

    public void getSuccessOfUploadDanger()
    {
        finish();
    }

    public void getSuccessOfFzrDatas(List<FzrBean> fzrBeans)
    {
        mFzrOptionBeans = fzrBeans;
        mCurrentSelectedFzrOptionItemOfIndex = mFzrOptionBeans.size() > 0 ? -1 : -1;
        mFzrOptionsPickerView.setNPicker(mFzrOptionBeans,null,null);
        if(mCurrentSelectedFzrOptionItemOfIndex > -1 && mCurrentSelectedFzrOptionItemOfIndex < mFzrOptionBeans.size())mFzrOptionsPickerView.setSelectOptions(mCurrentSelectedFzrOptionItemOfIndex);
        mAddproblemFzr.setText(mCurrentSelectedFzrOptionItemOfIndex > -1 && mCurrentSelectedFzrOptionItemOfIndex < mFzrOptionBeans.size() ? mFzrOptionBeans.get(mCurrentSelectedFzrOptionItemOfIndex).getPickerViewText().trim() : "");
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