package com.jk.msglist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.msglist.R;
import com.jk.msglist.model.Msg;

import java.util.List;


/**
 * Created by h.xiao on 2016/1/18.
 */
public class MsgAdapter extends ArrayAdapter<Msg>
{

    private int resource;

    public MsgAdapter(Context context, int resource, List<Msg> objects)
    {
        super(context, resource, objects);
        this.resource =resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(resource,null);
            viewHolder = new ViewHolder();
            viewHolder.left_linear = (LinearLayout) convertView.findViewById(R.id.left_layout);
            viewHolder.left_msg = (TextView) convertView.findViewById(R.id.left_msg);
            viewHolder.right_linear = (LinearLayout) convertView.findViewById(R.id.right_layout);
            viewHolder.right_msg = (TextView) convertView.findViewById(R.id.right_msg);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //判断短信的类型，根据短信的类型进行相应的操作
        Msg msg = getItem(position);
        if (msg.getType() == Msg.TYPE_RECEIVED)
        {
            //显示接受短信的时候，隐藏右边的内容，显示左边的内容
            viewHolder.left_linear.setVisibility(View.VISIBLE);
            viewHolder.right_linear.setVisibility(View.GONE);
            viewHolder.left_msg.setText(msg.getContent());
        }
        if (msg.getType() == Msg.TYPE_SEND)
        {
            //显示发送短信的时候，隐藏左边的内容，显示右边的内容
            viewHolder.right_linear.setVisibility(View.VISIBLE);
            viewHolder.left_linear.setVisibility(View.GONE);
            viewHolder.right_msg.setText(msg.getContent());
        }

        return convertView;
    }

    class ViewHolder
    {
        LinearLayout left_linear,right_linear;
        TextView left_msg,right_msg;
    }
}
