package edu.temple.webbrowingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PageControlFragment.ButtonClickInterface {
    PageControlFragment fragmentA = new PageControlFragment();
    PageViewerFragment fragmentB = new PageViewerFragment();
    public WebView myWebView;
    public EditText editText;
    ArrayList<CharSequence> urls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BrowserActivity");
        getSupportFragmentManager().beginTransaction().add(R.id.page_control,fragmentA)
                .add(R.id.page_viewer,fragmentB).commit();


    }

    @Override
    public void OnInputurl(final CharSequence input) {
        myWebView = fragmentB.view.findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)  {
                 super.shouldOverrideUrlLoading(view, url);
                 view.loadUrl(url);
                // urls.add(input);
                 return true;
            }


        });

        if(!input.toString().startsWith("https://")){
            myWebView.loadUrl(("https://"+input.toString()));
        }else{
        myWebView.loadUrl( input.toString());
    }
    }

    @Override
    public void backButton() {
        myWebView = fragmentB.view.findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        editText = fragmentA.L.findViewById(R.id.url_txt);


        if(myWebView.canGoBack()){

            myWebView.goBack();
            editText.setText(myWebView.getOriginalUrl());
           // editText.getText();
            Toast.makeText(fragmentB.getContext(),myWebView.getUrl() ,Toast.LENGTH_LONG).show();



        }

    }

    @Override
    public void forwardButton() {
        myWebView = fragmentB.view.findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        editText = fragmentA.L.findViewById(R.id.url_txt);
        if(myWebView.canGoForward()){
            myWebView.goForward();
            editText.setText((myWebView.getOriginalUrl()));
        }
    }
}