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


public class Micrsoft extends Fragment {
    private Button M_Bt1;
    private Button M_Bt2;
    private Button M_Bt3;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_micrsoft, container, false);
        M_Bt1 = (Button) view.findViewById(R.id.m_chapter1);
        M_Bt2 = (Button) view.findViewById(R.id.m_chapter2);
        M_Bt3 = (Button) view.findViewById(R.id.m_chapter3);
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        M_Bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"等待添加数据库",Toast.LENGTH_SHORT).show();
            }
        });
        M_Bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"等待添加数据库",Toast.LENGTH_SHORT).show();
            }
        });
        M_Bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"等待添加数据库",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
