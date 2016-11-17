package com.github.ltsopensource.queue.sqlserver;

import com.github.ltsopensource.admin.request.JobQueueReq;
import com.github.ltsopensource.core.cluster.Config;
import com.github.ltsopensource.core.support.JobQueueUtils;
import com.github.ltsopensource.queue.SuspendJobQueue;
import com.github.ltsopensource.queue.domain.JobPo;
import com.github.ltsopensource.queue.mysql.support.RshHolder;
import com.github.ltsopensource.store.jdbc.builder.DeleteSql;
import com.github.ltsopensource.store.jdbc.builder.SelectSql;
import com.github.ltsopensource.store.jdbc.builder.sqlserver.SqlServerEscape;

/**
 * @author bug (357693306@qq.com) on 3/4/16.
 */
public class SqlServerSuspendJobQueue extends AbstractSqlServerJobQueue implements SuspendJobQueue {

    public SqlServerSuspendJobQueue(Config config) {
        super(config);
        createTable(readSqlFile("sql/sqlserver/lts_suspend_job_queue.sql", getTableName()));
    }

    @Override
    protected String getTableName(JobQueueReq request) {
        return getTableName();
    }

    @Override
    public boolean add(JobPo jobPo) {
        return add(getTableName(), jobPo);
    }

    @Override
    public JobPo getJob(String jobId) {
        return new SelectSql(getSqlTemplate()).setEscape(SqlServerEscape.Holder.instance)
                .select()
                .all()
                .from()
                .table(getTableName())
                .where("job_id = ?", jobId)
                .single(RshHolder.JOB_PO_RSH);
    }

    @Override
    public boolean remove(String jobId) {
        return new DeleteSql(getSqlTemplate()).setEscape(SqlServerEscape.Holder.instance)
                .delete()
                .from()
                .table(getTableName())
                .where("job_id = ?", jobId)
                .doDelete() == 1;
    }

    @Override
    public JobPo getJob(String taskTrackerNodeGroup, String taskId) {
        return new SelectSql(getSqlTemplate()).setEscape(SqlServerEscape.Holder.instance)
                .select()
                .all()
                .from()
                .table(getTableName())
                .where("task_id = ?", taskId)
                .and("task_tracker_node_group = ?", taskTrackerNodeGroup)
                .single(RshHolder.JOB_PO_RSH);
    }

    private String getTableName() {
        return JobQueueUtils.SUSPEND_JOB_QUEUE;
    }

}
