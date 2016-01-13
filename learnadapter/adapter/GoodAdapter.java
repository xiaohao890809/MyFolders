package com.jk.learnadapter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.jk.learnadapter.R;
import com.jk.learnadapter.model.Good;
import java.util.List;

public class GoodAdapter extends BaseAdapter
{
    private List<Good> mList;

    public GoodAdapter(List<Good> mList)
    {
        this.mList = mList;
    }

    @Override
    public int getCount()
    {
        return null == mList ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = null;
        if (null == convertView)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_item,null);
            viewHolder = new ViewHolder();
            viewHolder.addCount = (Button) convertView.findViewById(R.id.addCount);
            viewHolder.reduceCount =(Button)convertView.findViewById(R.id.reduceCount);
            viewHolder.count = (TextView)convertView.findViewById(R.id.count);
            viewHolder.name =(TextView)convertView.findViewById(R.id.name);
            viewHolder.price =(TextView)convertView.findViewById(R.id.price);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Good good = mList.get(position);
        viewHolder.count.setText(""+good.getCount());
        viewHolder.name.setText(good.getName());
        viewHolder.price.setText(""+good.getPrice());
        viewHolder.image.setImageDrawable(parent.getContext().getResources().getDrawable(good.getImgRes()));
        viewHolder.addCount.setOnClickListener(new View.OnClickListener()
        {
            //不必设置单价价格，因为它一般是固定不变的
            @Override
            public void onClick(View v)
            {
                good.setCount(good.getCount()+1);
                notifyDataSetChanged();
            }
        });
        viewHolder.reduceCount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (good.getCount()>1)
                {
                    good.setCount(good.getCount()-1);
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }

    class ViewHolder
    {
        Button addCount;
        Button reduceCount;
        TextView name;
        TextView count;
        TextView price;
        ImageView image;
    }
}
