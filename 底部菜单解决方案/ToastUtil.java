package jk.com.weibo.common.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by h.xiao on 2016/5/10.
 */
public class ToastUtil
{
    public static boolean isShow = true;

    private ToastUtil()
    {
        throw new UnsupportedOperationException("instantiated");
    }

    //短时间显示
    public static void showShort(Context context,CharSequence message)
    {
        if (isShow)
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context,int message)
    {
        if (isShow)
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }


    //长时间显示
    public static void showLong(Context context,CharSequence message)
    {
        if (isShow)
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context,int message)
    {
        if (isShow)
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }



}
