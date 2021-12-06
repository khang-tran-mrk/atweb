package atweb.nhom6.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import atweb.nhom6.dao.SignInDAO;
import atweb.nhom6.model.ACCOUNT;

/**
 * Servlet implementation class ForgotPasswordController
 */
@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/forgot-password.jsp");

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		SignInDAO user = new SignInDAO();
		String code = user.getRandom();
		if(action.equals("guimail")) {
			String email = request.getParameter("users_email");
			boolean success = user.sendEmail(email, code);
			boolean check = new SignInDAO().checkEmail(email);
			if( check==true) {
				if (success) 
				{
					session.setAttribute("guimail", "true");
					
		            session.setAttribute("authcode", code);  
		            response.sendRedirect("./forgot-password");
		            
				}
				
			}
			else 
			{
				session.setAttribute("guimailFail", "true");
				
				response.sendRedirect("./forgot-password");
			}
			
					
		}
		else if (action.equals("xacthuc")) {
			
				String authcode = (String) session.getAttribute("authcode");	
				String users_email =  request.getParameter("email");			
				String users_password = request.getParameter("password");
				String repassword = request.getParameter("repassword");
				System.out.println("code" + authcode);
			
				if(users_password.equals(repassword)) 
				{
					ACCOUNT u = new ACCOUNT();
					u.setEmail(users_email);
					u.setPassword(users_password);
					
					SignInDAO.updatePassword(u);
					session.setAttribute("xacthuc", "true");
					session.removeAttribute("authcode");
					response.sendRedirect("./login");
				}
				else {
					session.setAttribute("xacthucFail", "true");
					response.sendRedirect("./do-forgot-password/"+authcode+"/"+users_email );
				}
			
					
		} 
	}

}
