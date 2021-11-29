package atweb.nhom6.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import atweb.nhom6.dao.LopDao;
import atweb.nhom6.model.Lop;

/**
 * Servlet implementation class LopController
 */
@WebServlet("/lop")
public class LopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LopDao lopDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LopController() {
        lopDao = new LopDao();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/lop.jsp");
		List<Lop> lops = lopDao.getAllLop();
		request.setAttribute("lops", lops);
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
