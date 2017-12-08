package com.alonz.reumanatlot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alonz.reumanatlot.Natlot.NatlotActivity;

public class CategoryActivity extends AppCompatActivity {
    private TextView mTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_actvity);
        Button natlotButton= findViewById(R.id.natlotButton);
        Button meyhamButton= findViewById(R.id.meyhamButton);
        Button sakomonimButton= findViewById(R.id.sacomonimButton);
        Button kidushButton= findViewById(R.id.kidushButton);
        Button holdersButton= findViewById(R.id.holdersButton);
        Button saltButton= findViewById(R.id.saltButton);


        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        natlotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CategoryActivity.this, NatlotActivity.class);
                intent.putExtra("color","Red");
                startActivity(intent);
            }
        });
        meyhamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CategoryActivity.this, MeyhamActivity.class);
                intent.putExtra("item","Meyham");
                startActivity(intent);
            }
        });
        sakomonimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CategoryActivity.this, MeyhamActivity.class);
                intent.putExtra("item","Sakomonim");
                startActivity(intent);
            }
        });
        kidushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CategoryActivity.this, MeyhamActivity.class);
                intent.putExtra("item","Kidush");
                startActivity(intent);
            }
        });
        holdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CategoryActivity.this, MeyhamActivity.class);
                intent.putExtra("item","Holders");
                startActivity(intent);
            }
        });
        saltButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CategoryActivity.this, MeyhamActivity.class);
                intent.putExtra("item","Salt");
                startActivity(intent);
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent =new Intent(CategoryActivity.this, MeyhamActivity.class);
                    intent.putExtra("item","Salt");
                    startActivity(intent);
                    break;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };
}
