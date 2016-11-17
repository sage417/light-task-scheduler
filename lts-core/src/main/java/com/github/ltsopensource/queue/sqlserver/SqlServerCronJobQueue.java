package com.github.ltsopensource.queue.sqlserver;

import com.github.ltsopensource.admin.request.JobQueueReq;
import com.github.ltsopensource.core.cluster.Config;
import com.github.ltsopensource.core.support.JobQueueUtils;
import com.github.ltsopensource.queue.CronJobQueue;
import com.github.ltsopensource.queue.domain.JobPo;
import com.github.ltsopensource.queue.mysql.support.RshHolder;
import com.github.ltsopensource.store.jdbc.builder.DeleteSql;
import com.github.ltsopensource.store.jdbc.builder.SelectSql;
import com.github.ltsopensource.store.jdbc.builder.sqlserver.SqlServerEscape;

/**
 * @author Robert HG (254963746@qq.com) on 5/31/15.
 */
public class SqlServerCronJobQueue extends SqlServerSchedulerJobQueue implements CronJobQueue {

    public SqlServerCronJobQueue(Config config) {
        super(config);
        createTable(readSqlFile("sql/sqlserver/lts_cron_job_queue.sql", getTableName()));
    }

    @Override
    protected String getTableName(JobQueueReq request) {
        return getTableName();
    }

    @Override
    public boolean add(JobPo jobPo) {
        return super.add(getTableName(), jobPo);
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

    protected String getTableName() {
        return JobQueueUtils.CRON_JOB_QUEUE;
    }

}
