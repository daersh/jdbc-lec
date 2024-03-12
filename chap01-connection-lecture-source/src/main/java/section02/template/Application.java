package section02.template;
import java.sql.Connection;
//설명. import 뒤에 static을 붙여 다른 클래스의 static 메소드를 내 클래스인 것 처럼 사용가능
import static section02.template.JDBCTemplate.*;

/**
 * 목표<br><br>
 *  모듈화를 통해 Connection 객체를 받아오기<br>
 */
public class Application {

    public static void main(String[] args) {
        Connection connection = getConnection();
        /*설명. 비즈니스 로직 실행 - 트랜잭션 처리*/

        System.out.println("connection = " + connection);

        close(connection);
    }

}
