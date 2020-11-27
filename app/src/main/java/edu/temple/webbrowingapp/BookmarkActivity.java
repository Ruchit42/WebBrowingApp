package edu.temple.webbrowingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import android.util.Log;
import android.view.View;

public class BookmarkActivity extends AppCompatActivity {
    ArrayList<BookMark> bookMarkArrayList;
    ListView listView;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        listView = findViewById(R.id.bookList);
        setTitle("Book Marks ");
//
        Intent getbookmarks = new Intent();
        bookMarkArrayList = getbookmarks.getParcelableArrayListExtra("BOOKMARKS_ARRAYLIST");

        Log.e("CODE",bookMarkArrayList.get(0).getTitle());

        BookMarkAdapter adapter = new BookMarkAdapter(this,bookMarkArrayList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String loadURL = bookMarkArrayList.get(position).getURL();
                MainActivity mainActivity = MainActivity.getInstance();
                mainActivity.addButton();
                mainActivity.OnInputurl(loadURL);
                finish();
            }
        });


    }


}