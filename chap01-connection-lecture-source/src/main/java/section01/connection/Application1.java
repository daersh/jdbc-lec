package section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 목표<br><br>
 * 해당 DBMS와 계정에 맞는 Connection객체를 새엇ㅇ할 수 있다. feat. DBMS Driver 추가 <br>
 */
public class Application1 {

    public static void main(String[] args) {
        Connection con = null;
        try {
            //설명. Class.forName:드라이버 클래스 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","mariadb");

            System.out.println("con = " + con);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(con!=null && !con.isClosed()) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
