package atweb.nhom6.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import atweb.nhom6.model.SinhVien;

public class SinhVienDao {

	// Ma hoa phia database
	public void insertSinhVien(SinhVien sv) {
		Connection conn = ConnectionToDB.getConnect();
		// MASV, HOTEN, NGAYSINH, DIACHI, MALOP, TENDN, MATKHAU
		String sql = "{call SP_INS_SINHVIEN(?, ?, ?, ?, ?, ?, ?)}";
		try {
			CallableStatement callableStatement = conn.prepareCall(sql);
			callableStatement.setString(1, sv.getMaSV());
			callableStatement.setString(2, sv.getHoTen());
			java.sql.Date sqlNgaySinh = new java.sql.Date(sv.getNgaySinh().getTime());
			callableStatement.setDate(3, sqlNgaySinh);
			callableStatement.setString(4, sv.getDiaChi());
			callableStatement.setString(5, sv.getMaLop());
			callableStatement.setString(6, sv.getTenDN());
			callableStatement.setString(7, sv.getMatKhau());
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<SinhVien> getAllSinhVien() {
		List<SinhVien> sinhViens = new ArrayList<>();
		Connection conn = ConnectionToDB.getConnect();
		String sql = "select * from sinhvien";
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				SinhVien sv = new SinhVien();
				sv.setMaSV(rs.getString(1));
				sv.setHoTen(rs.getString(2));
				sv.setNgaySinh(rs.getDate(3));
				sv.setDiaChi(rs.getString(4));
				sv.setMaLop(rs.getString(5));
				sv.setTenDN(rs.getString(6));
				sinhViens.add(sv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sinhViens;
	}

	public void deleteByMasv(String maSV) {
		Connection conn = ConnectionToDB.getConnect();
		String sql = "delete from sinhvien where masv = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, maSV);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public SinhVien getSinhVienByMasv(String maSV) {
		Connection conn = ConnectionToDB.getConnect();
		String sql = "select * from sinhvien where masv = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, maSV);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				SinhVien sv = new SinhVien();
				sv.setMaSV(rs.getString(1));
				sv.setHoTen(rs.getString(2));
				sv.setNgaySinh(rs.getDate(3));
				sv.setDiaChi(rs.getString(4));
				sv.setMaLop(rs.getString(5));
				sv.setTenDN(rs.getString(6));
				return sv;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateSinhVienByMasv(SinhVien sv) {
		Connection conn = ConnectionToDB.getConnect();
		// MASV, HOTEN, NGAYSINH, DIACHI, MALOP, TENDN, MATKHAU
		String sql = "{call SP_UPDATE_SINHVIEN(?, ?, ?, ?, ?, ?, ?)}";
		try {
			CallableStatement callableStatement = conn.prepareCall(sql);
			callableStatement.setString(1, sv.getMaSV());
			callableStatement.setString(2, sv.getHoTen());
			java.sql.Date sqlNgaySinh = new java.sql.Date(sv.getNgaySinh().getTime());
			callableStatement.setDate(3, sqlNgaySinh);
			callableStatement.setString(4, sv.getDiaChi());
			callableStatement.setString(5, sv.getMaLop());
			callableStatement.setString(6, sv.getTenDN());
			callableStatement.setString(7, sv.getMatKhau());
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SinhVienDao sinhVienDao = new SinhVienDao();
//		SinhVien sv = new SinhVien();
//		sv.setMaSV("N18DCAT001");
//		sv.setHoTen("Nguyễn Văn A");
//		sv.setNgaySinh(new Date());
//		sv.setDiaChi("somewhere");
//		sv.setMaLop("D18CQAT01-N");
//		sv.setTenDN("N18DCAT001");
//		sv.setMatKhau("11111");
// 		// ma hoa phia database
//		sinhVienDao.insertSinhVien(sv);

//		List<SinhVien> svs = sinhVienDao.getAllSinhVien();
//		for(SinhVien sv : svs) {
//			System.out.println(sv.getMaSV());
//		}

//		sinhVienDao.deleteByMasv("N18DCAT001");

//		SinhVien sv = sinhVienDao.getSinhVienByMasv("N18DCAT029");
//		System.out.println(sv.getTenDN());
		
//		SinhVien sv = new SinhVien();
//		sv.setMaSV("N18DCAT029");
//		sv.setHoTen("Nguyễn Quốc Huy");
//		sv.setNgaySinh(new Date());
//		sv.setDiaChi("somewhere");
//		sv.setMaLop("D18CQAT01-N");
//		sv.setTenDN("N18DCAT001");
//		//neu khong cap nhat mat khau thi khong can set mat khau(gia tri bang null)
//		sv.setMatKhau("11111");
//		sinhVienDao.updateSinhVienByMasv(sv);
		
		
	}
}
