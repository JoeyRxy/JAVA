package mine.learn.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import mine.learn.City;
import mine.learn.utils.JDBCUtils;

/**
 * 注意其中的反射！ Reflection!
 * <p>
 * 为防止出现类型错误，City\Country.java中的成员变量 的数据类型使用包装类（int -> Integer) 并且
 * 变量名字要使用和数据库中的名字一样的名字
 */
public class BasicDAO {
    // 1,通用的增删改

    public int update(String sql, Object... params) {
        int updateNum = 0;
        // 1.获取数据库连接
        Connection connection = JDBCUtils.getConnection();
        // 2.获得sql命令
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.length > 0) {
                // 占位符？设置参数
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            updateNum = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(null, preparedStatement, connection);
        }
        return updateNum;
    }

    // 2，查询记录 封装为对象
    public <T> T getBean(Class<T> type, String sql, @NotNull Object... params) {
        // 链接数据库
        Connection connection = JDBCUtils.getConnection();

        // 获取statement
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        T instance = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            // 设置sql占位符
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            // 执行sql
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = preparedStatement.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (resultSet != null && resultSet.next()) {
                // Object instance = constructor.newInstance();
                instance = type.getDeclaredConstructor().newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);// 得到第i列的名字
                    Object val = resultSet.getObject(i);// 得到第i列的数据
                    Field field = type.getDeclaredField(columnName);// 使用Field对类中的成员变量进行操作
                    field.setAccessible(true);
                    field.set(instance, val);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(resultSet, preparedStatement, connection);
        }
        return instance;
    }
    // 3，查询记录总条数的方法

    /**
     * 屡次打开关闭数据库会导致速度很慢
     *
     * @param type
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public <T> List<T> getBeans(@NotNull Class<T> type, String sql, @NotNull Object... params) {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<T> ans = new ArrayList<>();
        T instance = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int colNum = metaData.getColumnCount();
            String columnLabel = null;
            Object object = null;
            while (resultSet.next()) {
                instance = type.getDeclaredConstructor().newInstance();
                for (int i = 0; i < colNum; i++) {
                    columnLabel = metaData.getColumnName(i + 1);
                    object = resultSet.getObject(i + 1);
                    Field declaredField = type.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(instance, object);
                }
                ans.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(resultSet, preparedStatement, connection);
        }
        return ans;
    }

    public static void main(String[] args) {
        BasicDAO basicDAO = new BasicDAO();
        // @Language("SQL")
        // String sql = "insert into country(Code,Name) VALUES (?,?)";
        // int i = basicDAO.update(sql, "REN", "RXY");
        // System.out.println(i + " row(s) effected.");

        // 查询测试
        // @Language("SQL") String sql2 = "select Capital from country where Name= ?";
        // Country bean = basicDAO.getBean(Country.class, sql2, "China");
        // System.out.println(bean);
        @Language("SQL")
        String sql3 = "SELECT * FROM city WHERE CountryCode=? AND Population>?";
        List<City> cityList = basicDAO.getBeans(City.class, sql3, "CHN", 1000000);
        int count = 0;
        for (City c : cityList) {
            System.out.println(c);
            count++;
        }
        System.out.println(count);
    }
}
