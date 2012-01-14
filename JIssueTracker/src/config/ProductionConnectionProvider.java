/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * class used for obraining connection objects in production environment
 * 
 * @author Mychal
 */
public class ProductionConnectionProvider implements IssueTrackerConnectionProvider{

    @Override
    public Connection createConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:sqlite:baza_produkcyjna.db");
    }
    
}
