IF  NOT EXISTS (SELECT * FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[lts_admin_node_onoffline_log]') AND type in (N'U'))

BEGIN
CREATE TABLE [lts_admin_node_onoffline_log] (
            [id] int IDENTITY(1,1) NOT NULL ,
            [log_time] bigint ,
            [event] varchar(32) DEFAULT NULL,
            [node_type] varchar(16) DEFAULT NULL,
            [cluster_name] varchar(64) DEFAULT NULL,
            [ip] varchar(16) DEFAULT NULL,
            [port] int DEFAULT NULL,
            [host_name] varchar(64) DEFAULT NULL,
            [group] varchar(64) DEFAULT NULL,
            [create_time] bigint DEFAULT NULL,
            [threads] int DEFAULT NULL,
            [identity] varchar(64) DEFAULT NULL,
            [http_cmd_port] int DEFAULT NULL
            )
END