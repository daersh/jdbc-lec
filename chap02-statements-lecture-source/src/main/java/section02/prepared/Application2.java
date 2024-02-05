package section02.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static common.JDBCTemplate.*;

/**
 * @1.목표<br><br>
 * preparedStatement의 장점 숙지 <br>
 * @2.장점
 * 1. 하나의 문자열 형태로 쿼리 작성이 가능하다.<br>
 * 2. SQL Injection 공격을 막을 수 있다. <br>
 * 3. 쿼리문을 미리 파싱하고 캐싱하기 때문에 재해석 과정을 생략하여 속도도 더 빠름
 */
public class Application2 {
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        System.out.print("사번 입력: ");
        Scanner sc =new Scanner(System.in);
        String empId= sc.nextLine();
        //설명.
        // 1. PreparedStatement는 Statement와 달리 placeholder(?)를 통해 하나의 문자열 형태로 쿼리 작성 가능
        //       ?: placeholder (1~물음표 갯수)
        // 2. 이를 통해 하자(?)를 메꾸고 쿼리문을 완성하여 전송할 수 있다.
        // 3. SQL Injection 공격도 막을 수 있다.

        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?";
        String query2 = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ? AND ENT_YN = ?";
        //목차 1. placeHolder가 하나인 경우
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,empId);
            rset = pstmt.executeQuery();
            if(rset.next()){
                System.out.println(rset.getString("EMP_ID")+", "
                + rset.getString("EMP_NAME"));
            }else{
                System.out.println("해당 사원 번호를 가진 사원이 없습니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        //목차 2. 여러 placeHolder가 있는 경우
        con = getConnection();
        try {
            pstmt = con.prepareStatement(query2);
            pstmt.setString(1,empId);
            pstmt.setString(2,"N");

            rset = pstmt.executeQuery();
            if(rset.next()){
                System.out.println(rset.getString(1)+" "+rset.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }

    }
}
