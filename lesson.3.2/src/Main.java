import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Lesson 3.2");
        //DriverManager.getConnection("jdbc:mysql://localhost:3306/university?user=root&password=pass");

        Connection conn = DBConnection.getConnection();
    }
}
