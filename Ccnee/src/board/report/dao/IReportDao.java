package board.report.dao;

import java.sql.SQLException;
import java.util.List;

import vo.BoardVO;
import vo.PagingVO;
import vo.SearchVO;

public interface IReportDao {

	/**
	 * 신고하기위해 같은 팀이었던 멤버들의 아이디를 불러오는 메서드
	 * @param teamCode
	 * @return
	 */
	List<String> getMember(String teamCode) throws SQLException;

	/**
	 * 신고글 작성 메서드
	 * @param bv
	 * @return
	 */
	int insertReport(BoardVO bv) throws SQLException;

	/**
	 * 신고글 목록보기 메서드
	 * @param boardGrNo
	 * @return
	 */
	List<BoardVO> listReport(int boardGrNo,PagingVO pagingvo) throws SQLException;

	/**
	 * 전체 글의 수
	 * @param sv
	 * @return
	 */
	int getBoardAllCount(SearchVO sv) throws SQLException;

	/**
	 * 신고글 상세보기
	 * @param boardNo
	 * @return
	 */
	BoardVO getReport(int boardNo)  throws SQLException;

	/**
	 * 신고글 삭제하기
	 * @param boardNo
	 * @return
	 */
	int deleteReport(int boardNo) throws SQLException;

	/**
	 * 신고당한 회원 신고횟수 +1
	 * @param toMemId
	 * @return
	 * @throws SQLException
	 */
	int updateReportCnt(String toMemId) throws SQLException;
}
