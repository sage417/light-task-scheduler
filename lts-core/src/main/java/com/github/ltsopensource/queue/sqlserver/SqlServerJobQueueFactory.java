package com.github.ltsopensource.queue.sqlserver;

import com.github.ltsopensource.core.AppContext;
import com.github.ltsopensource.core.cluster.Config;
import com.github.ltsopensource.queue.*;

/**
 * @author Robert HG (254963746@qq.com) on 3/12/16.
 */
public class SqlServerJobQueueFactory implements JobQueueFactory {

    @Override
    public CronJobQueue getCronJobQueue(Config config) {
        return new SqlServerCronJobQueue(config);
    }

    @Override
    public RepeatJobQueue getRepeatJobQueue(Config config) {
        return new SqlServerRepeatJobQueue(config);
    }

    @Override
    public ExecutableJobQueue getExecutableJobQueue(Config config) {
        return new SqlServerExecutableJobQueue(config);
    }

    @Override
    public ExecutingJobQueue getExecutingJobQueue(Config config) {
        return new SqlServerExecutingJobQueue(config);
    }

    @Override
    public JobFeedbackQueue getJobFeedbackQueue(Config config) {
        return new SqlSreverJobFeedbackQueue(config);
    }

    @Override
    public NodeGroupStore getNodeGroupStore(Config config) {
        return new SqlServerNodeGroupStore(config);
    }

    @Override
    public SuspendJobQueue getSuspendJobQueue(Config config) {
        return new SqlServerSuspendJobQueue(config);
    }

    @Override
    public PreLoader getPreLoader(AppContext appContext) {
        return new SqlServerPreLoader(appContext);
    }
}
