package jk.com.hovertop;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by h.xiao on 2016/1/6.
 */
public class MyList extends ListView
{

    //这个方法必须得重写的，不然listview会报错
    public MyList(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
