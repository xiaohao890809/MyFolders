package com.jk.msglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.jk.msglist.adapter.MsgAdapter;
import com.jk.msglist.model.Msg;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ListView mListView;
    private MsgAdapter adapter;
    private EditText mEditText;
    private Button send;
    private List<Msg> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        mListView = (ListView) findViewById(R.id.list_view);
        mEditText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        adapter = new MsgAdapter(this,R.layout.msg_item,mList);
        mListView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String text = mEditText.getText().toString();
                if (!"".equals(text))
                {
                    Msg msg = new Msg(text,Msg.TYPE_SEND);
                    mList.add(msg);
                    //有新的消息，通知ListView更新
                    adapter.notifyDataSetChanged();
                    //将ListView定位到最后一行
                    mListView.setSelection(mList.size());
                    //清空输入框的内容
                    mEditText.setText("");
                }
            }
        });
    }

    private void initData()
    {
        Msg msg1 = new Msg("你好啊",Msg.TYPE_RECEIVED);
        mList.add(msg1);
        Msg msg2 = new Msg("Hello",Msg.TYPE_SEND);
        mList.add(msg2);
        Msg msg3 = new Msg("很高兴认识你啊",Msg.TYPE_RECEIVED);
        mList.add(msg3);
    }
}
