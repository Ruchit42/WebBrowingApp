package edu.temple.webbrowingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PageControlFragment.ButtonClickInterface {
    PageControlFragment fragmentA = new PageControlFragment();
    PageViewerFragment fragmentB = new PageViewerFragment();
    public WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BrowserActivity");
        getSupportFragmentManager().beginTransaction().add(R.id.page_control,fragmentA)
                .add(R.id.page_viewer,fragmentB).commit();


    }

    @Override
    public void OnInputurl(CharSequence input) {
        myWebView = fragmentB.view.findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                 super.shouldOverrideUrlLoading(view, url);
                 view.loadUrl(url);
                 return true;
            }
        });
        myWebView.loadUrl( input.toString());
    }

    @Override
    public void backButton() {
        myWebView = fragmentB.view.findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        if(myWebView.canGoBack()){
            myWebView.goBack();
        }

    }

    @Override
    public void forwardButton() {
        myWebView = fragmentB.view.findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        if(myWebView.canGoForward()){
            myWebView.goForward();
        }
    }
}