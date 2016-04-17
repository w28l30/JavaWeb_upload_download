package cn.w28l30.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import cn.w28l30.exception.DaoException;


public class DaoFactory {

	private static Properties daoconfig = new Properties();
	static{
		try {
			daoconfig.load(DaoFactory.class.getClassLoader().getResourceAsStream("uploadFileDao.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	private DaoFactory(){}
	private static final DaoFactory instance = new DaoFactory();
	public static DaoFactory getInstance(){
		return instance;
	}
	
	public <T> T createDao(Class<T> interfaceClass){
		String name = interfaceClass.getSimpleName();
		String daoClassname = daoconfig.getProperty(name);
		try {
			return (T) Class.forName(daoClassname).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
}
