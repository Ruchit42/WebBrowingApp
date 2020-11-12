package edu.temple.webbrowingapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


public class BrowserControlFragment extends Fragment {

    ImageButton newPage;
    View L;
    BrowserInterface browserInterface;

    public BrowserControlFragment() {
        // Required empty public constructor
    }
    public interface BrowserInterface{
        void addButton();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof BrowserInterface){
            browserInterface = (BrowserInterface) context;
        }else{
            throw  new RuntimeException("Please implement PageControlListenter");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        L =  inflater.inflate(R.layout.fragment_browser_control, container, false);
        newPage = L.findViewById(R.id.addPage);
        newPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"You have clicked the add button  " ,Toast.LENGTH_LONG).show();
                browserInterface.addButton();
            }
        });
        return L ;
    }
}