package edu.temple.webbrowingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Page_List_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Page_List_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match


    public Page_List_Fragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page__list_, container, false);
    }
}