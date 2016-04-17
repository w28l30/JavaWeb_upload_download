package cn.w28l30.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.w28l30.domain.UploadFile;
import cn.w28l30.service.impl.BusinessService;
import cn.w28l30.utils.WebUtils;

/**
 * Servlet implementation class UploadFileServlet
 */
@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/jsp/upload.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("message", "Wrong!");
			request.getRequestDispatcher("WEB-INF/jsp/message.jsp").forward(request, response);
			return;
		}

		String path = this.getServletContext().getRealPath("/WEB-INF/upload");
		File file = new File(path);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("Directory does not exist");
			file.mkdirs();
		}

		try {
			UploadFile uploadFile = WebUtils.doUpload(request, path);
			BusinessService service = new BusinessService();
			service.upload(uploadFile);
			request.setAttribute("message", "Success!");
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			request.setAttribute("message", "The size cannot excceed 500M!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Failed!");
		}
		request.getRequestDispatcher("WEB-INF/jsp/message.jsp").forward(request, response);

	}

}
