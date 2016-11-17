

IF  NOT EXISTS (SELECT * FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[lts_admin_jvm_thread]') AND type in (N'U'))

BEGIN
CREATE TABLE [lts_admin_jvm_thread] (
  [id] int IDENTITY(1,1) NOT NULL ,
            [gmt_created] bigint ,
            [identity] varchar(64) DEFAULT NULL,
            [timestamp] bigint ,
            [node_type] varchar(32) ,
            [node_group] varchar(64) ,
            [daemon_thread_count] int ,
            [thread_count] int ,
            [total_started_thread_count] bigint ,
            [dead_locked_thread_count] int ,
            [process_cpu_time_rate] float  DEFAULT NULL
            )
END