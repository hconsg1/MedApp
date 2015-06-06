package com.example.h.medapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.parse.Parse;
import com.parse.ParseObject;


import java.util.ArrayList;


public class activity_Q extends ActionBarActivity {

    private RadioGroup radioGroup;
    private Button btnNext;
    private int[] result;
    private String[] questions;
    private TextView question;
    private Intent intent;
    private ProgressBar mProgress;
    int count = 0;


    //write questions
    public void writeQ(){
        questions = new String[5];
        questions[0]="THIS IS Q1";
        questions[1]="THIS IS Q2";
        questions[2]="THIS IS Q3";
        questions[3]="THIS IS Q4";
        questions[4]="THIS IS Q5";

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "ycbnlbwMfF3gnEs7cUlMuC6Fonh59OXvOLpgaVDm", "RGf8b452l7jBzKtnitvZOSYjCeGQhZUkaALMkOQa");




        this.writeQ();

        mProgress = (ProgressBar) findViewById(R.id.progressBar1);
        intent = new Intent(this, activity_T.class);
        question  = (TextView) findViewById(R.id.textView2);
        question.setText(questions[0]);


        mProgress.setMax(questions.length);

        addButtonListener();
        result = new int[questions.length];

    }

    public void addButtonListener(){

        radioGroup =(RadioGroup) findViewById(R.id.radioGroup);
        btnNext = (Button) findViewById(R.id.button2);

        btnNext.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int btnId = radioGroup.getCheckedRadioButtonId();
                result[count]=btnId;
                radioGroup.clearCheck();
                count++;
                System.out.println(btnId);
                mProgress.setProgress(count);
                if (count == result.length-1){
                    btnNext.setText("Submit");
                }
                if (count == result.length){

                    startActivity(intent);
                    ParseObject testObject = new ParseObject("TestObject");
                    testObject.put("XXXXX",makeStringArray(result) );
                    testObject.saveInBackground();

                    System.out.println("XXXXXXXXXXXXXXXXXXXXX");
                }

                if (count < result.length){question.setText(questions[count]);}


            }

        });
    }

    public String makeStringArray(int[] res){
        String str = "";
        for (int i=0; i<res.length;i++){
            int ans = res[i]-2131492947;
            str += ans;
        }
        return str;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity__q, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
