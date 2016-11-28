package com.github.ltsopensource.admin.access.sqlserver;

import com.github.ltsopensource.admin.access.RshHandler;
import com.github.ltsopensource.admin.access.face.BackendTaskTrackerMAccess;
import com.github.ltsopensource.admin.request.MDataPaginationReq;
import com.github.ltsopensource.admin.web.vo.NodeInfo;
import com.github.ltsopensource.core.cluster.Config;
import com.github.ltsopensource.monitor.access.domain.TaskTrackerMDataPo;
import com.github.ltsopensource.monitor.access.sqlserver.SqlServerTaskTrackerMAccess;
import com.github.ltsopensource.store.jdbc.builder.DeleteSql;
import com.github.ltsopensource.store.jdbc.builder.SelectSql;
import com.github.ltsopensource.store.jdbc.builder.WhereSql;
import com.github.ltsopensource.store.jdbc.builder.sqlserver.SqlServerEscape;
import com.github.ltsopensource.store.jdbc.builder.sqlserver.SqlServerPage;

import java.util.List;

/**
 * @author Robert HG (254963746@qq.com) on 3/9/16.
 */
public class SqlServerBackendTaskTrackerMAccess extends SqlServerTaskTrackerMAccess implements BackendTaskTrackerMAccess {

    public SqlServerBackendTaskTrackerMAccess(Config config) {
        super(config);
    }

    @Override
    public List<TaskTrackerMDataPo> querySum(MDataPaginationReq request) {

        return new SelectSql(getSqlTemplate())
                .setEscape(SqlServerEscape.Holder.instance)
                .setPage(SqlServerPage.Holder.instance)
                .select()
                .columns("timestamp",
                        "SUM(exe_success_num) AS exe_success_num",
                        "SUM(exe_failed_num) AS exe_failed_num",
                        "SUM(exe_later_num) AS exe_later_num",
                        "SUM(exe_exception_num) AS exe_exception_num",
                        "SUM(total_running_time) AS total_running_time")
                .from()
                .table(getTableName())
                .whereSql(buildWhereSql(request))
                .groupBy(" timestamp ASC ")
                .limit(request.getStart(), request.getLimit())
                .list(RshHandler.TASK_TRACKER_SUM_M_DATA_RSH);
    }

    @Override
    public void delete(MDataPaginationReq request) {

        new DeleteSql(getSqlTemplate()).setEscape(SqlServerEscape.Holder.instance)
                .delete()
                .from()
                .table(getTableName())
                .whereSql(buildWhereSql(request))
                .doDelete();
    }

    @Override
    public List<NodeInfo> getTaskTrackers() {
        return new SelectSql(getSqlTemplate()).setEscape(SqlServerEscape.Holder.instance)
                .select()
                .columns("DISTINCT [identity]", "node_group")
                .from()
                .table(getTableName())
                .list(RshHandler.NODE_INFO_LIST_RSH);
    }

    public WhereSql buildWhereSql(MDataPaginationReq request) {
        return new WhereSql()
                .andOnNotNull("id = ?", request.getId())
                .andOnNotEmpty("[identity] = ?", request.getIdentity())
                .andOnNotEmpty("node_group = ?", request.getNodeGroup())
                .andBetween("timestamp", request.getStartTime(), request.getEndTime());
    }
}
