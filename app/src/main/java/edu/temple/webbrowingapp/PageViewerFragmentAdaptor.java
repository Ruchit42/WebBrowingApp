package edu.temple.webbrowingapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class PageViewerFragmentAdaptor extends BaseAdapter {

    ArrayList<PageViewerFragment> viewerArray;
    final Context context;
    TextView itemText;
    ViewPager viewPager;


    public PageViewerFragmentAdaptor(Context context, ArrayList<PageViewerFragment> viewerArray, ViewPager viewPager){
        this.viewerArray = viewerArray;
        this.context = context;
        this.viewPager = viewPager;
    }


    @Override
    public int getCount() {
        return viewerArray.size();
    }

    @Override
    public Object getItem(int i) {
        return viewerArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view != null){
            itemText = (TextView) view;
        }else{
            itemText = new TextView(context);
            itemText.setText(viewerArray.get(i).myWebView.getTitle());

        }
        return itemText;
    }
}

