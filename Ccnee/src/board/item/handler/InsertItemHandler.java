package board.item.handler;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import board.item.service.ItemService;
import board.item.service.ItemServiceImpl;
import common.handler.CommandHandler;
import common.util.FileUploadRequestWrapper;
import vo.ItemVO;

public class InsertItemHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/board/item/insertItem.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return false; 
		} else {
			return true; 
		}
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equals("GET")) { //GET방식인 경우
			return VIEW_PAGE;
			
		}else if(req.getMethod().equals("POST")) {
			
			//1. 요청 파라미터 정보 가져오기
			String itemName = req.getParameter("itemName");
			int itemPrice = Integer.parseInt(req.getParameter("itemPrice"));
			String itemContent = req.getParameter("itemContent");
			
			//2. 서비스 객체 생성하기
			ItemService itemService = ItemServiceImpl.getInstance();
			
			//3. 아이템정보 등록
			ItemVO item = new ItemVO();
			item.setItemName(itemName);
			item.setItemPrice(itemPrice);
			item.setItemContent(itemContent);
			
			//파일
			FileItem fileItem = ((FileUploadRequestWrapper)req).getFileItem("itemFile");		
			
			int cnt = itemService.insertItemBoard(item, fileItem);
			
			String msg ="";
			if(cnt > 0) {
				msg ="성공";
			} else {
				msg ="실패";
			}
			
			//4. 목록조회 화면으로 이동
			String redirectUrl = req.getContextPath()+"/views/board/item/listItem.do?msg="+URLEncoder.encode(msg,"UTF-8");
			
			return redirectUrl;
		}
		
		return null;
	}

}
