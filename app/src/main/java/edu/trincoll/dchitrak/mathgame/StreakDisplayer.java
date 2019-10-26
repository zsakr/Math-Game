package edu.trincoll.dchitrak.mathgame;

import android.widget.ImageView;

public class StreakDisplayer {

    private ImageView streakDisp;
    private int streak;

    public StreakDisplayer(ImageView s) {
        this.streakDisp = s;
    }

    public void setStreak(int streak){
        this.streak = streak;
    }

    public void dispStreak(){
        if (streak > 11){
            streakDisp.setImageResource(R.drawable.fire2);
        }else if(streak > 6){
            streakDisp.setImageResource(R.drawable.fire1);
        }else if(streak > 2){
            streakDisp.setImageResource(R.drawable.fire0);
        }else{
            streakDisp.setImageResource(R.drawable.transparent);
        }
    }

}
