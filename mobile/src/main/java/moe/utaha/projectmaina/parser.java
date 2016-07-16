package moe.utaha.projectmaina;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by chojeaho on 2016-07-15.
 */
public class Parser extends AsyncHttpResponseHandler{
    private AsyncHttpClient asyncHttpClient;
    public Parser(String url){
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

    }
}
