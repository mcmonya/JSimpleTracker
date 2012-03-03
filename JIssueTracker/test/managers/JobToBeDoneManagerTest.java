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
        assertEquals(job1.getRequestor().getId(), allJobs.get(1).getRequestor().getId());
        assertEquals(2, allJobs.size());
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
        assertEquals(1, result.size());
        assertEquals(programmer1.getId(), result.get(0).getProgrammer().getId());
    }


    @Test
    public void testGetAllJobsAfterId() throws Exception
    {
        System.out.println("getAllJobsAfterId");
        User programmer = userManager.createUser("programmer", "pass");
        User requestor = userManager.createUser("requestor", "pass");
        JobToBeDone[] jobs = new JobToBeDone[5];
        for(int i = 0; i < jobs.length; ++i)
        {
            jobs[i] = jobsManager.createNewJob(requestor, programmer, "TODO", 1);
        }
        List<JobToBeDone> res = jobsManager.getAllJobsAfterId(jobs[2].getId());
        assertEquals(2, res.size());
    }
    
    @Test
    public void testDeleteJob() throws Exception
    {
        User user = userManager.createUser("programmer", "pass");
        JobToBeDone[] jobs = new JobToBeDone[5];
        for(int i = 0; i < jobs.length; ++i)
        {
            jobs[i] = jobsManager.createNewJob(user, user, "TODO", 1);
        }
        
        jobsManager.deleteJob(jobs[0]);
        jobsManager.deleteJob(jobs[1]);
        
        List<JobToBeDone> res = jobsManager.getAllJobs();
        assertEquals(3, res.size());
    }
    
    
    /**
     * Test of getAllUndoneJobs method, of class JobToBeDoneManager.
     */
    @Test
    public void testGetAllUndoneJobs() throws Exception{
        System.out.println("getAllUndoneJobs");
        User user = userManager.createUser("User", "asd");
        JobToBeDone[] jobs = new JobToBeDone[5];
        for(int i = 0; i < jobs.length; ++i)
        {
            jobs[i] = jobsManager.createNewJob(user, user, "TODO", 1);
        }
        List<JobToBeDone> res = jobsManager.getAllUndoneJobs(); 
        assertEquals(5, res.size());
    }

    /**
     * Test of getAllDoneJobs method, of class JobToBeDoneManager.
     */
    @Test
    public void testGetAllDoneJobs() throws Exception {
        System.out.println("getAllDoneJobs");
        User user = userManager.createUser("User", "asd");
        JobToBeDone[] jobs = new JobToBeDone[5];
        for(int i = 0; i < jobs.length; ++i)
        {
            jobs[i] = jobsManager.createNewJob(user, user, "TODO", 1);
        }
        jobs[1].setDone(true);
        jobsManager.updateJob(jobs[1]);
        List<JobToBeDone> res = jobsManager.getAllDoneJobs();
        assertEquals(1, res.size());
        assertEquals(jobs[1].getId(), res.get(0).getId());
    }
}
