package edu.temple.webbrowingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;



import java.io.Serializable;
import java.lang.reflect.Type;
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
    private static final String KEY = "fragments";
    private static final String BookMarkKey = "bookmarks";
    public ArrayList<BookMark> bookmarks;
    public static MainActivity instance;
    private static final String SHARED_PREFS= "MY_SHARED_PREF";
    private static final String SAVE_KEY= "TASK_LIST";
    //First array list of bookmarks , second get URL and tile of the current page , start inside the bookmark object
    //transfer bookmarks from main activity to bookmark activity
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        if (savedInstanceState != null) {
            viewerArray = (ArrayList<PageViewerFragment>) savedInstanceState.getSerializable(KEY);
            bookmarks = (ArrayList<BookMark>) savedInstanceState.getSerializable(BookMarkKey);
        } else {
            viewerArray = new ArrayList<>();
            bookmarks = new ArrayList<>();
        }

        addFragments();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sharemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String getUrl = viewerArray.get(pagerFragment.myViewPager.getCurrentItem()).myWebView.getUrl();
                if(getUrl == null){
                    getUrl = "Web site link broken!";
                }
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getUrl);
                startActivity(Intent.createChooser(sharingIntent, "Sharing Option:"));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
    protected void onStart() {
        super.onStart();
        if(viewerArray.size() == 0) {
            viewerArray.add(new PageViewerFragment());
            pagerFragment.myViewPager.getAdapter().notifyDataSetChanged();
        }

    }

    @Override
    public void updateURL(String url) {
        pageControlFragment.editText.setText(viewerArray.get(pagerFragment.myViewPager.getCurrentItem()).myWebView.getUrl());
        pagerFragment.myViewPager.getAdapter().notifyDataSetChanged();
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
            pagerFragment.myViewPager.getAdapter().notifyDataSetChanged();
        }


        if(!urlInput.startsWith("https://")){

            viewerArray.get(pagerFragment.myViewPager.getCurrentItem()).searchButt("https://" + urlInput);
            String input = viewerArray.get(pagerFragment.myViewPager.getCurrentItem()).myWebView.getUrl();
            pagerFragment.myViewPager.getAdapter();
            pageControlFragment.editText.setText(input);
        }else {

            viewerArray.get(pagerFragment.myViewPager.getCurrentItem()).searchButt(urlInput);
            String input = viewerArray.get(pagerFragment.myViewPager.getCurrentItem()).myWebView.getUrl();
            pageControlFragment.editText.setText(input);
        }


    }

    @Override
    public void addButton() {
        if(viewerArray == null){
            viewerArray.add( new PageViewerFragment());
            pagerFragment.myViewPager.getAdapter().notifyDataSetChanged();
        }
        viewerArray.add(new PageViewerFragment());
        pagerFragment.myViewPager.getAdapter().notifyDataSetChanged();
        pagerFragment.myViewPager.setCurrentItem(viewerArray.size()-1);



    }
    @Override
    public void viewBookmark(){
        Intent goToBookMarkActivity = new Intent(getApplicationContext(), BookmarkActivity.class);
        /*Put parcelable array list here*/
        goToBookMarkActivity.putParcelableArrayListExtra("BOOKMARKS_ARRAYLIST", bookmarks);
        startActivity(goToBookMarkActivity);

    }


    @Override
    public void addBookmark(){
        String URL = viewerArray.get(pagerFragment.myViewPager.getCurrentItem()).myWebView.getUrl();
        String siteTitle = viewerArray.get(pagerFragment.myViewPager.getCurrentItem()).myWebView.getTitle() ;
        BookMark toAdd = new BookMark(URL, siteTitle);
        bookmarks.add(toAdd);


    }
    public static MainActivity getInstance() {
        return instance;
    }


    @Override
    public ArrayList<PageViewerFragment> getPageViewerList() {
        return viewerArray;
    }

    @Override
    public String getbackURL() {
        Intent intent = getIntent();
        String receivedAction = intent.getAction();
        Uri data = intent.getData();
        if(data == null){
            return "https//google.com";
        }
        return data.toString();
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY, viewerArray);
        outState.putSerializable(BookMarkKey,bookmarks);
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
