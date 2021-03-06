package com.example.h.medapp;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;

import android.content.Intent;

public class MainActivity extends ActionBarActivity {

    Button button;
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button)findViewById(R.id.button);

    }

    //create sendMessage
    public void sendMessage(View view){
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation_out, R.anim.animation_in).toBundle();
        Intent intent = new Intent(this,QuestionActivity.class);
        startActivity(intent, bndlanimation);
    }



}
