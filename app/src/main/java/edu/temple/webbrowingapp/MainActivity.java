package edu.temple.webbrowingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PageControlFragment.ButtonClickInterface ,
        BrowserControlFragment.BrowserInterface, PageViewerFragment.PageViewerInterface,
        Page_Display.PagerDisplayInterface,Page_List_Fragment.PageListInterface {

    PageControlFragment pageControlFragment;
    BrowserControlFragment browserControlFragment;
    Page_Display page_display;
    Page_List_Fragment page_list_fragment;
   FragmentManager fragmentManager;

    ArrayList<PageViewerFragment> viewerArray;
    private static final String LIST_KEY = "fragments";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BrowserActivity");

        if (savedInstanceState != null) {
            viewerArray = (ArrayList<PageViewerFragment>) savedInstanceState.getSerializable(LIST_KEY);
        } else {
            viewerArray = new ArrayList<>();
        }

        addFragments();
    }
    public void addFragments(){
        fragmentManager = getSupportFragmentManager();
        Fragment temp;

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


        if(findViewById(R.id.page_list) != null) {
            if ((temp = fragmentManager.findFragmentById(R.id.page_list)) instanceof Page_List_Fragment) {
                page_list_fragment = (Page_List_Fragment) temp;
            } else {
                page_list_fragment = new Page_List_Fragment();
                fragmentManager.beginTransaction().add(R.id.page_list, page_list_fragment).commit();
            }

            if ((temp = fragmentManager.findFragmentById(R.id.page_viewer)) instanceof Page_Display) {
                page_display = (Page_Display) temp;
            } else {
                page_display = new Page_Display();
                fragmentManager.beginTransaction().add(R.id.page_viewer, page_display).commit();
            }


        }
    }

    @Override
    public void OnInputurl(String input){

            if(viewerArray.size() == 0){
                addButton();
            }
            if(!input.startsWith("https://")){

                viewerArray.get(page_display.myPager.getCurrentItem()).searchButt("https://" + input);
            }else {

                viewerArray.get(page_display.myPager.getCurrentItem()).searchButt(input);
            }
    }


    @Override
    public void backButton() {
       viewerArray.get(page_display.myPager.getCurrentItem()).goBack();
           // editText.setText(myWebView.getOriginalUrl());
        }



    @Override
    public void forwardButton() {
        viewerArray.get(page_display.myPager.getCurrentItem()).goForward();
    }

    @Override
    public void addButton() {
        if(viewerArray == null){
            viewerArray.add(new PageViewerFragment());
        }
        viewerArray.add((new PageViewerFragment()));
        page_display.myPager.getAdapter().notifyDataSetChanged();
    }


    @Override
    public void updateURL(String url) {
        pageControlFragment.refreshURL(url);
    }

    @Override
    public ArrayList<PageViewerFragment> getPageViewerList() {
        return viewerArray;
    }


    @Override
    public ArrayList<PageViewerFragment> getArray() {
        return  viewerArray;
    }

    @Override
    public ViewPager getViewPager() {
        return page_display.myPager;
    }


    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(LIST_KEY, viewerArray);
    }

}


