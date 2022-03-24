import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Sender {

    private Sender() {}
    private static final char commandMarker = '#';
    private static final String[] commands = {
        "upload",
        "download",
            "getlist",
            "chdir",
            ""
    };


    static void getFile(DataInputStream is, Path clientDir, int size2, byte[] buf) throws IOException {
        String fileName = is.readUTF();
        System.out.println("received: " + fileName);
        long size = is.readLong();
        try(OutputStream fos = new FileOutputStream(clientDir.resolve(fileName).toFile())) {
            for (int i = 0; i < (size + size2 - 1) / size2; i++) {
                int readBytes = is.read(buf);
                fos.write(buf, 0 , readBytes);
            }
        }
    }


    static void sendFile(String fileName, DataOutputStream os, Path clientDir) throws IOException {

        byte[] fileNameBytes = fileName.getBytes(StandardCharsets.UTF_8);
        Path file = clientDir.resolve(fileName);
        long size = Files.size(file);

        os.write((byte)commandMarker);
        os.write(String.format("%8s","upload").getBytes(StandardCharsets.UTF_8));
        os.write(fileNameBytes.length);
        os.write(fileNameBytes);
        os.writeLong(size);
        os.write(Files.readAllBytes(file));
        os.flush();

        /*
        os.writeUTF("#file#");
        os.writeUTF(fileName);
        Path file = clientDir.resolve(fileName);
        long size = Files.size(file);
        byte[] bytes = Files.readAllBytes(file);
        os.writeLong(size);
        os.write(bytes);
        os.flush();
        */
    }

}
