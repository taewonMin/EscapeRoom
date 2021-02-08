package common.handler;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.command.DeleteImgCommand;
import common.util.GetUploadPath;

public class SummernoteImgDeleteHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		DeleteImgCommand delReq = mapper.readValue(request.getReader(), DeleteImgCommand.class);

		String savePath = GetUploadPath.getUploadPath("summernote.img");
		String fileName = delReq.getFileName();

		File target = new File(savePath + File.separator + fileName);

		response.setContentType("text/plain;charset=utf-8");

		PrintWriter out = response.getWriter();

		if (!target.exists()) {
			out.print(fileName + " 이미지를 삭제할 수 없습니다.");
		} else {
			target.delete();
			out.print(fileName + " 이미지를 삭제했습니다.");
		}
		return null;
	}

}
