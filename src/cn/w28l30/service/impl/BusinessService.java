package cn.w28l30.service.impl;

import java.io.File;
import java.util.List;

import cn.w28l30.dao.UploadFileDao;
import cn.w28l30.domain.UploadFile;
import cn.w28l30.factory.DaoFactory;
import cn.w28l30.utils.JdbcUtils;

public class BusinessService {
	private UploadFileDao dao = DaoFactory.getInstance().createDao(UploadFileDao.class);

	public void upload(UploadFile file) {
		dao.add(file);
	}

	public List getAllUploadFile() {
		return dao.getAll();
	}

	public void deleteFile(String id) {
		try {
			JdbcUtils.startTransaction();
			UploadFile uploadFile = findFile(id);
			dao.delete(id);
			if (uploadFile != null) {
				File file = new File(uploadFile.getSavepath() + File.separator + uploadFile.getUuidname());
				if (file.isFile() && file.exists()) {
					System.out.println(file.getAbsolutePath());
					file.delete();
				}
			}
			JdbcUtils.commitTransaction();
		} finally {
			JdbcUtils.closeTransaction();
		}
	}

	public UploadFile findFile(String id) {
		return dao.find(id);
	}

}
