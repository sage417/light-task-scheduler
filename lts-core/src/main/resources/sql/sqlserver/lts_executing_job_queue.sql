

IF  NOT EXISTS (SELECT * FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[{tableName}]') AND type in (N'U'))

BEGIN
CREATE TABLE [{tableName}] (
  [id] int IDENTITY(1,1) NOT NULL ,
  [job_id] varchar(32) ,
  [job_type] varchar(32) ,
  [priority] int ,
  [retry_times] int DEFAULT '0' ,
  [max_retry_times] int DEFAULT '0' ,
  [rely_on_prev_cycle] tinyint ,
  [task_id] varchar(64) ,
  [real_task_id] varchar(64) ,
  [gmt_created] bigint ,
  [gmt_modified] bigint ,
  [submit_node_group] varchar(64) ,
  [task_tracker_node_group] varchar(64) ,
  [ext_params] text ,
  [internal_ext_params] text ,
  [is_running] tinyint ,
  [task_tracker_identity] varchar(64) ,
  [need_feedback] tinyint ,
  [cron_expression] varchar(128) ,
  [trigger_time] bigint ,
  [repeat_count] int DEFAULT '0' ,
  [repeated_count] int DEFAULT '0' ,
  [repeat_interval] bigint DEFAULT '0',
  [last_generate_trigger_time] bigint DEFAULT '0'
)

END