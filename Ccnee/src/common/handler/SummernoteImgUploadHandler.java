
package common.handler;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import common.util.FileUploadRequestWrapper;
import common.util.GetUploadPath;
import common.util.MakeFileName;

public class SummernoteImgUploadHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FileUploadRequestWrapper req = (FileUploadRequestWrapper) request;

		// 파일저장 경로
		String uploadPath = GetUploadPath.getUploadPath("summernote.img");// 프로퍼티스

		// 저장경로 확인 및 새로 만들기
		File file = new File(uploadPath);
		if (!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 실패했습니다.");
		}
		
		FileItem fileItem = req.getFileItem("file");

		// uuid+구분자+파일명
		String fileName = MakeFileName.toUUIDFileName(".jpg", "");
		String filePath = uploadPath + File.separator + fileName;
		File storeFile = new File(filePath);

		// local HDD 에 저장.
		fileItem.write(storeFile);

		PrintWriter out = response.getWriter();
		/* String outStr=urlPath+"/"+fileName; */
		String outStr = request.getContextPath() + "/views/game/getImg.do?fileName=" + fileName;
		out.print(outStr);
		out.close();

		return null;
	}

}
