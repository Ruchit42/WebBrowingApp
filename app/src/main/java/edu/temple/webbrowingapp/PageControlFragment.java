package edu.temple.webbrowingapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PageControlFragment extends Fragment {
    //PageControlFragment pageControlFragment;
    ImageButton back;
    ImageButton forward;
    ImageButton search;

     View L;
    EditText editText;
    ButtonClickInterface buttonClickInterface;

    public PageControlFragment() {
        // Required empty public constructor
    }
    public interface ButtonClickInterface {
        void OnInputurl(String input);
        void backButton();
        void forwardButton();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof  ButtonClickInterface){
            buttonClickInterface = (ButtonClickInterface)context;
        }else{
            throw new RuntimeException("You must impleament");
        }
    }

    public void refreshURL (String url) {
        editText.setText(url);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        buttonClickInterface = null;
    }
//    @Override
//    public void onActivityCreated(@NonNull Bundle savedInstanceState){
//        super.onActivityCreated(savedInstanceState);
//        setRetainInstance(true);
//        if(savedInstanceState != null){
//            buttonClickInterface = (MainActivity)getActivity();
//        }
//    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         L =  inflater.inflate(R.layout.fragment_page_control, container, false);
        back = L.findViewById(R.id.back_btn);
        forward = L.findViewById(R.id.forward_btn);
        search = L.findViewById(R.id.search_btn);
        editText = L.findViewById(R.id.url_txt);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                Toast.makeText(getActivity(),"You have clicked the search button  " + input,Toast.LENGTH_LONG).show();
                Log.e("check input", input);
                buttonClickInterface.OnInputurl(input);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getActivity(),"You have clicked the back button  " ,Toast.LENGTH_SHORT).show();
                buttonClickInterface.backButton();
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getActivity(),"You have clicked the forward button  " ,Toast.LENGTH_LONG).show();
                buttonClickInterface.forwardButton();
            }
        });


        return L;
    }
}