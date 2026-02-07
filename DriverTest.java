import java.sql.Connection;
import java.sql.DriverManager;

public class DriverTest {
    public static void main(String[] args) throws Exception {

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test",
                "root",
                "");

        System.out.println("Connection SUCCESS");
        con.close();
    }
}
