package edu.temple.webbrowingapp;

import android.os.Parcel;
import android.os.Parcelable;

public class BookMark implements Parcelable {
    String URL;
    String title;

    public BookMark(String URL, String siteTitle){
        this.URL = URL;
        this.title = siteTitle;
    }

    public String getURL(){
        return this.URL;
    }

    public String get_web_Title(){
        return this.title;
    }

    protected BookMark(Parcel in) {
        URL = in.readString();
        title = in.readString();
    }

    public static final Creator<BookMark> CREATOR = new Creator<BookMark>() {
        @Override
        public BookMark createFromParcel(Parcel in) {
            return new BookMark(in);
        }

        @Override
        public BookMark[] newArray(int size) {
            return new BookMark[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(URL);
        parcel.writeString(title);
    }
}



