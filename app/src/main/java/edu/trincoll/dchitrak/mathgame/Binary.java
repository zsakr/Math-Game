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
            op = "-"
        }

        convert();
    }

    public void convert(){
        num1 =
    }
}
