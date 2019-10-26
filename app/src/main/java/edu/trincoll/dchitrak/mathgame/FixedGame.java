package edu.trincoll.dchitrak.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import java.time.chrono.MinguoChronology;
import java.util.Random;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FixedGame extends AppCompatActivity {
    Random rand = new Random();
    int number = 1;
    int realAnswer;
    int numQues = 20;
    NumTrack tracker = new NumTrack();

    private int generateNum(int max){
        return rand.nextInt(max);
    }


    private void clearDisplay(){
        TextView tv1 = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        TextView tv3 = (TextView) findViewById(R.id.textView4);
        TextView tv4 = (TextView) findViewById(R.id.textView3);

        realAnswer = -9999999;
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");

    }

    private void displayProblem(){

        int num1;
        int num2;

        int opNum;

        TextView tv1 = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        TextView tv3 = (TextView) findViewById(R.id.textView4);
        TextView tv4 = (TextView) findViewById(R.id.textView3);

        num1 = generateNum(10);
        num2 = generateNum(10);

        opNum = generateNum(4);

        if (opNum == 0){
            tv4.setText("+");
            realAnswer = num1+num2;
        }else if(opNum == 1){
            tv4.setText("-");
            realAnswer = num1-num2;
        }else if(opNum == 2){
            tv4.setText("X");
            realAnswer = num1*num2;
        }else{
            tv4.setText("/");
            realAnswer = (num1%num2)+generateNum(20);
            num1 = num2*realAnswer;
        }

        tv1.setText(num1+ "");
        tv2.setText(num2+ "");
        tv3.setText(((numQues + 1) - number)+"");


        checkEnd();
    }

    private void buttonClick(){
        ImageButton exitButton = (ImageButton) findViewById(R.id.imageButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startint = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startint);
            }
        });

        final EditText ed = (EditText) findViewById(R.id.editText4);
        Button press = (Button) findViewById(R.id.button2);
        press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = ed.getText().toString();
                int finalValue = Integer.parseInt(value);
                if (finalValue == realAnswer) {
                    //Log.d("Success", "Happy")
                    tracker.recalculateScore();
                    TextView tvs = (TextView) findViewById(R.id.score);
                    number++;;
                    displayProblem();

                    tvs.setText(tracker.getScore()+"");
                } else {
                    //Log.d("Fail", "Sad");
                    tracker.resetStreak();
                }
                ed.setText("");
            }
        });
    }

    private void checkEnd(){
        //Log.d("Exit", "Help");
        SystemClock.sleep(100);
        if (number>numQues){
            clearDisplay();
            Intent startint = new Intent(getApplicationContext(), ResultsPage.class);
            startint.putExtra("Score", tracker.getScore()+"");
            startint.putExtra("Time", tracker.getTime()+"");
            startActivity(startint);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        displayProblem();
        buttonClick();

    }
}
