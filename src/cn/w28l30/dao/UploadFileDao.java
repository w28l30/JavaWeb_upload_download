package cn.w28l30.dao;

import java.util.List;

import cn.w28l30.domain.UploadFile;

public interface UploadFileDao {

	void add(UploadFile file);

	List<UploadFile> getAll();

	void delete(String id);

	UploadFile find(String id);

	void update(UploadFile file);

}