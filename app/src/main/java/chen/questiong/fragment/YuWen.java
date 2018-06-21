package chen.questiong.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import chen.questiong.R;

public class YuWen extends Fragment {

    private Button Y_Bt1;
    private Button Y_Bt2;
    private Button Y_Bt3;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_yu_wen, container, false);
        Y_Bt1 = (Button) view.findViewById(R.id.y_chapter1);
        Y_Bt2 = (Button) view.findViewById(R.id.y_chapter2);
        Y_Bt3 = (Button) view.findViewById(R.id.y_chapter3);
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Y_Bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"等待添加数据库",Toast.LENGTH_SHORT).show();
            }
        });
        Y_Bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"等待添加数据库",Toast.LENGTH_SHORT).show();

            }
        });
        Y_Bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"等待添加数据库",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
