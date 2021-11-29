package atweb.nhom6.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import atweb.nhom6.dao.NhanVienDao;
import atweb.nhom6.model.NhanVien;

/**
 * Servlet implementation class NhanVienController
 */
@WebServlet("/nhan-vien")
public class NhanVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NhanVienDao nvdao;
   
    public NhanVienController() {
    	nvdao = new NhanVienDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/nhanvien.jsp");
//		List<NhanVien> nvs = nvdao.selectALLEncryptNhanVien();
//		for(NhanVien nv: nvs) {
//			System.out.println(nv.getHoTen());
//		}
//		request.setAttribute("nvs", nvs);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String maNV = request.getParameter("mnv");
		String hoTen = request.getParameter("hoten");
		String email = request.getParameter("email");
		String luongStr = request.getParameter("luong");
		int luong = Integer.parseInt(luongStr);
		String tenDN = request.getParameter("tendn");
		String matKhau = request.getParameter("matKhau");
		NhanVien nv = new NhanVien(maNV, hoTen, email, luong, tenDN, matKhau);
		String project_name = request.getContextPath().split("/")[1];
		String path = request.getServletContext().getRealPath("").split(".metadata")[0] + project_name + File.separator;
		try {
			nvdao.spInsertEncryptNhanVien(nv, path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("nhan-vien");
	}

}
