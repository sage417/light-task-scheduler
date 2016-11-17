package com.github.ltsopensource.monitor.access.sqlserver;

import com.github.ltsopensource.core.cluster.Config;
import com.github.ltsopensource.core.commons.utils.CollectionUtils;
import com.github.ltsopensource.monitor.access.domain.JVMThreadDataPo;
import com.github.ltsopensource.monitor.access.face.JVMThreadAccess;
import com.github.ltsopensource.store.jdbc.builder.InsertSql;
import com.github.ltsopensource.store.jdbc.builder.mysql.MySqlEscape;
import com.github.ltsopensource.store.jdbc.builder.sqlserver.SqlServerEscape;

import java.util.List;

/**
 * @author Robert HG (254963746@qq.com) on 3/9/16.
 */
public class SqlServerJVMThreadAccess extends SqlServerAbstractJdbcAccess implements JVMThreadAccess {

    public SqlServerJVMThreadAccess(Config config) {
        super(config);
    }

    @Override
    protected String getTableName() {
        return "lts_admin_jvm_thread";
    }

    @Override
    public void insert(List<JVMThreadDataPo> jvmThreadDataPos) {
        if (CollectionUtils.isEmpty(jvmThreadDataPos)) {
            return;
        }

        InsertSql insertSql = new InsertSql(getSqlTemplate())
                .setEscape(SqlServerEscape.Holder.instance)
                .insert(getTableName())
                .columns("gmt_created",
                        "identity",
                        "timestamp",
                        "node_type",
                        "node_group",
                        "daemon_thread_count",
                        "thread_count",
                        "total_started_thread_count",
                        "dead_locked_thread_count",
                        "process_cpu_time_rate");

        for (JVMThreadDataPo po : jvmThreadDataPos) {
            insertSql.values(
                    po.getGmtCreated(),
                    po.getIdentity(),
                    po.getTimestamp(),
                    po.getNodeType().name(),
                    po.getNodeGroup(),
                    po.getDaemonThreadCount(),
                    po.getThreadCount(),
                    po.getTotalStartedThreadCount(),
                    po.getDeadLockedThreadCount(),
                    po.getProcessCpuTimeRate()
            );
        }

        insertSql.doBatchInsert();
    }

}
