package edu.temple.webbrowingapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PageControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageControlFragment extends Fragment {
    ImageButton back;
    ImageButton forward;
    ImageButton search;
    EditText url;
     View L;
    ButtonClickInterface buttonClickInterface;
    private static final String U_R_L = "Link";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PageControlFragment() {
        // Required empty public constructor
    }
    public interface ButtonClickInterface {
        void OnInputurl(CharSequence input);
        void backButton();
        void forwardButton();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof  ButtonClickInterface){
            buttonClickInterface = (ButtonClickInterface)context;
        }else{
            throw new RuntimeException("You much impleament");
        }
    }
    public static PageControlFragment newInstance(CharSequence url){
        PageControlFragment pageControlFragment = new PageControlFragment();
        Bundle args = new Bundle();
        args.putCharSequence(U_R_L,url);
        pageControlFragment.setArguments(args);
        return pageControlFragment;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PageControlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PageControlFragment newInstance(String param1, String param2) {
        PageControlFragment fragment = new PageControlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         L =  inflater.inflate(R.layout.fragment_page_control, container, false);
        back = L.findViewById(R.id.back_btn);
        forward = L.findViewById(R.id.forward_btn);
        search = L.findViewById(R.id.search_btn);
        url = L.findViewById(R.id.url_txt);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = url.getText();
                Toast.makeText(getActivity(),"You have clicked the search button  " + input,Toast.LENGTH_LONG).show();
                buttonClickInterface.OnInputurl(input);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"You have clicked the back button  " ,Toast.LENGTH_SHORT).show();
                buttonClickInterface.backButton();
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"You have clicked the forward button  " ,Toast.LENGTH_LONG).show();
                buttonClickInterface.forwardButton();
            }
        });


        return L;
    }
}