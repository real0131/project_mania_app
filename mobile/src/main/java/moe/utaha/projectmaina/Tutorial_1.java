package moe.utaha.projectmaina;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Map;
import java.util.Set;

/**
 * Created by chojeaho on 2016-07-03.
 */
public class Tutorial_1 extends android.support.v4.app.Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.tutorial_2, container, false);
        Button btn_return = (Button)relativeLayout.findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SharedPreferences sp = getActivity().getSharedPreferences("Tutorial", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("isTutorial",1);
                editor.commit();*/ // use final app
                Intent intent = new Intent(getActivity(),ScrollingActivity.class);
                startActivity(intent);
            }
        });
        return relativeLayout;
    }
 }
