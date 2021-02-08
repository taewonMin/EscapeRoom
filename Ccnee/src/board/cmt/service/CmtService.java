package board.cmt.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import board.cmt.dao.CmtDao;
import board.cmt.dao.ICmtDao;
import common.util.SqlMapClientFactory;
import vo.CmtVO;


public class CmtService implements ICmtService{

	private ICmtDao cmtDao;
	private static ICmtService cmtService;
	private static SqlMapClient smc;
	
	private  CmtService() {
		cmtDao = CmtDao.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ICmtService getInstance() {
		if(cmtService==null) {
			cmtService = new CmtService();
		}
		return cmtService;
	}
	
	@Override
	public  List<CmtVO> listCmt(int boardNo) {
		List<CmtVO> commentList = Collections.emptyList();
		
		try{
			commentList = cmtDao.cmtListAll(smc, boardNo);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("댓글목록 조회중 오류발생");
		}
		
		return commentList;
	}

	@Override
	public int insertCmt(CmtVO cv) {
		int cnt = 0;
		
		try {
			cnt = cmtDao.insertCmt(smc, cv);
		} catch (SQLException e) {
			throw new RuntimeException("댓글 추가 중 오류발생");
		}
		
		return cnt;
	}

	@Override
	public int deleteCmt(int cmtNo) {
		int cnt = 0;
		
		try {
			cnt = cmtDao.deleteCmt(smc, cmtNo);
		}catch (SQLException e) {
			throw new RuntimeException("댓글 삭제중 예외 발생",e);
		}
		
		return cnt;
	}

	@Override
	public int updateCmt(CmtVO cmtVO) {

		int cnt = 0;
		
		try {
			cnt = cmtDao.updateCmt(smc, cmtVO);
		} catch (SQLException e) {
			throw new RuntimeException("댓글 추가 중 오류발생");
		}
		
		return cnt;
	}

	
	
}
