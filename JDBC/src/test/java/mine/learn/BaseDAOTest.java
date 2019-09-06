package mine.learn;

import mine.learn.dao.BaseDAO;
import org.intellij.lang.annotations.Language;
import org.junit.Test;

import java.util.List;

public class BaseDAOTest {
    @Test
    public void test() {
        BaseDAO dao = new BaseDAO();
        @Language("SQL") String sql = "SELECT * FROM country WHERE Population > ? AND Continent = ? ORDER BY Population";
        List<Country> countryList = dao.getBeanList(Country.class, sql, 1e8, "Asia");
        for (Country country : countryList) System.out.println(country);
    }

}
