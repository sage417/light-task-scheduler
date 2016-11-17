IF  NOT EXISTS (SELECT * FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[{tableName}]') AND type in (N'U'))

BEGIN
CREATE TABLE [{tableName}] (
  [id] int IDENTITY(1,1) NOT NULL ,
  [gmt_created] bigint ,
  [job_result] text ,
)

END