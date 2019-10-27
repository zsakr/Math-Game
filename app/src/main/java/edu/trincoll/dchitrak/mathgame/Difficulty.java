package edu.trincoll.dchitrak.mathgame;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;



public class Difficulty extends AppCompatActivity {


    String gameTag;
    public SoundPool soundPool;
    public int diff, boom, end, fix, right, wrong;

    //set up exit button, which returns to home page
    private void onExitButtClick() {

        ImageButton exit = (ImageButton) findViewById(R.id.exitButton);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(diff, 1, 1, 0, 0, 1);
                Intent startint = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startint);
            }
        });
    }

    //set up easy button, which returns to home page
    private void onEasyButtClick() {

        Button easy = findViewById(R.id.EasyButt);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(diff, 1, 1, 0, 0, 1);
                Intent startint = new Intent(getApplicationContext(), ChooseType.class);
                startint.putExtra("gameType", gameTag);
                startint.putExtra("difficulty", "easy");
                startActivity(startint);
            }
        });
    }

    //set up medium button, which returns to home page
    private void onMedButtClick() {

        Button medium = findViewById(R.id.MediumButt);
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(diff, 1, 1, 0, 0, 1);
                Intent startint = new Intent(getApplicationContext(), ChooseType.class);
                startint.putExtra("gameType", gameTag);
                startint.putExtra("difficulty", "medium");
                startActivity(startint);
            }
        });
    }

    //set up medium button, which returns to home page
    private void onHardButtClick() {

        Button hard = findViewById(R.id.HardButt);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(diff, 1, 1, 0, 0, 1);
                Intent startint = new Intent(getApplicationContext(), ChooseType.class);
                startint.putExtra("gameType", gameTag);
                startint.putExtra("difficulty", "hard");
                startActivity(startint);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        gameTag = getIntent().getExtras().getString("gameType");
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

        onExitButtClick();
        onEasyButtClick();
        onMedButtClick();
        onHardButtClick();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }

}
