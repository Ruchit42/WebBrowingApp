package edu.temple.webbrowingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PageViewerFragment extends Fragment implements Parcelable {


     public WebView myWebView;
     View view;
    PageViewerInterface pageViewerInterface;

    public PageViewerFragment() {
        // Required empty public constructor
    }

    protected PageViewerFragment(Parcel in) {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    public static final Parcelable.Creator<PageViewerFragment> CREATOR = new Parcelable.Creator<PageViewerFragment>() {
        @Override
        public PageViewerFragment createFromParcel(Parcel in) {
            return new PageViewerFragment(in);
        }

        @Override
        public PageViewerFragment[] newArray(int size) {
            return new PageViewerFragment[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
    @Override
    public void onActivityCreated(@NonNull Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }
    public interface PageViewerInterface{
        void updateURL(String url);
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if(context instanceof  PageViewerInterface){
            pageViewerInterface = (PageViewerInterface)context;
        }else{
            throw new RuntimeException("Implement your interface");
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        myWebView.saveState(outState);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        // Inflate the layout for this fragment
        if(savedInstanceState == null) {
            view = inflater.inflate(R.layout.fragment_page_viewer, container, false);
            myWebView = view.findViewById(R.id.webview);
            myWebView.getSettings().setJavaScriptEnabled(true);
            myWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    pageViewerInterface.updateURL(url);
                }


            });
            if (savedInstanceState != null) {
                myWebView.restoreState(savedInstanceState);
            }
        }



        return view;
    }
    public void goForward(){
        myWebView.goForward();
    }

    public void goBack(){
        myWebView.goBack();
    }

    public void searchButt(String url){
        Log.e("Check", url);

        myWebView.loadUrl(url);
    }
    public String getLink(){
        return myWebView.getTitle();
    }
}