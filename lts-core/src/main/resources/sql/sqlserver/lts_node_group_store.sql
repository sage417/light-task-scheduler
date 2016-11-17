

IF  NOT EXISTS (SELECT * FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[{tableName}]') AND type in (N'U'))

BEGIN
CREATE TABLE [{tableName}] (
  [node_type] varchar(16) NOT NULL DEFAULT '' ,
  [name] varchar(64) NOT NULL DEFAULT '' ,
  [gmt_created] bigint NULL
)

END