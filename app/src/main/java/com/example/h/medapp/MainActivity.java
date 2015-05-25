package com.example.h.medapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class MainActivity extends ActionBarActivity {

    Button button;
    TextView content;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        content = (TextView)findViewById(R.id.textview);
        button.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v)
            {
                try{
                    // CALL GetText method to make post method call
                    GetText();
                }
                catch(Exception ex)
                {
                    content.setText(" url exeption! " );
                }
            }
        });
    }

    // Create GetText Metod
    public  void  GetText()  throws UnsupportedEncodingException
    {
        // Get user defined values

        // Create data variable for sent values to server

        String data = URLEncoder.encode("patient_number", "UTF-8")
                + "=" + URLEncoder.encode("1", "UTF-8");

        for(int i=1; i<11; i++){
            String temp = "q";
            String q = temp + i;
            System.out.println(q);

            data += "&" + URLEncoder.encode(q, "UTF-8") + "="
                    + URLEncoder.encode("5", "UTF-8");

        }


        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL url = new URL("localhost:8080/app/src/main/php/create_result.php");

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null) {
                // Append server response in string
                System.out.println(line);
                sb.append(line + "\n");
            }
            text = sb.toString();
        }
        catch(Exception ex)
        {

        }
        finally
        {try{
                reader.close();
            }
        catch(Exception ex) {}
        }
        // Show response on activity
        content.setText( text  );
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
