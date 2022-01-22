package dbadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import dbadapter.Movies;
import dbadapter.Configuration;
import dbadapter.DBFacade;
import dbadapter.UserAccount;
import dbadapter.Rating;
import junit.framework.TestCase;

/**
 * Testing our DBFacade.
 * 
 * @author Moeed and Raza
 *
 */

public class DBFacadeTest extends TestCase {
	
	
	private Movies testM;
	private UserAccount testUA;
	
	/**
	 * Preparing classes with static methods
	 */

	
		
		@Before
		public void setUp() throws Exception {
			
			testM = new Movies("MusterMovie", "Mustermann", "MusterActor", Date.valueOf("2021-02-28") );
			testUA = new UserAccount("moeed", "moeeddash", "moeed", 34);
			Rating testR = new Rating("moeed","MusterMovie", 7, "Great Movie!");
			ArrayList<Movies> testMovies = new ArrayList<Movies>();
			testMovies.add(testM);
			testM.setMovies(testMovies);
			
			
			// SQL statements
			String sqlCleanDB = "DROP TABLE IF EXISTS useraccount,movies,ratings;";
			String sqlCreateTableUserAccount = "CREATE TABLE useraccount (   id int(11) NOT NULL AUTO_INCREMENT,   username varchar(20) NOT NULL,   email varchar(30) NOT NULL DEFAULT '',   password varchar(20) NOT NULL DEFAULT '',   age int(5) NOT NULL, PRIMARY KEY (id) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
			String sqlCreateTableMovies = "CREATE TABLE movies (   id int(11) NOT NULL AUTO_INCREMENT,   title varchar(20) NOT NULL,   director varchar(20) DEFAULT NULL,   mainActor varchar(20) NOT NULL,   publishingDate date NOT NULL,   PRIMARY KEY (id) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
			String sqlCreateTableRating = "CREATE TABLE ratings (   id int(11) NOT NULL AUTO_INCREMENT,   rating int(11) NOT NULL,   uid varchar(20) NOT NULL,   mid varchar(20) NOT NULL,   comment varchar(255) DEFAULT NULL, PRIMARY KEY (id) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
			String sqlInsertUserAccount = "INSERT INTO useraccount (username,email,password,age) VALUES (?,?,?,?)";
			String sqlInsertMovie = "INSERT INTO movies (title,director,mainActor,publishingDate) VALUES (?,?,?,?)";
			String sqlInsertRating = "INSERT INTO ratings (rating, uid, mid, comment) VALUES (?,?,?,?)";
			
			// Perform database updates
					try (Connection connection = DriverManager
							.getConnection(
									"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
											+ Configuration.getPort() + "/" + Configuration.getDatabase(),
									Configuration.getUser(), Configuration.getPassword())) {

						try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
							psClean.executeUpdate();
						}
						try (PreparedStatement psCreateUserAccount = connection.prepareStatement(sqlCreateTableUserAccount)) {
							psCreateUserAccount.executeUpdate();
						}
						try (PreparedStatement psCreateMovie = connection.prepareStatement(sqlCreateTableMovies)) {
							psCreateMovie.executeUpdate();
						}
						try (PreparedStatement psCreateRating = connection.prepareStatement(sqlCreateTableRating)) {
							psCreateRating.executeUpdate();
						}
						try (PreparedStatement psInsertUserAccount = connection.prepareStatement(sqlInsertUserAccount)) {
							psInsertUserAccount.setString(1, testUA.getUsername());
							psInsertUserAccount.setString(2, testUA.getEmail());
							psInsertUserAccount.setString(3, testUA.getPassword());
							psInsertUserAccount.setInt(4, testUA.getAge());
						}
						try (PreparedStatement psInsertMovie = connection.prepareStatement(sqlInsertMovie)) {
							psInsertMovie.setString(1, testM.getTitle());
							psInsertMovie.setString(2, testM.getDirector());
							psInsertMovie.setString(3, testM.getMainActor());
							psInsertMovie.setDate(4, (Date) testM.getPublishingDate());
						}
						try (PreparedStatement psInsertRating = connection.prepareStatement(sqlInsertRating)) {
							psInsertRating.setString(1, testR.getUid());
							psInsertRating.setString(2, testR.getMid());
							psInsertRating.setInt(3, testR.getRating());
							psInsertRating.setString(4, testR.getComment());
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
		}
		
	
	 //===============================================================================================================================================
                                                        //Create User Tests
     //===============================================================================================================================================

				

	@Test
	//test create user with correct info provided
	public final void testCreateUser() {
		
		String username="dummyman334";
		String email="dummyman@uni-due.de";
		String password="dummyman123";
		int age= 33;
		
		boolean result = DBFacade.getInstance().createUser(username,  email,  password,  age);
		
		assertTrue(result==true);
		
	}
	
	@Test
	//test create user with wrong age info provided
	public final void testCreateUserWrongAge() {
		
		String username="coolboi334";
		String email="coolboi334@uni-due.de";
		String password="dummyman123";
		int age= 10;
		
		boolean result = DBFacade.getInstance().createUser(username,  email,  password,  age);
		
		assertFalse(result==false);
		
	}
	
	@Test
	//test create user with already existing username info provided
	public final void testCreateUserWrongUsername() {
		
		String username="dummyman334";
		String email="dummyman334@uni-due.de";
		String password="dummyman123";
		int age= 33;
		
		boolean result = DBFacade.getInstance().createUser(username,  email,  password,  age);
		
		assertFalse(result==false);
		
	}
	
	
	 //===============================================================================================================================================
                                             //Create Movie Tests
     //===============================================================================================================================================
 
	
	
	@Test
	//test to create a successfull movie in db
	
    public final void testCreateMovie() {
		
		String title="hobitt";
		String director="john snow";
		String mainActor="donkey";
		Date publishingDate= new Date(2021-01-05);
		
		boolean result = DBFacade.getInstance().addingNewMoviesToDB(title,  director,  mainActor,  publishingDate);
		
		assertTrue(result==true);
		
	}
	
     //test to create a fail movie in db, that already exists
	
    public final void testCreateMovieThatAlreadyExists() {
		
		String title="hobitt";
		String director="john snow";
		String mainActor="donkey";
		Date publishingDate= new Date(2021-01-05);
		
		boolean result = DBFacade.getInstance().addingNewMoviesToDB(title,  director,  mainActor,  publishingDate);
		
		assertFalse(result==false);
		
	}
    
    
   //test to create a fail movie in db, that has a future publishing date
	
    public final void testCreateMovieWrongDate() {
		
		String title="sherlock";
		String director="john snow";
		String mainActor="Benedict";
		Date publishingDate= new Date(2026-01-05);
		
		boolean result = DBFacade.getInstance().addingNewMoviesToDB(title,  director,  mainActor,  publishingDate);
		
		assertFalse(result==false);
		
	}
    
 //test to create a fail movie in db, that has a missing parameter
	
    public final void testCreateMovieMissingData() {
		
		String title="sherlock";
		String director= null;
		String mainActor=null;
		Date publishingDate= new Date(2026-01-05);
		
		boolean result = DBFacade.getInstance().addingNewMoviesToDB(title,  director,  mainActor,  publishingDate);
		
		assertTrue(result==false);
		
	}

    
    //===============================================================================================================================================
                                         //Rate Movie Tests
   //===============================================================================================================================================
    
  
	//test to rate the movie which was successfully created
    @Test
    public final void testCreateRating() {
		
    	String username = "dummyman334";
		String title="hobitt";
    	int rating= 7;
    	String comment="too long";
		
		
		boolean result = DBFacade.getInstance().addingNewRatingToDB( username,  rating,  title,  comment);
		
		assertTrue(result==true);
		
	}
    
    
  //test to rate the movie again, duplicate rating 
    @Test
    public final void testCreateRatingDuplicate() {
		
    	String username = "dummyman334";
		String title="hobitt";
    	int rating= 8;
    	String comment="too long!!";
		
		
		boolean result = DBFacade.getInstance().addingNewRatingToDB( username,  rating,  title,  comment);
		
		assertFalse(result==false);
		
	}
     
    
    
  //test to rate the movie out of the range 1-10
    @Test
    public final void testCreateRatingRange() {
		
    	String username = "dummyman334";
		String title="hobitt";
    	int rating= 35;
    	String comment="too long";
		
		
		boolean result = DBFacade.getInstance().addingNewRatingToDB( username,  rating,  title,  comment);
		
		assertFalse(result==false);
		
	}
    
    //===============================================================================================================================================
                                               //Show Movie Tests
   //===============================================================================================================================================
    
    
    @Test
	public void testGetMoviesInDB() {

		ArrayList<Movies> m = DBFacade.getInstance().getMoviesInDB();

		// Verify return values
		//assertTrue(m.size() >= 1);
		assertTrue(m.get(0).getTitle() == testM.getTitle());
		assertTrue(m.get(0).getPublishingDate() == testM.getPublishingDate());
		assertTrue(m.get(0).getMainActor() == testM.getMainActor());
		assertTrue(m.get(0).getDirector() == testM.getDirector());
		assertTrue(m.get(0).getAvgRating() == testM.getAvgRating());

	}
    
    
    
	
	
	@After
	public void tearDown() throws Exception {
		
		
		// SQL statements
				String sqlCleanDB = "DROP TABLE IF EXISTS movies,useraccount,ratings";

				// Perform database updates
				try (Connection connection = DriverManager
						.getConnection(
								"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
										+ Configuration.getPort() + "/" + Configuration.getDatabase(),
								Configuration.getUser(), Configuration.getPassword())) {

					try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
						psClean.executeUpdate();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

}
