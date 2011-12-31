/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mychal
 */
public class DatabaseManager {
    private static final String connectionString = "jdbc:sqlite:production.db";
    public static Connection getConnection() throws SQLException
    {
        Connection result = DriverManager.getConnection(connectionString);
        return result;
    }
    public static void createTableStructure() throws SQLException
    {
        Connection conn = getConnection();
    }
}
