package com.github.ltsopensource.admin.access.sqlserver;

import com.github.ltsopensource.admin.access.BackendAccessFactory;
import com.github.ltsopensource.admin.access.face.*;
import com.github.ltsopensource.core.cluster.Config;

/**
 * @author Robert HG (254963746@qq.com) on 3/9/16.
 */
public class SqlServerBackendAccessFactory implements BackendAccessFactory {
    @Override
    public BackendJobTrackerMAccess getJobTrackerMAccess(Config config) {
        return new SqlServerBackendJobTrackerMAccess(config);
    }

    @Override
    public BackendJobClientMAccess getBackendJobClientMAccess(Config config) {
        return new SqlServerBackendJobClientMAccess(config);
    }

    @Override
    public BackendJVMGCAccess getBackendJVMGCAccess(Config config) {
        return new SqlServerBackendJVMGCAccess(config);
    }

    @Override
    public BackendJVMMemoryAccess getBackendJVMMemoryAccess(Config config) {
        return new SqlServerBackendJVMMemoryAccess(config);
    }

    @Override
    public BackendJVMThreadAccess getBackendJVMThreadAccess(Config config) {
        return new SqlServerBackendJVMThreadAccess(config);
    }

    @Override
    public BackendNodeOnOfflineLogAccess getBackendNodeOnOfflineLogAccess(Config config) {
        return new SqlServerBackendNodeOnOfflineLogAccess(config);
    }

    @Override
    public BackendTaskTrackerMAccess getBackendTaskTrackerMAccess(Config config) {
        return new SqlServerBackendTaskTrackerMAccess(config);
    }
}
