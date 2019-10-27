package edu.trincoll.dchitrak.mathgame;
//RIP
//Sorry Edwin

public class NumTrack {
    private long starttime;
    private long taketime;
    private long temptime;
    private int streaks;
    private int score;

    public NumTrack(long starttime, long taketime, long temptime, int streaks, int score) {
        this.starttime = starttime;
        this.taketime = taketime;
        this.temptime = temptime;
        this.streaks = streaks;
        this.score = score;
    }

    public NumTrack() {
        this(0,0,0,0,0);
        setStart();  //sets up basic stuff on game start
    }

    public void setStart() {
        this.starttime = System.currentTimeMillis();
        temptime = starttime;

    }

    public long getInterval() {
        this.taketime = System.currentTimeMillis();
        long time = taketime-temptime;
        this.temptime = taketime;
        return time;
    }

    public long getStart() {
        return starttime;
    }


    public String getTime() {
        this.taketime = System.currentTimeMillis();
        long time = taketime-starttime;
        String returnable = "";
        time = time/1000;
        int digit1and2 = (int)time/60;
        int digit3and4 = (int)time%60;
        if (digit3and4 <= 9) {
            return digit1and2+":0"+digit3and4;
        }
        return digit1and2+":"+digit3and4;
    }


    public int getTimeInt() {
        this.taketime = System.currentTimeMillis();
        long time = taketime-starttime;
        int timeI = (int) time/1000;

        return timeI;
    }

    public void resetStreak() {
        if (streaks >= 12) {
            this.streaks = 6;
        } else if (streaks >=7) {
            this.streaks = 4;
        }else{
            this.streaks = 0;
        }
    }

    public void incStreak() {
        streaks++;
    }

    public int getStreaks() {
        return streaks;
    }

    public void resetScore() {
        this.score = 0;
    }

    public void recalculateScore() {
        incStreak();
        int newval = (int)(getInterval())/1000;
        newval = newval*newval+1;
        newval = 100+500/newval;
        double newval2 = (1+.075*(getStreaks()));
        newval2 = newval2*newval;
        newval = (int) newval2;
        this.score += newval;

    }

    public int getScore() {
        return score;
    }

}