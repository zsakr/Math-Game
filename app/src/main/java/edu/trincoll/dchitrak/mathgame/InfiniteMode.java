package edu.trincoll.dchitrak.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class InfiniteMode extends AppCompatActivity {

    private GenerateProblem problem = new GenerateProblem(0, 10);
    private NumTrack tracker = new NumTrack();
    private int number = 1;



    private void clearDisplay(){
        TextView tv1 = (TextView) findViewById(R.id.infNum1);
        TextView tv2 = (TextView) findViewById(R.id.infNum2);
        TextView tv3 = (TextView) findViewById(R.id.infQs);
        TextView tv4 = (TextView) findViewById(R.id.infOp);

        problem.setResults(-999999);
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");
    }

    private void displayProblem(){

        problem.genarateParam();
        TextView tv1 = (TextView) findViewById(R.id.infNum1);
        TextView tv2 = (TextView) findViewById(R.id.infNum2);
        TextView tv3 = (TextView) findViewById(R.id.infQs);
        TextView tv4 = (TextView) findViewById(R.id.infOp);


        tv1.setText(problem.getNum1()+"");
        tv2.setText(problem.getNum2()+"");
        tv3.setText(number+"");
        tv4.setText(problem.getOp());


    }

    private void buttonClick(){
        ImageButton exitButton = (ImageButton) findViewById(R.id.infExit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startint = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startint);
            }
        });

        final EditText ed = (EditText) findViewById(R.id.infAns);
        Button press = (Button) findViewById(R.id.infGo);
        press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = ed.getText().toString();
                int finalValue = Integer.parseInt(value);
                if (finalValue == problem.getResults()) {
                    //Log.d("Success", "Happy")
                    tracker.recalculateScore();
                    TextView tvs = (TextView) findViewById(R.id.infScore);
                    number++;;
                    displayProblem();

                    tvs.setText(tracker.getScore()+"");
                } else {
                    //Log.d("Fail", "Sad");
                    Intent startint = new Intent(getApplicationContext(), ResultsPage.class);
                    startint.putExtra("Score", tracker.getScore()+"");
                    startint.putExtra("Time", tracker.getTime()+"");
                    startActivity(startint);
                }
                ed.setText("");
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_mode);
        displayProblem();
        buttonClick();

    }
}
