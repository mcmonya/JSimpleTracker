/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;

/**
 *
 * @author Mychal
 */
public class ProductionConnectionProvider implements IssueTrackerConnectionProvider{

    @Override
    public Connection createConnection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
