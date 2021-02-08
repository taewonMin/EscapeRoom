package board.notice.service;

import java.sql.SQLException;
import java.util.List;

import vo.BoardVO;
import vo.PagingVO;
import vo.SearchVO;


public interface NoticeService {
	
	/**
	 * BoardVO에 담겨진 데이터를 DB에 insert하는 메서드
	 * @param boardVO DB에 insert할 자료가 저장된 BoardVO
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertNoticeBoard(BoardVO boardVO);
	
	/**
	 * 하나의BoardVO테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @param boardVO 객체를 담고 있는 List 객체
	 * @return
	 */
	public int updateNoticeBoard(BoardVO boardVO);
	
	/**
	 * 게시판 번호를 받아서 그 회원정보를 삭제하는 메서드
	 * @param boardNo 삭제할 게시판 번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteNoticeBoard(int boardNo) throws SQLException;
	
	/**
	 * DB의 NOTICE테이블 전체를 가져와서 List에 담아서 반환하는 메서드
	 * @param pagingVO
	 * @return BoardVO객체를 담고있는 List객체
	 */
	public List<BoardVO> getAllNoticeBoard(SearchVO sv) throws SQLException;

	/**
	 * BoardVO에 담긴 데이터를 이용하여 회원들 검색하는 메서드
	 * @param boardNo 검색할 자료가 들어있는 boardVO객체
	 * @return 검색결과를 담은 List 객체
	 */
	public BoardVO getSearchNoticeBoard(int boardNo) throws SQLException;
	
	/**
	 * NOTICE 테이블의 전체 데이터수를 조휘하는 메서드
	 * @return 테이블 전체 데이터 갯수
	 */
	public int getAllCountNoticeBoard(SearchVO sv) throws SQLException;
	
	/**
	 * 질문게시판 조회수 증가
	 * @param boardNo
	 */
	public int updateCntBoard(int boardNo) throws SQLException;
}
