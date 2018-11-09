package company.naturalgas.client.base;

import android.content.Context;
import me.jessyan.autosize.AutoSize;
import android.support.multidex.MultiDex;
import me.jessyan.autosize.unit.Subunits;
import me.jessyan.autosize.AutoSizeConfig;
import com.yuan.devlibrary._1App.BaseApplication;
import company.naturalgas.client.bean.main.MainInfo;

public class BaseApp extends BaseApplication
{
    private BaseApp mBaseApp;
    private MainInfo mMainInfo;

    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void onCreate()
    {
        super.onCreate();
        mBaseApp  = this;
        adapterExternalUi();
        AutoSize.initCompatMultiProcess(this);
        AutoSizeConfig.getInstance().setLog(true).setBaseOnWidth(true).
                       setUseDeviceSize(false).setCustomFragment(true);
        AutoSizeConfig.getInstance().getUnitsManager().setSupportDP(false).
                        setSupportSP(true).setSupportSubunits(Subunits.MM);
    }

    public void adapterExternalUi()
    {
        //AutoSizeConfig.getInstance().getExternalAdaptManager().addExternalAdaptInfoOfActivity();
    }

    public MainInfo getMainInfo()
    {
        return mMainInfo;
    }

    public void setMainInfo(MainInfo mainInfo)
    {
        mMainInfo = mainInfo;
    }
}