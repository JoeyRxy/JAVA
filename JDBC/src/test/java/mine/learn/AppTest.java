package mine.learn;

// import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testConn() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunck?serverTimezone=UTC", "root",
                "73699rxy");
        System.out.println(conn);
        Statement statement = conn.createStatement();
        String sql = "INSERT INTO `sunck`.t_stu (cid, gender, id, name, score)VALUES( 1,'f',7,'it',89);";
        int i = statement.executeUpdate(sql);
        System.out.println(i);

        statement.close();
        conn.close();
    }

    @Test
    public void testQuery() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_trial?serverTimezone=UTC", "root",
                "73699rxy");
        System.out.println(conn);

        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM t_stu WHERE id=13";
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            String stu_name = rs.getString("name");
            String score = rs.getString("score");
            System.out.println("name : " + stu_name + " , score : " + score);
        }

        statement.close();
        conn.close();
    }

}
