package atweb.nhom6.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import atweb.nhom6.dao.SignInDAO;
import atweb.nhom6.model.ACCOUNT;

/**
 * Servlet implementation class signinServlet
 */
@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SignInDAO signinDao = new SignInDAO();

	public SignInServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signin/signin_form.jsp");

		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signin/success.jsp");
		//lấy thông tin từ client
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//set username, pass 
		ACCOUNT account = new ACCOUNT();
		account.setUsername(username);
		account.setPassword(password);
		
		System.out.println(account);
		
		//kiểm tra 'account' có tồn tại không 
		int result = signinDao.check(account);
		
		System.out.println("result: " + result);
		

		//Trả về trạng thái tương ứng
		if (result > 0) {
			System.out.println("Thanh cong");
		} else {
			dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signin/fail.jsp");
		}
		
		dispatcher.forward(request, response);

	}

}
