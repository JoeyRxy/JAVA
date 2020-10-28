package rxy.learn.datascience.datasicencespringboot;

import javax.activation.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataSicenceSpringBootApplicationTests {

	@Autowired
	DataSource datasource;

	@Test
	void contextLoads() {
		System.out.println(datasource.getClass());
	}

}
