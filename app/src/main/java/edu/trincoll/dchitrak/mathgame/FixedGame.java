package edu.trincoll.dchitrak.mathgame;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FixedGame extends AppCompatActivity {
    private GenerateProblem problem = new GenerateProblem(0, 10);
    private NumTrack tracker = new NumTrack();
    private int numQues = 20;
    private int number = 1;



    private void clearDisplay(){
        TextView tv1 = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        TextView tv3 = (TextView) findViewById(R.id.qsLeft);
        TextView tv4 = (TextView) findViewById(R.id.textView3);

        problem.setResults(-999999);
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");
    }

    private void displayProblem(){

        problem.genarateParam();
        TextView tv1 = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        TextView tv3 = (TextView) findViewById(R.id.qsLeft);
        TextView tv4 = (TextView) findViewById(R.id.textView3);


        tv1.setText(problem.getNum1()+"");
        tv2.setText(problem.getNum2()+"");
        tv3.setText(((numQues + 1) - number)+"");
        tv4.setText(problem.getOp());


        checkEnd();
    }

    private void buttonClick(){
        ImageButton exitButton = (ImageButton) findViewById(R.id.exitButton);
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
                if (finalValue == problem.getResults()) {
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
                StreakDisplay();
            }
        });
    }
    private void StreakDisplay(){
        ImageView streak = (ImageView) findViewById(R.id.fixedStreak);
        StreakDisplayer sdisplay = new StreakDisplayer(streak);
        sdisplay.setStreak(tracker.getStreaks());
        sdisplay.dispStreak();
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
        setContentView(R.layout.activity_fixed_game);
        displayProblem();
        buttonClick();

    }
}
