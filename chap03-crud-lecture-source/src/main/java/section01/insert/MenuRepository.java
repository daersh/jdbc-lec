package section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class MenuRepository {
    PreparedStatement pstmt = null;
    int result = 0;
    Properties prop = new Properties();
    public int registMenu(Connection con, Menu registMenu) {

        try {

            prop.loadFromXML(new FileInputStream("src/main/java/section01/insert/mapper/menu-mapper.xml"));

            String query = prop.getProperty("insertMenu");

            System.out.println("query = " + query);

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, registMenu.getMenuName());
            pstmt.setInt(2, registMenu.getMenuPrice());
            pstmt.setInt(3, registMenu.getCategoryCode());
            pstmt.setString(4, registMenu.getOrderableStatus());

            result = pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
