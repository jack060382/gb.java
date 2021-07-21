package task8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DigitBtn extends Btn implements ActionListener {

    public DigitBtn(CalcState calcState, String name) {
        super(calcState, name);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (calcState.isWaitSecondOperand() && calcState.getOperator() == "") {
            calcState.clear();
            calcState.getTextArea().setText("");
        }

        double operand;
        if (!calcState.isWaitSecondOperand()) {
            operand = calcState.getFirstOperand();
        }
        else {
            operand = calcState.getSecondOperand();
        }

        if (operand == 0 && !calcState.isWaitDecimalPart()) {
            operand = Integer.valueOf(name);
        }
        else {
            if (calcState.isWaitDecimalPart()) {
                if (Math.round(operand) < operand && operand != 0) {
                    operand = Double.valueOf(String.valueOf(operand) + name);
                }
                else {
                    operand = Math.round(operand) + 0.1*Integer.parseInt(name);
                }
            }
            else {
                operand = Double.valueOf(String.valueOf(Integer.valueOf(String.valueOf(Math.round(operand)))) + name);
            }
        }

        if (!calcState.isWaitSecondOperand()) {
            calcState.setFirstOperand(Double.valueOf(operand));
        }
        else{
            calcState.setSecondOperand(Double.valueOf(operand));
        }

        pushBtn();
    }

}
