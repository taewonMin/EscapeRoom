package board.report.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.BaseDao;
import vo.BoardVO;
import vo.MemberVO;
import vo.PagingVO;
import vo.SearchVO;

public class ReportDaoImpl extends BaseDao implements IReportDao{
	private SqlMapClient smc;
	
	public ReportDaoImpl() {
		smc = super.getSqlMapClient();
	}
	
	private static IReportDao reportDao;
	
	public static IReportDao getInstance() {
		if(reportDao == null) {
			reportDao = new ReportDaoImpl();
		}
		return reportDao;
	}

	@Override
	public List<String> getMember(String teamCode) throws SQLException{
		List<String> memberList = (List<String>) smc.queryForList("teamMem.getTeamMember",teamCode);
		return memberList;
	}

	@Override
	public int insertReport(BoardVO bv) throws SQLException {
		int cnt = 0;
		
		Object obj = smc.insert("board.insertBoard", bv);
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> listReport(int boardGrNo, PagingVO pagingvo) throws SQLException {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("boardGrNo", boardGrNo);
		map.put("firstRecNo", pagingvo.getFirstRecNo());
		map.put("lastRecNo", pagingvo.getLastRecNo());
		
		List<BoardVO> reportList = smc.queryForList("board.getAllBoard", map);
		
		return reportList;
	}

	@Override
	public int getBoardAllCount(SearchVO sv) throws SQLException {
		return (int) smc.queryForObject("board.getBoardAllCount", sv);
	}

	@Override
	public BoardVO getReport(int boardNo) throws SQLException{
		BoardVO bv = (BoardVO) smc.queryForObject("board.getBoard", boardNo);
		return bv;
	}

	@Override
	public int deleteReport(int boardNo) throws SQLException {
		int cnt = 0;
		cnt = smc.delete("board.deleteBoard", boardNo);
		return cnt;
	}

	@Override
	public int updateReportCnt(String toMemId) throws SQLException {
		int cnt = 0;
		cnt = smc.update("member.updateCnt",toMemId);
		return cnt;
	}
}
