package edu.trincoll.dchitrak.mathgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;



public class Difficulty extends AppCompatActivity {


    String gameTag;

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

    //set up easy button, which returns to home page
    private void onEasyButtClick() {

        Button easy = findViewById(R.id.EasyButt);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

        onExitButtClick();
        onEasyButtClick();
        onMedButtClick();
        onHardButtClick();

    }


}
