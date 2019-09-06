package mine.learn;

import mine.learn.utils.JDBCUtils;

import org.intellij.lang.annotations.Language;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//数据的增删改查
public class JDBCTest2 {
    // 一个项目对应一个数据库
    // 查询一条记录作为一个对象
    private List<City> cityList = new ArrayList<>();

    @Test
    public void testSelectOne() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Connection conn = JDBCUtils.getConnection();

        String sql = "SELECT * FROM city WHERE CountryCode=? AND Population>?";
        City city;

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(1, "CHN");
            preparedStatement.setObject(2, 1000000);
            resultSet = preparedStatement.executeQuery();
            // 解析结果并封装为对象
            System.out.println("Name\t" + "Population");

            while (resultSet.next()) {
                city = new City();
                city.setCountryCode("CHN");
                city.setDistrict(resultSet.getString("District"));
                city.setID(resultSet.getInt("ID"));
                city.setName(resultSet.getString("Name"));
                city.setPopulation(resultSet.getInt("Population"));
                cityList.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(resultSet, preparedStatement, conn);
        }
        for (City var : cityList) {
            System.out.println(var);
        }
    }

    @Test
    public void testInsert() {
        Connection connection = JDBCUtils.getConnection();
    }
}
