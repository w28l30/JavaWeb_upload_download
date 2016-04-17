package cn.w28l30.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.w28l30.domain.UploadFile;

public class WebUtils {

	public static UploadFile doUpload(HttpServletRequest request, String path) throws FileSizeLimitExceededException {
		UploadFile bean = new UploadFile();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(1024 * 1024 * 500);
			List<FileItem> list = upload.parseRequest(request);
			
			
			
			for (FileItem item : list) {
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					BeanUtils.setProperty(bean, name, value);
				} else {
					String filename = item.getName();
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					if (filename == null || filename.trim() == "") {
						continue;
					}
					String uuidname = generateFileName(filename);
					String savepath = generateSavePath(path, filename);
					InputStream in = item.getInputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
					FileOutputStream out = new FileOutputStream(savepath + File.separator + uuidname);
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();
					
					bean.setUuidname(uuidname);
					bean.setId(UUID.randomUUID().toString());
					bean.setFilename(filename);
					bean.setSavepath(savepath);
					bean.setUploadtime(new Date());
				}
			}
			return bean;
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public static String generateFileName(String filename) {
		return UUID.randomUUID() + "_" + filename;
	}
	
	public static String generateSavePath(String path, String filename) {
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 15;
		int dir2 = (hashcode >> 4) & 15;
		String savePath = path + File.separator + dir1 + File.separator + dir2;
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return savePath;
	}
}
