

IF  NOT EXISTS (SELECT * FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[lts_admin_jvm_gc]') AND type in (N'U'))

BEGIN
CREATE TABLE [lts_admin_jvm_gc] (
  [id] int IDENTITY(1,1) NOT NULL ,
            [gmt_created] bigint ,
            [identity] varchar(64) DEFAULT NULL,
            [timestamp] bigint ,
            [node_type] varchar(32) ,
            [node_group] varchar(64) ,
            [young_gc_collection_count] bigint ,
            [young_gc_collection_time] bigint ,
            [full_gc_collection_count] bigint ,
            [full_gc_collection_time] bigint ,
            [span_young_gc_collection_count] bigint ,
            [span_young_gc_collection_time] bigint ,
            [span_full_gc_collection_count] bigint ,
            [span_full_gc_collection_time] bigint
            )

END