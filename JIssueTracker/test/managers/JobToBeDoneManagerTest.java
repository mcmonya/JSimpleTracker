/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import config.TestConnectionProvider;
import entities.JobToBeDone;
import entities.User;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author Mychal
 */
public class JobToBeDoneManagerTest {
    
    JobToBeDoneManager jobsManager = new JobToBeDoneManager(new TestConnectionProvider());
    UserManager userManager = new UserManager(new TestConnectionProvider());
    
    public JobToBeDoneManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Class.forName("org.sqlite.JDBC");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() throws SQLException{
        UserManager.dropTableIfExists(new TestConnectionProvider());
        UserManager.createTableIfNotExists(new TestConnectionProvider());
        JobToBeDoneManager.dropTableIfExists(new TestConnectionProvider());
        JobToBeDoneManager.createTableIfNotExists(new TestConnectionProvider());
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
        User requestor = userManager.createUser("Requestor", "pass");
        User programmer = userManager.createUser("Programmer", "pass");
        String todo = "DO ZROBIENIA";
        int priority = 2;
        JobToBeDone result = jobsManager.createNewJob(requestor, programmer, todo, priority);
        assertFalse(result.getId() == -1);
        assertTrue(result.getRequestor().getLogin().equals("Requestor"));
        assertEquals("DO ZROBIENIA", result.getTodo());
    }

    /**
     * Test of getAllJobs method, of class JobToBeDoneManager.
     */
    @Test
    public void testGetAllJobs() throws Exception {
        System.out.println("getAllJobs");
        User requestor = userManager.createUser("Requestor", "pass");
        User programmer = userManager.createUser("Programmer", "pass");
        JobToBeDone job1 = jobsManager.createNewJob(requestor, programmer, "DO ZROBIENIA", 2);
        JobToBeDone job2 = jobsManager.createNewJob(requestor, programmer, "DO ZROBIENIA 2", 1);
        List<JobToBeDone> allJobs = jobsManager.getAllJobs();
        assertEquals(job1.getTodo(), allJobs.get(0).getTodo());
        assertEquals(allJobs.get(1).getRequestor().getId(), job1.getRequestor().getId());
        assertEquals(allJobs.size(), 2);
    }

    /**
     * Test of getAllJobsAssignedToProgrammer method, of class JobToBeDoneManager.
     */
    @Test
    public void testGetAllJobsAssignedToProgrammer() throws Exception{
        System.out.println("getAllJobsAssignedToProgrammer");
        User programmer1 = userManager.createUser("Programmer1", "pass1");
        User programmer2 = userManager.createUser("Programmer2", "pass1");
        User requestor = userManager.createUser("Requestor", "pass");
        JobToBeDone job1 = jobsManager.createNewJob(requestor, programmer1, "TODO", 1);
        JobToBeDone job2 = jobsManager.createNewJob(requestor, programmer2, "TODO", 1);
        List<JobToBeDone> result = jobsManager.getAllJobsAssignedToProgrammer(programmer1);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getProgrammer().getId(), programmer1.getId());
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
