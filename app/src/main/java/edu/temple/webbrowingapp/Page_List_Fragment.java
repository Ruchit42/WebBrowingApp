package edu.temple.webbrowingapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class Page_List_Fragment extends Fragment {

    View v;
    PageListInterface pageListListener;
    ArrayList<PageViewerFragment> viewersList;
    ListView listView;
    ViewPager viewPager;
    PageViewerFragmentAdaptor adapter;

    public Page_List_Fragment() {
        // Required empty public constructor
    }

    interface PageListInterface{
        ArrayList<PageViewerFragment> getArray();
        ViewPager getViewPager();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof PageListInterface){
            pageListListener = (PageListInterface) context;
        }else{
            throw new RuntimeException("Please implement PageListInterface");
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
        v =  inflater.inflate(R.layout.fragment_page__list_, container, false);
        listView = v.findViewById(R.id.webSite_List);
        viewersList = pageListListener.getArray();
        viewPager = pageListListener.getViewPager();
        adapter = new PageViewerFragmentAdaptor(getActivity(), viewersList, viewPager);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posotion, long l) {
                viewPager.setCurrentItem(posotion);
            }
        });

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        pageListListener = null;

    }
}