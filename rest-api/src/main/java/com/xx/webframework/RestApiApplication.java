package com.xx.webframework;

import com.xx.webframework.restapi.util.FileUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jca.support.ResourceAdapterFactoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

@SpringBootApplication
@MapperScan("com.xx.webframework.mapper")
public class RestApiApplication implements CommandLineRunner {

	@Autowired(required = false)
	private  JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// init db
		if(null == jdbcTemplate)
			return;
		Connection conn = jdbcTemplate.getDataSource().getConnection();
		ResultSet rs = null;
		try {
			DatabaseMetaData dbMetaData = conn.getMetaData();
			rs = dbMetaData.getTables(null, null, "product", new String[]{"TABLE"});
			if (!rs.next()) {
				System.out.println("@@@@@@@@@@  init DB  @@@@@@@@@@");
				jdbcTemplate.execute(FileUtils.readString(ResourceUtils.getFile("../doc/db.sql")));
				System.out.println("@@@@@@@@@@  init DB finish @@@@@@@@@@");
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			rs.close();
			conn.close();
		}
	}
}
