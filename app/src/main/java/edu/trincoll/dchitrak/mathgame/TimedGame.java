package edu.trincoll.dchitrak.mathgame;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TimedGame extends AppCompatActivity {
    public SoundPool soundPool;
    public int diff, boom, end, fix, right, wrong;
    protected int counter = 0;
    protected final int MAXTIME = 60000;    // max time in millis
    protected final int SECOND = 1000;      // sec is 1000 millis

    private final NumTrack tracker = new NumTrack();
    private GenerateProblem problem;


    // functiont to determine which constructor to use base on dec/hex/binary
    private void chooseType(){
        String Game = getIntent().getExtras().getString("type");
        if(Game.equals("binary")){
            problem= new Binary();
        }else if(Game.equals("dec")){
            problem= new Decimal();
        }else if(Game.equals("hex")){
            problem = new Hex();
        }
    }

    // clears the input value
    protected void clearInputText() {

        EditText inputText = findViewById(R.id.guessInput);
        inputText.setText("");

    }

    // displays a new problem
    protected void displayProblem() {

        // set up layout for problem
        TextView num1Text = findViewById(R.id.num1);
        TextView num2Text =  findViewById(R.id.num2);
        TextView opText =  findViewById(R.id.operator);

        //init vars and screen
        problem.makeProblem();
        num1Text.setText(problem.getNum1()+"");
        num2Text.setText(problem.getNum2()+"");
        opText.setText(problem.getOp()+"");

    }

//    // displays the fire/streak image
//    private void displayStreak(){
//        ImageView streak = (ImageView) findViewById(R.id.fixedStreak);
//        StreakDisplayer sdisplay = new StreakDisplayer(streak);
//        sdisplay.setStreak(tracker.getStreaks());
//        sdisplay.dispStreak();
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timed_game);
        chooseType();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }

        diff = soundPool.load(this, R.raw.difficulty, 1);
        boom = soundPool.load(this, R.raw.boom, 1);
        end = soundPool.load(this, R.raw.end, 1);
        fix = soundPool.load(this, R.raw.fix, 1);
        right = soundPool.load(this, R.raw.right, 1);
        wrong = soundPool.load(this, R.raw.wrong, 1);
        final EditText inputText = findViewById(R.id.guessInput);
        final TextView timerText = findViewById(R.id.countdown);
        final TextView scoreText = findViewById(R.id.score);

        displayProblem();


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
                soundPool.play(end, 1, 1, 0, 0, 1);
                startint.putExtra("Score", tracker.getScore()+"");
                startint.putExtra("Time", tracker.getTime()+"");
                startActivity(startint);
            }


        }.start();




        //set up exit button, which returns to home page
       ImageButton exit = (ImageButton) findViewById(R.id.exitButton);

       exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                Intent startint = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startint);
            }
        });


       // set up submit button
        Button submitButton = findViewById(R.id.submitButton);

       submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = inputText.getText().toString();

                if (value.equals(problem.getResults())) {  // problem answered correctly
                    soundPool.play(right, 1, 1, 0, 0, 1);
                    tracker.recalculateScore();
                    displayProblem();           // change problem only if answered correctly
                } else {
                    soundPool.play(wrong, 1, 1, 0, 0, 1);
                    tracker.resetStreak();
                }

                scoreText.setText("");
                clearInputText();
                scoreText.setText((tracker.getScore()+""));

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }

    @Override
    public void onBackPressed()
    {
        Intent startint = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(startint);
    }
}
