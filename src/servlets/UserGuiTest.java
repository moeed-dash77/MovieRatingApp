package servlets;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.Test;

import net.sourceforge.jwebunit.junit.WebTester;


/**
 * This class performs a system test on the UserGUI using JWebUnit.
 * 
 * @author Moeed and Raza
 *
 */


public class UserGuiTest {

	private WebTester tester;
	
	/**
	 * Create a new WebTester object that performs the test.
	 */
	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://localhost:8080/MovieRatingApp/");
	}
	
	@Test
	public void testUserGuiLifCycle() {
		
		// Start testing for defaultWebpageUser which contains the register form
		
		 tester.beginAt("defaultWebpageUser?action=selectRegisterForm");
		 
		// Check all components of the register form
			tester.assertTitleEquals("Movie Rating App - Register");
			tester.assertFormPresent();
			tester.assertTextPresent("Required Information");
			tester.assertTextPresent("Username");
			tester.assertFormElementPresent("username");
			tester.assertTextPresent("email");
			tester.assertFormElementPresent("email");
			tester.assertTextPresent("Password");
			tester.assertFormElementPresent("password");
			tester.assertTextPresent("age");
			tester.assertFormElementPresent("age");
			tester.assertButtonPresent("SelectHOWebpage");
			
	//change here while retesting	 
		// Submit the form with given parameters
			tester.setTextField("username", "comustersdumm222");
			tester.setTextField("email", "cmusterss2umm@gummy.de");
			tester.setTextField("password", "testPs");
			tester.setTextField("age", "27");
			
			tester.clickButton("SelectHOWebpage");
			
		//check if user successfully registered representation showed
			tester.assertTitleEquals("Movie Rating App - Registeration Form");
			tester.assertTextPresent("Registeration Successful");
			tester.assertLinkPresent("OkRegister");
			tester.assertLinkPresentWithText("User Webpage");
			
		//check if fail registeration representation shown correctly
			
			 tester.clickLinkWithExactText("Register");
			 
			 //set false info where age < 18
			    tester.setTextField("username", "muster34222");
				tester.setTextField("email", "testmuster342222@info.de");
				tester.setTextField("password", "testFail");
				tester.setTextField("age", "12");
				
				tester.clickButton("SelectHOWebpage");
				
			//fail representaion
				tester.assertTitleEquals("Movie Rating App - Registeration Form");
				tester.assertTextPresent("Registeration Failed");
				tester.assertLinkPresent("FailRegister");
				tester.assertLinkPresentWithText("Try Again!");
				
			//set false info where same username used.
				tester.clickLinkWithText("Try Again!");
	//change here			
				tester.setTextField("username", "comustersdumm222");
				tester.setTextField("email", "test@inf.de");
				tester.setTextField("password", "testF");
				tester.setTextField("age", "21");
				
				tester.clickButton("SelectHOWebpage");
			//fail representation	
				tester.assertTitleEquals("Movie Rating App - Registeration Form");
				tester.assertTextPresent("Registeration Failed");
				tester.assertLinkPresent("FailRegister");
				tester.assertLinkPresentWithText("Try Again!");
				
//------------------------------------------------------------------------------------------------------------
				
				
				
	// registering the user again to test create movies 
				
//change here while retesting
				tester.clickLinkWithExactText("Register");
				
				tester.setTextField("username", "musters");
				tester.setTextField("email", "musters@info.de");
				tester.setTextField("password", "testtocreate");
				tester.setTextField("age", "22");
				
				tester.clickButton("SelectHOWebpage");
				
				
	// click on the link user webpage	
				tester.assertTextPresent("Registeration Successful");
				tester.clickLinkWithExactText("User Webpage");
				
	//change here while retesting	
				
				tester.gotoPage("http://localhost:8080/MovieRatingApp/MOWebpage?username=musters");
			
	
	// click on add movie to get the create movie form
				
				tester.clickLinkWithExactText("Add Movie");
				
				tester.gotoPage("http://localhost:8080/MovieRatingApp/MOWebpage?action=selectAddMovieForm&username=musters");
				
				
				
   // Check all components of the create movie form
				tester.assertTitleEquals("Movie Rating App - AddNewMovie");
				tester.assertFormPresent();
				tester.assertTextPresent("Required Information");
				tester.assertTextPresent("Movie Title");
				tester.assertFormElementPresent("title");
				tester.assertTextPresent("Director");
				tester.assertFormElementPresent("director");
				tester.assertTextPresent("Main Actor");
				tester.assertFormElementPresent("mainActor");
				tester.assertTextPresent("Publishing Date");
				tester.assertFormElementPresent("publishingDate");
				tester.assertButtonPresentWithText("Submit!");
				tester.assertButtonPresent("SelectHOWebpage");
				
				//submitt the form with given parameter
	//change here			
				tester.setTextField("title", "bros4life");
				tester.setTextField("director", "ashfak");
				tester.setTextField("mainActor", "nazeer");
				tester.setTextField("publishingDate", "06/23/2016");
				
				tester.clickButton("SelectHOWebpage");
				
				//check if user successfully added movie representation showed
				tester.assertTitleEquals("Movie Rating App - Movie Added Success");
				tester.assertTextPresent("New Movie successfully created.");
				
				//fail cases 1) publishing date of future
				tester.clickLinkWithExactText("Add Movie");
				
				tester.setTextField("title", "hoolman");
				tester.setTextField("director", "ashfak");
				tester.setTextField("mainActor", "nazeer");
				tester.setTextField("publishingDate", "06/23/2025");
				
				tester.clickButton("SelectHOWebpage");
				
				//fail representation
				tester.assertTitleEquals("Movie Rating App - Movie Added Failed");
				tester.assertTextPresent("Movie not added, failed.");
				
				
				
				//fail cases 2) movie already exists in Db, i.e title is same as before
				tester.clickLinkWithExactText("Add Movie");
				
				tester.setTextField("title", "bros4life");
				tester.setTextField("director", "ashfak");
				tester.setTextField("mainActor", "nazeer");
				tester.setTextField("publishingDate", "06/23/2019");
				
				tester.clickButton("SelectHOWebpage");
				
				//fail representation
				tester.assertTitleEquals("Movie Rating App - Movie Added Failed");
				tester.assertTextPresent("Movie not added, failed.");
				
				
				
	}
	
}



















