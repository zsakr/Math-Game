package edu.trincoll.dchitrak.mathgame;

import android.content.Intent;
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

    GameType finalType;

    // when the exit button is clicked
    private void buttonClick() {
        ImageButton exitButton = (ImageButton) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                startActivity(startint);
            }
        });
    }

    // creates the appropriate intent based on the game type enum
    private Intent createIntent() {
        if (finalType == GameType.FIXED) {
            return new Intent(getApplicationContext(), FixedGame.class);
        } else if (finalType ==  GameType.INFINITE) {
            return new Intent(getApplicationContext(), InfiniteMode.class);
        } else if (finalType == GameType.TIMED) {
            return new Intent(getApplicationContext(), TimedGame.class);
        }

        return new Intent(getApplicationContext(), ResultsPage.class);
    }


    // convert tag to an enum game type
    private GameType convertTag(String gameTypeTag) {
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

        String gameTypeTag = getIntent().getExtras().getString("gameType");
        Log.d("GameType", gameTypeTag);
        finalType = convertTag(gameTypeTag);





        // check the different buttons
        onDecimalClick();
        onBinaryClick();
        onHexClick();
    }


}
