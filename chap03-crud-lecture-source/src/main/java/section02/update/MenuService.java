package section02.update;

import common.JDBCTemplate;
import java.sql.Connection;
import static common.JDBCTemplate.*;

public class MenuService {

    public void modifyMenu(Menu modifyMenu) {
        Connection con = getConnection();
        MenuRepository repository = new MenuRepository();


        if(repository.modifyMenu(con, modifyMenu)>0){
            System.out.println("found!");
            commit(con);
        }else{
            System.out.println("Not found!");
            rollback(con);
        }



        close(con);
    }
}
