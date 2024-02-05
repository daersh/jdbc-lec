package section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static common.JDBCTemplate.*;

/**
 * 목표<br><br>
 * Statement, ResultSet을 통해 연결된 jdbc에 쿼리를 전송하고 결과를 반환하여 저장한 후 출력 <br>
 */
public class Application {
    public static void main(String[] args) {
        /*목차 1. 트랜잭션 처리를 위한 DBMS 연동용 Connection객체 생성*/
        Connection connection = getConnection();

        /*목차 2. Connection을 통해 트랜젝션 처리 - 비즈니스 로직 수행, CRUD(create,read,update,delete) */
        System.out.println("connection = " + connection);
        Statement stmt = null; // 설명. 쿼리 운반, 결과를 반환하는 객체
        ResultSet rset = null; // 설명. 조회 결과를 반환하는 객체
                               //  resultset: 다중행
        try {
            //설명. 연결, 쿼리 전송, 반환 값 저장, 출력
            stmt = connection.createStatement();
            rset=stmt.executeQuery("SELECT * FROM EMPLOYEE");   //참고.excuteQuery는 resultset 타입으로 반환함.
            while (rset.next()){ // 다음값이 없을때까지 돌림
                System.out.println(rset.getString("EMP_NAME")
                +','+rset.getInt("SALARY")); //인덱스는 1부터시작
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            //참고. close는 가장 범위 작은것부터
            //설명. overloading을 통해 여러 타입을 받음
            close(rset);
            close(stmt);
            close(connection);
        }



    }
}
