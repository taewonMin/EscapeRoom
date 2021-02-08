package common.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.GetUploadPath;


public class SummernoteImgGetHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 파일명
				String fileName = request.getParameter("fileName");

				// 실제 저장 경로를 설정.
				String savePath = GetUploadPath.getUploadPath("summernote.img");

				// 보낼파일 설정
				String filePath = savePath + File.separator + fileName;
				File sendFile = new File(filePath);
				FileInputStream inStream = new FileInputStream(sendFile);

				ServletContext context = request.getServletContext();
				// 파일 포맷으로 MIME를 결정한다.
				String mimeType = context.getMimeType(filePath);

				// response 수정.
				response.setContentType(mimeType);
				response.setContentLength((int) sendFile.length());

				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\'%s\'", fileName);
				response.setHeader(headerKey, headerValue);

				// 파일 내보내기
				OutputStream outStream = null;
				try {
					 outStream = response.getOutputStream();
					byte[] buffer = new byte[4096];
					int bytesRead = -1;

					while ((bytesRead = inStream.read(buffer)) != -1) {
						outStream.write(buffer, 0, bytesRead);
					}
				} catch (IOException e) {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					throw e;
				} finally {
					inStream.close();
					outStream.close();
				}

				return null;
	}
}
