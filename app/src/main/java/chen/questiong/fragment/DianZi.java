package chen.questiong.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import chen.questiong.R;
import chen.questiong.chapter.ChapterOne;
import chen.questiong.chapter.ChapterThree;
import chen.questiong.chapter.ChapterTow;

public class DianZi extends Fragment {

private View dianzi;
    private ImageButton D_Bt1;
    private ImageButton D_Bt2;
    private ImageButton D_Bt3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dianzi = inflater.inflate(R.layout.fragment_dian_zi, container, false);
       D_Bt1 = (ImageButton) dianzi.findViewById(R.id.D_chapter1);
       D_Bt2 = (ImageButton) dianzi.findViewById(R.id.D_chapter2);
       D_Bt3 = (ImageButton) dianzi.findViewById(R.id.D_chapter3);
        return dianzi;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        D_Bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentClass(ChapterOne.class);
            }
        });
        D_Bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentClass(ChapterTow.class);
            }
        });
        D_Bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentClass(ChapterThree.class);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void intentClass(Class cl){
        Intent intent = new Intent(getActivity(), cl);
        startActivity(intent);
    }
}
