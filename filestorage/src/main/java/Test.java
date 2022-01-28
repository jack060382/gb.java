import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) throws IOException {

        System.out.println((byte) '#');
        System.out.println(String.format("%8s", "upload"));

        byte[] b = "upload   ".getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }
    }
}
