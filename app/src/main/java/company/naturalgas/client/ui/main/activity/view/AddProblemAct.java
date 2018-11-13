package company.naturalgas.client.ui.main.activity.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import company.naturalgas.client.R;
import android.support.v7.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.yuan.devlibrary._12_______Utils.PromptBoxUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import company.naturalgas.client.adapter.main.AddProblemAdapter;
import company.naturalgas.client.base.BasePhotoAct;
import company.naturalgas.client.ui.main.activity.presenter.AddProblemPresenter;
import company.naturalgas.client.ui.main.activity.view_v.AddProblemAct_V;

public class AddProblemAct extends BasePhotoAct implements AddProblemAct_V, View.OnClickListener
{
    private Button mAddproblemBtn;
    private EditText mAddproblemEt;
    private TextView mAddproblemFzr;
    private LinearLayout mAddproblemFzrAll;
    private PictureSelector mPicturesSelector;
    private AddProblemAdapter mAddProblemAdapter;
    private RecyclerView mAddproblemRecyclerview;
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
    }

    protected void initDatas()
    {
        mAddProblemPresenter = new AddProblemPresenter();
        bindBaseMvpPresenter(mAddProblemPresenter);
    }

    protected void initLogic()
    {
        mAddproblemBtn.setOnClickListener(this);
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
                    if("jpeg".equals(mAddProblemAdapter.getData().get(position).toLowerCase().trim()) || "jpg".equals(mAddProblemAdapter.getData().get(position).toLowerCase().trim()) || "png".equals(mAddProblemAdapter.getData().get(position).toLowerCase().trim()))
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
            case R.id.addproblem_btn:
            {
               /*if(mAddProblemAdapter.getData().size() > 0 && !"".equals(mAddProblemAdapter.getData().get(0).trim()))
                    mBjczPresenter.disposeAlarmImage(mAddProblemAdapter.getData());
                else
                    mBjczPresenter.disposeAlarm(mAlarmId,mBjczEt.getText().toString().trim(),new HashMap<String, String>());
                break;*/
            }
        }
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