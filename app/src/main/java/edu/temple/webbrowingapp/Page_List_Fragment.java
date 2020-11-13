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

    // TODO: Rename parameter arguments, choose names that match
    PageListInterface pageListInterface;
    View l;
    ArrayList<PageViewerFragment> viewerList;
    PageViewerFragmentAdaptor new_adaptor;
    ViewPager viewPager;
    ListView listView;

    public Page_List_Fragment() {
        // Required empty public constructor
    }

    interface PageListInterface {
        ArrayList<PageViewerFragment> getArray();

        ViewPager getViewPager();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof PageListInterface) {
            pageListInterface = (PageListInterface) context;
        } else {
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
        l = inflater.inflate(R.layout.fragment_page__list_, container, false);
        listView = l.findViewById(R.id.webSite_List);
        viewerList = pageListInterface.getArray();
        viewPager = pageListInterface.getViewPager();
        new_adaptor = new PageViewerFragmentAdaptor(getActivity(), viewerList, viewPager);
        listView.setAdapter(new_adaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewPager.setCurrentItem(position);
            }
        });

        return l;
    }

    public void onDetach() {
        super.onDetach();
        pageListInterface = null;

    }
}