package section01.statement;

import common.JDBCTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static common.JDBCTemplate.*;

/**사번을 입력받아 한명의 사원을 조회하는 기능을 가진 클래스*/
public class Application2 {
    public static void main(String[] args) throws SQLException {
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;
        String empID=null;

        Scanner sc = new Scanner(System.in);
        System.out.print("사번 입력:");
        empID = sc.nextLine();
        stmt= con.createStatement();

        // 중요. 쿼리문 입력값 들어가는 곳에 sql Injection 주의
        String query =  "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empID+"'";
        stmt.executeQuery(query);
        rset= stmt.getResultSet();
        if(rset.next()){
            System.out.println(rset.getString("EMP_ID")+ rset.getString("EMP_NAME"));
        }else {
            System.out.println("조회 결과 없음");
        }

        close(rset);
        close(stmt);
        close(con);

    }
}
