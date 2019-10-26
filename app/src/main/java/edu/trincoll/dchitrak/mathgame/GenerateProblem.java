package edu.trincoll.dchitrak.mathgame;

import java.util.Random;

public class GenerateProblem {

    private int max;
    private int min;
    private int num1;
    private int num2;
    private int opNum;
    private String op;
    private int results;
    Random rand = new Random();

    public GenerateProblem(int min, int max){
        this.min = min;
        this.max = max;
    }
//
//    public GenerateProblem(){
//        this.min = 1;
//        this.max = 10;
//    }
//
//    public GenerateProblem(int max){
//        this.min = 1;
//        this.max = max;
//    }

    public void makeProblem(){
        num1 = rand.nextInt(max-min) + min;
        num2 = rand.nextInt(max-min) + min;
        opNum = rand.nextInt(4);

        if (opNum == 0){
            op = "+";
            results = num1+num2;
        }else if(opNum == 1){
            op = "-";
            results = num1-num2;
        }else if(opNum == 2){
            op = "x";
            results = num1*num2;
        }else{
            op = "/";
            results = (num1%num2)+rand.nextInt((max-min)*2);
            num1 = num2*results;
        }
    }

    public void setResults(int num){
        results = num;
    }

    public int getNum1(){
        return num1;
    }

    public int getNum2(){
        return num2;
    }

    public String getOp(){
        return op;
    }

    public int getResults(){
        return results;
    }
}
