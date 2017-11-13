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
        setContentView(R.layout.category_actvity);
        Button redButton= (Button)findViewById(R.id.RedButton);
        Button cyanButton=(Button)findViewById(R.id.CyanButton);

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CategoryActivity.this, MainActivity.class);
                intent.putExtra("color","Red");
                startActivity(intent);
            }
        });

        cyanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CategoryActivity.this, MainActivity.class);
                intent.putExtra("color","Cyan");
                startActivity(intent);
            }
        });

    }



}
