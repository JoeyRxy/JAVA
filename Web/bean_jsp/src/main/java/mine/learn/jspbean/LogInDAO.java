package mine.learn.jspbean;

import java.sql.*;

public class LogInDAO {

    private static Connection conn;
    private static PreparedStatement pstmt;
    private static ResultSet resultSet;

    public static boolean check(String uname, String upwd) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunck?serverTimezone=UTC", "root", "73699rxy");
        final String sql = "SELECT pwd FROM sunck.myapp_students where sname = ?";
        pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, uname);
        resultSet = pstmt.executeQuery();
        String pwd = null;
        if (resultSet.next()) {
            pwd = resultSet.getString("pwd");
        }
        assert pwd != null;

        resultSet.close();
        pstmt.close();
        conn.close();

        if (pwd.equals(upwd))
            return true;
        else
            return false;

    }
}