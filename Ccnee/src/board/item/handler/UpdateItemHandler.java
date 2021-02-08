package board.item.handler;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import board.item.service.ItemService;
import board.item.service.ItemServiceImpl;
import common.file.service.AtchFileService;
import common.file.service.AtchFileServiceImpl;
import common.handler.CommandHandler;
import common.util.FileUploadRequestWrapper;
import vo.ItemVO;

public class UpdateItemHandler implements CommandHandler {
	private static final String VIEW_PAGE = "/views/board/item/updateItemNew.jsp";
	
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
		ItemService itemService = ItemServiceImpl.getInstance();
		
		if(req.getMethod().equals("GET")) {
			int itemNo = Integer.parseInt(req.getParameter("itemNo"));
			
			//1. 아이템 정보 조회
			ItemVO item = new ItemVO();
			item.setItemNo(itemNo);
			
			List<ItemVO> itemList = itemService.displayItemBoardList(item);
			
			//2. request객체에 아이템 정보 저장
			req.setAttribute("itemVO", itemList.get(0));
			
			return VIEW_PAGE;
		} else if(req.getMethod().equals("POST")){
			
			//1. 요청 파라미터 정보 가져오기
			int itemNo = Integer.parseInt(req.getParameter("itemNo"));
			String itemName = req.getParameter("itemName");
			String itemContent = req.getParameter("itemContent");
			int itemPrice = Integer.parseInt(req.getParameter("itemPrice"));
			String itemActivate = req.getParameter("itemActivate");
			
			//2. 아이템 정보 수정
			ItemVO item = new ItemVO();
			item.setItemNo(itemNo);
			item.setItemName(itemName);
			item.setItemContent(itemContent);
			item.setItemPrice(itemPrice);
			item.setItemActivate(itemActivate);
			
			FileItem fileItem = ((FileUploadRequestWrapper)req).getFileItem("itemFile");
			
			int cnt = itemService.updateItemBoard(item, fileItem);
			
			String msg = "";
			if(cnt > 0) {
				msg = "성공";
			} else {
				msg = "실패";
			}
			
			//목록 조회화면으로 리다이렉트
			String redirectUrl = req.getContextPath() + "/views/board/item/listItem.do?msg="+ URLEncoder.encode(msg,"UTF-8");
			
			return redirectUrl;
		}
		return null;
	}

}
