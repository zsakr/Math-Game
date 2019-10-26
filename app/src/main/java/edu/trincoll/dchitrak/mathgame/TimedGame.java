package edu.trincoll.dchitrak.mathgame;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TimedGame extends AppCompatActivity {

    protected int counter = 0;
    protected final int MAXTIME = 5000;    // max time in millis
    protected final int SECOND = 1000;      // sec is 1000 millis
    private int numCorrect = 0;

    private NumTrack tracker = new NumTrack();
    private GenerateProblem prob = new GenerateProblem(1, 10);

    /* set up page */
    private TextView timerText = findViewById(R.id.countdown);

    /* set up layout for problem */
    private TextView num1 = (TextView) findViewById(R.id.num1);
    private TextView num2 = (TextView) findViewById(R.id.num2);
    private TextView op = (TextView) findViewById(R.id.operator);
    private EditText input = (EditText) findViewById(R.id.guessInput);
    private int realAnswer = -999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timed_game);

/*        //init vars and screen
        counter = 0;
        prob.genarateParam();
        num1.setText(prob.getNum1());
        num2.setText(prob.getNum2());
        op.setText(prob.getOp());
        realAnswer = prob.getResults();


        // create and display display a timer
        final CountDownTimer timer = new CountDownTimer(MAXTIME, SECOND){

            @Override
            public void onTick(long millisLeft) {
                timerText.setText(String.valueOf(MAXTIME/SECOND- counter));  // display a countdown timer in secs
                counter++;
            }

            @Override
            // go to results page when time is up
            public void onFinish(){
                Intent startint = new Intent(getApplicationContext(), ResultsPage.class);
                startint.putExtra("Score", tracker.getScore()+"");
                startint.putExtra("Time", tracker.getTime()+"");
                startActivity(startint);
            }


        }.start(); */


  /*      //set up exit button, which returns to home page
        ImageButton exitButton = (ImageButton) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                Intent startint = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startint);
            }
        });

       // set up submit button
        Button submitButton = (Button) findViewById(R.id.submitButton);

       submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guessString = input.getText().toString();
                int guess = Integer.parseInt(guessString);

                if (guess == realAnswer) {  // problem answered correctly

                    numCorrect++;
                    Log.d("Answer", "Correct");
                } else {
                    Log.d("Answer", "Error");
                }
            }
        }); */

    }


}
