package section03.delete;

import common.JDBCTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static common.JDBCTemplate.*;

/**
 * 목표<br><br>
 * 삭제 기능
 */
public class Application {

    /** @DELETE
     * @1.soft-delete: 보통의 경우에는 soft-delete 함. 값을 update하는 경우에 해당
     * @2.hard-delte: DELETE를 하여 실제 데이터도 제거되도록 한다.
     * */
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;



        /* 목차. soft-delete*/
        String query1 = "UPDATE TBL_MENU set orderable_status = 'N' where menu_code = ?";   //참고. orderable_status: 주문가능상태를 'N'으로 하여 불가하게 함?
        /* 목차. hard-delete*/
        String query2 = "delete from tbl_menu where menu_code = ?";
        try {
            pstmt = con.prepareStatement(query2);           //참고. query2로 변경 후 hard-delete가 되어 삭제된다.
            pstmt.setInt(1,27);
            result = pstmt.executeUpdate();

            if(result>0){

                commit(con);
            }else{
                rollback(con);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(con);
        }

        System.out.println("result = " + result);
    }
}
