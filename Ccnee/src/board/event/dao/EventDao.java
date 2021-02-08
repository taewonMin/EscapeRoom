package board.event.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import vo.BoardFileVO;
import vo.BoardVO;
import vo.PagingVO;

public interface EventDao {
	
	/**
	 * 
	 * @param smc
	 * @param eventSeq
	 * @return DB에서 다음 게시물번호 받아오기
	 * @throws SQLException
	 */
	public int getEventSeq(SqlMapClient smc) throws SQLException;
	
	/**
	 * 
	 * @param smc
	 * @return DB에서 Board와 Board_file board_no 가져오기
	 * @throws SQLException
	 */
	public int getBoardNo(SqlMapClient smc) throws SQLException;
	
	/**
	 * BoardVO에 담겨진 데이터를 DB에 Insert하는 메서드
	 * @param SqlMapClient smc 객체
	 * @param bv DB에 insert할 자료가 저장된 BoardVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다.
	 * @throws SQLException
	 */
	public int insertEventBoard(SqlMapClient smc, BoardVO bv) throws SQLException;
	
	/**
	 * BoardFileVO에 담겨진 데이터를 DB에 Insert하는 메서드
	 * @param SqlMapClient smc 객체
	 * @param bfv DB에 insert할 자료가 저장된 BoardFileVO 객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다.
	 * @throws SQLException
	 */
	public int insertEventFile(SqlMapClient smc, BoardFileVO bfv) throws SQLException;
	/**
	 * 삽입되어진 게시물번호를 체크하여 번호체크
	 * @param SqlMapClient smc 객체
	 * @param boardNo 방금 삽입한 게시판 번호 
	 * @return 제목이 있으면 true, 없으면 false
	 * @throws SQLException
	 */
//	public boolean getBoardNo (SqlMapClient smc, int boardNo) throws SQLException;
	/**
	 * DB의 board테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @param BoardVO객체를 List 형식으로 담고있다.
	 * @param PagingVO 객체를 List 형식으로 담고있다.
	 * @return BoardVO 객체를 담고 있는 List 객체
	 * @throws SQLException
	 */
	public List<BoardVO> displayAllEvent(SqlMapClient smc, PagingVO pagingVO)throws SQLException;
	/**
	 * 하나의 MemberVO객체 데이터를 이용하여 DB를 업데이트 하는 메서드
	 * @param bv update할 회원정보가 들어있는 BoardVO객체
	 * @param SqlMapClient smc 객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 * @throws SQLException
	 */
	public int updateEvent (SqlMapClient smc, BoardVO boardVO) throws SQLException;
	/**
	 * 게시판번호를 매개변수로 받아서 해당 게시판을 삭제하는 메서드
	 * @param SqlMapClient smc 객체
	 * @param boardNo 삭제할 게시판 번호
	 * @return 작업성공: 1, 작업실패 : 0
	 * @throws SQLException;
	 */
	public int deleteEvent(SqlMapClient smc, int boardNo) throws SQLException;
	
	/**
	 * 
	 * BoardVO에 담긴 데이터를 이용하여 자료를 검색하는 메서드
	 * @param SqlMapClient smc 객체
	 * @param bv 검색할 자료가 들어있는 BoardVO 객체
	 * @return 검색결과를 담은 List객체
	 * @throws SQLException
	 */
	public int getSearchEvent(SqlMapClient smc, BoardVO bv) throws SQLException;
	
	/**
	 * event 테이블의 전체 데이터 수를 조회하는 메서드
	 * @param SqlMapClient smc 객체
	 * @return 
	 * @throws SQLException
	 */
	public int getEventAllCount(SqlMapClient smc) throws SQLException;
	
	/**
	 * 이벤트 게시판 조회수 증가
	 * @param SqlMapClient smc 객체
	 * @param boardNo
	 * @return
	 * @throws SQLException
	 */
	public int cntEventBoard(SqlMapClient smc, int boardNo) throws SQLException;
	
	/**
	 * 사용자가 클릭한 게시판 조회
	 * @param smc
	 * @param boardNo
	 * @return
	 */
	public BoardVO eventSearch(SqlMapClient smc, int boardNo) throws SQLException;
	
	/**
	 * 메인에 뿌릴 이벤트 게시판 전체 조회
	 * @return
	 * @throws SQLException
	 */
	public List<BoardVO> getEventList() throws SQLException;
}
