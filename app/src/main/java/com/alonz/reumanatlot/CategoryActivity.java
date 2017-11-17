package com.alonz.reumanatlot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_actvity_test);
        Button natlotButton= (Button)findViewById(R.id.Natlot);
        Button meyhamButton= (Button)findViewById(R.id.meyhamim);


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
                intent.putExtra("color","Red");
                startActivity(intent);
            }
        });

    }



}
