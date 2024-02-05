package common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
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
            con.setAutoCommit(false);//설명. DML(insert,update,delete)실행 시 커밋 수동으로 하겠다는 뜻
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
    public static void close(Statement connection){
        try {
            if(connection!=null&&!connection.isClosed())
                connection.close();
            System.out.println("Statement closed...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void close(ResultSet connection){
        try {
            if(connection!=null&&!connection.isClosed())
                connection.close();
            System.out.println("ResultSet closed...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void commit(Connection con) {
        try {
            if(con!=null&&!con.isClosed())
                con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollback(Connection con) {
        try {
            if(con!=null&&!con.isClosed())
                con.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
