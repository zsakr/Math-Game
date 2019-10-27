package edu.trincoll.dchitrak.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class LeaderboardInput extends AppCompatActivity {

    private void addToLeaderboard(String name, String score, String time, String game){
        System.out.println("Hi");
    }

    private void hitSubmitButt(){
        Button submitButton = (Button) findViewById(R.id.submitButt);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.nameInput);
                if(!name.equals("")) {
                    String Score = getIntent().getExtras().getString("Score");
                    String Time = getIntent().getExtras().getString("Time");
                    String Game = getIntent().getExtras().getString("Game");

                    addToLeaderboard(name, Score, Time, Game);
                    Intent startint = new Intent(getApplicationContext(), ResultsPage.class);
                    startint.putExtra("Score", Score);
                    startint.putExtra("Time", Time);
                    startActivity(startint);
                }
            }
        });

    }


    private void dispFinal(){
        String Score = getIntent().getExtras().getString("Score");
        String Time = getIntent().getExtras().getString("Time");

        TextView tvs = (TextView) findViewById(R.id.dispScore);
        TextView tvt = (TextView) findViewById(R.id.dispTime);

        tvs.setText(Score);
        tvt.setText(Time);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_input);
        hitSubmitButt();
    }
}
