package cn.w28l30.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.w28l30.dao.UploadFileDao;
import cn.w28l30.domain.UploadFile;
import cn.w28l30.utils.JdbcUtils;

public class UploadFileDaoImpl implements UploadFileDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.w28l30.dao.impl.UploadFileDao#add(cn.w28l30.domain.UploadFile)
	 */
	public void add(UploadFile file) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "insert into uploadfile(id, uuidname, filename, username, uploadtime, description, savepath) values(?,?,?,?,?,?,?)";
			Object[] params = { file.getId(), file.getUuidname(), file.getFilename(), file.getUsername(),
					file.getUploadtime(), file.getDescription(), file.getSavepath() };
			System.out.println(params);
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.w28l30.dao.impl.UploadFileDao#getAll()
	 */
	public List<UploadFile> getAll() {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "select * from uploadfile";
			return (List<UploadFile>) runner.query(JdbcUtils.getConnection(), sql, new BeanListHandler(UploadFile.class));
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.w28l30.dao.impl.UploadFileDao#delete(java.lang.String)
	 */
	public void delete(String id) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "delete from uploadfile where id=?";
			runner.update(JdbcUtils.getConnection(), sql, id);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.w28l30.dao.impl.UploadFileDao#find(java.lang.String)
	 */
	public UploadFile find(String id) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from uploadfile where id=?";
			return (UploadFile) runner.query(JdbcUtils.getConnection(),sql, id, new BeanHandler(UploadFile.class));
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.w28l30.dao.impl.UploadFileDao#update(cn.w28l30.domain.UploadFile)
	 */
	public void update(UploadFile file) {

	}
}
