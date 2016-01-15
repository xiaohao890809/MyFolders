package com.jk.myviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jk.myviewpager.fragment.Fragment1;
import com.jk.myviewpager.fragment.Fragment2;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{

    private ArrayList<Fragment> fragments;
    private ViewPager viewPager;
    private TextView tab_app;
    private TextView tab_game;
    private View line;
    private int line_width;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tab_app = (TextView) findViewById(R.id.tab_app);
        tab_game = (TextView) findViewById(R.id.tab_game);
        line = findViewById(R.id.line);

        fragments = new ArrayList<Fragment>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        line_width = getWindowManager().getDefaultDisplay().getWidth()/fragments.size();
        line.getLayoutParams().width = line_width;
        line.requestLayout();

        ViewPropertyAnimator.animate(tab_app).scaleX(1.2f).setDuration(0);
        ViewPropertyAnimator.animate(tab_app).scaleY(1.2f).setDuration(0);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                return fragments.get(position);
            }

            @Override
            public int getCount()
            {
                return fragments.size();
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
                float targetX = position*line_width + positionOffsetPixels/fragments.size();
                ViewPropertyAnimator.animate(line).translationX(targetX).setDuration(0);

            }

            @Override
            public void onPageSelected(int position)
            {
                changeState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

        tab_app.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(0);
            }
        });

        tab_game.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(1);
            }
        });
    }

    //滑动时改变字体的颜色和大小
    private void changeState(int position)
    {
        if (position == 0)
        {
            tab_app.setTextColor(getResources().getColor(R.color.green));
            tab_game.setTextColor(getResources().getColor(R.color.grey));
            ViewPropertyAnimator.animate(tab_app).scaleX(1.2f).setDuration(200);
            ViewPropertyAnimator.animate(tab_app).scaleY(1.2f).setDuration(200);
            ViewPropertyAnimator.animate(tab_game).scaleX(1.0f).setDuration(200);
            ViewPropertyAnimator.animate(tab_game).scaleY(1.0f).setDuration(200);
        }else
        {
            tab_game.setTextColor(getResources().getColor(R.color.green));
            tab_app.setTextColor(getResources().getColor(R.color.grey));
            ViewPropertyAnimator.animate(tab_game).scaleX(1.2f).setDuration(200);
            ViewPropertyAnimator.animate(tab_game).scaleY(1.2f).setDuration(200);
            ViewPropertyAnimator.animate(tab_app).scaleX(1.0f).setDuration(200);
            ViewPropertyAnimator.animate(tab_app).scaleY(1.0f).setDuration(200);
        }
    }

}
