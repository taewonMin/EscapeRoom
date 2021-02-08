package board.qa.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import vo.BoardVO;
import vo.PagingVO;
import vo.SearchVO;

public interface IQaDao {
	
	/**
	 * 질문 게시판 글 등록
	 * @param bv
	 * @throws SQLException
	 */
	public void insertQa(BoardVO bv) throws SQLException;
	
	/**
	 * 질문게시글 수정
	 * @param bv
	 * @throws SQLException
	 */
	public void updateQa(BoardVO bv) throws SQLException;
	
	/**
	 * 질문게시글 삭제
	 * @param boardNo
	 * @return 성공하면 1, 아니면 0
	 * @throws SQLException
	 */
	public int deleteQa(int boardNo) throws SQLException;
	
	/**
	 * 질문 게시판 전체 리스트 출력
	 * @param sv
	 * @return List<BoardVO>
	 * @throws SQLException
	 */
	public List<BoardVO> getAllBoard(SearchVO sv) throws SQLException;
	
	/**
	 * 질문게시판 선택글 조회
	 * @param boardNo
	 * @return
	 * @throws SQLException
	 */
	public BoardVO getBoard(int boardNo) throws SQLException;

	/**
	 * 질문게시판 조회수 증가
	 * @param boardNo
	 * @return
	 * @throws SQLException
	 */
	public int updateCnt(int boardNo) throws SQLException;

	/**
	 * 해당 게시판의 모든 글의 수를 가져오는 메서드
	 * @param sv
	 * @return
	 * @throws SQLException
	 */
	public int getBoardAllCount(SearchVO sv) throws SQLException;
}