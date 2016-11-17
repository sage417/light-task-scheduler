

IF  NOT EXISTS (SELECT * FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[lts_job_log_po]') AND type in (N'U'))

BEGIN
CREATE TABLE [lts_job_log_po] (
  [id] int IDENTITY(1,1) NOT NULL ,
  [gmt_created] bigint ,
  [log_time] bigint ,
  [log_type] varchar(32) ,
  [success] tinyint ,
  [msg] text ,
  [code] varchar(32) ,
  [job_type] varchar(32) ,
  [task_tracker_identity] varchar(64) ,
  [level] varchar(32) ,
  [task_id] varchar(64) ,
  [real_task_id] varchar(64) ,
  [job_id] varchar(64) DEFAULT '' ,
  [priority] int ,
  [submit_node_group] varchar(64) ,
  [task_tracker_node_group] varchar(64) ,
  [ext_params] text ,
  [internal_ext_params] text ,
  [need_feedback] tinyint ,
  [cron_expression] varchar(128) ,
  [trigger_time] bigint ,
  [retry_times] int ,
  [max_retry_times] int DEFAULT '0' ,
  [rely_on_prev_cycle] tinyint ,
  [repeat_count] int DEFAULT '0' ,
  [repeated_count] int DEFAULT '0' ,
  [repeat_interval] bigint DEFAULT '0'
)

END