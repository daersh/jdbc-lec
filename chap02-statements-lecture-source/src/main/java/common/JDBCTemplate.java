package common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection(){
        Connection con = null;
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("src/main/java/config/connection-info.properties"));
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            Class.forName(driver);
            con = DriverManager.getConnection(url,properties);

            System.out.println("Connection opened...");
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

    public static void close(Connection connection){
        try {
            if(connection!=null&&!connection.isClosed())
                connection.close();
            System.out.println("Connection closed...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
