package com.github.ltsopensource.monitor.access.sqlserver;

import com.github.ltsopensource.core.cluster.Config;
import com.github.ltsopensource.monitor.access.MonitorAccessFactory;
import com.github.ltsopensource.monitor.access.face.*;

/**
 * @author Robert HG (254963746@qq.com) on 3/9/16.
 */
public class SqlServerMonitorAccessFactory implements MonitorAccessFactory {

    @Override
    public JobTrackerMAccess getJobTrackerMAccess(Config config) {
        return new SqlServerJobTrackerMAccess(config);
    }

    @Override
    public TaskTrackerMAccess getTaskTrackerMAccess(Config config) {
        return new SqlServerTaskTrackerMAccess(config);
    }

    @Override
    public JVMGCAccess getJVMGCAccess(Config config) {
        return new SqlServerJVMGCAccess(config);
    }

    @Override
    public JVMMemoryAccess getJVMMemoryAccess(Config config) {
        return new SqlServerJVMMemoryAccess(config);
    }

    @Override
    public JVMThreadAccess getJVMThreadAccess(Config config) {
        return new SqlServerJVMThreadAccess(config);
    }

    @Override
    public JobClientMAccess getJobClientMAccess(Config config) {
        return new SqlServerJobClientMAccess(config);
    }

}
