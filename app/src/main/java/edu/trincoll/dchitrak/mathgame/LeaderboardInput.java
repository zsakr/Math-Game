package edu.trincoll.dchitrak.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class LeaderboardInput extends AppCompatActivity {

    NumTrack tracker = new NumTrack();

    private void addToLeaderboard(String name, String score, String time, String streaks){
        tracker.set20qSAS(name, Integer.parseInt(time), Integer.parseInt(streaks));
        tracker.set20qSCORE(name, Integer.parseInt(streaks));
        Log.d("Added to", "Leaderboard");
    }

    private void hitSubmitButt(){
        Button submitButton = (Button) findViewById(R.id.submitButt);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.nameInput);
                String NameString = name.getText().toString();
                if(!NameString.isEmpty()) {
                    String Streaks = getIntent().getExtras().getString("Streaks");
                    String Score = getIntent().getExtras().getString("Score");
                    String Time = getIntent().getExtras().getString("Time");

                    addToLeaderboard(NameString, Score, Time, Streaks);
                    Intent startint = new Intent(getApplicationContext(), ResultsPage.class);
                    startint.putExtra("Score", Score);
                    startint.putExtra("Time", Time);
                    startActivity(startint);
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_input);
        hitSubmitButt();
    }
}
