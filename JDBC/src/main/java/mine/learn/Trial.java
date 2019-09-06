package mine.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Trial
 */
public class Trial {

    public void testConn() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_trial?serverTimezone=UTC", "root",
                "73699rxy");
        System.out.println(conn);

        Statement statement = conn.createStatement();
        String sql = "INSERT INTO `db_trial`.t_stu (cid, gender, id, name, score)VALUES( 1,'f',3,'shiiiiit',99);";
        int i = statement.executeUpdate(sql);
        System.out.println(i);
        conn.close();
    }

    public static void main(String[] args) {
        Trial trial = new Trial();
        try {
            trial.testConn();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}