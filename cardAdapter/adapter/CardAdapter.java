package com.jk.googleadapter.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jk.googleadapter.R;
import com.nhaarman.listviewanimations.ArrayAdapter;

/**
 * Created by h.xiao on 2016/1/13.
 */
public class CardAdapter extends ArrayAdapter<Integer>
{
    private Context mContext;
    private LruCache<Integer,Bitmap> mMemoryCache;

    public CardAdapter(Context mContext)
    {
        this.mContext = mContext;
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);

        final int cacheSize = maxMemory/8;
        mMemoryCache = new LruCache<Integer, Bitmap>(cacheSize)
        {
            @Override
            protected int sizeOf(Integer key, Bitmap value)
            {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };

    }

    private static class ViewHolder
    {
        TextView textView;
        ImageView imageView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.card_item,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText("This is card"+getItem(position+1));
        setImageView(viewHolder,position);
        return convertView;
    }

    private void setImageView(ViewHolder viewHolder,int postion)
    {
        int imageResId;
        switch (getItem(postion)%5)
        {
            case 0:
                imageResId = R.mipmap.img_nature1;
                break;
            case 1:
                imageResId = R.mipmap.img_nature2;
                break;
            case 2:
                imageResId = R.mipmap.img_nature3;
                break;
            case 3:
                imageResId = R.mipmap.img_nature4;
                break;
            default:
                imageResId = R.mipmap.img_nature5;
                break;
        }
        Bitmap bitmap = getBitmapFromCache(imageResId);
        if (bitmap == null)
        {
            bitmap = BitmapFactory.decodeResource(mContext.getResources(),imageResId);
            addBitmapToMemoryCache(imageResId,bitmap);
        }
        viewHolder.imageView.setImageBitmap(bitmap);

    }

    private void addBitmapToMemoryCache(int key,Bitmap bitmap)
    {
        if (getBitmapFromCache(key) == null)
        {
            mMemoryCache.put(key,bitmap);
        }
    }

    private Bitmap getBitmapFromCache(int key)
    {
        return mMemoryCache.get(key);
    }
}
