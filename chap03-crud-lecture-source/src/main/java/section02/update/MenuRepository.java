package section02.update;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MenuRepository {

    public int modifyMenu(Connection con, Menu modifyMenu) {
        int result =0;
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/section01/insert/mapper/menu-mapper.xml"));
            pstmt = con.prepareStatement(prop.getProperty("updateMenu"));
            pstmt.setString(1, modifyMenu.getMenuName());
            pstmt.setInt(2, modifyMenu.getMenuPrice());
            pstmt.setInt(3,modifyMenu.getMenuCode());
            System.out.println(prop.getProperty("updateMenu"));

            result = pstmt.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
