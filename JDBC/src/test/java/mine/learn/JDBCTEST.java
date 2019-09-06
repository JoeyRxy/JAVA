package mine.learn;

import org.junit.Test;

import java.sql.*;

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
}
