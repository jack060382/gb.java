public class Main {

        public static void main(String[] args) throws InterruptedException {
            System.out.println("Lesson 2.6");
            new Thread(() -> new Server()).start();
            new Thread(() -> new Client()).start();

            //new Thread(Server::new).start();
        }

}
