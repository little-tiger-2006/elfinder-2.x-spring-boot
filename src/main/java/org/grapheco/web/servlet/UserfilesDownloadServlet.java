package org.grapheco.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.util.UriUtils;

import java.io.*;

/**
 * To view the uploaded picture using a simple URL, 
 * hope to reduce the complexity of reciting Hash code
 * 
 * @author little-tiger
 * @version 2020-05-05
 */
@WebServlet(urlPatterns = "/userfiles/*")
public class UserfilesDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void fileOutputStream(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String USERFILES_BASE_URL = "userfiles";
		String userfilesBaseDir = ElfinderBeansConfiguration.userfilesBaseDir();
		
		String filepath = req.getRequestURI();
		String regpath = USERFILES_BASE_URL;
		int index = filepath.indexOf(regpath);
		if (index >= 0) {
			filepath = filepath.substring(index + regpath.length());
		}
		try {
			filepath = UriUtils.decode(filepath, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			resp.getWriter().write(String.format("Explain file path failure，URL:%s (%s)", filepath, e1.getMessage()));
		}

		File file = new File(userfilesBaseDir + File.separator + filepath);
		
		try {
			FileCopyUtils.copy(new FileInputStream(file), resp.getOutputStream());
			resp.setHeader("Content-Type", "application/octet-stream");
			return;
		} catch (FileNotFoundException e) {
			FileNotFoundException e2 = new FileNotFoundException("The requested file does not exist.");
			req.setAttribute("exception", e2);
			resp.setContentType("text/plain; charset=utf-8"); 
			resp.getWriter().write(e2.getMessage());
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		fileOutputStream(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		fileOutputStream(req, resp);
	}
}
