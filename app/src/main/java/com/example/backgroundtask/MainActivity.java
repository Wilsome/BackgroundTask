package com.example.backgroundtask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText secondsInput;
    private TextView delayInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        secondsInput = findViewById(R.id.editTextText2);
        delayInfo = findViewById(R.id.textView3);
        delayInfo.setVisibility(View.INVISIBLE);
    }

    public void startDelay(View view) {
        // Get the number of seconds from the input
        String secondsStr = secondsInput.getText().toString();
        int seconds = Integer.parseInt(secondsStr);

        // Start AsyncTask to delay
        new DelayTask().execute(seconds);
    }

    private class DelayTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            try {
                // Sleep for the specified number of seconds
                Thread.sleep(params[0] * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Update TextView to display delay info
            int secondsDelayed = Integer.parseInt(secondsInput.getText().toString());
            delayInfo.setVisibility(View.VISIBLE);
            delayInfo.setText("Your message was delayed by " + secondsDelayed + " seconds.");
        }

    }
}
