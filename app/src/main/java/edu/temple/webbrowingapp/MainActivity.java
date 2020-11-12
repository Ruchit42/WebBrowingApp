package edu.temple.webbrowingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PageControlFragment.ButtonClickInterface ,BrowserControlFragment.BrowserInterface, PageViewerFragment.browserInterface {
    PageControlFragment pageControlFragment;
    PageViewerFragment pageViewerFragment;
    BrowserControlFragment browserControlFragment;
    Page_Display page_display;
    Page_List_Fragment page_list_fragment;
    public WebView myWebView;
   FragmentManager fragmentManager;
   Fragment temp;
    ArrayList<CharSequence> urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BrowserActivity");

        fragmentManager = getSupportFragmentManager();

        if((temp = fragmentManager.findFragmentById(R.id.page_control)) instanceof PageControlFragment){
    pageControlFragment = (PageControlFragment) temp;
        }else{
            pageControlFragment = new PageControlFragment();
            fragmentManager.beginTransaction().add(R.id.page_control,pageControlFragment).commit();
        }

        if((temp = fragmentManager.findFragmentById(R.id.browser_control)) instanceof BrowserControlFragment  ){
            browserControlFragment = (BrowserControlFragment) temp;
        }else{
            browserControlFragment = new BrowserControlFragment() ;
            fragmentManager.beginTransaction().add(R.id.browser_control,browserControlFragment).commit();
        }
        if((temp = fragmentManager.findFragmentById(R.id.page_list)) instanceof Page_List_Fragment){
            page_list_fragment =  (Page_List_Fragment) temp;
        }else{
            page_list_fragment = new Page_List_Fragment();
            fragmentManager.beginTransaction().add(R.id.page_list,page_list_fragment).commit();

        }
        if((temp = fragmentManager.findFragmentById(R.id.pager)) instanceof  Page_Display){
            page_display = (Page_Display) temp;
        }else{
            page_display = new Page_Display();
            fragmentManager.beginTransaction().add(R.id.page_viewer,page_display).commit();
        }
    }

    @Override
    public void OnInputurl(final CharSequence input) {
        int i = page_display.myPager.getCurrentItem();
        if (page_display.viewerFragments.size() == 0) {
            page_display.viewerFragments.add(new PageViewerFragment());
            page_display.myPager.getAdapter().notifyDataSetChanged();
        }
        page_display.viewerFragments.get(i).searchButt(input.toString());

    }


    @Override
    public void backButton() {
        int i = page_display.myPager.getCurrentItem();
        page_display.viewerFragments.get(i).goBack();
           // editText.setText(myWebView.getOriginalUrl());



        }



    @Override
    public void forwardButton() {
        int i = page_display.myPager.getCurrentItem();
        page_display.viewerFragments.get(i).goForward();
    }

    @Override
    public void addButton() {
        findViewById(R.id.addPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page_display.viewerFragments.add(new PageViewerFragment());
                page_display.myPager.getAdapter().notifyDataSetChanged();
                Log.e("tagw","opennewPage");
            }
        });
    }


    @Override
    public void updateURL(String url) {

    }
}