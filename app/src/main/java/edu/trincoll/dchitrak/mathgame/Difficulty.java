package edu.trincoll.dchitrak.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

enum Diff{
    EASY,
    MEDIUM,
    HARD
}



public class Difficulty extends AppCompatActivity {

//    static difficultyValue diffVal = new difficultyValue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
    }
}
