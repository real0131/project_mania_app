package moe.utaha.projectmaina;

import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , SearchView.OnQueryTextListener , SwipeRefreshLayout.OnRefreshListener{
    SearchView searchView; //액션바 검색
    WebView webView;
    Parser parser;
    private AsyncHttpClient asyncHttpClient;
    String url,link,query;
    final String BASE_URL="http://blog.saltfactory.net/android/using-https-on-android.html"; //최종(wiki.utaha.moe)
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         /*
        if (getTutorial()==0) //튜토리얼 뷰 불러오기
        {
            Intent intent = new Intent(ScrollingActivity.this,TutorialActivity.class);
            startActivity(intent);
        }
        */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // 툴바
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout); //네비게이션 드로어
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getWebView("asdf");
        getHttp("asdf");
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(webView.canGoBack()){
          webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //검색
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
       /* searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(),query+"",Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/
        //searchView.setQueryHint("검색할 단어를 입력해주세요");

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        //TODO
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        //TODO
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId())
        {
            case R.id.action_settings:
                return true;
            case R.id.action_search:
                searchView.setIconified(false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    int getTutorial(){
        SharedPreferences pref = getSharedPreferences("Tutorial", MODE_PRIVATE);
        return pref.getInt("isTutorial", 0);
    }

    void getWebView(String query){

        this.query = query;
        url = BASE_URL;//+this.query;//웹뷰
        link = "http://namu.wiki/w/";
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view,String url){
                //if(url is internal link)
                //내부 url일때
                Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
                if(url.matches(BASE_URL)){
                    Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_LONG).show();
                    return true;
                }else{return false;}
                //else if(url is outer link)
                //return true;
            }
        });
    }

    void getHttp(String query){
        final String url = BASE_URL;//+query;
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(getApplicationContext(), "통신성공" + statusCode, Toast.LENGTH_SHORT).show();
                String result = new String(responseBody, 0, responseBody.length);
                Log.i("sunrin", result);
                InputStream is = new ByteArrayInputStream(responseBody);
                try {
                    parser = new Parser(is,url);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "통신실패" + statusCode, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        webView.reload();
        getHttp(query);
        swipeRefresh.setRefreshing(false);
    }
}
