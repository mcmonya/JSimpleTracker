/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.JobToBeDone;
import entities.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mychal
 */
public class JobToBeDoneManagerTest {
    
    public JobToBeDoneManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createNewJob method, of class JobToBeDoneManager.
     */
    @Test
    public void testCreateNewJob() throws Exception {
        System.out.println("createNewJob");
        User requestor = null;
        User programmer = null;
        String todo = "";
        int priority = 0;
        JobToBeDoneManager instance = null;
        JobToBeDone expResult = null;
        JobToBeDone result = instance.createNewJob(requestor, programmer, todo, priority);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllJobs method, of class JobToBeDoneManager.
     */
    @Test
    public void testGetAllJobs() throws Exception {
        System.out.println("getAllJobs");
        JobToBeDoneManager instance = null;
        List expResult = null;
        List result = instance.getAllJobs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllJobsAssignedToProgrammer method, of class JobToBeDoneManager.
     */
    @Test
    public void testGetAllJobsAssignedToProgrammer() {
        System.out.println("getAllJobsAssignedToProgrammer");
        User programmer = null;
        JobToBeDoneManager instance = null;
        List expResult = null;
        List result = instance.getAllJobsAssignedToProgrammer(programmer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllJobsByRequestor method, of class JobToBeDoneManager.
     */
    @Test
    public void testGetAllJobsByRequestor() {
        System.out.println("getAllJobsByRequestor");
        User requestor = null;
        JobToBeDoneManager instance = null;
        List expResult = null;
        List result = instance.getAllJobsByRequestor(requestor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUndoneJobs method, of class JobToBeDoneManager.
     */
    @Test
    public void testGetAllUndoneJobs() {
        System.out.println("getAllUndoneJobs");
        JobToBeDoneManager instance = null;
        List expResult = null;
        List result = instance.getAllUndoneJobs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllDoneJobs method, of class JobToBeDoneManager.
     */
    @Test
    public void testGetAllDoneJobs() {
        System.out.println("getAllDoneJobs");
        JobToBeDoneManager instance = null;
        List expResult = null;
        List result = instance.getAllDoneJobs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
