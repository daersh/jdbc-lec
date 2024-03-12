package section02.template;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @JDBCTemplate:
 * @- JDBC를 위한 메서드만 따로 모듈화(Connection 객체 생성, close 메소드 처리(Connection, Statement, PreparedStatement))
 * 하기 위한 클래스이다.
 * */
public class JDBCTemplate {
    public static Connection getConnection(){
        Connection con = null;
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/java/section01/connection/jdbc-config.properties"));
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            Class.forName(driver);
            //설명. user,password를 안뽑고 properties를 통해 얻을 수 있다.
            // properties 객체가 user, password라는 키를 가지고 있어 Connection 객체 생성
            con = DriverManager.getConnection(url,properties);
            //설명. 이번에는 close메소드를 여기서 호출 안함.
            // 이유: 이 모듈은 연결한 con을 반환하기 위한 것이기 때문에 해당 dbms와 연결할 수 있는 Connection 반환만 해당되게 작성
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
