package task8;

public class Btn {

    protected CalcState calcState;

    protected String name;

    public Btn(CalcState calcState, String name) {
        this.name = name;
        this.calcState = calcState;
    }

    protected void pushBtn() {
        calcState.getTextArea().setText(calcState.toString());
    }

}
