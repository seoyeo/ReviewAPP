package chen.questiong.menu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import chen.questiong.R;

public class Setting extends AppCompatActivity {

    private String[] arr = {"更换第一章的背景", "更换第二张的背景"};
    private ArrayAdapter<String> adapter = null;
    private View setting;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setting = findViewById(R.id.setting);
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.back2);
        adapter = new ArrayAdapter<String>(Setting.this, android.R.layout.simple_list_item_1, arr);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //setting.setBackgroundResource(R.mipmap.back2);
                        break;
                }
            }
        });
    }
}
