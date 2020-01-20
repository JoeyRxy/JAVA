package mine.learn;

import org.junit.Test;

import java.sql.*;
import java.util.Random;

public class JDBCTEST {

    @Test
    public void testQuery1() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_trial?serverTimezone=UTC",
                "root", "73699rxy");
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM  sunck.myapp_students where id = ?");
        System.out.println("plz enter id:");
        String id = "10";
        preparedStatement.setObject(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int sid = resultSet.getInt("id");
            String name = resultSet.getString(2);
            String score = resultSet.getString("score");
            System.out.println("cid : " + sid + " , name : " + name + " , score : " + score);
        }

        preparedStatement.close();
        resultSet.close();
        connection.close();
    }

    private static String randomName(int seed) {
        Random r = new Random(System.nanoTime() * seed * seed);
        StringBuilder builder = new StringBuilder(11);
        builder.append((char) (r.nextInt(27) + 65));
        for (int i = 0; i < 5; i++) {
            char c = (char) r.nextInt(127);
            while (!((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122))) {
                c = (char) r.nextInt(127);
            }
            builder.append(c);
        }
        return new String(builder);
    }

    @Test
    public void pwdGen() {
        for (int i = 0; i < 100; i++) {
            String s = JDBCTEST.randomName(i);
            System.out.println(s);
        }
    }

    @Test
    // IMPORTANT
    public void sunckCreate() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunck?serverTimezone=UTC", "root",
                "73699rxy");
        System.out.println(conn);

        PreparedStatement statement = conn.prepareStatement("UPDATE sunck.myapp_students SET sname=? WHERE id = ?");
        for (int i = 0; i < 1000; i++) {
            statement.setString(1, JDBCTEST.randomName(i));
            statement.setInt(2, 1 + i);
            statement.execute();
        }

        statement.close();

    }

    @Test
    public void testLogin() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunck?serverTimezone=UTC", "root",
                "73699rxy");

        PreparedStatement pstmt = conn.prepareStatement("select * from myapp_students where sname=?");

        pstmt.setString(1, "Yy42V3");
        ResultSet executeQuery = pstmt.executeQuery();

        while (executeQuery.next()) {
            String pwd = executeQuery.getString("pwd");
            System.out.println(pwd);
        }

    }

}
