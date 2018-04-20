package com.example.sheld.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
    }

    public void restart(View view){
        //just send them back to the main my man
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
