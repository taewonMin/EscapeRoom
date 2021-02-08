package board.item.handler;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.item.service.ItemService;
import board.item.service.ItemServiceImpl;
import common.handler.CommandHandler;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.ItemVO;
import vo.MemberVO;
import vo.PagingVO;

public class SearchItemHandler implements CommandHandler {
	private static final String VIEW_PAGE = "/views/board/item/listItem.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(true);
		String memId = (String) session.getAttribute("memId");
		
		//1. 서비스 생성하기
		ItemService itemService = ItemServiceImpl.getInstance();
		IMemberService memService = MemberServiceImpl.getInstance();
		
		//2. 아이템 정보 조회
		ItemVO item = new ItemVO();
		String itemName = req.getParameter("searchItem");
		item.setItemName(itemName);

		List<ItemVO> itemSearch = itemService.searchItemBoard(item);
		req.setAttribute("itemList", itemSearch);
		
		//2-2. 아이템 파일 정보 조회
//		ItemFileVO fileItem = new ItemFileVO();
//		fileItem.setItemNo(item.getItemNo());
		
		//페이징
		int pageNo = req.getParameter("pageNo") == null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
		
		PagingVO pagingVO = new PagingVO();
		int totalCount = itemService.getsearchItemAllCount(item);
			//필수정보>>
		pagingVO.setTotalCount(totalCount);
		pagingVO.setCurrentPageNo(pageNo);
		pagingVO.setCountPerPage(4);
		pagingVO.setPageSize(4);
		
		//페이징 후 act=y인 아이템 조회
		//List<ItemFileVO> itemFileList = itemAtchFileService.getItemAtchFileActYlist(pagingVO);
		
		//req.setAttribute("fileItemList", itemFileList);
		req.setAttribute("pagingVO", pagingVO);
		
		//멤버 포인트 조회
		MemberVO member = new MemberVO();
		member = memService.getMember(memId);
		req.setAttribute("memberVO", member);
		
		return VIEW_PAGE;
	}

}
