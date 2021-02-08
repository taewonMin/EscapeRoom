package board.notice.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import board.notice.dao.NoticeDao;
import board.notice.dao.NoticeDaoImpl;
import common.util.SqlMapClientFactory;
import vo.BoardVO;
import vo.PagingVO;
import vo.SearchVO;

public class NoticeServiceImpl implements NoticeService {
	
	private static NoticeService noticeService=null;
	
	private NoticeDao noticeDao;
//	private SqlMapClient smc;
	
	private NoticeServiceImpl() {
		noticeDao = NoticeDaoImpl.getInstance();
//		smc = SqlMapClientFactory.getInstance();
	}
	
	public static NoticeService getInstance() {
		if(noticeService == null) {
			noticeService = new NoticeServiceImpl();
		}
		
		return noticeService;
		
	}
	
	@Override
	public int insertNoticeBoard(BoardVO boardVO) {
		int cnt = 0;
		
		try {
			cnt = noticeDao.insertNoticeBoard(boardVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int updateNoticeBoard(BoardVO boardVO){
		int cnt = 0;
		try {
			cnt = noticeDao.updateNoticeBoard(boardVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int deleteNoticeBoard(int boardNo) {
		int cnt = 0;
		try {
			cnt = noticeDao.deleteNoticeBoard(boardNo);
		} catch (SQLException e) {
			throw new RuntimeException("회원삭제중 예외발생");
		}
		return cnt; 
	}
	
	@Override
	public List<BoardVO> getAllNoticeBoard(SearchVO sv) {
		List<BoardVO> noticeBoardList = Collections.emptyList();
		try {
			noticeBoardList = noticeDao.getAllNoticeBoard(sv);
		} catch (SQLException e) {
			throw new RuntimeException("전체 게시판 조회중 예외발생",e);
		}
		return noticeBoardList;
		
	}
	
	@Override
	public BoardVO getSearchNoticeBoard (int boardNO) {
		BoardVO boardVO= new BoardVO();
		try {
			boardVO = noticeDao.getSearchNoticeBoard(boardNO);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("게시판 검색중 예외발생",e);
		}
		return boardVO;
		
	}
	
	
	@Override
	public int getAllCountNoticeBoard(SearchVO sv) {
		int cnt = 0;
		try {
			cnt = noticeDao.getAllCountNoticeBoard(sv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	@Override
	public int updateCntBoard(int boardNo) throws SQLException {
		return noticeDao.updateCntBoard(boardNo);
	}
	
	
	
}
