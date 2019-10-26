package edu.trincoll.dchitrak.mathgame;

import java.util.Random;

public class GenerateProblem {

    private int max;
    private int min;
    private int num1;
    private int num2;
    private int opNum;
    Random rand = new Random();

    public GenerateProblem(int min, int max){
        this.min = min;
        this.max = max;
    }

    public GenerateProblem(){
        this.min = 0;
        this.max = 10;
    }

    public GenerateProblem(int max){
        this.min = 0;
        this.max = max;
    }

    public void randNum(){
        num1 = rand.nextInt(max-min) + min;
        num2 = rand.nextInt(max-min) + min;
    }

    public int getNum1(){
        return num1;
    }

    public int getNum2(){
        return num2;
    }
}
