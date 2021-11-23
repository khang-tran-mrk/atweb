package atweb.nhom6.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import atweb.nhom6.dao.SignUpDAO;
import atweb.nhom6.model.ACCOUNT;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SignUpDAO signUpDao = new SignUpDAO();

	public SignUpServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signup/signup_form.jsp");

		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signup/success.jsp");
		
		//nhận thông tin từ client
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String sdt = request.getParameter("sdt");

		ACCOUNT account = new ACCOUNT(username, password, email, sdt);
		
		System.out.println(account);
		
		//thêm tài khoản vào db
		int result = signUpDao.add(account);
		System.out.println("result: " + result);
		

		//trả về trạng thái tương ứng
		if (result > 0) {
			System.out.println("Thanh cong");
		} else {
			dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signup/fail.jsp");
		}
		
		dispatcher.forward(request, response);

	}

}
