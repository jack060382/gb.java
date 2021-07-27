public class ThreadSafeTank {
    private Object lock = new Object();
    float size = 20.5f;

    float get () {
        System.out.println("Enter");
        synchronized (this) {
            size -= 1.25;
            return size;
        }
    }
}
