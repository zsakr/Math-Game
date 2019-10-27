package edu.trincoll.dchitrak.mathgame;

import java.util.Random;

public class GenerateProblem{

    protected int max;
    protected int min;
    protected int num1;
    protected int num2;
    protected int opNum;
    protected String op;
    protected int results;
    protected Random rand = new Random();

    public GenerateProblem (int min, int max){
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

        if(num2>num1){
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        opNum = rand.nextInt(2);

        if (opNum == 0){
            op = "+";
            results = num1+num2;
        }else if(opNum == 1){
            op = "-";
            results = num1-num2;
        }
    }

    public void setResults(int num){
        results = num;
    }

    public String getNum1(){
        return Integer.toString(num1);
    }

    public String getNum2(){
        return Integer.toString(num2);
    }

    public String getOp(){
        return op;
    }

    public String getResults(){
        return Integer.toString(results);
    }
}
