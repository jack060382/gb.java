package task8;

import javax.swing.*;

public class CalcState {

    private double firstOperand = 0;
    private double secondOperand = 0;
    private boolean isWaitSecondOperand = false;
    private boolean isWaitDecimalPart = false;
    private String operator = "";
    private JTextField inputArea;

    public JTextField getTextArea() {
        return inputArea;
    }

    public void clear() {
        firstOperand = 0;
        secondOperand = 0;
        isWaitSecondOperand = false;
        isWaitDecimalPart = false;
        operator = "";
    }

    public void setTextArea(JTextField inputArea) {
        this.inputArea = inputArea;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double getFirstOperand() {
        return firstOperand;
    }

    public double getSecondOperand() {
        return secondOperand;
    }

    public boolean isWaitDecimalPart() {
        return isWaitDecimalPart;
    }

    public boolean isWaitSecondOperand() {
        return isWaitSecondOperand;
    }

    public void setFirstOperand(double value) {
        firstOperand = value;
    }

    public void setSecondOperand(double value) {
        secondOperand = value;
    }

    public void setWaitState(boolean state) {
        isWaitSecondOperand = state;
    }

    public void setWaitDecimalPart(boolean state) {
        isWaitDecimalPart = state;
    }

    public String toString() {
        //System.out.println(String.valueOf(firstOperand) + " " + operator + " " + String.valueOf(secondOperand));
        String result = "";

        result += String.valueOf(firstOperand);

        if (operator != "") {
            result += operator;
        }
        if (secondOperand > 0) {
            result += String.valueOf(secondOperand);
        }

        return result;
    }

}
