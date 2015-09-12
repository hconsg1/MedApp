/*
package com.example.h.medapp;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class QuestionActivity extends ActionBarActivity {

    private ViewGroup question_layout;
    RadioGroup group;
    ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);



        addItem();

    }
    public void addItem(){
        LayoutInflater inf = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = inf.inflate(R.layout.row,null);
        container = (ViewGroup) findViewById(R.id.container);

        final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.row, container, false);
        System.out.println("added");

        RadioGroup radioGroupX = (RadioGroup) newView.findViewById(R.id.radioGroup);
        radioGroupX.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                System.out.println("CHECKED");
                addItem();
            }
        });
        container.addView(newView, 0);
        container=(ViewGroup) findViewById(R.id.container);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
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
*/
/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.h.medapp;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * This sample demonstrates how to use system-provided, automatic layout transitions. Layout
 * transitions are animations that occur when views are added to, removed from, or changed within
 * a {@link ViewGroup}.
 *
 * <p>In this sample, the user can add rows to and remove rows from a vertical
 * {@link android.widget.LinearLayout}.</p>
 */
public class QuestionActivity extends Activity {
    /**
     * The container view which has layout change animations turned on. In this sample, this view
     * is a {@link android.widget.LinearLayout}.
     */
    private ViewGroup mContainerView;
    private int total_number_of_question = 10;
    private boolean answered_flag;
    private TextView textView;
    private ScrollView scrollView;
    int count =0;
    boolean fin = false;
    int result_array[] = new int[total_number_of_question];
    String[] colors = {"#ff03ef00","#ff5aff00","#fffeff00","#ffffae00","#ffff4800","#ffff1d00","#ffff0011"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        mContainerView = (ViewGroup) findViewById(R.id.container);
        textView = (TextView) findViewById(R.id.textViewX);

        addItem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }



    private void addItem() {
        answered_flag = false;
        textView.setText("Question " + (count + 1) + " / 10");

        // Instantiate a new "row" view.
        final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(
                R.layout.row, mContainerView, false);

        mContainerView.addView(newView);

        SeekBar seekBar = (SeekBar) newView.findViewById(R.id.progress_bar);
        TextView question_text_view = (TextView) newView.findViewById(R.id.question_text);
        Button next_button = (Button)newView.findViewById(R.id.next_button);
        final TextView slider_textView = (TextView) newView.findViewById(R.id.slider_text);

        scrollView.scrollBy(0, +1000);

        slider_textView.setVisibility(View.INVISIBLE);
        question_text_view.setText(questions_array[count]);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                answered_flag = true;
                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
                //TODO: save the result to an array
                result_array[count] = progress;
                slider_textView.setVisibility(View.VISIBLE);
                slider_textView.setText("you have chosen: " + seekBar.getProgress() + "/" + seekBar.getMax() +" as your degree of symptom.");

            }
        });


        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(answered_flag) {
                    //MAKE SURE THIS QUESTION IS NOT THE LAST QUESTION.
                    //IF IT IS THE LAST QUESTION GO TO THANK YOU ACTIVITY
                    if(count == total_number_of_question-1){
                        //TODO: save to parse



                        //TODO: go to thank you activity
                        Intent intent = new Intent(QuestionActivity.this, activity_T.class);
                        startActivity(intent);

                    }else{
                        //IF NOT GO TO NEXT QUESTION
                        count++;
                        addItem();
                    }


                }
            }
        });




    }


    private static final String[] questions_array = new String[]{
            "THIS IS QUESTION 1",
            "THIS IS QUESTION 2",
            "THIS IS QUESTION 3",
            "THIS IS QUESTION 4",
            "THIS IS QUESTION 5",
            "THIS IS QUESTION 6",
            "THIS IS QUESTION 7",
            "THIS IS QUESTION 8",
            "THIS IS QUESTION 9",
            "THIS IS QUESTION 10",
    };
}
