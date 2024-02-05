package section01.statement;

import common.JDBCTemplate;

import java.sql.Connection;

import static common.JDBCTemplate.*;

/**
 * 목표<br><br>
 *  <br>
 */
public class Application {
    public static void main(String[] args) {
        Connection connection = getConnection();
        System.out.println("connection = " + connection);
        close(connection);

    }
}
