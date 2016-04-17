package cn.w28l30.utils;

import cn.w28l30.dao.UploadFileDao;
import cn.w28l30.factory.DaoFactory;

public class test {
	public static void main(String[] args) {
		UploadFileDao dao = DaoFactory.getInstance().createDao(UploadFileDao.class);
	}
}
