package atweb.nhom6.model;

public class ACCOUNT {
	private String username;
	private String password;
	private String email;
	private String sdt;

	public ACCOUNT() {
		super();
	}

	public ACCOUNT(String username, String password, String email, String sdt) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.sdt = sdt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	@Override
	public String toString() {
		return "ACCOUNT [username=" + username + ", password=" + password + ", email=" + email + ", sdt=" + sdt + "]";
	}
	
	
}
