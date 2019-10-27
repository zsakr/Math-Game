package edu.trincoll.dchitrak.mathgame;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    boolean toggle = true;

    public SoundPool soundPool;
    public int SDifficulty, SEnd, SRight, SWrong;

    private void menuSelection(){
        /* this button is for the fixed game setup */
        Button press = (Button) findViewById(R.id.button);
        press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(SDifficulty,1,1,0,0, 2);
                Intent startint = new Intent(getApplicationContext(), Difficulty.class);
                startint.putExtra("gameType", "FixedGame.class");

                startActivity(startint);
            }
        });


        /* this button is for the timed game setup */
        Button timePress = (Button) findViewById(R.id.timeButton);
        timePress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(SDifficulty,1,1,0,0, 2);
                Intent startint = new Intent(getApplicationContext(), Difficulty.class);
                startint.putExtra("gameType", "TimedGame.class");
                startActivity(startint);
            }
        });


        /* this button is for the infinite mode setup */
        Button infinitePress = (Button) findViewById(R.id.infinityButton);
        infinitePress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                soundPool.play(SDifficulty,1,1,0,0, 2);
                Intent startint = new Intent(getApplicationContext(), Difficulty.class);
                startint.putExtra("gameType", "InfiniteMode.class");
                startActivity(startint);
            }
        });
    }

    private void rocketAnimate() {

//        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -2000.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
//        animation.setDuration(10000);  // animation duration
//        //animation.setRepeatCount(5);  // animation repeat count
//        //animation.setFillAfter(true);
//
//        /* this button is for the infinte mode setup */
        Button rocketPress = (Button) findViewById(R.id.blastButt);
        rocketPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggle){
                ImageView animate = findViewById(R.id.rocket);
                animate.setImageResource(R.drawable.bomb_transparent);
                toggle = !toggle;
            }else{
                    ImageView animate = findViewById(R.id.rocket);
                    animate.setImageResource(R.drawable.rocket);
                    toggle = !toggle;
                }
            }
        });



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(4)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }

        SDifficulty = soundPool.load(this, R.raw.difficulty, 1);
        SEnd = soundPool.load(this, R.raw.end, 1);
        SRight = soundPool.load(this, R.raw.right, 1);
        SWrong = soundPool.load(this, R.raw.wrong, 1);




        menuSelection();
        rocketAnimate();
    }}

