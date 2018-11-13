package com.yuan.devlibrary._12_______Utils;

import java.io.File;
import android.net.Uri;
import android.content.Context;
import android.media.MediaScannerConnection;

public class SingleMediaUpdateUtils implements MediaScannerConnection.MediaScannerConnectionClient
{
    private File mTartgetFile;
    private MediaScannerConnection mMs;

    public SingleMediaUpdateUtils(Context context,File file)
    {
        mTartgetFile = file;
        mMs = new MediaScannerConnection(context, this);
        mMs.connect();
    }

    public void onMediaScannerConnected()
    {
        mMs.scanFile(mTartgetFile.getAbsolutePath(), null);
    }

    public void onScanCompleted(String path, Uri uri)
    {
        mMs.disconnect();
    }
}