public class ThreadSafe {
    int count = 0;

    synchronized void increase () {
        count ++ ;
    }

}
