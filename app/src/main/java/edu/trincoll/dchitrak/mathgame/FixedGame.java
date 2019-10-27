package edu.trincoll.dchitrak.mathgame;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FixedGame extends AppCompatActivity {
    private GenerateProblem problem;
    private NumTrack tracker = new NumTrack();
    public SoundPool soundPool;
    public int diff, boom, end, fix, right, wrong;
    private int numQues = 20;
    private int number = 1;

    // functiont to determine which constructor to use base on dec/hex/binary
    private void chooseType(){
        String Game = getIntent().getExtras().getString("type");
        String diff = getIntent().getExtras().getString("difficulty");
        int min = 0, max = 0;
        if(Game.equals("binary")){
            if (diff.equals("easy")){
                min = 3;
                max = 8;
            }else if(diff.equals("medium")){
                min = 7;
                max = 16;
            }else{
                min = 13;
                max = 32;
            }
            problem= new Binary(min,max);
        }else if(Game.equals("dec")){
            if (diff.equals("easy")){
                min = 3;
                max = 14;
            }else if(diff.equals("medium")){
                min = 19;
                max = 73;
            }else{
                min = 69;
                max = 108;
            }
            problem= new Decimal(min, max);
        }else if(Game.equals("hex")){
            if (diff.equals("easy")){
                min = 4;
                max = 16;
            }else if(diff.equals("medium")){
                min = 12;
                max = 64;
            }else{
                min = 69;
                max = 256;
            }
            problem = new Hex(min, max);
        }
    }

    private void clearDisplay(){
        TextView tv1 = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        TextView tv3 = (TextView) findViewById(R.id.qsLeft);
        TextView tv4 = (TextView) findViewById(R.id.textView3);

        problem.setResults(-999999);
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");
    }

    private void displayProblem(){

        problem.makeProblem();
        TextView tv1 = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        TextView tv3 = (TextView) findViewById(R.id.qsLeft);
        TextView tv4 = (TextView) findViewById(R.id.textView3);


        tv1.setText(problem.getNum1()+"");
        tv2.setText(problem.getNum2()+"");
        tv3.setText(((numQues + 1) - number)+"");
        tv4.setText(problem.getOp());


        checkEnd();
    }

    private void buttonClick(){
        ImageButton exitButton = (ImageButton) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(diff, 1, 1, 0, 0, 1);
                Intent startint = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startint);
            }
        });

        final EditText ed = (EditText) findViewById(R.id.editText4);
        Button press = (Button) findViewById(R.id.button2);
        press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = ed.getText().toString();

                if (value.equals(problem.getResults())) {
                    //Log.d("Success", "Happy")
                    soundPool.play(right, 1, 1, 0, 0, 1);
                    tracker.recalculateScore();
                    TextView tvs = (TextView) findViewById(R.id.score);
                    number++;;
                    displayProblem();

                    tvs.setText(tracker.getScore()+"");
                } else {
                    //Log.d("Fail", "Sad");
                    soundPool.play(wrong, 1, 1, 0, 0, 1);
                    tracker.resetStreak();
                }
                ed.setText("");
                StreakDisplay();
            }
        });
    }
    private void StreakDisplay(){
        ImageView streak = (ImageView) findViewById(R.id.fixedStreak);
        StreakDisplayer sdisplay = new StreakDisplayer(streak);
        sdisplay.setStreak(tracker.getStreaks());
        sdisplay.dispStreak();
    }
    private void checkEnd(){
        //Log.d("Exit", "Help");
        SystemClock.sleep(100);
        if (number>numQues){
            clearDisplay();
            Intent startint;
            soundPool.play(end, 1, 1, 0, 0, 1);
            startint = new Intent(getApplicationContext(), ResultsPage.class);

            startint.putExtra("Score", tracker.getScore()+"");
            startint.putExtra("Time", tracker.getTimeInt()+"");
            startint.putExtra("Streaks", tracker.getStreaks()+"");

            startActivity(startint);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_game);
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
        chooseType();
        displayProblem();
        buttonClick();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
