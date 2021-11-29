package atweb.nhom6.dao;

import static atweb.nhom6.service.RSAUtil.encrypt;
import static atweb.nhom6.service.RSAUtil.keyToString;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import atweb.nhom6.model.NhanVien;
import atweb.nhom6.service.KeyReader;
import atweb.nhom6.service.RSAKeyPairGenerator;
import atweb.nhom6.service.RSAUtil;

public class NhanVienDao {

	public void spInsertEncryptNhanVien(NhanVien nv) throws Exception {
		Connection conn = ConnectionToDB.getConnect();
		// MaNV, ho ten, email, luong, tendn, mat khau
		String sql = "{call SP_INS_PUBLIC_ENCRYPT_NHANVIEN(?, ?,?, ?, ?, ?, ?)}";

		// generate key for nhanVien
		RSAKeyPairGenerator keyPairGenerator = new RSAKeyPairGenerator();
		keyPairGenerator.writeToFile("RSA\\publicKey_" + nv.getMaNV(), keyPairGenerator.getPublicKey().getEncoded());
		keyPairGenerator.writeToFile("RSA\\privateKey_" + nv.getMaNV(), keyPairGenerator.getPrivateKey().getEncoded());
		// encrypt message by PublicKey
		PublicKey publicKey = KeyReader.getPublicKeyFromFile("RSA\\publicKey_" + nv.getMaNV());
		String encryptedLuong = Base64.getEncoder()
				.encodeToString(encrypt(String.valueOf(nv.getLuong()), keyToString(publicKey)));

		String matKhauEncryptedStr = getSHA1(nv.getMatKhau());
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, nv.getMaNV());
			cs.setString(2, nv.getHoTen());
			cs.setString(3, nv.getEmail());
			cs.setString(4, encryptedLuong);
			cs.setString(5, nv.getTenDN());
			cs.setString(6, matKhauEncryptedStr);
			cs.setString(7, "RSA\\publicKey_" + nv.getMaNV());
			cs.execute();
		} catch (SQLException ex) {
			Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void spUpdateEncryptNhanVien(NhanVien nv) throws Exception {
		Connection conn = ConnectionToDB.getConnect();
		// MaNV, ho ten, email, luong, tendn, mat khau
		String sql = "{call SP_UPDATE_PUBLIC_ENCRYPT_NHANVIEN(?, ?,?, ?, ?, ?, ?)}";

		// generate key for nhanVien
		RSAKeyPairGenerator keyPairGenerator = new RSAKeyPairGenerator();
		keyPairGenerator.writeToFile("RSA/publicKey_" + nv.getMaNV(), keyPairGenerator.getPublicKey().getEncoded());
		keyPairGenerator.writeToFile("RSA/privateKey_" + nv.getMaNV(), keyPairGenerator.getPrivateKey().getEncoded());
		// encrypt message by PublicKey
		PublicKey publicKey = KeyReader.getPublicKeyFromFile("RSA/publicKey_" + nv.getMaNV());
		String encryptedLuong = Base64.getEncoder()
				.encodeToString(encrypt(String.valueOf(nv.getLuong()), keyToString(publicKey)));
		String matKhauEncryptedStr = null;
		if(nv.getMatKhau() != null) {
			matKhauEncryptedStr = getSHA1(nv.getMatKhau());
		}
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, nv.getMaNV());
			cs.setString(2, nv.getHoTen());
			cs.setString(3, nv.getEmail());
			cs.setString(4, encryptedLuong);
			cs.setString(5, nv.getTenDN());
			cs.setString(6, matKhauEncryptedStr);
			cs.setString(7, "RSA/publicKey_" + nv.getMaNV());
			cs.execute();
		} catch (SQLException ex) {
			Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public List<NhanVien> selectALLEncryptNhanVien() {
        Connection conn = ConnectionToDB.getConnect();
        List<NhanVien> nvs = new ArrayList<>();
        String sql = "SELECT MANV, HOTEN, EMAIL, cast(N'' as xml).value('xs:base64Binary(sql:column(\"NHANVIEN.LUONG\"))', 'varchar(max)') as LUONG, TENDN, CONVERT(VARCHAR(MAX), MATKHAU, 2) AS MATKHAU, PUBKEY FROM NHANVIEN";
        Statement statement;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                //decrypt luong by public key
                String pubKeyStr = rs.getString("PUBKEY");
                if (!pubKeyStr.substring(0, 4).equals("RSA/")) {
                    continue;
                }

                //decrypt by private key
                String luongBase64 = rs.getString("LUONG");
                PrivateKey privateKey = KeyReader.getPrivateKeyFromFile("RSA/privateKey_"+rs.getString("MANV"));
                String descryptedLuong = RSAUtil.decrypt(luongBase64, keyToString(privateKey));
                
                nv.setMaNV(rs.getString("MANV"));
                nv.setHoTen(rs.getString("HOTEN"));
                nv.setEmail(rs.getString("EMAIL"));
                nv.setTenDN(rs.getString("TENDN"));
                nv.setLuong(Float.parseFloat(descryptedLuong));
                nvs.add(nv);
            }
            return nvs;
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
	
	public NhanVien getNhanVienByMaNV(String maNV) {
        Connection conn = ConnectionToDB.getConnect();
        String sql = "SELECT MANV, HOTEN, EMAIL, cast(N'' as xml).value('xs:base64Binary(sql:column(\"NHANVIEN.LUONG\"))', 'varchar(max)') as LUONG, TENDN, CONVERT(VARCHAR(MAX), MATKHAU, 2) AS MATKHAU, PUBKEY FROM NHANVIEN WHERE MANV = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, maNV);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String pubKeyStr = rs.getString("PUBKEY");
                if (!pubKeyStr.substring(0, 4).equals("RSA/")) {
                    return null;
                }

                //decrypt by private key
                String luongBase64 = rs.getString("LUONG");
                PrivateKey privateKey = KeyReader.getPrivateKeyFromFile("RSA/privateKey_"+rs.getString("MANV"));
                String descryptedLuong = RSAUtil.decrypt(luongBase64, keyToString(privateKey));
                
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MANV"));
                nv.setHoTen(rs.getString("HOTEN"));
                nv.setEmail(rs.getString("EMAIL"));
                nv.setTenDN(rs.getString("TENDN"));
                nv.setLuong(Float.parseFloat(descryptedLuong));
                return nv;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
	
	public NhanVien spSelectEncryptNhanVien(String username, String password) {
        Connection conn = ConnectionToDB.getConnect();
        String sql = "{call SP_SEL_PUBLIC_ENCRYPT_NHANVIEN(?, ?)}";

        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, username);
            String matKhauEncryptedStr = getSHA1(password);
            cs.setString(2, matKhauEncryptedStr);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MANV"));
                nv.setHoTen(rs.getString("HOTEN"));
                nv.setEmail(rs.getString("EMAIL"));

                //decrypt by private key
                String luongBase64 = rs.getString("LUONG");
                PrivateKey privateKey = KeyReader.getPrivateKeyFromFile("RSA/privateKey_"+nv.getMaNV());
                String descryptedLuong = RSAUtil.decrypt(luongBase64, keyToString(privateKey));
                
                nv.setLuong(Float.parseFloat(descryptedLuong));
                return nv;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
	
    public void deleteNhanVienByMaNV(String maNV) {
        Connection conn = ConnectionToDB.getConnect();
        String sql = "DELETE FROM NHANVIEN WHERE MANV = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, maNV);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	// =====
	// sha1
	// ====
	public String convertByteToHex(byte[] data) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	public String getSHA1(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] messageDigest = md.digest(input.getBytes());
			return convertByteToHex(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	// ====
	// end sha1
	// ====

	public static void main(String[] args) {
		NhanVienDao nvdao = new NhanVienDao();
//		NhanVien nv = new NhanVien();
//		nv.setHoTen("NV1");
//		nv.setTenDN("NV1");
//		nv.setEmail("nv1@mail.com");
//		nv.setMaNV("NV1");
//		nv.setLuong(1300000);
//		nv.setMatKhau("11111");
//		try {
//			nvdao.spInsertEncryptNhanVien(nv);
//		} catch (Exception ex) {
//			Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
//		}
		
		List<NhanVien> nvs = nvdao.selectALLEncryptNhanVien();
		for(NhanVien nv : nvs) {
			System.out.println(nv.getMaNV());
			System.out.println(nv.getHoTen());
			System.out.println(nv.getEmail());
			System.out.println(nv.getLuong());
			System.out.println(nv.getTenDN());
			System.out.println(nv.getLuong());
		}
		
//		NhanVien nv = nvdao.getNhanVienByMaNV("NV1");
//		System.out.println(nv.getMaNV());
//		System.out.println(nv.getHoTen());
//		System.out.println(nv.getEmail());
//		System.out.println(nv.getLuong());
//		System.out.println(nv.getTenDN());
//		System.out.println(nv.getLuong());
		
//		NhanVien nv = nvdao.spSelectEncryptNhanVien("NV1", "11111");
//		System.out.println(nv.getMaNV());
//		System.out.println(nv.getHoTen());
//		System.out.println(nv.getEmail());
//		System.out.println(nv.getLuong());
		
//		NhanVien nv = new NhanVien();
//		nv.setHoTen("Nhân viên 1");
//		nv.setTenDN("NV1");
//		nv.setEmail("nhanvien1@mail.com");
//		nv.setMaNV("NV1");
//		nv.setLuong(1200000);
////		//neu khong cap nhat mat khau thi khong can set mat khau(gia tri bang null)
////		nv.setMatKhau("11111");
//		try {
//			nvdao.spUpdateEncryptNhanVien(nv);
//		} catch (Exception ex) {
//			Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
//		}
	}
}
