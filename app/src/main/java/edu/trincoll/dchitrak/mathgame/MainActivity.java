package edu.trincoll.dchitrak.mathgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    boolean toggle = true;

    static Diff diffval = Diff.EASY;

    private void menuSelection(){
        /* this button is for the fixed game setup */
        Button press = (Button) findViewById(R.id.button);
        press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startint = new Intent(getApplicationContext(), ChooseType.class);
                Log.d("class", "went to the Choose type class!!!");
                startint.putExtra("gameType", "FixedGame.class");
                startActivity(startint);
            }
        });


        /* this button is for the timed game setup */
        Button timePress = (Button) findViewById(R.id.timeButton);
        timePress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startint = new Intent(getApplicationContext(), ChooseType.class);
                startint.putExtra("gameType", "TimedGame.class");
                startActivity(startint);
            }
        });


        /* this button is for the infinite mode setup */
        Button infinitePress = (Button) findViewById(R.id.infinityButton);
        infinitePress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startint = new Intent(getApplicationContext(), ChooseType.class);
                startint.putExtra("gameType", "InfiniteMode.class");
                startActivity(startint);
            }
        });
    }

    private void rocketAnimate() {

//        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -2000.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
//        animation.setDuration(10000);  // animation duration
//        //animation.setRepeatCount(5);  // animation repeat count
//        //animation.setFillAfter(true);
//
//        /* this button is for the infinte mode setup */
        Button rocketPress = (Button) findViewById(R.id.blastButt);
        rocketPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggle){
                ImageView animate = findViewById(R.id.rocket);
                animate.setImageResource(R.drawable.bomb_transparent);
                toggle = !toggle;
            }else{
                    ImageView animate = findViewById(R.id.rocket);
                    animate.setImageResource(R.drawable.rocket);
                    toggle = !toggle;
                }
            }
        });


//        if (move) {
//            animate.startAnimation(animation);  // start animation
//
//        }
//
//        if (!move) {
//
//        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        menuSelection();
        rocketAnimate();
    }}

