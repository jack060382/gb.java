import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// https://iliachemodanov.ru/ru/blog-ru/12-tools/53-jetbrains-intellij-idea-cannot-resolve-symbol-ru
public class ExecutorApp {
    public static void main(String[] args) {
        ExecutorService executorService = new Executors.newCachedThreadPool();
        executorService.execute(() -> System.out.println(Thread.currentThread().getName()));

        Future<?> future = executorService.submit(() -> System.out.println(Thread.currentThread().getName()));
        // future.get - join у обычного потока. ждёт, пока задача не будет выполнена
        Future<String> str = executorService.submit(new MyCallable());

        executorService.shutdown(); // не принимает новых задач, но старые закончит
        executorService.shutdownNow(); // ещё и всем работающим шлёт interrupt
    }

    private static class MyCallable implements Callable<String> {
        public String call() throws Exception {
            return "string";
        }
    }
}
