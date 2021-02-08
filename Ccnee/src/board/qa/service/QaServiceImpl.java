package board.qa.service;

import java.sql.SQLException;
import java.util.List;

import board.qa.dao.IQaDao;
import board.qa.dao.QaDaoImpl;
import member.dao.IMemberDao;
import member.dao.MemberDaoImpl;
import vo.BoardVO;
import vo.MemberVO;
import vo.SearchVO;

public class QaServiceImpl implements IQaService {

	private static IQaService qaService = null;
	private IQaDao iqd;
	private IMemberDao imd;
	
	private QaServiceImpl() {
		iqd = QaDaoImpl.getInstance();
		imd = MemberDaoImpl.getInstance();
	}
	
	public static IQaService getInstance() {
		if(qaService == null) {
			qaService = new QaServiceImpl();
		}
		return qaService;
	}
	
	@Override
	public void insertQa(BoardVO bv) throws SQLException {
		iqd.insertQa(bv);
	}
	
	@Override
	public void updateQa(BoardVO bv) throws SQLException {
		iqd.updateQa(bv);
	}
	
	@Override
	public int deleteQa(int boardNo) throws SQLException {
		return iqd.deleteQa(boardNo);
	}


	@Override
	public List<BoardVO> displayAllList(SearchVO sv) throws SQLException {
		setMemNo(sv);
		return iqd.getAllBoard(sv);
	}

	@Override
	public BoardVO getBoard(int boardNo) throws SQLException {
		return iqd.getBoard(boardNo);
	}

	@Override
	public int updateCnt(int boardNo) throws SQLException {
		return iqd.updateCnt(boardNo);
	}

	@Override
	public int getBoardAllCount(SearchVO sv) throws SQLException {
		setMemNo(sv);
		return iqd.getBoardAllCount(sv);
	}

	// memId -> memNo로 변경
	private void setMemNo(SearchVO sv) throws SQLException {
		if("w".equals(sv.getCriteria())) {
			String memId = sv.getSearch();
			
			MemberVO mv = imd.getMember(memId);
			if(mv != null) {
				sv.setSearch(mv.getMemNo());
			}
		}
	}
}
