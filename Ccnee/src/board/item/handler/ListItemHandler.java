package board.item.handler;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.Cookie;
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

public class ListItemHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/board/item/listItem.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//세션
		HttpSession session = req.getSession(true);
		String memId = (String) session.getAttribute("memId");
		
		//1. 서비스 생성하기
		ItemService itemService = ItemServiceImpl.getInstance();
		IMemberService memService = MemberServiceImpl.getInstance();
		
		// 요청 페이지 번호
		int pageNo = req.getParameter("pageNo") == null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
		
		//2. 페이징 객체 생성 >> 페이징 후 null안뜨고 검색 가능
		
		PagingVO pagingVO = new PagingVO();
		int totalCount = 0;
		List<ItemVO> itemList = null;
		//List<ItemFileVO> fileItemList = null;
		
		if(memId != null && memId.equals("TEST")) { //관리자용 페이징
			totalCount = itemService.getItemAllCount();
			pagingVO.setTotalCount(totalCount);
			pagingVO.setCurrentPageNo(pageNo);
			pagingVO.setCountPerPage(4);
			pagingVO.setPageSize(4);
			
			itemList = itemService.displayItemBoardList(pagingVO);
		//	fileItemList = itemAtchFileService.getItemAtchFileList(pagingVO); //파일
		
			
		} else { //일반 유저용 페이징
			totalCount = itemService.getItemActYcount();
			pagingVO.setTotalCount(totalCount);
			pagingVO.setCurrentPageNo(pageNo);
			pagingVO.setCountPerPage(4);
			pagingVO.setPageSize(4);
			
			itemList = itemService.displayItemActYpagingBoardList(pagingVO);
		//	fileItemList = itemAtchFileService.getItemAtchFileActYlist(pagingVO); //파일
			
		}
		
		req.setAttribute("itemList", itemList);
		//req.setAttribute("fileItemList", fileItemList);
		req.setAttribute("pagingVO", pagingVO);
		
		//멤버 포인트 조회
		MemberVO member = new MemberVO();
		member = memService.getMember(memId);
		req.setAttribute("memberVO", member);
		
		return VIEW_PAGE;
	}

}
