package moe.utaha.projectmaina;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

import cz.msebera.android.httpclient.Header;

/**
 * Created by chojeaho on 2016-07-15.
 */
public class Parser{
    Document doc;
    public Parser(InputStream is,String url) throws IOException
    {
        doc = Jsoup.parse(is,null,url);
        Elements elements = doc.select("h2#section");
        Log.e("html parse","-------------"+elements);
    }

    Document getHtml(){
        return doc;
    }
}
