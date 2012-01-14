/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Mychal
 */
public interface IssueTrackerConnectionProvider {
    public Connection createConnection() throws SQLException;
}
