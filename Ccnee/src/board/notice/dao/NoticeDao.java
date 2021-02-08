package board.notice.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import vo.BoardVO;
import vo.PagingVO;
import vo.SearchVO;


public interface NoticeDao {
	
	/**
	 * NoticeVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param smc SqlMapClient 객체
	 * @param noticeVO DB에 insert할 자료가 저장된 NoticeVO객체
	 * @return 성공시 1, 실패시 0 리턴
	 * @throws SQLException
	 */
	public int insertNoticeBoard(BoardVO boardVO) throws SQLException;
    
    
	/**
	 * 하나의 게시판 NoticeVO정보를 업데이트하는 메서드
	 * @param smc SqlMapClient 객체
	 * @param noticeVO 업데이트할 게시판정보가 들어있는 NoticeVO객체 
	 * @return 성공시 1, 실패시 0 리턴
	 * @throws SQLException
	 */
	public int updateNoticeBoard(BoardVO boardVO) throws SQLException;
     
	
	/**
	 * 매개변수로 받은 noticeNo에 해당하는 정보를 삭제하는 메서드
	 * @param smc SqlMapClient 객체
	 * @param noticeNo
	 * @return 성공시 1, 실패시 0 리턴
	 * @throws SQLException
	 */
	public int deleteNoticeBoard(int boardNo) throws SQLException;

	/**
	 * DB의 Notice테이블의 전체정보를 List에 담아서 반환하는메서드
	 * @param smc SqlMapClient 객체
	 * @param pagingVO객체를 담고있는 List객체 
	 * @return NoticeVO객체를 담고있는 List객체
	 * @throws SQLException
	 */
	public List<BoardVO> getAllNoticeBoard(SearchVO sv) throws SQLException;
     
	
	/**
	 * 회원상세조회하는 메서드
	 * @param smc
	 * @param boardNo
	 * @return
	 * @throws SQLException
	 */
	public BoardVO getSearchNoticeBoard(int boardNo) throws SQLException;
     
	/**
	 * NOtice테이블의 전체데이터수를 가져오는 메서드
	 * @param smc
	 * @return
	 * @throws SQLException
	 */
	public int getAllCountNoticeBoard(SearchVO sv) throws SQLException;

	/**
	 * 질문게시판 조회수 증가
	 * @param boardNo
	 * @return
	 * @throws SQLException
	 */
	public int updateCntBoard(int boardNo) throws SQLException;
	
	
}
