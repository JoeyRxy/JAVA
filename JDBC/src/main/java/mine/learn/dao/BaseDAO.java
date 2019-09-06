package mine.learn.dao;

import mine.learn.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class BaseDAO {
    // 使用DBUtils
    // QueryRunner 封装了增删改查的方法
    private QueryRunner queryRunner = new QueryRunner();

    // 增删改
    public int update(String sql, Object... params) {
        Connection connection = JDBCUtils.getConnection();
        int i = 0;
        try {
            i = queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return i;
    }

    // 查询一条
    public <T> T getBean(Class<T> type, String sql, Object... params) {
        Connection conn = JDBCUtils.getConnection();
        T query = null;
        try {
            query = queryRunner.query(conn, sql, new BeanHandler<T>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
        return query;
    }

    // 查询多条并封装
    public <T> List<T> getBeanList(Class<T> type, String sql, Object... param) {
        Connection conn = JDBCUtils.getConnection();
        List<T> list = new ArrayList<>();
        try {
            list = queryRunner.query(conn, sql, new BeanListHandler<T>(type), param);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
        return list;
    }

    // 查询表行数
    public <T> long getCount(Class<T> type, String sql, Object... params) {
        Connection conn = JDBCUtils.getConnection();
        T query = null;
        try {
            query = queryRunner.query(conn, sql, new ScalarHandler<T>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
        return (long) query;
    }

    // 批量处理：针对大量操作
    public void batchUpdate(String sql, Object[][] params) {
        Connection conn = JDBCUtils.getConnection();
        try {
            queryRunner.batch(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
    }

    public static void main(String[] args) {
        QueryRunner queryRunner = new QueryRunner();
    }
}
