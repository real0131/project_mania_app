package moe.utaha.projectmaina;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by chojeaho on 2016-07-03.
 */
public class Tutorial_0 extends android.support.v4.app.Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.tutorial_1,container,false);
        return linearLayout;
    }
}
