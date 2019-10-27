package edu.trincoll.dchitrak.mathgame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class NumTrack {
    private long starttime;
    private long taketime;
    private long temptime;
    private int streaks;
    private int score;
    private int maxStreak;


    public NumTrack(long starttime, long taketime, long temptime, int streaks, int score, int maxStreak) {
        this.starttime = starttime;
        this.taketime = taketime;
        this.temptime = temptime;
        this.streaks = streaks;
        this.score = score;
        this.maxStreak = maxStreak;
    }

    public NumTrack() {
        this(0,0,0,0,0,0);
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
                    writer.write(""); //Names
                    writer.write("\n");
                }
                for (lineNumber = 1; lineNumber < 11; lineNumber++) {
                    writer.write("0");  //Max score top 10
                    writer.write("\n");
                }
                for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                    writer.write("");  //Names
                    writer.write("\n");
                }
                for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                    writer.write("0");  //Streaks
                    writer.write("\n");
                }
                for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                    writer.write("");  //Names
                    writer.write("\n");
                }
                for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                    writer.write("0");  //Speed
                    writer.write("\n");
                }
                for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                    writer.write("");  //Names
                    writer.write("\n");
                }
                for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                    writer.write("0");  //Score Infinity
                    writer.write("\n");
                }
                for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                    writer.write("");  //Names
                    writer.write("\n");
                }
                for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                    writer.write("0");  //Streak Infinity
                    writer.write("\n");
                }
                for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                    writer.write("");  //Names
                    writer.write("\n");
                }
                for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                    writer.write("0");  //Time score
                    writer.write("\n");
                }
                for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                    writer.write("");  //Names
                    writer.write("\n");
                }
                for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                    writer.write("0");  //Streak streak score
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

    public int getMaxStreak() {
        return maxStreak;
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
        if (streaks > maxStreak) {
            maxStreak = streaks;
        }
        if (streaks >= 6) {
            this.streaks = 3;
        } else {
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



    public void set20qSCORE(String Pname, int score) {
        String scoreS = score+"";
        String text = "";
        String player[] = new String[12];
        String playerScore[] = new String[12];
        String data[] = new String[61];
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
            for (lineNumber = 1; lineNumber <= 60; lineNumber++) {
                text = readbuffer.readLine();
                data[lineNumber] = text;
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

            for (lineNumber = 1; lineNumber < 61; lineNumber++) {
                writer.write(data[lineNumber]);
                writer.write("\n");
            }

            writer.flush();

        } catch (IOException ex) {

        } finally {
            try {writer.close();} catch (Exception ex) {
            }
        }

    }

    public void set20qSAS(String Pname, int time, int streaks) {
        String sStreak = streaks+"";
        String sTime = time+"";
        String text = "";
        String player[] = new String[7];
        String player2[] = new String[7];
        String playerStreaks[] = new String[7];
        String playerSpeed[] = new String[7];
        String data[] = new String[61];

        int lineNumber;
        try {
            FileReader readfile = new FileReader("leaderboard.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (lineNumber = 1; lineNumber < 21; lineNumber++) {
                text = readbuffer.readLine();
                data[lineNumber] = text;
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                text = readbuffer.readLine();
                player[lineNumber] = text;
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                text = readbuffer.readLine();
                playerStreaks[lineNumber] = text;
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                text = readbuffer.readLine();
                player2[lineNumber] = text;
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                text = readbuffer.readLine();
                playerSpeed[lineNumber] = text;
            }
            for (lineNumber = 21; lineNumber < 61; lineNumber++) {
                text = readbuffer.readLine();
                data[lineNumber] = text;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        player[6] = Pname;
        playerStreaks[6] = sStreak;
        player2[6] = Pname;
        playerSpeed[6] = sTime;

        int i;
        boolean sorted = false;
        int temp;
        String tempS;
        while (!sorted) {
            sorted = true;
            for(i = 1; i < 6; i++) {
                if(Integer.parseInt(playerStreaks[i]) > Integer.parseInt(playerStreaks[i+1])) {
                    temp = Integer.parseInt(playerStreaks[i]);
                    tempS = player[i];
                    playerStreaks[i] = playerStreaks[i+1];
                    player[i] = player[i+1];
                    playerStreaks[i+1] = temp+"";
                    player[i+1] = tempS;
                    sorted = false;
                }
            }
        }

        sorted = false;

        while (!sorted) {
            sorted = true;
            for(i = 1; i < 6; i++) {
                if(Integer.parseInt(playerSpeed[i]) > Integer.parseInt(playerSpeed[i+1])) {
                    temp = Integer.parseInt(playerSpeed[i]);
                    tempS = player2[i];
                    playerSpeed[i] = playerSpeed[i+1];
                    player2[i] = player2[i+1];
                    playerSpeed[i+1] = temp+"";
                    player2[i+1] = tempS;
                    sorted = false;
                }
            }
        }


        Writer writer = null;

        try {
            writer =new BufferedWriter(new OutputStreamWriter(new FileOutputStream("leaderboard.txt"), "utf-8"));

            for (lineNumber = 1; lineNumber < 21; lineNumber++) {
                writer.write(data[lineNumber]);
                writer.write("\n");
            }
            for (lineNumber = 2; lineNumber <= 6; lineNumber++) {
                writer.write(player[lineNumber]);
                writer.write("\n");
            }
            for (lineNumber = 2; lineNumber <= 6; lineNumber++) {
                writer.write(playerStreaks[lineNumber]);
                writer.write("\n");
            }
            for (lineNumber = 2; lineNumber <= 6; lineNumber++) {
                writer.write(player2[lineNumber]);
                writer.write("\n");
            }




            for (lineNumber = 2; lineNumber <= 6; lineNumber++) {
                writer.write(playerSpeed[lineNumber]);
                writer.write("\n");
            }

            for (lineNumber = 21; lineNumber <= 60; lineNumber++) {
                writer.write(data[lineNumber]);
                writer.write("\n");
            }

            writer.flush();

        } catch (IOException ex) {

        } finally {
            try {writer.close();} catch (Exception ex) {
            }
        }
    }

    public void infinitySAS(String Pname, int score, int streaks) {
        String sStreak = streaks+"";
        String sScore = score+"";
        String text = "";
        String player[] = new String[7];
        String player2[] = new String[7];
        String playerStreaks[] = new String[7];
        String playerScore[] = new String[7];
        String data[] = new String[61];

        int lineNumber;
        try {
            FileReader readfile = new FileReader("leaderboard.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (lineNumber = 1; lineNumber < 41; lineNumber++) {
                text = readbuffer.readLine();
                data[lineNumber] = text;
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                text = readbuffer.readLine();
                player[lineNumber] = text;
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                text = readbuffer.readLine();
                playerScore[lineNumber] = text;
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                text = readbuffer.readLine();
                player2[lineNumber] = text;
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                text = readbuffer.readLine();
                playerStreaks[lineNumber] = text;
            }
            for (lineNumber = 41; lineNumber < 61; lineNumber++) {
                text = readbuffer.readLine();
                data[lineNumber] = text;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        player[6] = Pname;
        playerScore[6] = sScore;
        player2[6] = Pname;
        playerStreaks[6] = sStreak;

        int i;
        boolean sorted = false;
        int temp;
        String tempS;
        while (!sorted) {
            sorted = true;
            for(i = 1; i < 6; i++) {
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

        sorted = false;

        while (!sorted) {
            sorted = true;
            for(i = 1; i < 6; i++) {
                if(Integer.parseInt(playerStreaks[i]) > Integer.parseInt(playerStreaks[i+1])) {
                    temp = Integer.parseInt(playerStreaks[i]);
                    tempS = player2[i];
                    playerStreaks[i] = playerStreaks[i+1];
                    player2[i] = player2[i+1];
                    playerStreaks[i+1] = temp+"";
                    player2[i+1] = tempS;
                    sorted = false;
                }
            }
        }


        Writer writer = null;

        try {
            writer =new BufferedWriter(new OutputStreamWriter(new FileOutputStream("leaderboard.txt"), "utf-8"));

            for (lineNumber = 1; lineNumber < 41; lineNumber++) {
                writer.write(data[lineNumber]);
                writer.write("\n");
            }
            for (lineNumber = 2; lineNumber <= 6; lineNumber++) {
                writer.write(player[lineNumber]);
                writer.write("\n");
            }
            for (lineNumber = 2; lineNumber <= 6; lineNumber++) {
                writer.write(playerScore[lineNumber]);
                writer.write("\n");
            }
            for (lineNumber = 2; lineNumber <= 6; lineNumber++) {
                writer.write(player2[lineNumber]);
                writer.write("\n");
            }
            for (lineNumber = 2; lineNumber <= 6; lineNumber++) {
                writer.write(playerStreaks[lineNumber]);
                writer.write("\n");
            }

            for (lineNumber = 41; lineNumber <= 60; lineNumber++) {
                writer.write(data[lineNumber]);
                writer.write("\n");
            }

            writer.flush();

        } catch (IOException ex) {

        } finally {
            try {writer.close();} catch (Exception ex) {
            }
        }
    }

    public void timedSAS(String Pname, int score, int streaks) {
        String sStreak = streaks+"";
        String sScore = score+"";
        String text = "";
        String player[] = new String[7];
        String player2[] = new String[7];
        String playerStreaks[] = new String[7];
        String playerScore[] = new String[7];
        String data[] = new String[61];

        int lineNumber;
        try {
            FileReader readfile = new FileReader("leaderboard.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (lineNumber = 1; lineNumber < 61; lineNumber++) {
                text = readbuffer.readLine();
                data[lineNumber] = text;
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                text = readbuffer.readLine();
                player[lineNumber] = text;
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                text = readbuffer.readLine();
                playerScore[lineNumber] = text;
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                text = readbuffer.readLine();
                player2[lineNumber] = text;
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                text = readbuffer.readLine();
                playerStreaks[lineNumber] = text;
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        player[6] = Pname;
        playerScore[6] = sScore;
        player2[6] = Pname;
        playerStreaks[6] = sStreak;

        int i;
        boolean sorted = false;
        int temp;
        String tempS;
        while (!sorted) {
            sorted = true;
            for(i = 1; i < 6; i++) {
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

        sorted = false;

        while (!sorted) {
            sorted = true;
            for(i = 1; i < 6; i++) {
                if(Integer.parseInt(playerStreaks[i]) > Integer.parseInt(playerStreaks[i+1])) {
                    temp = Integer.parseInt(playerStreaks[i]);
                    tempS = player2[i];
                    playerStreaks[i] = playerStreaks[i+1];
                    player2[i] = player2[i+1];
                    playerStreaks[i+1] = temp+"";
                    player2[i+1] = tempS;
                    sorted = false;
                }
            }
        }


        Writer writer = null;

        try {
            writer =new BufferedWriter(new OutputStreamWriter(new FileOutputStream("leaderboard.txt"), "utf-8"));

            for (lineNumber = 1; lineNumber < 61; lineNumber++) {
                writer.write(data[lineNumber]);
                writer.write("\n");
            }
            for (lineNumber = 2; lineNumber <= 6; lineNumber++) {
                writer.write(player[lineNumber]);
                writer.write("\n");
            }
            for (lineNumber = 2; lineNumber <= 6; lineNumber++) {
                writer.write(playerScore[lineNumber]);
                writer.write("\n");
            }
            for (lineNumber = 2; lineNumber <= 6; lineNumber++) {
                writer.write(player2[lineNumber]);
                writer.write("\n");
            }
            for (lineNumber = 2; lineNumber <= 6; lineNumber++) {
                writer.write(playerStreaks[lineNumber]);
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
                writer.write(""); //Names
                writer.write("\n");
            }
            for (lineNumber = 1; lineNumber < 11; lineNumber++) {
                writer.write("0");  //Max score top 10
                writer.write("\n");
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                writer.write("");  //Names
                writer.write("\n");
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                writer.write("0");  //Streaks
                writer.write("\n");
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                writer.write("");  //Names
                writer.write("\n");
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                writer.write("0");  //Speed
                writer.write("\n");
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                writer.write("");  //Names
                writer.write("\n");
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                writer.write("0");  //Score Infinity
                writer.write("\n");
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                writer.write("");  //Names
                writer.write("\n");
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                writer.write("0");  //Streak Infinity
                writer.write("\n");
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                writer.write("");  //Names
                writer.write("\n");
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                writer.write("0");  //Time score
                writer.write("\n");
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                writer.write("");  //Names
                writer.write("\n");
            }
            for (lineNumber = 1; lineNumber < 6; lineNumber++) {
                writer.write("0");  //Streak streak score
                writer.write("\n");
            }

            writer.flush();

        } catch (IOException ex) {

        } finally {
            try {writer.close();} catch (Exception ex) {

            }
        }
    }

    public String[] getleader20qscores() {
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

    public String[] getleader20qstrandsped() {
        String text = "";
        String returnArr[] = new String[21];
        int lineNumber;
        try {
            FileReader readfile = new FileReader("leaderboard.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (lineNumber = 1; lineNumber <= 20; lineNumber++) {
                text = readbuffer.readLine();
            }

            for (lineNumber = 20; lineNumber >= 1; lineNumber--) {
                text = readbuffer.readLine();
                returnArr[lineNumber] = text;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 20; i++) {
            System.out.println(returnArr[i]);
        }
        return returnArr;
    }

    public String[] getleadinfinity() {
        String text = "";
        String returnArr[] = new String[21];
        int lineNumber;
        try {
            FileReader readfile = new FileReader("leaderboard.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (lineNumber = 1; lineNumber <= 40; lineNumber++) {
                text = readbuffer.readLine();
            }

            for (lineNumber = 20; lineNumber >= 1; lineNumber--) {
                text = readbuffer.readLine();
                returnArr[lineNumber] = text;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 20; i++) {
            System.out.println(returnArr[i]);
        }
        return returnArr;
    }

    public String[] getTopScore20() {
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
        for (int i = 1; i <= 20; i++) {
            System.out.println(returnArr[i]);
        }
        return returnArr;
    }

    public String[] getTopStreaksAndSpeedQ20() {
        String text = "";
        String returnArr[] = new String[21];
        int lineNumber;
        try {
            FileReader readfile = new FileReader("leaderboard.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);

            for (int i = 1; i <= 20; i++) {
                text = readbuffer.readLine();
            }
            for (lineNumber = 20; lineNumber >= 1; lineNumber--) {
                text = readbuffer.readLine();
                returnArr[lineNumber] = text;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 20; i++) {
            System.out.println(returnArr[i]);
        }
        return returnArr;
    }

    public String[] getTopScoreAndStreakInfinity() {
        String text = "";
        String returnArr[] = new String[21];
        int lineNumber;
        try {
            FileReader readfile = new FileReader("leaderboard.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);

            for (int i = 1; i <= 40; i++) {
                text = readbuffer.readLine();
            }
            for (lineNumber = 20; lineNumber >= 1; lineNumber--) {
                text = readbuffer.readLine();
                returnArr[lineNumber] = text;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 20; i++) {
            System.out.println(returnArr[i]);
        }
        return returnArr;
    }

    public String[] getTopScoreAndStreaksTimed() {
        String text = "";
        String returnArr[] = new String[21];
        int lineNumber;
        try {
            FileReader readfile = new FileReader("leaderboard.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);

            for (int i = 1; i <= 60; i++) {
                text = readbuffer.readLine();
            }
            for (lineNumber = 20; lineNumber >= 1; lineNumber--) {
                text = readbuffer.readLine();
                returnArr[lineNumber] = text;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 20; i++) {
            System.out.println(returnArr[i]);
        }
        return returnArr;
    }

    public boolean checkSkill20Q(int score, int streaks, int time) {
        String text1 = "0";
        String text2 = "0";
        String text3 = "0";

        int lineNumber;
        try {
            FileReader readfile = new FileReader("leaderboard.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (lineNumber = 1; lineNumber <= 11; lineNumber++) {
                text1 = readbuffer.readLine();
            }

            for (lineNumber = 1; lineNumber <= 15; lineNumber++) {
                text2 = readbuffer.readLine();
            }

            for (lineNumber = 1; lineNumber <= 10; lineNumber++) {
                text3 = readbuffer.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (score > Integer.parseInt(text1)) {
            return true;
        }
        if (streaks > Integer.parseInt(text2)) {
            return true;
        }
        if (time > Integer.parseInt(text3)) {
            return true;
        }


        return false;
    }

    public boolean checkskillInfinity(int score, int streaks) {
        String text1 = "";
        String text2 = "";

        int lineNumber;
        try {
            FileReader readfile = new FileReader("leaderboard.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (lineNumber = 1; lineNumber <= 46; lineNumber++) {
                text1 = readbuffer.readLine();
            }
            for (lineNumber = 1; lineNumber <= 10; lineNumber++) {
                text2 = readbuffer.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (score > Integer.parseInt(text1)) {
            return true;
        }
        if (streaks > Integer.parseInt(text2)) {
            return true;
        }



        return false;
    }

    public boolean checkskilltimed(int score, int streaks) {
        String text1 = "";
        String text2 = "";

        int lineNumber;
        try {
            FileReader readfile = new FileReader("leaderboard.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);
            for (lineNumber = 1; lineNumber <= 66; lineNumber++) {
                text1 = readbuffer.readLine();
            }
            System.out.println(text1);
            for (lineNumber = 1; lineNumber <= 10; lineNumber++) {
                text2 = readbuffer.readLine();
            }
            System.out.println(text1);


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (score > Integer.parseInt(text1)) {
            return true;
        }
        if (streaks > Integer.parseInt(text2)) {
            return true;
        }



        return false;
    }




}