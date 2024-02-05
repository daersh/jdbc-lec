package section01.insert;

import java.sql.Connection;
import static common.JDBCTemplate.*;

/**
 * @MenuService: Connection 객체 생성, 소멸(close) 및 비즈니스 로직, 트랜젝션(commit,rollback) 처리하기 위한 클래스
 * */
public class MenuService {
    public void registMenu(Menu registMenu) {
        Connection con = getConnection();

        MenuRepository repo = new MenuRepository();
        int result= repo.registMenu(con,registMenu);
        if(result>0){
            commit(con);
        }else{
            rollback(con);
        }
        close(con);
    }
}
