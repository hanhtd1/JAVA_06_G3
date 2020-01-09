USE [master]
GO
/****** Object:  Database [mock_project]    Script Date: 1/8/2020 11:06:17 ******/
CREATE DATABASE [mock_project]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'mock_project', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\mock_project.mdf' , SIZE = 3136KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'mock_project_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\mock_project_log.ldf' , SIZE = 784KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [mock_project] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [mock_project].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [mock_project] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [mock_project] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [mock_project] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [mock_project] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [mock_project] SET ARITHABORT OFF 
GO
ALTER DATABASE [mock_project] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [mock_project] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [mock_project] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [mock_project] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [mock_project] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [mock_project] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [mock_project] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [mock_project] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [mock_project] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [mock_project] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [mock_project] SET  ENABLE_BROKER 
GO
ALTER DATABASE [mock_project] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [mock_project] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [mock_project] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [mock_project] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [mock_project] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [mock_project] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [mock_project] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [mock_project] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [mock_project] SET  MULTI_USER 
GO
ALTER DATABASE [mock_project] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [mock_project] SET DB_CHAINING OFF 
GO
ALTER DATABASE [mock_project] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [mock_project] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [mock_project]
GO
/****** Object:  Table [dbo].[attendance]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[attendance](
	[date] [date] NOT NULL,
	[note] [varchar](255) NULL,
	[type] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[date] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[attendance_user]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[attendance_user](
	[attendance_date] [date] NOT NULL,
	[user_id] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[clazz]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[clazz](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[category] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[note] [varchar](255) NULL,
	[open_date] [date] NULL,
	[status] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[clazz_subject]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[clazz_subject](
	[clazz_id] [int] NOT NULL,
	[subject_id] [int] NOT NULL,
	[status] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[clazz_id] ASC,
	[subject_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[feedback]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[feedback](
	[subject_id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
	[content] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[subject_id] ASC,
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[review_trainee]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[review_trainee](
	[trainee_id] [int] NOT NULL,
	[trainer_id] [int] NOT NULL,
	[content] [varchar](255) NULL,
	[type] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[trainee_id] ASC,
	[trainer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[score]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[score](
	[id] [int] NOT NULL,
	[subject_id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
	[name] [varchar](255) NULL,
	[value] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC,
	[subject_id] ASC,
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[subject]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[subject](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](255) NULL,
	[duration] [float] NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[user_clazz]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_clazz](
	[clazz_id] [int] NOT NULL,
	[user_id] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[uzer]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[uzer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[account] [varchar](255) NULL,
	[birth_day] [date] NULL,
	[email] [varchar](255) NULL,
	[first_name] [varchar](255) NULL,
	[gender] [varchar](255) NULL,
	[last_name] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[phone] [varchar](255) NULL,
	[role] [varchar](255) NULL,
	[status] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  View [dbo].[ClazzUserView]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[ClazzUserView]
AS
	SELECT * FROM clazz c JOIN user_clazz uc ON c.id = uc.clazz_id
GO
/****** Object:  UserDefinedFunction [dbo].[udf_findClazzByStatus]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[udf_findClazzByStatus](@user_id INT, @status varchar(255))
RETURNS TABLE
RETURN
	SELECT * FROM clazz c WHERE c.id IN (SELECT cuv.clazz_id FROM ClazzUserView cuv WHERE cuv.user_id = @user_id) AND c.status = @status
GO
/****** Object:  UserDefinedFunction [dbo].[udf_findClazzByUserId]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[udf_findClazzByUserId](@user_id INT)
RETURNS TABLE
RETURN
	SELECT * FROM clazz c WHERE c.id IN (SELECT cuv.clazz_id FROM ClazzUserView cuv WHERE cuv.user_id = @user_id)
GO
/****** Object:  UserDefinedFunction [dbo].[udf_findTraineeByCategory]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[udf_findTraineeByCategory](@category varchar(255))
RETURNS TABLE
RETURN
	SELECT * FROM uzer u WHERE u.id IN (SELECT user_id FROM ClazzUserView cuv WHERE cuv.category = @category)
GO
/****** Object:  UserDefinedFunction [dbo].[udf_findClazzByNameOrCategory]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[udf_findClazzByNameOrCategory](@user_id INT, @content varchar(255))
RETURNS TABLE
RETURN
	SELECT * FROM clazz c WHERE c.id IN (SELECT cuv.clazz_id FROM ClazzUserView cuv WHERE cuv.user_id = @user_id) AND (c.category LIKE CONCAT('%', @content, '%') OR c.name LIKE CONCAT('%', @content, '%'))
GO
/****** Object:  UserDefinedFunction [dbo].[udf_findClazzByStatusAndContent]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[udf_findClazzByStatusAndContent](@user_id INT, @status varchar(255), @content varchar(255))
RETURNS TABLE
RETURN
	SELECT * FROM clazz c WHERE c.id IN (SELECT cuv.clazz_id FROM ClazzUserView cuv WHERE cuv.user_id = @user_id) AND (c.category = @content OR c.name = @content) AND c.status = @status


GO
/****** Object:  UserDefinedFunction [dbo].[udf_findClazzByStatusAndNameOrCategory]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[udf_findClazzByStatusAndNameOrCategory](@user_id INT, @status varchar(255), @content varchar(255))
RETURNS TABLE
RETURN
	SELECT * FROM clazz c WHERE c.id IN (SELECT cuv.clazz_id FROM ClazzUserView cuv WHERE cuv.user_id = @user_id) AND (c.category LIKE CONCAT('%', @content, '%') OR c.name LIKE CONCAT('%', @content, '%')) AND c.status = @status

GO
/****** Object:  UserDefinedFunction [dbo].[udf_findFeedbackByClazz]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[udf_findFeedbackByClazz](@subject_id INT, @clazz_id INT)
RETURNS TABLE
RETURN
	SELECT * FROM feedback f WHERE f.user_id IN (SELECT u.id FROM user_clazz uc JOIN uzer u ON uc.user_id = u.id WHERE uc.clazz_id = @clazz_id) AND f.subject_id=@subject_id

GO
/****** Object:  UserDefinedFunction [dbo].[udf_findScoreByUserId]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[udf_findScoreByUserId](@user_id INT)
RETURNS TABLE
RETURN
	SELECT su.name, sc.value FROM subject su JOIN score sc ON su.id = sc.subject_id WHERE sc.user_id = @user_id

GO
/****** Object:  UserDefinedFunction [dbo].[udf_findSubjectByClazz]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[udf_findSubjectByClazz](@clazz_id INT)
RETURNS TABLE
AS 
RETURN
	SELECT * FROM subject s WHERE s.id IN (SELECT cs.subject_id FROM clazz_subject cs WHERE cs.clazz_id = @clazz_id) 
GO
/****** Object:  UserDefinedFunction [dbo].[udf_findSubjectByUserId]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[udf_findSubjectByUserId](@user_id INT)
RETURNS TABLE
RETURN
	SELECT * FROM subject su1 WHERE su1.id IN (SELECT su.id FROM score sc JOIN subject su ON sc.subject_id = su.id WHERE sc.user_id = @user_id)
GO
/****** Object:  UserDefinedFunction [dbo].[udf_findTraineeByClazz]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[udf_findTraineeByClazz](@clazz_id INT)
RETURNS TABLE
AS 
RETURN
	SELECT * FROM uzer u WHERE u.id IN (SELECT uc.user_id FROM user_clazz uc WHERE uc.clazz_id = @clazz_id) AND u.role = 'Trainee'
GO
/****** Object:  View [dbo].[ClazzSubject]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[ClazzSubject]
AS
	SELECT cs.clazz_id, cs.subject_id, cs.status, c.category FROM clazz c JOIN clazz_subject cs ON c.id = cs.clazz_id
GO
/****** Object:  View [dbo].[ClazzSubjectView]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[ClazzSubjectView]
AS
	SELECT cs.clazz_id, cs.subject_id, cs.status FROM (clazz_subject cs JOIN clazz c ON cs.clazz_id = c.id) JOIN subject s ON cs.subject_id = s.id
GO
/****** Object:  View [dbo].[SubjectScoreView]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
	CREATE VIEW [dbo].[SubjectScoreView]
	AS 
		SELECT sc.name as 'Score Name', sc.value, su.name as 'Subject Name' FROM subject su JOIN score sc ON su.id = sc.subject_id
GO
/****** Object:  View [dbo].[UserClazzView]    Script Date: 1/8/2020 11:06:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[UserClazzView]
AS
	SELECT c.id FROM user_clazz uc JOIN clazz c ON uc.clazz_id = c.id

GO
SET IDENTITY_INSERT [dbo].[clazz] ON 

INSERT [dbo].[clazz] ([id], [category], [name], [note], [open_date], [status]) VALUES (1, N'Fresher', N'Java 01', N'Fresher Java First', CAST(N'2019-01-01' AS Date), N'Active')
INSERT [dbo].[clazz] ([id], [category], [name], [note], [open_date], [status]) VALUES (2, N'Fresher', N'Java 02', N'Fresher Java Second', CAST(N'2019-02-24' AS Date), N'Active')
INSERT [dbo].[clazz] ([id], [category], [name], [note], [open_date], [status]) VALUES (3, N'Fresher', N'Java 03', N'Fresher Java Third', CAST(N'2019-03-16' AS Date), N'Inactive')
INSERT [dbo].[clazz] ([id], [category], [name], [note], [open_date], [status]) VALUES (4, N'Fresher', N'Java 04', N'Fresher Java Four', CAST(N'2019-06-01' AS Date), N'Active')
INSERT [dbo].[clazz] ([id], [category], [name], [note], [open_date], [status]) VALUES (5, N'Fresher', N'Java 05', N'Fresher Java Five', CAST(N'2019-08-21' AS Date), N'Inactive')
INSERT [dbo].[clazz] ([id], [category], [name], [note], [open_date], [status]) VALUES (6, N'Fresher ', N'Java 06', N'Fresher Java Six', CAST(N'2019-09-16' AS Date), N'Active')
INSERT [dbo].[clazz] ([id], [category], [name], [note], [open_date], [status]) VALUES (7, N'Fresher', N'C 01', N'C First', CAST(N'2019-01-01' AS Date), N'Inactive')
INSERT [dbo].[clazz] ([id], [category], [name], [note], [open_date], [status]) VALUES (8, N'Campus Link', N'.Net & C# 01', N'C# Programing', CAST(N'2019-03-16' AS Date), N'Active')
INSERT [dbo].[clazz] ([id], [category], [name], [note], [open_date], [status]) VALUES (9, N'Campus Link', N'Front-end 01', N'Javascript & Angular', CAST(N'2019-06-01' AS Date), N'Inactive')
INSERT [dbo].[clazz] ([id], [category], [name], [note], [open_date], [status]) VALUES (13, N'Fresher', N'Front-end 02', N'Javascript & React Native', CAST(N'2019-09-16' AS Date), N'2019/12/20')
INSERT [dbo].[clazz] ([id], [category], [name], [note], [open_date], [status]) VALUES (14, N'Fresher', N'.Net & C# 02', N'C# Programing', CAST(N'2019-08-21' AS Date), N'Inactive')
INSERT [dbo].[clazz] ([id], [category], [name], [note], [open_date], [status]) VALUES (15, N'Fresher', N'C/C++ Automotive', N'C/C++ Automotive', CAST(N'2019-01-01' AS Date), N'Inactive')
SET IDENTITY_INSERT [dbo].[clazz] OFF
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (1, 1, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (1, 2, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (1, 3, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (2, 1, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (2, 2, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (2, 6, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (2, 10, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (3, 5, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (3, 7, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (3, 12, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (3, 20, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (4, 10, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (4, 11, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (4, 13, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (4, 19, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (5, 4, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (5, 9, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (5, 12, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (5, 14, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (5, 16, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (6, 1, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (6, 10, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (7, 22, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (7, 23, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (7, 24, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (7, 25, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (8, 15, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (8, 16, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (8, 17, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (8, 18, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (8, 19, N'Continue')
INSERT [dbo].[clazz_subject] ([clazz_id], [subject_id], [status]) VALUES (8, 20, N'Continue')
INSERT [dbo].[feedback] ([subject_id], [user_id], [content]) VALUES (1, 1, N'Can bo sung them thoi gian')
INSERT [dbo].[review_trainee] ([trainee_id], [trainer_id], [content], [type]) VALUES (1, 1, N'Hoc hanh tap trung, kien thuc tot', N'A')
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (1, 1, 1, N'Theory', 8)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (2, 1, 1, N'Practice', 8)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (3, 2, 1, N'Theory', 7)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (4, 2, 1, N'Practice', 9)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (5, 3, 1, N'Theory', 8)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (6, 3, 1, N'Practice', 10)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (7, 1, 2, N'Theory', 7)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (8, 2, 2, N'Practice', 6)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (9, 7, 2, N'Theory', 9)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (10, 9, 2, N'Practice', 10)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (11, 9, 3, N'Theory', 8)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (12, 20, 3, N'Practice', 10)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (13, 6, 3, N'Practice', 9)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (14, 12, 3, N'Theory', 10)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (15, 22, 3, N'Practice', 2)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (16, 33, 4, N'Theory', 7)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (17, 27, 4, N'Practice', 7)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (18, 39, 4, N'Theory', 1)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (19, 40, 4, N'Practice', 9)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (20, 14, 5, N'Theory', 2)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (21, 11, 5, N'Practice', 6)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (22, 19, 5, N'Theory', 10)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (23, 15, 5, N'Practice', 9)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (24, 22, 6, N'Theory', 8)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (25, 28, 6, N'Practice', 7)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (26, 21, 6, N'Theory', 9)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (27, 30, 6, N'Practice', 9)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (28, 8, 7, N'Theory', 4)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (29, 3, 7, N'Practice', 5)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (30, 5, 7, N'Theory', 10)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (31, 9, 7, N'Practice', 1)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (32, 14, 8, N'Theory', 8)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (33, 17, 8, N'Practice', 9)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (34, 34, 8, N'Theory', 9)
INSERT [dbo].[score] ([id], [subject_id], [user_id], [name], [value]) VALUES (35, 39, 8, N'Practice', 5)
SET IDENTITY_INSERT [dbo].[subject] ON 

INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (1, N'ENT103', 45, N'Topnotch 1 - Fundamentals ')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (2, N'SSS101', 30, N'Study skill')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (3, N'VOV112', 25, N'Vovinam 1')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (4, N'ENT203', 55, N'Top Notch 2 ')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (5, N'ENT303', 15, N'Top Notch 3 ')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (6, N'VOV113', 35, N'Vovinam 2 ')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (7, N'VOV122', 20, N'Vovinam 3')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (8, N'ENT403', 25, N'Summit 1')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (9, N'ENT503', 15, N'Summit 2')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (10, N'PRO001', 10, N'Programming with Alice')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (11, N'VOV123', 29, N'Vovinam 4')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (12, N'DNG101', 40, N'Dan Nguyet')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (13, N'CEA201', 24, N'Computer Organization and Architecture')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (14, N'CSI101', 30, N'Introduction to computer sciences ')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (15, N'MAE101', 25, N'Mathematics for Engineering')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (16, N'PRF192', 40, N'Programming Fundamentals')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (17, N'SSG101', 22, N'Working in Group')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (18, N'VOV134', 10, N'Vovinam 3')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (19, N'DBI202', 60, N'Introduction to Databases')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (20, N'LAB101', 44, N'C Lab')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (21, N'MAD101', 75, N'Discrete mathematics')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (22, N'PRO192', 10, N'Object-Oriented Programming')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (23, N'PRO201', 50, N'Front-end Web Development')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (24, N'CSD201', 44, N'Data Structures and Algorithms')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (25, N'JPD111', 23, N'Japanese 1')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (26, N'LAB211', 67, N'OOP with Java Lab')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (27, N'OSG202', 34, N'Operating Systems')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (28, N'PRJ311', 56, N'Desktop Java Applications')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (29, N'JPD121', 49, N'Japanese Elementary 2')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (30, N'LAB221', 70, N'Desktop Java Lab')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (31, N'NWC202', 22, N'Computer Networking')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (32, N'PRJ321', 56, N'Web-Based Java Applications')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (33, N'SWE102', 80, N'Introduction to Software Engineering ')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (34, N'LAB231', 57, N'Web Java Lab')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (35, N'PRN292', 30, N'.NET and C#')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (36, N'SWR302', 55, N'Software Requirement')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (37, N'SWT301', 33, N'Software Testing')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (38, N'SYB301', 23, N'Start Your Business')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (39, N'JPL', 56, N'Java Programing Language')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (40, N'CNU', 24, N'Unit Test')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (41, N'ORM', 32, N'Object Relational Mapping')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (42, N'Front-end', 30, N'Front-end')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (43, N'JWEB', 30, N'Java Web')
INSERT [dbo].[subject] ([id], [code], [duration], [name]) VALUES (44, N'JSWF', 40, N'Spring Framework')
SET IDENTITY_INSERT [dbo].[subject] OFF
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (1, 1)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (1, 2)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (1, 3)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (1, 7)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (2, 5)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (2, 6)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (2, 7)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (3, 7)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (3, 8)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (3, 5)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (4, 4)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (4, 7)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (4, 6)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (4, 2)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (7, 2)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (7, 3)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (6, 8)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (6, 4)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (5, 9)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (5, 10)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (8, 3)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (8, 9)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (8, 4)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (8, 1)
INSERT [dbo].[user_clazz] ([clazz_id], [user_id]) VALUES (9, 10)
SET IDENTITY_INSERT [dbo].[uzer] ON 

INSERT [dbo].[uzer] ([id], [account], [birth_day], [email], [first_name], [gender], [last_name], [password], [phone], [role], [status]) VALUES (1, N'admin', CAST(N'1999-10-10' AS Date), N'admin@fsoft.com.vn', N'admin', N'Male', N'my', N'mynameisadmin', N'0968888259', N'admin', N'Active')
INSERT [dbo].[uzer] ([id], [account], [birth_day], [email], [first_name], [gender], [last_name], [password], [phone], [role], [status]) VALUES (2, N'userone', CAST(N'1993-10-23' AS Date), N'userone@fsoft.com.vn', N'user', N'Male', N'one', N'mynameisuserone', N'0968888259', N'Trainer', N'Active')
INSERT [dbo].[uzer] ([id], [account], [birth_day], [email], [first_name], [gender], [last_name], [password], [phone], [role], [status]) VALUES (3, N'usertwo', CAST(N'1989-07-11' AS Date), N'usertwo@fsoft.com.vn', N'user', N'Female', N'two', N'mynameisusertwo', N'0845684888', N'Trainer', N'Inactive')
INSERT [dbo].[uzer] ([id], [account], [birth_day], [email], [first_name], [gender], [last_name], [password], [phone], [role], [status]) VALUES (4, N'userthree', CAST(N'1987-11-11' AS Date), N'userthree@fsoft.com.vn', N'user', N'Female', N'three', N'mynameisuserthree', N'0845684888', N'Trainer', N'Active')
INSERT [dbo].[uzer] ([id], [account], [birth_day], [email], [first_name], [gender], [last_name], [password], [phone], [role], [status]) VALUES (5, N'userfour', CAST(N'1995-01-22' AS Date), N'userfour@fsoft.com.vn', N'user', N'Male', N'four', N'mynameisuserfour', N'0845684888', N'Trainee', N'Inactive')
INSERT [dbo].[uzer] ([id], [account], [birth_day], [email], [first_name], [gender], [last_name], [password], [phone], [role], [status]) VALUES (6, N'userfive', CAST(N'1994-08-12' AS Date), N'userfive@fsoft.com.vn', N'user', N'Female', N'five', N'mynameisuserfive', N'0845684888', N'Trainee', N'Inactive')
INSERT [dbo].[uzer] ([id], [account], [birth_day], [email], [first_name], [gender], [last_name], [password], [phone], [role], [status]) VALUES (7, N'usersix', CAST(N'1994-08-12' AS Date), N'usersix@fsoft.com.vn', N'user', N'Male', N'six', N'mynameisusersix', N'0845684888', N'Trainee', N'Active')
INSERT [dbo].[uzer] ([id], [account], [birth_day], [email], [first_name], [gender], [last_name], [password], [phone], [role], [status]) VALUES (8, N'userseven', CAST(N'1994-08-12' AS Date), N'userseven@fsoft.com.vn', N'user', N'Female', N'seven', N'mynameisuserseven', N'0845684888', N'Trainee', N'Inactive')
INSERT [dbo].[uzer] ([id], [account], [birth_day], [email], [first_name], [gender], [last_name], [password], [phone], [role], [status]) VALUES (9, N'usereight', CAST(N'1994-08-12' AS Date), N'usereight@fsoft.com.vn', N'user', N'Female', N'eight', N'mynameisusereight', N'0845684888', N'Trainee', N'Active')
INSERT [dbo].[uzer] ([id], [account], [birth_day], [email], [first_name], [gender], [last_name], [password], [phone], [role], [status]) VALUES (10, N'usernine', CAST(N'1994-08-12' AS Date), N'usernine@fsoft.com.vn', N'user', N'Female', N'nine', N'mynameisusernine', N'0845684888', N'Trainee', N'Active')
INSERT [dbo].[uzer] ([id], [account], [birth_day], [email], [first_name], [gender], [last_name], [password], [phone], [role], [status]) VALUES (11, N'userten', CAST(N'1994-08-12' AS Date), N'userten@fsoft.com.vn', N'user', N'Female', N'ten', N'mynameisuserten', N'0845684888', N'Trainee', N'Active')
SET IDENTITY_INSERT [dbo].[uzer] OFF
ALTER TABLE [dbo].[attendance_user]  WITH CHECK ADD  CONSTRAINT [FKduktmlurppadtittnlrigcs2g] FOREIGN KEY([attendance_date])
REFERENCES [dbo].[attendance] ([date])
GO
ALTER TABLE [dbo].[attendance_user] CHECK CONSTRAINT [FKduktmlurppadtittnlrigcs2g]
GO
ALTER TABLE [dbo].[attendance_user]  WITH CHECK ADD  CONSTRAINT [FKhm0otyjigvnalproqrdpt3h4v] FOREIGN KEY([user_id])
REFERENCES [dbo].[uzer] ([id])
GO
ALTER TABLE [dbo].[attendance_user] CHECK CONSTRAINT [FKhm0otyjigvnalproqrdpt3h4v]
GO
ALTER TABLE [dbo].[clazz_subject]  WITH CHECK ADD  CONSTRAINT [FK79bt3j0sx8d2xfnhtrpjm9m3h] FOREIGN KEY([clazz_id])
REFERENCES [dbo].[clazz] ([id])
GO
ALTER TABLE [dbo].[clazz_subject] CHECK CONSTRAINT [FK79bt3j0sx8d2xfnhtrpjm9m3h]
GO
ALTER TABLE [dbo].[clazz_subject]  WITH CHECK ADD  CONSTRAINT [FKqeas8larc3eyh9rx73t6lkx84] FOREIGN KEY([subject_id])
REFERENCES [dbo].[subject] ([id])
GO
ALTER TABLE [dbo].[clazz_subject] CHECK CONSTRAINT [FKqeas8larc3eyh9rx73t6lkx84]
GO
ALTER TABLE [dbo].[feedback]  WITH CHECK ADD  CONSTRAINT [FKkkqbq3bc9q4dh9amx7g9fci47] FOREIGN KEY([subject_id])
REFERENCES [dbo].[subject] ([id])
GO
ALTER TABLE [dbo].[feedback] CHECK CONSTRAINT [FKkkqbq3bc9q4dh9amx7g9fci47]
GO
ALTER TABLE [dbo].[feedback]  WITH CHECK ADD  CONSTRAINT [FKmoyi49rrkhk71mo97iqvjicvc] FOREIGN KEY([user_id])
REFERENCES [dbo].[uzer] ([id])
GO
ALTER TABLE [dbo].[feedback] CHECK CONSTRAINT [FKmoyi49rrkhk71mo97iqvjicvc]
GO
ALTER TABLE [dbo].[review_trainee]  WITH CHECK ADD  CONSTRAINT [FKe4faftpryomd3lxac92uthuua] FOREIGN KEY([trainer_id])
REFERENCES [dbo].[uzer] ([id])
GO
ALTER TABLE [dbo].[review_trainee] CHECK CONSTRAINT [FKe4faftpryomd3lxac92uthuua]
GO
ALTER TABLE [dbo].[review_trainee]  WITH CHECK ADD  CONSTRAINT [FKk1mg2oowy8hdpl8fcnq41w3c6] FOREIGN KEY([trainee_id])
REFERENCES [dbo].[uzer] ([id])
GO
ALTER TABLE [dbo].[review_trainee] CHECK CONSTRAINT [FKk1mg2oowy8hdpl8fcnq41w3c6]
GO
ALTER TABLE [dbo].[score]  WITH CHECK ADD  CONSTRAINT [FK56nv285e8l73fru4sw2152y87] FOREIGN KEY([subject_id])
REFERENCES [dbo].[subject] ([id])
GO
ALTER TABLE [dbo].[score] CHECK CONSTRAINT [FK56nv285e8l73fru4sw2152y87]
GO
ALTER TABLE [dbo].[score]  WITH CHECK ADD  CONSTRAINT [FKrpg1roeyxoofolkb817dmqykr] FOREIGN KEY([user_id])
REFERENCES [dbo].[uzer] ([id])
GO
ALTER TABLE [dbo].[score] CHECK CONSTRAINT [FKrpg1roeyxoofolkb817dmqykr]
GO
ALTER TABLE [dbo].[user_clazz]  WITH CHECK ADD  CONSTRAINT [FK8pia2lo3iforrcf3c07bunf50] FOREIGN KEY([user_id])
REFERENCES [dbo].[uzer] ([id])
GO
ALTER TABLE [dbo].[user_clazz] CHECK CONSTRAINT [FK8pia2lo3iforrcf3c07bunf50]
GO
ALTER TABLE [dbo].[user_clazz]  WITH CHECK ADD  CONSTRAINT [FKbfhja35inyi4w7bq77vp4mrcx] FOREIGN KEY([clazz_id])
REFERENCES [dbo].[clazz] ([id])
GO
ALTER TABLE [dbo].[user_clazz] CHECK CONSTRAINT [FKbfhja35inyi4w7bq77vp4mrcx]
GO
USE [master]
GO
ALTER DATABASE [mock_project] SET  READ_WRITE 
GO
