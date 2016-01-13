package com.jk.learnadapter;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.jk.learnadapter.adapter.GoodAdapter;
import com.jk.learnadapter.model.Good;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ListView listView;
    private GoodAdapter adapter;
    private List<Good> mList;
    private TextView amountView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        listView = (ListView) findViewById(R.id.listView);
        amountView = (TextView) findViewById(R.id.textview_amount);
		
        mList = new ArrayList<Good>();
        mList.add(new Good("橡胶花盆", R.mipmap.icon, 1, 12.00f));
        mList.add(new Good("搪瓷水壶", R.mipmap.icon2, 1, 13.00f));
        mList.add(new Good("扫把和簸箕", R.mipmap.icon3, 1, 14.00f));

        calculate();
        adapter = new GoodAdapter(mList);
        listView.setAdapter(adapter);
        adapter.registerDataSetObserver(new DataSetObserver()
        {
            @Override
            public void onChanged()
            {
                calculate();
                super.onChanged();
            }
        });
    }

    private void calculate()
    {
        float amount = 0;
        for (Good good:mList)
        {
            amount += good.getCount()*good.getPrice();
        }
        amountView.setText(String.format("%.2f",amount));
    }
}
