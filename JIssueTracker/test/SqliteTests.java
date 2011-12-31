/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;

/**
 *
 * @author Mychal
 */
public class SqliteTests {
    
    private static Connection connection;
    
    public SqliteTests() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:baza.db");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        connection.close();
    }
    
    @Before
    public void setUp() {
    
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testTableCreation() throws Exception
    {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS PROGRAMMERS;");
        statement.executeUpdate("CREATE TABLE PROGRAMMERS(ID INTEGER PRIMARY KEY AUTOINCREMENT"+
                                    ", NICK VARCHAR(50));");
        statement.executeUpdate("INSERT INTO PROGRAMMERS(NICK) VALUES('Michał');");
        ResultSet r = statement.executeQuery("Select * FROM PROGRAMMERS;");
        while(r.next())
        {
            System.out.println(r.getString(2));
        }
        r = statement.getGeneratedKeys();
        if(r.next())
            assertEquals(1, r.getInt(1));
    }
}
