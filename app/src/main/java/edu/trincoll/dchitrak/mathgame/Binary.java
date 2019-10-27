package edu.trincoll.dchitrak.mathgame;

public class Binary extends GenerateProblem {

    public Binary(int min, int max) {
        super(min, max);
    }

    public void makeProblem(){
        super.makeProblem();
        if(op.equals("*")){
            op = "+";
        } else{
            op = "-";
        }

    }

    public String getNum1(){
        return Integer.toBinaryString(num1);
    }

    public String getNum2(){
        return Integer.toBinaryString(num2);
    }

    public String getResults(){
        return Integer.toBinaryString(results);
    }

}
