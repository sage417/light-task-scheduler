

IF  NOT EXISTS (SELECT * FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[lts_admin_job_tracker_monitor_data]') AND type in (N'U'))

BEGIN
CREATE TABLE  [lts_admin_job_tracker_monitor_data] (
  [id] int IDENTITY(1,1) NOT NULL ,
            [gmt_created] bigint ,
            [identity] varchar(64) DEFAULT NULL,
            [receive_job_num] bigint DEFAULT NULL,
            [push_job_num] bigint DEFAULT NULL,
            [exe_success_num] bigint DEFAULT NULL,
            [exe_failed_num] bigint DEFAULT NULL,
            [exe_later_num] bigint DEFAULT NULL,
            [exe_exception_num] bigint DEFAULT NULL,
            [fix_executing_job_num] bigint DEFAULT NULL,
            [timestamp] bigint
)

END