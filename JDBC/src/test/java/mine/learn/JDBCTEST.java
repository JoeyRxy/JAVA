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
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM  t_stu where id = ?");
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

    @Test
    // IMPORTANT
    public void sunckCreate() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunck?serverTimezone=UTC", "root",
                "73699rxy");
        System.out.println(conn);

        Random r = new Random(System.currentTimeMillis());

        PreparedStatement statement = conn.prepareStatement("INSERT INTO myapp_grades VALUES(?,?,?,?,?,?)");
        for (int i = 2; i < 10; i++) {
            statement.setInt(1, i);
            statement.setBoolean(6, true);
            statement.setDate(3, new Date(2000, 1 + r.nextInt(13), 1 + r.nextInt(29)));
            statement.setString(2, "Class" + i);
            statement.setInt(4, r.nextInt(50));
            statement.setInt(5, r.nextInt(100));
            statement.execute();
        }

        statement.close();

        PreparedStatement statement2 = conn.prepareStatement("INSERT INTO myapp_students VALUES(?,?,?,?,?,?,?)");
        String name;
        for (int i = 0; i < 1000; i++) {
            name = String.format("r%5d", i + 1);
            statement2.setString(2, name);// name
            statement2.setInt(1, i + 1);// id
            statement2.setBoolean(6, true);// isDelete
            statement2.setInt(7, 1 + r.nextInt(10));// sgrade_id
            statement2.setString(5, "my name is " + name);// scontent
            statement2.setBoolean(3, r.nextBoolean());// sgender
            statement2.setInt(4, r.nextInt(10) + 10);// sage
            statement2.execute();
        }
        statement2.close();

    }
}
