package moe.utaha.projectmaina;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {
    WebView webView;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        if (getTutorial()==0)
        {
            Intent intent = new Intent(ScrollingActivity.this,TutorialActivity.class);
            startActivity(intent);
        }
        */
        url = "http://google.com";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        Toast.makeText(getApplicationContext(),"this is scroll",Toast.LENGTH_LONG).show();
    }


    int getTutorial(){
        SharedPreferences pref = getSharedPreferences("Tutorial", MODE_PRIVATE);
        return pref.getInt("isTutorial", 0);
    }
}
