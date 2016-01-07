package jk.com.hovertop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private MyhoverScrollView view_hover;
    private MyList lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = (MyList) findViewById(R.id.lv);

        lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,getData()));

        view_hover = (MyhoverScrollView) findViewById(R.id.view_hover);
        view_hover.setmTopView(R.id.top);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this,"点击了我",Toast.LENGTH_LONG).show();
            }
        });
    }


    public List<String> getData()
    {
        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("1");
        data.add("2");
        data.add("3");
        return data;
    }
}
