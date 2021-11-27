package atweb.nhom6.model;

public class NhanVien {
	private String maNV;
	private String hoTen;
	private String email;
	private float luong;
	private String tenDN;
	private String matKhau;
	private String pubkey;

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getLuong() {
		return luong;
	}

	public void setLuong(float f) {
		this.luong = f;
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
	
	public String getPubkey() {
		return pubkey;
	}
	
	public void setPubkey(String pubkey) {
		this.pubkey = pubkey;
	}

	public NhanVien() {
	}

	public NhanVien(String maNV, String hoTen, String email, int luong, String tenDN, String matKhau) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.email = email;
		this.luong = luong;
		this.tenDN = tenDN;
		this.matKhau = matKhau;
	}

}
