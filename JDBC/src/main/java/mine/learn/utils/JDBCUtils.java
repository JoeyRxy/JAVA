package mine.learn.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtils {
    // 将配置参数提取到配置文件，便于修改

    private static String driverClassName;
    private static String username;
    private static String password;
    private static String url;

    private InputStream inputStream;

    public JDBCUtils() {
        inputStream = getClass().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        assert inputStream != null;
        try {
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // static {

    // // 加载配置文件
    // try {
    // /*
    // * // InputStream resourceAsStream = //
    // * JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"); //
    // * Properties info = new Properties(); // assert resourceAsStream != null; //
    // * info.load(resourceAsStream); // driverClassName =
    // * info.getProperty("mysql.driverClassName"); driverClassName =
    // * "com.mysql.cj.jdbc.Driver"; // username = info.getProperty("mysql.user");
    // * username = "root"; // password = info.getProperty("mysql.password");
    // password
    // * = "73699rxy"; // url = info.getProperty("mysql.url"); url =
    // * "jdbc:mysql://localhost:3306/world?serverTimezone=UTC";
    // *
    // * Class.forName(driverClassName);
    // */

    // // Map<String, String> properties = new HashMap<>();
    // // properties.put("DriverClassName", "com.mysql.cj.jdbc.Driver");
    // // properties.put("password", "73699rxy");
    // // properties.put("username", "root");
    // // properties.put("url",
    // // "jdbc:mysql://localhost:3306/world?serverTimezone=UTC");
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    public static Connection getConnection() {
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     *
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 数据库连接池
    private static DataSource dataSource;
    // 数据库连接池

    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection.getClientInfo());
        JDBCUtils.closeConnection(null, null, connection);
    }
}
