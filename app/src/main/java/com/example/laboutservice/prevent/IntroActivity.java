package com.example.laboutservice.prevent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.laboutservice.R;

public class IntroActivity extends AppCompatActivity {
    private ImageView goBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        goBtn=findViewById(R.id.goBtn);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(IntroActivity.this,LoginActivity.class);
                startActivity(intent);
            }

        });
    }
}