package servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import application.VRApplication;
import application.MRApplication;
//import datatypes.GuestData;
//import dbadapter.HolidayOffer;
import dbadapter.Movies;


public class UserGui extends HttpServlet {
 
	private static final long serialVersionUID = 1L;
     
	/**
	 * doGet is responsible for displaying browse of movies and user registration form and add-movie form and rate-movie form
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		// Set navtype
		request.setAttribute("navtype", "general");

		// Catch error if there is no page contained in the request
		String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
		
		// Case: Request Registering form
				if (action.equals("selectRegisterForm")) {
					// Set request attributes
					request.setAttribute("pagetitle", "Register");
					// Dispatch request to template engine
					try {
						request.getRequestDispatcher("/templates/defaultWebpageUser.ftl").forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(action.equals("selectAddMovieForm")){

					// Set request attributes
					request.setAttribute("pagetitle", "AddNewMovie");
					request.setAttribute("username", request.getParameter("username"));


					// Dispatch request to template engine
					try {
						request.getRequestDispatcher("/templates/showNewMovieFrom.ftl").forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(action.equals("selectRateMoviesForm")){

					// Set request attributes
					request.setAttribute("pagetitle", "RateMovies");
					request.setAttribute("username", request.getParameter("username"));

					// Dispatch request to template engine
					try {
						request.getRequestDispatcher("/templates/showRateMovieFrom.ftl").forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					// Generate and show results of a search
				}else if (request.getParameter("action").equals("showAllMoviesInDB")) {
					request.setAttribute("pagetitle", "Movies In Database");
					List<Movies> allMovies = null;

					// Call application to request the results
					try {
						allMovies = MRApplication.getInstance().getMoviesInDB();

						// Dispatch results to template engine
						request.setAttribute("moviesList", allMovies);
						request.getRequestDispatcher("/templates/moviesRepresentation.ftl").forward(request, response);
						
					}catch (Exception e1) {
						try {
							request.setAttribute("errormessage", "Database error: please contact the administator");
							request.getRequestDispatcher("/templates/error.ftl").forward(request, response);
						} catch (Exception e) {
							request.setAttribute("errormessage", "System error: please contact the administrator");
							e.printStackTrace();
						}
						e1.printStackTrace();
					}
					
					//adding new movie to database
					
				} 
	}
	
	
	/**
	 * doPost manages handling of submitted forms.
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		// Set attribute for navigation type.
				request.setAttribute("navtype", "user");
				
				
                if (request.getParameter("action").equals("addNewMovie")) {
					
					// Decide whether creating data was successful or not
					if (MRApplication.getInstance().addMovieToDB(request.getParameter("username"),
							request.getParameter("title"), request.getParameter("director"),
							request.getParameter("mainActor"), request.getParameter("publishingDate")) != null) {
						
						// Set request attributes
						request.setAttribute("pagetitle", "Added Successful");
						request.setAttribute("message",
								"New Movie successfully created.");
						
						// Dispatch to template engine
						try {
							request.getRequestDispatcher("/templates/MRA_Templates/successCreateMovie.ftl").forward(request, response);
						} catch (ServletException | IOException e) {
							e.printStackTrace();
						}
					}else {
						request.setAttribute("pagetitle", "Adding failed");
						request.setAttribute("message",
								"Movie not added, failed.");

						try {
							request.getRequestDispatcher("/templates/failCreateMovie.ftl").forward(request,
									response);
						} catch (ServletException | IOException e) {
							e.printStackTrace();
						}

					}
					//creating user 
				}else if (request.getParameter("action").equals("registerUser")) {
					
					
					// Decide whether creating data was successful or not
					if (MRApplication.getInstance().create(request.getParameter("username"),
							request.getParameter("email"), request.getParameter("password"),
							request.getParameter("age")) != null) {
						
						// Set request attributes
						request.setAttribute("pagetitle", "Registered Successfully");
						request.setAttribute("message",
								"Account created Successfully!!");
						
						// Dispatch to template engine
						try {
							request.getRequestDispatcher("/templates/MRA_Templates/OkRegister.ftl").forward(request, response);
						} catch (ServletException | IOException e) {
							e.printStackTrace();
						}
					}else {
						request.setAttribute("pagetitle", "Adding failed");
						request.setAttribute("message",
								"Registeration failed.");

						try {
							request.getRequestDispatcher("/templates/MRA_Templates/failRegister.ftl").forward(request,
									response);
						} catch (ServletException | IOException e) {
							e.printStackTrace();
						}

					}
					//Adding Rating to DB 
				}else if (request.getParameter("action").equals("rateMovie")) {
					
					
					// Decide whether creating data was successful or not
					if (MRApplication.getInstance().createRating(request.getParameter("username"),
							request.getParameter("rating"), request.getParameter("title"),
							request.getParameter("comment")) != null) {
						
						// Set request attributes
						request.setAttribute("pagetitle", "Rated Successfully");
						request.setAttribute("message",
								"Movie Rated Successfully!!");
						
						// Dispatch to template engine
						try {
							request.getRequestDispatcher("/templates/MRA_Templates/successRateMovie.ftl").forward(request, response);
						} catch (ServletException | IOException e) {
							e.printStackTrace();
						}
					}else {
						request.setAttribute("pagetitle", "Failed Rating");
						request.setAttribute("message",
								"Failed to Rate");

						try {
							request.getRequestDispatcher("/templates/MRA_Templates/failRateMovie.ftl").forward(request,
									response);
						} catch (ServletException | IOException e) {
							e.printStackTrace();
						}

					}
					
				}
				
				
	}      
}
