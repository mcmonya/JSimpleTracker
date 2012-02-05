/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import config.IssueTrackerConnectionProvider;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Mychal
 */
public class IssueTrackerManager {

    private IssueTrackerConnectionProvider connectionProvider;
    
    public IssueTrackerManager(IssueTrackerConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }
    protected Connection connection = null;

    protected void closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
        } finally {
            this.connection = null;
        }
    }

    protected void createConnection() throws SQLException {
        if (this.connection == null) {
            this.connection = connectionProvider.createConnection();
        }
    }
    
}
