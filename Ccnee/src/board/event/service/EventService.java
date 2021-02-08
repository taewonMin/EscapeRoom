package board.event.service;

import java.sql.SQLException;
import java.util.List;

import vo.BoardVO;
import vo.PagingVO;

public interface EventService {
	
	/**
	 * 게시물번호 다음 시퀀스 번호 가져오기
	 * @param eventSeq
	 * @return
	 */
	public int getEventSeq();
	
	/**
	 * boardNo 가져오기
	 * @return
	 */
	public int getBoardNo();
	
	/**
	 * BoardVO에 담겨진 데이터를 DB에 Insert하는 메서드
	 * @param bv DB에 insert할 자료가 저장된 BoardVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int insertEvent(BoardVO bv);
	/**
	 * 주어진 게시물의 제목을 체크하여 존재하는지 여부를 알아내는 메서드
	 * @param boardTitle 검색할 게시판 제목
	 * @return 제목이 있으면 true, 없으면 false
	 */
//	public boolean getBoardNo (int boardNo);
	/**
	 * DB의 board테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return PagingVO 객체를 담고 있는 List 객체
	 */
	public List<BoardVO> displayAllEvent(PagingVO pagingVO);
	/**
	 * 하나의 MemberVO객체 데이터를 이용하여 DB를 업데이트 하는 메서드
	 * @param mv update할 회원정보가 들어있는 BoardVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateEvent (BoardVO boardVO);
	/**
	 * 게시판번호를 매개변수로 받아서 해당 게시판을 삭제하는 메서드
	 * @param boardNo 삭제할 게시판 번호
	 * @return 작업성공: 1, 작업실패 : 0
	 */
	public int deleteEvent(int boardNo);
	
	/**
	 * BoardVO에 담긴 데이터를 이용하여 자료를 검색하는 메서드
	 * @param bv 검색할 자료가 들어있는 BoardVO 객체
	 * @return 검색결과를 담은 List객체
	 */
	public int getSearchEvnet(BoardVO bv);
	
	/**
	 * event 테이블의 전체 데이터 수를 조회하는 메서드
	 * @return
	 */
	public int getEventAllCount();
	
	/**
	 * 이벤트 게시판 조회수 증가
	 * @param boardNo
	 * @return
	 * @throws SQLException
	 */
	public int cntEventBoard(int boardNo) throws SQLException;
	
	/**
	 * boardNo를 통해 해당 테이블 내용 조회
	 * @param boardNo
	 * @return
	 */
	public BoardVO eventSearch(int boardNo);
	
	/**
	 * 메인에 이벤트 게시판 뿌릴 전체 게시판 조회
	 * @return
	 */
	public List<BoardVO> getEventList();
	
}
