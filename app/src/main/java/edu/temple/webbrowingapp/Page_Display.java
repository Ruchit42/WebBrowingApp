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
ViewPager myPager;
PagerDisplayInterface pagerDisplayInterface;
    ArrayList<PageViewerFragment> viewerFragmentsArray;
    View viewGroup;

ArrayList<PageViewerFragment> viewerFragments;
    public Page_Display() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    interface PagerDisplayInterface{
        ArrayList<PageViewerFragment> getPageViewerList();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof PagerDisplayInterface){
            pagerDisplayInterface = (PagerDisplayInterface) context;
        }else {
            throw new RuntimeException("Please implement Interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_page__display,container,false);
        myPager = viewGroup.findViewById(R.id.viewPager);
        viewerFragmentsArray = pagerDisplayInterface.getPageViewerList();



        myPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return viewerFragmentsArray.get(position);
            }

            @Override
            public int getCount() {
                return viewerFragmentsArray.size();
            }
        });
        return viewGroup;
    }
}