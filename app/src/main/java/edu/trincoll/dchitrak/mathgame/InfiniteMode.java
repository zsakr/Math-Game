package edu.trincoll.dchitrak.mathgame;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfiniteMode extends AppCompatActivity {

    private GenerateProblem problem;
    private NumTrack tracker = new NumTrack();
    private int number = 1;
    public SoundPool soundPool;
    public int diff, boom, end, fix, right, wrong;

    // functiont to determine which constructor to use base on dec/hex/binary
    private void chooseType() {
        String Game = getIntent().getExtras().getString("type");
        String diff = getIntent().getExtras().getString("difficulty");
        int min = 0, max = 0;
        if (Game.equals("binary")) {
            if (diff.equals("easy")) {
                min = 3;
                max = 8;
            } else if (diff.equals("medium")) {
                min = 7;
                max = 16;
            } else {
                min = 13;
                max = 32;
            }
            problem = new Binary(min, max);
        } else if (Game.equals("dec")) {
            if (diff.equals("easy")) {
                min = 3;
                max = 14;
            } else if (diff.equals("medium")) {
                min = 19;
                max = 73;
            } else {
                min = 69;
                max = 108;
            }
            problem = new Decimal(min, max);
        } else if (Game.equals("hex")) {
            if (diff.equals("easy")) {
                min = 4;
                max = 16;
            } else if (diff.equals("medium")) {
                min = 12;
                max = 64;
            } else {
                min = 69;
                max = 256;
            }
            problem = new Hex(min, max);
        }
    }



    private void clearDisplay(){
        TextView tv1 = (TextView) findViewById(R.id.infNum1);
        TextView tv2 = (TextView) findViewById(R.id.infNum2);
        TextView tv3 = (TextView) findViewById(R.id.infQs);
        TextView tv4 = (TextView) findViewById(R.id.infOp);

        problem.setResults(-999999);
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");
    }

    private void displayProblem(){

        problem.makeProblem();
        TextView tv1 = (TextView) findViewById(R.id.infNum1);
        TextView tv2 = (TextView) findViewById(R.id.infNum2);
        TextView tv3 = (TextView) findViewById(R.id.infQs);
        TextView tv4 = (TextView) findViewById(R.id.infOp);


        tv1.setText(problem.getNum1()+"");
        tv2.setText(problem.getNum2()+"");
        tv3.setText(number+"");
        tv4.setText(problem.getOp());


    }

    private void buttonClick(){
        ImageButton exitButton = (ImageButton) findViewById(R.id.infExit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(diff, 1, 1, 0, 0, 1);
                Intent startint = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startint);
            }
        });

        final EditText ed = (EditText) findViewById(R.id.infAns);
        Button press = (Button) findViewById(R.id.infGo);
        press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = ed.getText().toString();

                if (value.equals(problem.getResults())) {
                    //Log.d("Success", "Happy")
                    soundPool.play(right, 1, 1, 0, 0, 1);
                    tracker.recalculateScore();
                    TextView tvs = (TextView) findViewById(R.id.infScore);
                    number++;;
                    displayProblem();

                    tvs.setText(tracker.getScore()+"");
                } else {
                    //Log.d("Fail", "Sad");
                    soundPool.play(end, 1, 1, 0, 0, 1);
                    Intent startint = new Intent(getApplicationContext(), ResultsPage.class);
                    startint.putExtra("Score", tracker.getScore()+"");
                    startint.putExtra("Time", tracker.getTime()+"");
                    startActivity(startint);
                }
                ed.setText("");
                StreakDisplay();
            }
        });
    }

    private void StreakDisplay(){
        ImageView streak = (ImageView) findViewById(R.id.infinityStreak);
        StreakDisplayer sdisplay = new StreakDisplayer(streak);
        sdisplay.setStreak(tracker.getStreaks());
        sdisplay.dispStreak();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        setContentView(R.layout.activity_infinite_mode);
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
