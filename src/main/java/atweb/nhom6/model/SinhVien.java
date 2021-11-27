package atweb.nhom6.model;

import java.util.Date;

public class SinhVien {
	private String maSV;
	private String hoTen;
	private Date ngaySinh;
	private String diaChi;
	private String maLop;
	private String tenDN;
	private String matKhau;
	public SinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaSV() {
		return maSV;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getMaLop() {
		return maLop;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	public String getTenDN() {
		return tenDN;
	}
	public void setTenDN(String tenDN) {
		this.tenDN = tenDN;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public SinhVien(String maSV, String hoTen, Date ngaySinh, String diaChi, String maLop, String tenDN,
			String matKhau) {
		super();
		this.maSV = maSV;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.maLop = maLop;
		this.tenDN = tenDN;
		this.matKhau = matKhau;
	}
	
	
}
