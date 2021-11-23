public class SimpleBoxApp {
    // Класс-обёртка

    public static void main(String[] args) {
        SimpleBox box1 = new SimpleBox(10);
        SimpleBox box2 = new SimpleBox(20);

        if (box1.getObj() instanceof Integer && box2.getObj() instanceof Integer) {
            int sum = (int)box1.getObj() + (int)box2.getObj();
            System.out.println("sum = " + sum);
        }
        else {
            System.out.println("Incorrect objects.");
        }

    }

    private static class SimpleBox {

        private Object obj;

        public SimpleBox(Object obj) {
            this.obj = obj;
        }

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }

    }

}
