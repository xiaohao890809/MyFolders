package jk.com.weibo.unlogin.activity;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jk.com.weibo.R;
import jk.com.weibo.common.util.ToastUtil;
import jk.com.weibo.unlogin.fragment.DiscoverFragment;
import jk.com.weibo.unlogin.fragment.HomeFragment;
import jk.com.weibo.unlogin.fragment.MessageFragment;
import jk.com.weibo.unlogin.fragment.ProfileFragment;

/**
 * Created by h.xiao on 2016/5/10.
 */
public class UnloginActivity extends FragmentActivity
{
    private static final int HOME_FRAGMENT = 0X001;
    private static final int MESSAGE_FRAGMENT = 0X002;
    private static final int DISCOVER_FRAGMENT = 0X004;
    private static final int PROFILE_FRAGMENT = 0X005;

    private int mCurrentIndex;
    private Context mContext;
    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private DiscoverFragment mDiscoverFragment;
    private ProfileFragment mProfileFragment;

    private FragmentManager mFragmentManager;

    @Bind(R.id.rl_home)
    RelativeLayout rlHome;
    @Bind(R.id.rl_message)
    RelativeLayout rlMessage;
    @Bind(R.id.fl_post)
    FrameLayout flPost;
    @Bind(R.id.rl_search)
    RelativeLayout rlSearch;
    @Bind(R.id.rl_profile)
    RelativeLayout rlProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        mFragmentManager = getSupportFragmentManager();
        setFragment(HOME_FRAGMENT);

    }

    @OnClick({R.id.rl_home, R.id.rl_message, R.id.fl_post, R.id.rl_search, R.id.rl_profile})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_home:
                setFragment(HOME_FRAGMENT);
                break;
            case R.id.rl_message:
                setFragment(MESSAGE_FRAGMENT);
                break;
            case R.id.fl_post:
                ToastUtil.showShort(mContext, "请先登陆");
                break;
            case R.id.rl_search:
                setFragment(DISCOVER_FRAGMENT);
                break;
            case R.id.rl_profile:
                setFragment(PROFILE_FRAGMENT);
                break;
        }
    }

    private void setFragment(int index)
    {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideAllFragments(transaction);
        switch (index)
        {
            case HOME_FRAGMENT:
                rlHome.setSelected(true);
                if (mHomeFragment == null)
                {
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.content, mHomeFragment);
                } else
                {
                    transaction.show(mHomeFragment);
                }
                break;
            case MESSAGE_FRAGMENT:
                rlMessage.setSelected(true);
                if (mMessageFragment == null)
                {
                    mMessageFragment = new MessageFragment();
                    transaction.add(R.id.content, mMessageFragment);
                } else
                {
                    transaction.show(mMessageFragment);
                }
                break;
            case DISCOVER_FRAGMENT:
                rlSearch.setSelected(true);
                if (mDiscoverFragment == null)
                {
                    mDiscoverFragment = new DiscoverFragment();
                    transaction.add(R.id.content, mDiscoverFragment);
                } else
                {
                    transaction.show(mDiscoverFragment);
                }
                break;
            case PROFILE_FRAGMENT:
                rlProfile.setSelected(true);
                if (mProfileFragment == null)
                {
                    mProfileFragment = new ProfileFragment();
                    transaction.add(R.id.content, mProfileFragment);
                } else
                {
                    transaction.show(mProfileFragment);
                }
                break;
        }
        transaction.commit();

    }

    private void hideAllFragments(FragmentTransaction transaction)
    {
        if (mHomeFragment != null)
        {
            transaction.hide(mHomeFragment);
        }
        if (mMessageFragment != null)
        {
            transaction.hide(mMessageFragment);
        }
        if (mDiscoverFragment != null)
        {
            transaction.hide(mDiscoverFragment);
        }
        if (mProfileFragment != null)
        {
            transaction.hide(mProfileFragment);
        }

        rlHome.setSelected(false);
        rlMessage.setSelected(false);
        rlSearch.setSelected(false);
        rlProfile.setSelected(false);

    }


}
