package board.item.dao;

import java.sql.SQLException;
import java.util.List;

import org.w3c.dom.ls.LSInput;

import com.ibatis.sqlmap.client.SqlMapClient;

import vo.ItemVO;
import vo.PagingVO;


/**
 * 상점 Dao를 위한 interface
 * @author 홍다이
 *
 */
public interface ItemDao {
	
	/**
	 * 상점 - 게시판 글 쓰기
	 * @return 성공:1, 실패:0
	 * @param item
	 * @throws SQLException
	 */
	public int insertItemBoard(ItemVO item) throws SQLException;
	
	/**
	 * 상점 - 페이징해서 아이템 전체 리스트 출력
	 * @param pv
	 * @return List<ItemVO>
	 * @throws SQLException
	 */
	public List<ItemVO> displayItemBoardList(PagingVO pv) throws SQLException;
	
	/**
	 * 상점 - act=y인 아이템을 페이징해서 리스트 출력
	 * @param pv
	 * @return List<ItemVO>
	 * @throws SQLException
	 */
	public List<ItemVO> displayItemActYpagingBoardList(PagingVO pv) throws SQLException;
	
	/**
	 * 상점 - 페이징 없는 아이템 전체 리스트 출력
	 * @param item
	 * @return List<ItemVO>
	 * @throws SQLException
	 */
	public List<ItemVO> displayItemBoardList(ItemVO item) throws SQLException;
	
	/**
	 * 상점 - 아이템 명으로 게시판 검색
	 * @param itemVO
	 * @return List<ItemVO>
	 * @throws SQLException
	 */
	public List<ItemVO> searchItemBoard(ItemVO item) throws SQLException;
	
	/**
	 * 상점 - 선택된 아이템 출력
	 * @param itemNo
	 * @return ItemVO
	 * @throws SQLException
	 */
	public ItemVO getItem(int itemNo) throws SQLException;
	
	/**
	 * 상점 - item 테이블의 전체 데이터 수를 조회하는 메서드
	 * @return int
	 * @throws SQLException
	 */
	public int getItemAllCount() throws SQLException;
	
	/**
	 * 상점 - item 테이블의 act=y 인 데이터 수를 조회하는 메서드
	 * @return int
	 * @throws SQLException
	 */
	public int getItemActYcount() throws SQLException;
	
	/**
	 * 상점 - 현재 itemNo 뽑아옴
	 * @return int
	 * @throws SQLException
	 */
	public int getItemNo() throws SQLException;
	
	/**
	 * 상점- 제목으로 검색된 item 전체 개수를 조회하는 메서드
	 * @return int
	 * @throws SQLException
	 */
	public int getsearchItemAllCount(ItemVO item) throws SQLException;

	/**
	 * 상점 - 게시판 글 수정
	 * @param itemVO
	 * @return 성공:1, 실패:0
	 * @throws SQLException
	 */
	public int updateItemBoard(ItemVO item) throws SQLException;
	
	/**
	 * 상점 - 게시판 글 삭제
	 * @param itemNo
	 * @return 성공:1, 실패:0
	 * @throws SQLException
	 */
	public int deleteItemBoard(int itemNo) throws SQLException;
	
	/**
	 * 아이템 리스트 가져오기
	 * @return
	 */
	public List<ItemVO> getItemList() throws SQLException;

}
