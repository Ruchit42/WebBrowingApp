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
import android.widget.Toast;

public class BookmarkActivity extends AppCompatActivity {
    ListView bookMarkListView;
    ArrayList<BookMark> bookMarksList;

    private static final String SHARED_PREFS= "MY_SHARED_PREF";
    private static final String SAVE_KEY= "TASK_LIST";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        setTitle("Book Marks");



        Intent toget = getIntent();
        bookMarksList = toget.getParcelableArrayListExtra("BOOKMARKS_ARRAYLIST");



        Log.e("CODE", bookMarksList.get(0).get_web_Title());

        bookMarkListView = (ListView) findViewById(R.id.bookList);





        BookMarkAdapter adapter = new BookMarkAdapter(this, bookMarksList);
        bookMarkListView.setAdapter(adapter);
        bookMarkListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplication(), bookMarksList.get(i).getURL(), Toast.LENGTH_SHORT).show();
                String loadUrl = bookMarksList.get(i).getURL();
                MainActivity bi = MainActivity.getInstance();
                bi.addButton();
                bi.OnInputurl(loadUrl);
                finish();
            }

        });

    }



}