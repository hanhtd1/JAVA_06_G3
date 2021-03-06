USE [mock_project]
GO
/****** Object:  UserDefinedFunction [dbo].[findBestTrainee]    Script Date: 1/19/2020 19:52:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[findBestTrainee]() 
RETURNS @trainee TABLE (
		name varchar(255),
		score float,
		clazz varchar(255)
	)
AS 
BEGIN 
	WITH TopThreeUserCTE(id, fullname, avg_score)
	AS(
			SELECT u.id, u.first_name + u.last_name AS 'Full name', t.AverageScore 
			FROM TopThreeUser t JOIN uzer u ON t.user_id = u.id
	) 
	-- SELECT * FROM (SELECT * FROM view_UserAndUserClazz uv where uv.user_id IN (SELECT ttc.id FROM TopThreeUserCTE ttc)) AS temp LEFT JOIN TopThreeUserCTE ttc ON temp.user_id = ttc.id
	INSERT INTO @trainee
	SELECT ttc.fullname, ttc.avg_score AS 'AvgScore', temp.name AS 'ClazzName' FROM (SELECT * FROM view_UserAndUserClazz uv where uv.user_id IN (SELECT ttc.id FROM TopThreeUserCTE ttc)) AS temp LEFT JOIN TopThreeUserCTE ttc ON temp.user_id = ttc.id
	RETURN;
END
GO
CREATE FUNCTION [dbo].[udf_findClazzByCategory](@user_id INT, @category varchar(255))
RETURNS TABLE
RETURN
	SELECT * FROM clazz c WHERE c.id IN (SELECT uc.clazz_id FROM user_clazz uc WHERE uc.user_id = @user_id) AND c.category LIKE CONCAT('%',@category ,'%')
GO
CREATE FUNCTION [dbo].[udf_findClazzByNameOrCategory](@user_id INT, @content varchar(255))
RETURNS TABLE
RETURN
	SELECT * FROM clazz c WHERE c.id IN (SELECT uc.clazz_id FROM user_clazz uc WHERE uc.user_id = @user_id) AND (c.category LIKE CONCAT('%',@content ,'%') OR c.name LIKE CONCAT('%',@content ,'%'))
GO
CREATE FUNCTION [dbo].[udf_findClazzByStatus](@user_id INT, @status varchar(255))
RETURNS TABLE
RETURN
	SELECT * FROM clazz c WHERE c.id IN (SELECT uc.clazz_id FROM user_clazz uc WHERE uc.user_id = @user_id) AND c.status LIKE CONCAT('%',@status ,'%')
GO
CREATE FUNCTION [dbo].[udf_findClazzByStatusAndNameOrCategory](@user_id INT, @status varchar(255), @content varchar(255))
RETURNS TABLE
RETURN
	SELECT * FROM clazz c WHERE c.id IN (SELECT uc.clazz_id FROM user_clazz uc WHERE uc.user_id = @user_id) AND c.status LIKE CONCAT('%',@status ,'%') AND (c.category LIKE CONCAT('%',@content ,'%') OR c.name LIKE CONCAT('%',@content ,'%'))
GO
CREATE FUNCTION [dbo].[udf_findClazzByUserId](@user_id INT)
RETURNS TABLE
RETURN
	SELECT * FROM clazz c WHERE c.id IN (SELECT uc.clazz_id FROM user_clazz uc WHERE uc.user_id = @user_id)
GO
CREATE FUNCTION [dbo].[udf_findFeedbackBySubjectAndClazz](@subject_id INT, @clazz_id INT)
RETURNS TABLE
RETURN
	SELECT * FROM feedback f WHERE f.subject_id IN (SELECT cs.subject_id FROM clazz_subject cs WHERE cs.clazz_id = @clazz_id AND cs.subject_id = @subject_id)
GO
CREATE FUNCTION [dbo].[udf_findFeedbackBySubjectId](@subject_id INT)
RETURNS TABLE
RETURN
	SELECT * FROM feedback f WHERE f.subject_id IN (SELECT s.id FROM subject s WHERE s.id = @subject_id)
GO
CREATE FUNCTION [dbo].[udf_findScoreByUserId](@user_id INT)
RETURNS TABLE 
RETURN
	SELECT su.name, sc.theory, sc.practice FROM score sc JOIN subject su ON sc.subject_id = su.id WHERE sc.user_id = @user_id
GO
CREATE FUNCTION [dbo].[udf_findSubjectByClazz](@clazz_id INT)
RETURNS TABLE 
RETURN
	SELECT * FROM subject s WHERE s.id IN (SELECT cs.subject_id FROM clazz_subject cs WHERE cs.clazz_id = @clazz_id) 
GO
CREATE FUNCTION [dbo].[udf_findSubjectByUser](@user_id INT)
RETURNS TABLE 
RETURN
	SELECT * FROM subject su WHERE su.id IN (SELECT f.subject_id FROM feedback f WHERE f.user_id = @user_id) 
GO
CREATE FUNCTION [dbo].[udf_findTraineeByCategory](@category varchar(255))
RETURNS TABLE
RETURN
	SELECT * FROM uzer u WHERE u.id IN (SELECT uc.user_id FROM clazz c JOIN user_clazz uc ON c.id = uc.clazz_id WHERE c.category = @category) AND u.role = 'ROLE_TRAINEE'
GO
CREATE FUNCTION [dbo].[udf_findTraineeByCategoryAndClazz](@category varchar(255), @clazz_name varchar(255))
  RETURNS TABLE
  RETURN
	SELECT * FROM uzer u WHERE u.id IN (SELECT uc.user_id FROM user_clazz uc JOIN clazz c ON c.id = uc.clazz_id AND c.name = @clazz_name AND c.category = @category)

GO
CREATE FUNCTION [dbo].[udf_findTraineeByCategoryAndClazzAndStatus](@category varchar(255), @clazz_name varchar(255), @status varchar(255))
  RETURNS TABLE
  RETURN
	SELECT * FROM uzer u WHERE u.id IN (SELECT uc.user_id FROM user_clazz uc JOIN clazz c ON c.id = uc.clazz_id AND c.category = @category AND c.name = @clazz_name AND c.status = @status)
GO
CREATE FUNCTION [dbo].[udf_findTraineeByCategoryAndStatus](@category varchar(255), @status varchar(255))
  RETURNS TABLE
  RETURN
	SELECT * FROM uzer u WHERE u.id IN (SELECT uc.user_id FROM user_clazz uc JOIN clazz c ON c.id = uc.clazz_id AND c.status = @status AND c.category = @category)
GO
CREATE FUNCTION [dbo].[udf_findTraineeByClazzAndStatus](@clazz_name varchar(255), @status varchar(255))
  RETURNS TABLE
  RETURN
	SELECT * FROM uzer u WHERE u.id IN (SELECT uc.user_id FROM user_clazz uc JOIN clazz c ON c.id = uc.clazz_id AND c.name = @clazz_name AND c.status = @status)

	GO

  CREATE FUNCTION [dbo].[udf_findTraineeByClazzId](@clazz_id INT)
  RETURNS TABLE
  RETURN
	SELECT * FROM uzer u WHERE u.id IN (SELECT uc.user_id FROM user_clazz uc JOIN clazz c ON c.id = uc.clazz_id AND uc.clazz_id = @clazz_id)


GO

  CREATE FUNCTION [dbo].[udf_findTraineeByClazzName](@clazz_name varchar(255))
  RETURNS TABLE
  RETURN
	SELECT * FROM uzer u WHERE u.id IN (SELECT uc.user_id FROM user_clazz uc JOIN clazz c ON c.id = uc.clazz_id AND c.name = @clazz_name)

GO
CREATE FUNCTION [dbo].[udf_udf_findTraineeByClazz](@clazz_id INT)
RETURNS TABLE
RETURN
	SELECT * FROM uzer u WHERE u.id IN (SELECT uc.user_id FROM user_clazz uc WHERE uc.clazz_id = @clazz_id) 

GO

CREATE VIEW [dbo].[TopThreeUser]
AS
	SELECT TOP 3 s.user_id, AVG((s.practice + s.theory)/2) AS 'AverageScore' FROM score s 
	GROUP BY s.user_id
	ORDER BY AverageScore DESC

GO

CREATE VIEW [dbo].[UserClazzViewCategory]
AS
	SELECT * FROM clazz c JOIN user_clazz uc ON c.id = uc.clazz_id

GO
CREATE VIEW [dbo].[view_UserAndUserClazz]
AS
	SELECT c.name, uc.user_id FROM clazz c JOIN user_clazz uc ON c.id = uc.clazz_id