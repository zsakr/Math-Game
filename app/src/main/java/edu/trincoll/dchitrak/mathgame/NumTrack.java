package edu.trincoll.dchitrak.mathgame;
import android.util.Log;

import java.io.*;

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



        String filename = "leaderboard.txt";

        File tmpDir = new File(filename);


        //makes a new file for leaderboard tracking if one doesn't exist
        if(tmpDir.exists() == false) {
            try {
                File file = new File(filename);
                boolean fvar = file.createNewFile();
                if (fvar){
                    System.out.println("File has been created successfully");
                }
                else{
                    System.out.println("File already present at the specified location");
                }
            } catch (IOException e) {
                System.out.println("Exception Occurred:");
                e.printStackTrace();
            }


            Writer writer = null;

            int lineNumber;
            try {
                writer =new BufferedWriter(new OutputStreamWriter(new FileOutputStream("leaderboard.txt"), "utf-8"));


                for (lineNumber = 1; lineNumber < 11; lineNumber++) {
                    writer.write("");
                    writer.write("\n");
                }
                for (lineNumber = 1; lineNumber < 11; lineNumber++) {
                    writer.write("0");
                    writer.write("\n");
                }

                writer.flush();

            } catch (IOException ex) {

            } finally {
                try {writer.close();} catch (Exception ex) {

                }
            }



        }


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

    public void resetStreak() {
        this.streaks = 0;
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



    public void checkAndsetBoard(String Pname, int score) {
        String scoreS = score+"";
        String text = "";
        String player[] = new String[12];
        String playerScore[] = new String[12];
        int lineNumber;
        try {
            FileReader readfile = new FileReader("leaderboard.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (lineNumber = 1; lineNumber <= 10; lineNumber++) {
                text = readbuffer.readLine();

                player[lineNumber] = text;
            }
            for (lineNumber = 11; lineNumber <= 20; lineNumber++) {
                text = readbuffer.readLine();

                playerScore[lineNumber-10] = text;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        player[11] = Pname;
        playerScore[11] = scoreS;


        int i;
        boolean sorted = false;
        int temp;
        String tempS;
        while (!sorted) {
            sorted = true;
            for(i = 1; i < 11; i++) {
                if(Integer.parseInt(playerScore[i]) > Integer.parseInt(playerScore[i+1])) {
                    temp = Integer.parseInt(playerScore[i]);
                    tempS = player[i];
                    playerScore[i] = playerScore[i+1];
                    player[i] = player[i+1];
                    playerScore[i+1] = temp+"";
                    player[i+1] = tempS;
                    sorted = false;
                }
            }
        }


        Writer writer = null;

        try {
            writer =new BufferedWriter(new OutputStreamWriter(new FileOutputStream("leaderboard.txt"), "utf-8"));


            for (lineNumber = 2; lineNumber < 12; lineNumber++) {
                writer.write(player[lineNumber]);
                writer.write("\n");
            }
            for (lineNumber = 2; lineNumber < 12; lineNumber++) {
                writer.write(playerScore[lineNumber]);
                writer.write("\n");

            }

            writer.flush();

        } catch (IOException ex) {

        } finally {
            try {writer.close();} catch (Exception ex) {

            }
        }


    }

    public void OhSnap() {
        Writer writer = null;

        int lineNumber;
        try {
            writer =new BufferedWriter(new OutputStreamWriter(new FileOutputStream("leaderboard.txt"), "utf-8"));


            for (lineNumber = 1; lineNumber < 11; lineNumber++) {
                writer.write("");
                writer.write("\n");
            }
            for (lineNumber = 1; lineNumber < 11; lineNumber++) {
                writer.write("0");
                writer.write("\n");
            }

            writer.flush();

        } catch (IOException ex) {

        } finally {
            try {writer.close();} catch (Exception ex) {

            }
        }
    }

    public String[] getLeaderboardQ20() {
        String text = "";
        String returnArr[] = new String[21];
        int lineNumber;
        try {
            FileReader readfile = new FileReader("leaderboard.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (lineNumber = 20; lineNumber >= 1; lineNumber--) {
                text = readbuffer.readLine();
                returnArr[lineNumber] = text;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < 20; i++) {
            System.out.println(returnArr[i]);
        }

        return returnArr;
    }

    public boolean checkSkill(int score) {
        String text = "";
        int lineNumber;
        try {
            FileReader readfile = new FileReader("leaderboard.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (lineNumber = 1; lineNumber <= 11; lineNumber++) {
                text = readbuffer.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(text) > score) {
            return false;
        }
        return true;
    }



}