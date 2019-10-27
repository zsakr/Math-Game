package edu.trincoll.dchitrak.mathgame;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseType extends AppCompatActivity {

    enum GameType {
        FIXED,
        TIMED,
        INFINITE,
        SHIT
    }


    public SoundPool soundPool;
    public int diff, boom, end, fix, right, wrong;

    GameType game;
    String difficulty;

    // when the exit button is clicked
    private void onExitClick() {
        ImageButton exitButton = (ImageButton) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(diff, 1, 1, 0, 0, 1);
                Intent startint = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startint);
            }
        });
    }

    // when the decimal button is clicked
    private void onDecimalClick() {
        Button decimalButton = (Button) findViewById(R.id.decimalButton);
        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startint = createIntent();
                soundPool.play(diff, 1, 1, 0, 0, 1);
                startint.putExtra("type", "dec");
                startint.putExtra("difficulty", difficulty);
                startActivity(startint);
            }
        });
    }

    // when the binary button is clicked
    private void onBinaryClick() {
        Button binaryButton = (Button) findViewById(R.id.binaryButton);
        binaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startint = createIntent();
                soundPool.play(diff, 1, 1, 0, 0, 1);
                startint.putExtra("type", "binary");
                startint.putExtra("difficulty", difficulty);
                startActivity(startint);
            }
        });
    }

    // when the hex button is clicked
    private void onHexClick() {
        Button hexButton = (Button) findViewById(R.id.hexButton);
        hexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startint = createIntent();
                soundPool.play(diff, 1, 1, 0, 0, 1);
                startint.putExtra("type", "hex");
                startint.putExtra("difficulty", difficulty);
                startActivity(startint);
            }
        });
    }

    // creates the appropriate intent based on the game type enum
    private Intent createIntent() {
        if (game == GameType.FIXED) {
            return new Intent(getApplicationContext(), FixedGame.class);
        } else if (game ==  GameType.INFINITE) {
            return new Intent(getApplicationContext(), InfiniteMode.class);
        } else if (game == GameType.TIMED) {
            return new Intent(getApplicationContext(), TimedGame.class);
        }

        return new Intent(getApplicationContext(), ResultsPage.class);
    }


    // convert tag to an enum game type
    private GameType convertGameTag(String gameTypeTag) {
        if (gameTypeTag.equals("FixedGame.class")) {
            return GameType.FIXED;
        } else if (gameTypeTag.equals("TimedGame.class")) {
            return GameType.TIMED;
        } else if (gameTypeTag.equals("InfiniteMode.class")) {
            return GameType.INFINITE;
        }

        return GameType.SHIT;   // sth went wrong
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);

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

        String gameTypeTag = getIntent().getExtras().getString("gameType");
        difficulty = getIntent().getExtras().getString("difficulty");

        Log.d("gameType", gameTypeTag);
        game = convertGameTag(gameTypeTag);
//        difficulty = convertDiffTag(difficultyTag);


        // check the different buttons
        onDecimalClick();
        onBinaryClick();
        onHexClick();
        onExitClick();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
