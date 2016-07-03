package moe.utaha.projectmaina;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

/**
 * Created by chojeaho on 2016-07-01.
 */
public class TutorialActivity extends AppCompatActivity{
    int MAX_PAGE = 2;
    Fragment fragment = new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);



        ViewPager viewPager = (ViewPager) findViewById(R.id.TutorialView);
        viewPager.setAdapter(new adapter(getSupportFragmentManager()));
    }

    private class adapter extends FragmentPagerAdapter {
        public adapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if(position<0||MAX_PAGE<=position){
                return null;
            }
            switch (position)
            {
                case 0:
                    fragment = new Tutorial_0();
                    break;
                case 1:
                    fragment = new Tutorial_1();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }

    }
}
