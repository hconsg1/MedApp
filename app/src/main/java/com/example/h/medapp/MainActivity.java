package com.example.h.medapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    Button button;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            a = button.getText().toString();

            new SummaryAsyncTask().execute((Void) null);
        }
        });
    }

    class SummaryAsyncTask extends AsyncTask<Void, Void, Boolean> {

        private void postData(String patient_id, int[] scores) {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("localhost:8080/app/src/main/php/create_result.php");

            try {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(11);
                nameValuePairs.add(new BasicNameValuePair("id", patient_id));
                for(int i =0; i<scores.length; i++ ){
                    String title = "q" + i;
                    String score = String.valueOf(scores[i]);
                    nameValuePairs.add(new BasicNameValuePair(title, score));
                }
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
            }
            catch(Exception e)
            {
                Log.e("log_tag", "Error:  " + e.toString());
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            int[] a = {5,5,5,5,5,5,5,5,5,5};
            postData("test", a);
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
