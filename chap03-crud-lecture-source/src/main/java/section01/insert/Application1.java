package section01.insert;

import common.JDBCTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static common.JDBCTemplate.*;

/**
 * 목표<br><br>
 * 1. <br>
 */
public class Application1 {
    public static void main(String[] args) {
        Connection con = getConnection();
        System.out.println("con = " + con);
        PreparedStatement pstmt = null;
        /*설명.
            * DML(insert,update,delete) 작업 시 반환 결과는 int값이 된다. (ResultSet 사용 안한다.)
            * 기존 조회일 때는 executeQuery()를 사용했지만 DML 작업 시에는 executeUpdate()를 사용한다.
        * */

        int result = 0;
        String query = "INSERT INTO TBL_MENU (MENU_NAME,MENU_PRICE,CATEGORY_CODE,ORDERABLE_STATUS) "
                +"VALUES (?,?,?,?)";

        try {

            pstmt = con.prepareStatement(query);

            pstmt.setString(1,"봉골레청국장");
            pstmt.setInt(2,5000);
            pstmt.setInt(3,4);
            pstmt.setString(4,"Y");
            /* 설명.
             *  혹시라도 기존의 테이블에서 auto_increment 값이 증가되어 있다면 다시 줄이고자 할 때 잘 못 들어간 뒤의 값을 지우고
             *  auto_increment를 원하는 값까지 초기화 후 insert를 해야한다.
             *  (ex: 100번대부터 insert가 될 경우)
             *  DELETE FROM tbl_menu WHERE menu_code > 99;
             *  alter table tbl_menu auto_increment = 24;
             *  INSERT INTO tbl_menu(menu_name, menu_price, category_code, orderable_status)
             *   VALUES('봉골레 청국장', 5000, 4, 'Y');
             *  SELECT * FROM tbl_menu ORDER BY 1 DESC;
             * */

            result = pstmt.executeUpdate(); //중요. 자동 commit 상태에서는 executeUpdate 하면 자동 커밋된다
                                            // 수동 커밋 변환 필요. jdbctemplate 클래스에 con.autocommit(false)시키먄됨

            if(result>0){
                con.commit();
                System.out.println("insert result: "+result +", 수동 커밋!");
            }else{
                con.rollback();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(con);
        }

    }
}
