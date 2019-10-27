package edu.trincoll.dchitrak.mathgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseType extends AppCompatActivity {

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
                Intent startint = new Intent(getApplicationContext(), FixedGame.class);
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
                Intent startint = new Intent(getApplicationContext(), FixedGame.class);
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
                Intent startint = new Intent(getApplicationContext(), FixedGame.class);
                startActivity(startint);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);

        String gameType = getIntent().getExtras().getString("GameType");
        Log.d("GameType", "Hi");

        // check the different buttons
        onDecimalClick();
        onBinaryClick();
        onHexClick();
    }


}
