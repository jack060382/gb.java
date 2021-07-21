package task8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperatorBtn extends Btn implements ActionListener {

    public OperatorBtn(CalcState calcState, String name) {
        super(calcState, name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (calcState.isWaitSecondOperand() || name == "sqrt") {
            // Calculating
            String operator = calcState.getOperator();
            if (name == "sqrt") {
                operator = name;
            }
            switch (operator) {
                case "+" : {
                    calcState.setFirstOperand(
                            calcState.getFirstOperand() + calcState.getSecondOperand()
                    );
                } break;
                case "-" : {
                    calcState.setFirstOperand(
                            calcState.getFirstOperand() - calcState.getSecondOperand()
                    );
                } break;
                case "*" : {
                    calcState.setFirstOperand(
                            calcState.getFirstOperand() * calcState.getSecondOperand()
                    );
                } break;
                case "/" : {
                    calcState.setFirstOperand(
                            calcState.getFirstOperand() / calcState.getSecondOperand()
                    );
                } break;
                case "sqrt" : {
                    calcState.setFirstOperand(
                            Math.sqrt(calcState.getFirstOperand())
                    );
                } break;
            }
            calcState.setSecondOperand(0);
        }
        if (name != "=" && name != "sqrt") {
            calcState.setOperator(name);
        }
        else {
            calcState.setOperator("");
        }
        calcState.setWaitState(true);
        calcState.setWaitDecimalPart(false);

        pushBtn();
    }

}
