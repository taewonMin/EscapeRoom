package board.report.service;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import board.cmt.dao.CmtDao;
import board.report.dao.IReportDao;
import board.report.dao.ReportDaoImpl;
import common.util.SqlMapClientFactory;
import vo.BoardVO;
import vo.MemberVO;
import vo.PagingVO;
import vo.SearchVO;

public class ReportServiceImpl implements IReportService {

	private IReportDao reportDao;
	private static IReportService reportService;
	private static SqlMapClient smc;
	
	private ReportServiceImpl() {
		reportDao = ReportDaoImpl.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IReportService getInstance() {
		if(reportService==null) {
			reportService = new ReportServiceImpl();
		}
		return reportService;
	}

	@Override
	public List<String> getMember(String teamCode) throws SQLException {
		return reportDao.getMember(teamCode);
	}

	@Override
	public int insertReport(BoardVO bv) throws SQLException {
		return reportDao.insertReport(bv);
	}

	@Override
	public List<BoardVO> listReport(int boardGrNo, PagingVO pagingvo) throws SQLException {
		return reportDao.listReport(boardGrNo, pagingvo);
	}

	@Override
	public int getBoardAllCount(SearchVO sv) throws SQLException {
		return reportDao.getBoardAllCount(sv);
	}

	@Override
	public BoardVO getReport(int boardNo)  throws SQLException{
		return reportDao.getReport(boardNo);
	}

	@Override
	public int deleteReport(int boardNo) throws SQLException {
		return reportDao.deleteReport(boardNo);
	}

	@Override
	public int updateReportCnt(String toMemId) throws SQLException {
		return reportDao.updateReportCnt(toMemId);
	}
}
