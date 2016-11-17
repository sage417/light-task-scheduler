

IF  NOT EXISTS (SELECT * FROM sys.objects
WHERE object_id = OBJECT_ID(N'[dbo].[lts_admin_jvm_memory]') AND type in (N'U'))

BEGIN
CREATE TABLE  [lts_admin_jvm_memory] (
  [id] int IDENTITY(1,1) NOT NULL ,
            [gmt_created] bigint ,
            [identity] varchar(64) DEFAULT NULL,
            [timestamp] bigint ,
            [node_type] varchar(32) ,
            [node_group] varchar(64) ,
            [heap_memory_committed] bigint ,
            [heap_memory_init] bigint ,
            [heap_memory_max] bigint ,
            [heap_memory_used] bigint ,
            [non_heap_memory_committed] bigint ,
            [non_heap_memory_init] bigint ,
            [non_heap_memory_max] bigint ,
            [non_heap_memory_used] bigint ,
            [perm_gen_committed] bigint ,
            [perm_gen_init] bigint ,
            [perm_gen_max] bigint ,
            [perm_gen_used] bigint ,
            [old_gen_committed] bigint ,
            [old_gen_init] bigint ,
            [old_gen_max] bigint ,
            [old_gen_used] bigint ,
            [eden_space_committed] bigint ,
            [eden_space_init] bigint ,
            [eden_space_max] bigint ,
            [eden_space_used] bigint ,
            [survivor_committed] bigint ,
            [survivor_init] bigint ,
            [survivor_max] bigint ,
            [survivor_used] bigint
            )

END