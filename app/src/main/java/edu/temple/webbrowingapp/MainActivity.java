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
        Page_Display.PagerFragmentInterface,Page_List_Fragment.PageListInterface {

    Page_Display pagerFragment;
    FragmentManager fragmentManager;
    PageControlFragment pageControlFragment;
    BrowserControlFragment browserControlFragment;
    Page_List_Fragment pageListFragment;
    public ArrayList<PageViewerFragment> viewerArray;
    private static final String LIST_KEY = "fragments";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        //add page control
        if((temp = fragmentManager.findFragmentById(R.id.page_control)) instanceof PageControlFragment){
            pageControlFragment = (PageControlFragment) temp;
        }else{
            pageControlFragment = new PageControlFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.page_control, pageControlFragment)
                    .commit();
        }
        //add browser control
        if((temp = fragmentManager.findFragmentById(R.id.browser_control) )instanceof BrowserControlFragment){
            browserControlFragment = (BrowserControlFragment) temp;
        }else{
            browserControlFragment = new BrowserControlFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.browser_control, browserControlFragment)
                    .commit();
        }
        //add pager fragment
        if ((temp = fragmentManager.findFragmentById(R.id.page_viewer)) instanceof Page_Display){
            pagerFragment = (Page_Display) temp;
        }else {
            pagerFragment = new Page_Display();
            fragmentManager.beginTransaction().
                    add(R.id.page_viewer, pagerFragment)
                    .commit();

        }
        //add pageList
        if(findViewById(R.id.page_list) != null) {
            if ((temp = fragmentManager.findFragmentById(R.id.page_list)) instanceof Page_Display) {
                pageListFragment = (Page_List_Fragment) temp;
            } else {
                pageListFragment = new Page_List_Fragment();
                fragmentManager.beginTransaction()
                        .add(R.id.page_list, pageListFragment)
                        .commit();
            }
        }



    }

    @Override
    public void updateURL(String url) {
        pageControlFragment.refreshURL(url);
    }

    @Override
    public void forwardButton() {

        viewerArray.get(pagerFragment.myViewPager.getCurrentItem()).goForward();
    }

    @Override
    public void backButton() {

        viewerArray.get(pagerFragment.myViewPager.getCurrentItem()).goBack();
    }

    @Override
    public void OnInputurl(String urlInput) {
        if(viewerArray.size() == 0){
            addButton();
        }


        if(!urlInput.startsWith("https://")){

            viewerArray.get(pagerFragment.myViewPager.getCurrentItem()).searchButt("https://" + urlInput);
        }else {

            viewerArray.get(pagerFragment.myViewPager.getCurrentItem()).searchButt(urlInput);
        }


    }

    @Override
    public void addButton() {
        if(viewerArray == null){
            viewerArray.add( new PageViewerFragment());
        }
        viewerArray.add(new PageViewerFragment());
        pagerFragment.myViewPager.getAdapter().notifyDataSetChanged();



    }

    @Override
    public ArrayList<PageViewerFragment> getPageViewerList() {
        return viewerArray;
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(LIST_KEY, viewerArray);
    }

    @Override
    public ArrayList<PageViewerFragment> getArray() {
        return viewerArray;
    }

    @Override
    public ViewPager getViewPager() {
        return pagerFragment.myViewPager;
    }
}