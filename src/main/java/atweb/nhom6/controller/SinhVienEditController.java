package atweb.nhom6.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import atweb.nhom6.dao.LopDao;
import atweb.nhom6.dao.SinhVienDao;
import atweb.nhom6.model.Lop;
import atweb.nhom6.model.SinhVien;

/**
 * Servlet implementation class SinhVienEditController
 */
@WebServlet("/edit-sinh-vien")
public class SinhVienEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SinhVienDao sinhVienDao;
	private LopDao lopDao;

	public SinhVienEditController() {
		sinhVienDao = new SinhVienDao();
		lopDao = new LopDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msv = request.getParameter("msv");
		SinhVien sv = sinhVienDao.getSinhVienByMasv(msv);
		List<Lop> lops = lopDao.getAllLop();
		request.setAttribute("sv", sv);
		request.setAttribute("lops", lops);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/sinhvien-edit.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String msv = request.getParameter("msv");
		String hoTen = request.getParameter("hoten");
		System.out.println(hoTen);
		String ngaySinhStr = request.getParameter("ngaysinh");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date ngaySinh = new Date();
		try {
			ngaySinh = simpleDateFormat.parse(ngaySinhStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String diaChi = request.getParameter("diachi");
		String maLop = request.getParameter("malop");
		String tenDN = request.getParameter("tendn");
		String matKhau = request.getParameter("matkhau");
		SinhVien sv = new SinhVien(msv, hoTen, ngaySinh, diaChi, maLop, tenDN, matKhau);
		sinhVienDao.updateSinhVienByMasv(sv);
		response.sendRedirect("sinh-vien");
	}

}
