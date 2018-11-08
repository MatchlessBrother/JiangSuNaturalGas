package company.naturalgas.client.ui.main.activity.view;

import android.net.Uri;
import android.view.View;
import android.text.TextUtils;
import android.content.Intent;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.provider.Settings;
import android.view.LayoutInflater;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;
import company.naturalgas.client.R;
import android.content.ComponentName;
import android.app.NotificationManager;
import com.xdandroid.hellodaemon.DaemonEnv;
import company.naturalgas.client.base.BaseAct;
import android.support.v4.app.NotificationManagerCompat;
import com.yuan.devlibrary._12_______Utils.SharepreferenceUtils;
import com.yuan.devlibrary._11___Widget.promptBox.BasePopupWindow;
import company.naturalgas.client.ui.main.activity.view_v.MainAct_V;
import company.naturalgas.client.ui.main.activity.view_v.SignInAct_V;
import company.naturalgas.client.service.main.ProtectNotifycationService;
import company.naturalgas.client.ui.main.activity.presenter.MainPresenter;
import company.naturalgas.client.ui.main.activity.presenter.SignInPresenter;

public class MainAct extends BaseAct implements MainAct_V,SignInAct_V
{
    private BGABanner mBanner;
    private MainPresenter mMainPresenter;
    private SignInPresenter mSignInPresenter;
    private BGALocalImageSize mBannerLocalImageSize;


    protected int setLayoutResID()
    {
        return R.layout.activity_main;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("主页");
        setTitleBack(R.mipmap.usericon);
        mBanner=(BGABanner)rootView.findViewById(R.id.mainact_banner);
        mBanner.setAutoPlayAble(true);
        mBanner.setAutoPlayInterval(6000);
        mBanner.setAllowUserScrollable(true);
        mBannerLocalImageSize = new BGALocalImageSize(720, 1280, 320, 640);
        mBanner.setData(mBannerLocalImageSize,ImageView.ScaleType.CENTER_CROP,R.mipmap.img_banner,R.mipmap.img_banner,R.mipmap.img_banner);

    }

    protected void initDatas()
    {
        mMainPresenter = new MainPresenter();
        mSignInPresenter = new SignInPresenter();
        bindBaseMvpPresenter(mMainPresenter);
        bindBaseMvpPresenter(mSignInPresenter);
    }

    protected void initLogic()
    {
        if(!getIntent().getBooleanExtra("islogined",false))
            mSignInPresenter.signIn(SharepreferenceUtils.extractObject(this,"username",String.class).trim(),SharepreferenceUtils.extractObject(this,"password",String.class).trim());
        else
        {
            mMainPresenter.getDatas();
            DaemonEnv.initialize(this, ProtectNotifycationService.class, DaemonEnv.DEFAULT_WAKE_UP_INTERVAL);
            ProtectNotifycationService.sShouldStopService = false;
            startService(new Intent(this,ProtectNotifycationService.class));
        }

        mBanner.setDelegate(new BGABanner.Delegate<ImageView, String>()
        {
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position)
            {
                showToast("点击了第" + position + "张图片！");
            }
        });
    }

    public void signInSuccess()
    {
        mMainPresenter.getDatas();
        DaemonEnv.initialize(this, ProtectNotifycationService.class, DaemonEnv.DEFAULT_WAKE_UP_INTERVAL);
        ProtectNotifycationService.sShouldStopService = false;
        startService(new Intent(this,ProtectNotifycationService.class));
    }

    public void signInFailure()
    {
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        ProtectNotifycationService.stopService();
        SignInAct.quitCrrentAccount(this,"账号发生异常，请重新登录！");
    }

    public void signOutAction()
    {
        mMainPresenter.signOut();

    }

    public void signOutSuccess()
    {
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        ProtectNotifycationService.stopService();
        SignInAct.quitCrrentAccount((BaseAct)mActivity,"退出登录成功！");
    }

    public void signOutFailure()
    {

    }

    public void getFailOfDatas()
    {

    }

    public void getSuccessOfDatas()
    {

    }

    protected void onTitleBackClick()
    {
        final View basePopupWindowContent = LayoutInflater.from(mActivity).inflate(R.layout.dialog_signin_exit,null);
        TextView signInBtn =(TextView)basePopupWindowContent.findViewById(R.id.dialogsigninexit_signin);
        TextView exitBtn =(TextView)basePopupWindowContent.findViewById(R.id.dialogsigninexit_exit);
        final BasePopupWindow basePopupWindow  =  new BasePopupWindow(mActivity);
        basePopupWindow.setContentView(basePopupWindowContent);
        signInBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(mActivity,ModifyPasswordAct.class);
                if(basePopupWindow.isShowing()) basePopupWindow.dismiss();
                startActivity(intent);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(basePopupWindow.isShowing()) basePopupWindow.dismiss();
                ((MainAct)mActivity).signOutAction();
            }
        });
        if(isUseDefaultTitleLine())
            basePopupWindow.showAsDropDown(mTitleBackBtn,12,6);
    }

    private void openNotifycationEnable()
    {
        if(!NotificationManagerCompat.from(getApplicationContext()).areNotificationsEnabled())
        {
            Intent intent = new Intent();
            showToast("请选择通知选项并开启通知权限，否则无法接收消息通知！谢谢");
            Uri uri = Uri.fromParts("package",getPackageName(), null);
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(uri);
            startActivity(intent);
        }
    }

    private void openNotifycationListenerEnable()
    {
        if(!isNotifycationListenerEnable())
            startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
    }

    private boolean isNotifycationListenerEnable()
    {
        String pkgName = getPackageName();
        final String flag = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (!TextUtils.isEmpty(flag))
        {
            final String[] names = flag.split(":");
            for (int index = 0; index < names.length; index++)
            {
                final ComponentName componentName = ComponentName.unflattenFromString(names[index]);
                if (componentName != null)
                {
                    if (TextUtils.equals(pkgName, componentName.getPackageName()))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        mMainPresenter.getDatas();
    }
}