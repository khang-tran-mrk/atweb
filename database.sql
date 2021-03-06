USE [master]
GO
/****** Object:  Database [FINALLTM]    Script Date: 11/28/2021 5:14:02 AM ******/
CREATE DATABASE [FINALLTM]
GO
USE [FINALLTM]
GO
/****** Object:  Table [dbo].[account]    Script Date: 11/28/2021 5:14:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[username] [varchar](255) NOT NULL,
	[password] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[sdt] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BANGDIEM]    Script Date: 11/28/2021 5:14:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BANGDIEM](
	[MASV] [varchar](20) NOT NULL,
	[MAHP] [varchar](20) NOT NULL,
	[DIEMTHI] [varbinary](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[MASV] ASC,
	[MAHP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HOCPHAN]    Script Date: 11/28/2021 5:14:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HOCPHAN](
	[MAHP] [varchar](20) NOT NULL,
	[TENHP] [nvarchar](100) NOT NULL,
	[SOTC] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MAHP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LOP]    Script Date: 11/28/2021 5:14:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOP](
	[MALOP] [varchar](20) NOT NULL,
	[TENLOP] [nvarchar](100) NOT NULL,
	[MANV] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[MALOP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 11/28/2021 5:14:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MANV] [varchar](20) NOT NULL,
	[HOTEN] [nvarchar](100) NOT NULL,
	[EMAIL] [varchar](20) NULL,
	[LUONG] [varbinary](max) NULL,
	[TENDN] [nvarchar](100) NOT NULL,
	[MATKHAU] [varbinary](max) NOT NULL,
	[PUBKEY] [varchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[MANV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SINHVIEN]    Script Date: 11/28/2021 5:14:02 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SINHVIEN](
	[MASV] [varchar](20) NOT NULL,
	[HOTEN] [nvarchar](100) NOT NULL,
	[NGAYSINH] [datetime] NULL,
	[DIACHI] [nvarchar](200) NULL,
	[MALOP] [varchar](20) NULL,
	[TENDN] [nvarchar](100) NOT NULL,
	[MATKHAU] [varbinary](max) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MASV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[account] ([username], [password], [email], [sdt]) VALUES (N'admin', N'admin', N'huynguyen5212@gmail.com', N'01234123123')
GO
INSERT [dbo].[LOP] ([MALOP], [TENLOP], [MANV]) VALUES (N'D18CQAT01-N', N'An toàn thông tin 1', NULL)
GO
INSERT [dbo].[NHANVIEN] ([MANV], [HOTEN], [EMAIL], [LUONG], [TENDN], [MATKHAU], [PUBKEY]) VALUES (N'NV1', N'Nhân viên 1', N'nhanvien1@mail.com', 0x7FA2228811D9EC8127669D4C715447B2CDE4C6F11EEDA0A60950E56648DD8B4B04FAC1C55A228E7BBAC1BB9F7B4388C7417DAE199B1FC590F068F1D88EA08FC2EF8FE8DFE9DE25B88FCDB16CD1095CF07F7594F9BD94900DCAC878CC93339F56D3D90CBE3D2EA0C445009DAA5EF26A0BED14582CFFF56B880F31ACE54699E3B1, N'NV1', 0x7B21848AC9AF35BE0DDB2D6B9FC3851934DB8420, N'RSA/publicKey_NV1')
GO
INSERT [dbo].[SINHVIEN] ([MASV], [HOTEN], [NGAYSINH], [DIACHI], [MALOP], [TENDN], [MATKHAU]) VALUES (N'N18DCAT029', N'Nguyễn Quốc Huy', CAST(N'2021-11-28T00:00:00.000' AS DateTime), N'somewhere', N'D18CQAT01-N', N'N18DCAT029', 0xD17F25ECFBCC7857F7BEBEA469308BE0B2580943E96D13A3AD98A13675C4BFC2)
GO
ALTER TABLE [dbo].[BANGDIEM]  WITH CHECK ADD FOREIGN KEY([MAHP])
REFERENCES [dbo].[HOCPHAN] ([MAHP])
GO
ALTER TABLE [dbo].[BANGDIEM]  WITH CHECK ADD FOREIGN KEY([MASV])
REFERENCES [dbo].[SINHVIEN] ([MASV])
GO
ALTER TABLE [dbo].[LOP]  WITH CHECK ADD FOREIGN KEY([MANV])
REFERENCES [dbo].[NHANVIEN] ([MANV])
GO
ALTER TABLE [dbo].[SINHVIEN]  WITH CHECK ADD FOREIGN KEY([MALOP])
REFERENCES [dbo].[LOP] ([MALOP])
GO
/****** Object:  StoredProcedure [dbo].[SP_INS_PUBLIC_ENCRYPT_BANGDIEM]    Script Date: 11/28/2021 5:14:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_INS_PUBLIC_ENCRYPT_BANGDIEM]
@masv varchar(255), @mahp varchar(255), @diemthi varchar(max)
AS
BEGIN
DECLARE @diemthibinary VARBINARY(MAX);
SET @diemthibinary = cast(N'' as xml).value('xs:base64Binary(sql:variable("@diemthi"))', 'varbinary(max)')

INSERT INTO BANGDIEM VALUES(@masv, @mahp, @diemthibinary)
END
GO
/****** Object:  StoredProcedure [dbo].[SP_INS_PUBLIC_ENCRYPT_NHANVIEN]    Script Date: 11/28/2021 5:14:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_INS_PUBLIC_ENCRYPT_NHANVIEN]
@manv varchar(20), @hoten nvarchar(100), @email varchar(20), @luong varchar(max), @tendn nvarchar(100), @matkhau varchar(255),
@pubkey varchar(max)
AS
BEGIN
DECLARE @mkbinary VARBINARY(MAX);
SET @mkbinary = CONVERT(varbinary(max), @matkhau, 2);
DECLARE @luongbinary VARBINARY(MAX);
SET @luongbinary = cast(N'' as xml).value('xs:base64Binary(sql:variable("@luong"))', 'varbinary(max)')

INSERT INTO NHANVIEN VALUES(@manv, @hoten, @email, @luongbinary, @tendn, @mkbinary, @pubkey)
END
GO
/****** Object:  StoredProcedure [dbo].[SP_INS_PUBLIC_NHANVIEN]    Script Date: 11/28/2021 5:14:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_INS_PUBLIC_NHANVIEN]  
@manv VARCHAR(20), @hoten NVARCHAR(100), @email VARCHAR(20),
@luongcb FLOAT, @tendn NVARCHAR(100), @mk VARCHAR(255)
AS
BEGIN
	DECLARE @matkhaubinary VARBINARY(MAX);
	DECLARE @asymmetrickeysql VARCHAR(MAX); --SCRIPT CREATE ASYMMETRIC KEY WITH PULIC KEY = MANV AND PRIVATE KEY = MK
	DECLARE @luongvarchar VARCHAR(255);
	DECLARE @luongbinary VARBINARY(MAX);
	
	SET @asymmetrickeysql = 'CREATE ASYMMETRIC KEY ' + @manv + 
	' WITH ALGORITHM = RSA_512' + 
	' ENCRYPTION BY PASSWORD = ''' + @mk + '''';
	EXEC (@asymmetrickeysql);

	SET @luongvarchar = CONVERT (VARCHAR(50), @luongcb,128);
	SET @matkhaubinary = CONVERT(VARBINARY(MAX), HASHBYTES('SHA1', @mk), 2);
	set @luongbinary = ENCRYPTBYASYMKEY(ASYMKEY_ID(@manv),  @luongvarchar)
	
	INSERT INTO NHANVIEN VALUES(@manv, @hoten, @email, @luongbinary, @tendn, @matkhaubinary, @manv)
END
GO
/****** Object:  StoredProcedure [dbo].[SP_INS_SINHVIEN]    Script Date: 11/28/2021 5:14:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_INS_SINHVIEN]
@masv nvarchar(20), @hoten nvarchar(100), @ngaysinh datetime, @diachi nvarchar(200),
@malop varchar(20), @tendn nvarchar(100), @matkhau varchar(255)
AS
BEGIN
	DECLARE @binaryhash VARBINARY(MAX);
	SET @binaryhash = CONVERT(VARBINARY(MAX), HASHBYTES('SHA2_256', @matkhau), 2);
	
	INSERT INTO SINHVIEN 
	VALUES(@masv, @hoten, @ngaysinh, @diachi, @malop, @tendn, @binaryhash);
END
GO
/****** Object:  StoredProcedure [dbo].[SP_SEL_PUBLIC_BANGDIEM]    Script Date: 11/28/2021 5:14:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SEL_PUBLIC_BANGDIEM]
@masv varchar(255), @mahp varchar(255), @diemthi float, @manv VARCHAR(20)
AS
BEGIN
	DECLARE @diemthivarchar VARCHAR(255);
	SET @diemthivarchar = CONVERT (VARCHAR(50), @diemthi,128);
	DECLARE @diemthibinary VARBINARY(MAX);
	set @diemthibinary = ENCRYPTBYASYMKEY(ASYMKEY_ID(@manv),  @diemthivarchar)

	INSERT INTO BANGDIEM VALUES(@masv, @mahp, @diemthibinary)
END
GO
/****** Object:  StoredProcedure [dbo].[SP_SEL_PUBLIC_ENCRYPT_NHANVIEN]    Script Date: 11/28/2021 5:14:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SEL_PUBLIC_ENCRYPT_NHANVIEN]
@tendn varchar(255), @mk varchar(max)
AS
BEGIN
	DECLARE @mkbinary VARBINARY(MAX);
	SET @mkbinary = (SELECT TOP 1 MATKHAU FROM NHANVIEN WHERE TENDN = @tendn)
	DECLARE @mkhex VARCHAR(MAX);
	SET @mkhex = CONVERT(VARCHAR(1000), @mkbinary, 2);
	SELECT MANV, HOTEN, EMAIL, cast(N'' as xml).value(
        'xs:base64Binary(sql:column("NHANVIEN.LUONG"))', 'varchar(max)'
    ) as LUONG, PUBKEY FROM NHANVIEN WHERE TENDN = @tendn AND @mkhex = @mk
END
GO
/****** Object:  StoredProcedure [dbo].[SP_SEL_PUBLIC_NHANVIEN]    Script Date: 11/28/2021 5:14:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SEL_PUBLIC_NHANVIEN]
	@tendn NVARCHAR(100), @mk NVARCHAR(255)
AS
BEGIN
	DECLARE @selectsql VARCHAR(255);
	DECLARE @matkhaubinary VARBINARY(MAX);
	SET @matkhaubinary = CONVERT(VARBINARY(MAX), HASHBYTES('SHA1', @mk), 2);
	
	SELECT MANV, HOTEN, EMAIL ,
	CONVERT(FLOAT, CONVERT(varchar(255), DECRYPTBYASYMKEY(ASYMKEY_ID(@tendn), LUONG, @mk))) AS LUONGCB 
	FROM NHANVIEN where MANV = @tendn 
END 
GO
/****** Object:  StoredProcedure [dbo].[SP_UPDATE_PUBLIC_ENCRYPT_NHANVIEN]    Script Date: 11/28/2021 5:14:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_UPDATE_PUBLIC_ENCRYPT_NHANVIEN]
@manv varchar(20), @hoten nvarchar(100), @email varchar(20), @luong varchar(max), @tendn nvarchar(100), @matkhau varchar(255),
@pubkey varchar(max)
AS
BEGIN

DECLARE @luongbinary VARBINARY(MAX);
SET @luongbinary = cast(N'' as xml).value('xs:base64Binary(sql:variable("@luong"))', 'varbinary(max)')

IF (@matkhau <> '')
	BEGIN
		DECLARE @mkbinary VARBINARY(MAX);
		SET @mkbinary = CONVERT(varbinary(max), @matkhau, 2);
		UPDATE NHANVIEN
		SET HOTEN = @hoten, EMAIL = @email, LUONG = @luongbinary, TENDN = @tendn, MATKHAU = @mkbinary, PUBKEY = @pubkey
	END
ELSE
	UPDATE NHANVIEN
	SET HOTEN = @hoten, EMAIL = @email, LUONG = @luongbinary, TENDN = @tendn, PUBKEY = @pubkey
END
GO
/****** Object:  StoredProcedure [dbo].[SP_UPDATE_SINHVIEN]    Script Date: 11/28/2021 5:14:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_UPDATE_SINHVIEN]
@masv nvarchar(20), @hoten nvarchar(100), @ngaysinh datetime, @diachi nvarchar(200),
@malop varchar(20), @tendn nvarchar(100), @matkhau varchar(255)
AS
BEGIN
	IF (@matkhau <> '')
		BEGIN
			DECLARE @binaryhash VARBINARY(MAX);
			SET @binaryhash = CONVERT(VARBINARY(MAX), HASHBYTES('SHA2_256', @matkhau), 2);
			UPDATE SINHVIEN 
			SET  HOTEN = @hoten, NGAYSINH = @ngaysinh, DIACHI = @diachi, MALOP = @malop, @tendn = TENDN, MATKHAU = @binaryhash
			WHERE MASV = @masv;
		END
	ELSE
		UPDATE SINHVIEN 
		SET  HOTEN = @hoten, NGAYSINH = @ngaysinh, DIACHI = @diachi, MALOP = @malop, @tendn = TENDN
		WHERE MASV = @masv;
END
GO
USE [master]
GO
ALTER DATABASE [FINALLTM] SET  READ_WRITE 
GO
