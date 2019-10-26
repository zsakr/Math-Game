package edu.trincoll.dchitrak.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class TimedGame extends AppCompatActivity {

    protected int counter;
    protected final int MAXTIME = 5000;    // max time in millis
    protected final int SECOND = 1000;      // sec is 1000 millis


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timed_game);

        /* init vars for timer display */
        counter = 0;
        final TextView timerText = findViewById(R.id.countdownText);

        TextView tv1 = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);

        // display a timer
        new CountDownTimer(MAXTIME, SECOND){

            @Override
            public void onTick(long millisLeft) {
                timerText.setText(String.valueOf(MAXTIME/SECOND- counter));  /* display a countdown timer in secs */
                counter++;
            }

            @Override
            public void onFinish(){
                timerText.setText("Done");
                Intent startint = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startint);
            }



        }.start();

    }
}
