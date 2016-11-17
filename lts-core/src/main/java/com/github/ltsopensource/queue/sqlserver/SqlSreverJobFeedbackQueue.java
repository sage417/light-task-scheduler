package com.github.ltsopensource.queue.sqlserver;

import com.github.ltsopensource.core.cluster.Config;
import com.github.ltsopensource.core.commons.utils.CollectionUtils;
import com.github.ltsopensource.core.json.JSON;
import com.github.ltsopensource.core.support.JobQueueUtils;
import com.github.ltsopensource.queue.JobFeedbackQueue;
import com.github.ltsopensource.queue.domain.JobFeedbackPo;
import com.github.ltsopensource.queue.mysql.support.RshHolder;
import com.github.ltsopensource.store.jdbc.JdbcAbstractAccess;
import com.github.ltsopensource.store.jdbc.builder.*;
import com.github.ltsopensource.store.jdbc.builder.sqlserver.SqlServerEscape;
import com.github.ltsopensource.store.jdbc.builder.sqlserver.SqlServerPage;

import java.util.List;

/**
 * @author Robert HG (254963746@qq.com) on 5/20/15.
 */
public class SqlSreverJobFeedbackQueue extends JdbcAbstractAccess implements JobFeedbackQueue {

    public SqlSreverJobFeedbackQueue(Config config) {
        super(config);
    }

    @Override
    public boolean createQueue(String jobClientNodeGroup) {
        createTable(readSqlFile("sql/sqlserver/lts_job_feedback_queue.sql", getTableName(jobClientNodeGroup)));
        return true;
    }

    @Override
    public boolean removeQueue(String jobClientNodeGroup) {
        return new DropTableSql(getSqlTemplate())
                .drop(JobQueueUtils.getFeedbackQueueName(jobClientNodeGroup))
                .doDrop();
    }

    private String getTableName(String jobClientNodeGroup) {
        return JobQueueUtils.getFeedbackQueueName(jobClientNodeGroup);
    }

    @Override
    public boolean add(List<JobFeedbackPo> jobFeedbackPos) {
        if (CollectionUtils.isEmpty(jobFeedbackPos)) {
            return true;
        }
        // insert ignore duplicate record
        for (JobFeedbackPo jobFeedbackPo : jobFeedbackPos) {
            String jobClientNodeGroup = jobFeedbackPo.getJobRunResult().getJobMeta().getJob().getSubmitNodeGroup();
            new InsertSql(getSqlTemplate()).setEscape(SqlServerEscape.Holder.instance)
                    .insertIgnore(getTableName(jobClientNodeGroup))
                    .columns("gmt_created", "job_result")
                    .values(jobFeedbackPo.getGmtCreated(), JSON.toJSONString(jobFeedbackPo.getJobRunResult()))
                    .doInsert();
        }
        return true;
    }

    @Override
    public boolean remove(String jobClientNodeGroup, String id) {
        return new DeleteSql(getSqlTemplate()).setEscape(SqlServerEscape.Holder.instance)
                .delete()
                .from()
                .table(getTableName(jobClientNodeGroup))
                .where("id = ?", id)
                .doDelete() == 1;
    }

    @Override
    public long getCount(String jobClientNodeGroup) {
        return (Integer) new SelectSql(getSqlTemplate()).setEscape(SqlServerEscape.Holder.instance)
                .select()
                .columns("count(1)")
                .from()
                .table(getTableName(jobClientNodeGroup))
                .single();
    }

    @Override
    public List<JobFeedbackPo> fetchTop(String jobClientNodeGroup, int top) {

        return new SelectSql(getSqlTemplate())
                .setEscape(SqlServerEscape.Holder.instance)
                .setPage(SqlServerPage.Holder.instance)
                .select()
                .all()
                .from()
                .table(getTableName(jobClientNodeGroup))
                .orderBy()
                .column("gmt_created", OrderByType.ASC)
                .limit(0, top)
                .list(RshHolder.JOB_FEED_BACK_LIST_RSH);
    }


}
