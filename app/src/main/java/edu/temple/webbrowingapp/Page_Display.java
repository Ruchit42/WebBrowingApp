package edu.temple.webbrowingapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Page_Display extends Fragment {
    View v;
    ViewPager myViewPager;
    PagerFragmentInterface pagerFragmentListener;
    ArrayList<PageViewerFragment> viewerFragmentsArray;

    public Page_Display() {
        // Required empty public constructor
    }
    interface PagerFragmentInterface{
        ArrayList<PageViewerFragment> getPageViewerList();
        String getbackURL();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof PagerFragmentInterface){
            pagerFragmentListener = (PagerFragmentInterface) context;
        }else {
            throw new RuntimeException("Please implement PagerFragmentInterface");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_page__display, container, false);
        myViewPager = v.findViewById(R.id.viewPager);
        //get array list from activity
        viewerFragmentsArray = pagerFragmentListener.getPageViewerList();
        //communicate with activity
        myViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {


            @NonNull
            @Override
            public Fragment getItem(int position) {
                return viewerFragmentsArray.get(position);
            }

            @Override
            public int getCount() {
                return viewerFragmentsArray.size();
            }

            @Override
            public void finishUpdate(@NonNull ViewGroup container) {
                super.finishUpdate(container);
                String getbackURL = pagerFragmentListener.getbackURL();

                if (getbackURL != null && viewerFragmentsArray.size() == 1 && !getbackURL.equals("https://Enter a url")) {
                    viewerFragmentsArray.get(myViewPager.getCurrentItem()).myWebView.loadUrl(getbackURL);
                }
            }

        });




        return v;
    }
}