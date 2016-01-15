package com.jk.googleadapter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jk.googleadapter.adapter.CardAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnDismissCallback
{
    private ListView listView;
    private CardAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        mCardAdapter = new CardAdapter(this);
        mCardAdapter.addAll(getItems());

        SwingBottomInAnimationAdapter bottom = new SwingBottomInAnimationAdapter(new SwipeDismissAdapter(mCardAdapter,this));
        bottom.setAbsListView(listView);
        listView.setAdapter(bottom);

    }
    private ArrayList<Integer> getItems()
    {
        ArrayList<Integer> items = new ArrayList<Integer>();
        for (int i=0;i<50;i++)
        {
            items.add(i);
        }
        return items;
    }

    //侧滑删掉卡片
    @Override
    public void onDismiss(@NonNull ViewGroup listView, @NonNull int[] reverseSortedPositions)
    {
        for (int postion:reverseSortedPositions)
        {
            mCardAdapter.remove(postion);
        }
    }
}
