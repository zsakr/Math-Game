package edu.trincoll.dchitrak.mathgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

enum Diff{
    EASY,
    MEDIUM,
    HARD
}



public class Difficulty extends AppCompatActivity {


//    static difficultyValue diffVal = new difficultyValue();

    //set up exit button, which returns to home page
    private void onExitButtClick() {

        ImageButton exit = (ImageButton) findViewById(R.id.exitButton);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startint = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startint);
            }
        });
    }

    //set up exit button, which returns to home page
    private void onEasyButtClick() {

        ImageButton easy = (ImageButton) findViewById(R.id.easyButt);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                Intent startint = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startint);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        onExitButtClick();
    }
}
