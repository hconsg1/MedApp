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
import android.widget.TextView;

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
    private Button submit;
    private TextView textView;
    int count =0;
    boolean fin = false;
    ArrayList<Integer> res = new ArrayList<Integer>();
    String[] colors = {"#ff03ef00","#ff5aff00","#fffeff00","#ffffae00","#ffff4800","#ffff1d00","#ffff0011"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mContainerView = (ViewGroup) findViewById(R.id.container);
        submit = (Button) findViewById(R.id.submit);
        textView = (TextView) findViewById(R.id.textViewX);

        addItem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addItem() {
        // Instantiate a new "row" view.

        final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(
                R.layout.row, mContainerView, false);
        textView.setText(count+" / 10");


        RadioGroup group = (RadioGroup) newView.findViewById(R.id.radioGroup);
        group.setId(count);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton btn = (RadioButton) group.findViewById(checkedId);
                System.out.println(checkedId);


                btn.setTextColor(Color.parseColor(colors[checkedId - 2131492972]));


     //           btn.setBackgroundColor(-16777216);

                System.out.println(count);




                if (count==group.getId()){
                    res.add(checkedId);

                    if (res.size()==Questions.length){
                        submit.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                        fin=true;
                    }

                    count++;
                    addItem();
                }
                else{
                    System.out.println("fdifhid");
                    RadioButton preBtn = (RadioButton) (group.findViewById(res.get(group.getId())));
                    preBtn.setTextColor(Color.parseColor("#000000"));
                    res.set(group.getId(),checkedId);
                }




            }
        });

        if (!fin) {
            ((TextView) newView.findViewById(R.id.question1)).setText(Questions[count]);
            mContainerView.addView(newView, 0);
        }

        // Because mContainerView has android:animateLayoutChanges set to true,
        // adding this view is automatically animated.


    }

    public void submit(View view){

        for (int i=0; i<res.size();i++){
            System.out.println(res.get(i)- 2131492971);
        }
        System.out.println("res"+res.get(0));

        Intent intent = new Intent(this, activity_T.class);
        startActivity(intent);
    }

    /**
     * A static list of country names.
     */
    private static final String[] Questions = new String[]{
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
