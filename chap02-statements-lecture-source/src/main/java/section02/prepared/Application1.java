package section02.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static common.JDBCTemplate.*;
/**
 * 목표<br><br>
 * PreparedStatement 사용법 숙지 <br>
 */
public class Application1 {
    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        try {
            //설명. sql문을 미리 받고 생성됨.
            //설명. sql Injection 공격 막을 수 있다.

            pstmt = con.prepareStatement("SELECT EMP_ID,EMP_NAME FROM EMPLOYEE");
            rset = pstmt.executeQuery();
            while (rset.next()){
                System.out.println(rset.getString(1)+ ' '+rset.getString(2));
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
