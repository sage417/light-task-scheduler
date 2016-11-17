

IF  NOT EXISTS (SELECT * FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[lts_admin_job_client_monitor_data]') AND type in (N'U'))

BEGIN
CREATE TABLE  [lts_admin_job_client_monitor_data] (
  [id] int IDENTITY(1,1) NOT NULL ,
            [gmt_created] bigint ,
            [node_group] varchar(64) DEFAULT NULL,
            [identity] varchar(64) DEFAULT NULL,
            [submit_success_num] bigint DEFAULT NULL,
            [submit_failed_num] bigint DEFAULT NULL,
            [fail_store_num] bigint DEFAULT NULL,
            [submit_fail_store_num] bigint DEFAULT NULL,
            [handle_feedback_num] bigint DEFAULT NULL,
            [timestamp] bigint
            )

END