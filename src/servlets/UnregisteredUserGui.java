package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.MRApplication;
import dbadapter.DBFacade;
import dbadapter.UserAccount;

public class UnregisteredUserGui extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String action = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("navtype", "general");
		action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
		if (action.equals("selectRegisterForm")) {
			// Set request attributes
			request.setAttribute("pagetitle", "Register");
			// Dispatch request to template engine
			try {
				request.getRequestDispatcher("/templates/defaultWebpageUser.ftl").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
		request.setAttribute("navtype", "general");
		request.setAttribute("pagetitle", "Registeration Form");
		if (action.equals("registerUser")) {
			try {
				boolean registeredStatus = MRApplication.getInstance().create(request.getParameter("username"),
						 request.getParameter("email"), 
						 request.getParameter("password"), 
						 request.getParameter("age"));
				if(registeredStatus) {
					request.setAttribute("userMessage", "Registeration Successful");
					request.setAttribute("username", request.getParameter("username"));
					request.getRequestDispatcher("/templates/OkRegister.ftl").forward(request, response);
				}else {
					request.setAttribute("userMessage", "Registeration Failed");
					request.getRequestDispatcher("/templates/failRegister.ftl").forward(request, response);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		

	}

}
