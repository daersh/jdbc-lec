package section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 목표<br><br>
 * properties 파일을 통해 jdbc 연결 <br>
 */
//참고. properties파일은 실제 서비스 만들 떄 같이 깃에 올리면 안된다!!!!!!!!!!!!!!!!!!!!!!
public class Application2 {
    public static void main(String[] args) {
        Properties properties =new Properties();
        Connection con = null;
        try {
            properties.load(new FileReader("src/main/java/section01/connection/jdbc-config.properties"));
            System.out.println("properties = " + properties);
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            System.out.println("con = " + con);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
        }
    }

    private static void close(Connection con) {
        try {
            if(con !=null && !con.isClosed())
                con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
