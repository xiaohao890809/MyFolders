package jk.com.hovertop;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

/**
 * Created by h.xiao on 2016/1/6.
 */
public class MyhoverScrollView extends FrameLayout
{
    //固定在顶部的View
    private ViewGroup mTopView;

    //滚动条的内容
    private ViewGroup mContentView;

    //固定在顶部的View里面的内容
    private View mTopContent;

    //固定在顶部的View在滚动条里最上端的位置
    private int mTopViewTop;


    public MyhoverScrollView(Context context)
    {
        this(context,null);
    }

    public MyhoverScrollView(Context context, AttributeSet attrs)
    {
        this(context, attrs,0);
    }

    public MyhoverScrollView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi (Build.VERSION_CODES.JELLY_BEAN)
    public MyhoverScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init()
    {
        post(new Runnable()
        {
            @Override
            public void run()
            {
                mContentView = (ViewGroup) getChildAt(0);
                removeAllViews();

                MyScroolView scroolView = new MyScroolView(getContext(),MyhoverScrollView.this);
                scroolView.addView(mContentView);
                addView(scroolView);
            }
        });
    }


    @SuppressLint("ViewConstructor")
    private static class MyScroolView extends ScrollView
    {

        private MyhoverScrollView mScrollView;

        public MyScroolView(Context context, MyhoverScrollView scrollView)
        {
            super(context);
            mScrollView = scrollView;
        }

        @Override
        protected void onScrollChanged(int l, int t, int oldl, int oldt)
        {
            super.onScrollChanged(l, t, oldl, oldt);
            mScrollView.onScroll(t);
        }
    }

    private void onScroll(final int scrollY)
    {
        post(new Runnable()
        {
            @Override
            public void run()
            {
                if (mTopView == null) return;
                if (scrollY>=mTopViewTop && mTopContent.getParent() == mTopView)
                {
                    //头部应该被固定
                    mTopView.removeView(mTopContent);
                    addView(mTopContent);
                } else if (scrollY<mTopViewTop && mTopContent.getParent() == MyhoverScrollView.this)
                {
                    //头部应该取消固定，还原到父类的位置
                    removeView(mTopContent);
                    mTopView.addView(mTopContent);
                }
            }
        });
    }

    public void setmTopView(final int id)
    {
        post(new Runnable()
        {
            @Override
            public void run()
            {
                mTopView = (ViewGroup) mContentView.findViewById(id);

                int height = mTopView.getChildAt(0).getMeasuredHeight();
                ViewGroup.LayoutParams params = mTopView.getLayoutParams();
                params.height = height;
                mTopView.setLayoutParams(params);
                mTopViewTop = mTopView.getTop();
                mTopContent = mTopView.getChildAt(0);
            }
        });
    }


}
