IF  NOT EXISTS (SELECT * FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[lts_admin_task_tracker_monitor_data]') AND type in (N'U'))

BEGIN
CREATE TABLE [lts_admin_task_tracker_monitor_data] (
            [id] int IDENTITY(1,1) NOT NULL ,
            [gmt_created] bigint ,
            [node_group] varchar(64) DEFAULT NULL,
            [identity] varchar(64) DEFAULT NULL,
            [exe_success_num] bigint DEFAULT NULL,
            [exe_failed_num] bigint DEFAULT NULL,
            [exe_later_num] bigint DEFAULT NULL,
            [exe_exception_num] bigint DEFAULT NULL,
            [total_running_time] bigint DEFAULT NULL,
            [timestamp] bigint
)

END