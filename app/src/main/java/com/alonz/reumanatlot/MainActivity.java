package com.alonz.reumanatlot;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recycleView;
    private ProgressBar pb;


    private ArrayList<String> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.tab_and_viewpage);


        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

//        setContentView(R.layout.activity_main);
//        pb = (ProgressBar)findViewById(R.id.pb);
//            recycleView = (RecyclerView) findViewById(R.id.recyclerview);
//            recycleView.setHasFixedSize(true);
//        recycleView.setLayoutManager(new GridLayoutManager(this, 3));
//        //new GetDataFromFireBase().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        Intent intent =getIntent();
//        intent.getStringExtra("color");
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference root = database.getReference("Natlot");
//        DatabaseReference colorRoot = root.child(intent.getStringExtra("color"));
//        colorRoot.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    ArrayList<String> values = (ArrayList<String>) dataSnapshot.getValue();
//                    recycleView.setAdapter(new ItemAdapter(getApplicationContext(), values));
//
//                }
//                @Override
//                public void onCancelled(DatabaseError error) {
//                    // Failed to read value
//                    Log.w("hh", "Failed to read value.", error.toException());
//                }
//            });
    }
    }
