public class GenericBoxApp {

    public static void main(String[] args) {
        GenericBox<String> stringBox = new GenericBox<>("string");
        stringBox.showType();

        GenericBox<Integer> intBox1 = new GenericBox<>(100);
        GenericBox<Integer> intBox2 = new GenericBox<>(200);

        intBox1.showType();

        int value = intBox1.getObj();

        int sum = intBox1.getObj() + intBox2.getObj();

        ComplexGenericBox<String, Integer> ComplexGenericBox = new ComplexGenericBox("str", 111);
        ComplexGenericBox.showType();
    }

    private static class ComplexGenericBox <V, R> {
        private V obj1;
        private R obj2;

        public ComplexGenericBox(V obj1, R obj2) {
            this.obj1 = obj1;
            this.obj2 = obj2;
        }

        public void showType() {
            System.out.println("Type of V is "+ obj1.getClass().getName());
            System.out.println("Type of R is "+ obj2.getClass().getName());
        }
    }

    private static class GenericBox <T> {
        private T obj;

        public GenericBox(T obj) {
            this.obj = obj;
        }

        public T getObj() {
            return obj;
        }

        public void setObj(T obj) {
            this.obj = obj;
        }

        public void showType() {
            System.out.println("Type of T is "+ obj.getClass().getName());
        }

    }

}
