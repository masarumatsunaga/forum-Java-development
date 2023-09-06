package com.sample.exec;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Executor {
	public static SqlSessionFactory initquery() throws IOException {
		String resource = "config.xml";
		InputStream stream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlsession = new SqlSessionFactoryBuilder().build(stream);
		return sqlsession;
	}
}
