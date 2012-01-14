/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mychal
 */
public class TestConnectionProvider implements IssueTrackerConnectionProvider {

    @Override
    public Connection createConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:sqlite:baza_testowa.db");
    }
    
}
