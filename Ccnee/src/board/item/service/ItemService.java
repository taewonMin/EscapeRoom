package board.item.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import vo.ItemVO;
import vo.PagingVO;

public interface ItemService {
	
	/**
	 * 상점 - 게시판 글 쓰기
	 * @return 성공:1, 실패:0
	 * @param item
	 */
	public int insertItemBoard(ItemVO item, FileItem fileItem);
	
	/**
	 * 상점 - 페이징해서 아이템 리스트 출력
	 * @param pv : 페이징 처리 위한 매개변수
	 * @return List<ItemVO>
	 */
	public List<ItemVO> displayItemBoardList(PagingVO pv);
	
	/**
	 * 상점 - 페이징해서 아이템 리스트 출력
	 * @param pv : 페이징 처리 위한 매개변수
	 * @return List<ItemVO>
	 */
	public List<ItemVO> displayItemActYpagingBoardList(PagingVO pv);
	
	/**
	 * 상점 - 아이템 리스트 출력
	 * @param item
	 * @return
	 */
	public List<ItemVO> displayItemBoardList(ItemVO item);
	
	/**
	 * 상점 - 아이템 명으로 게시판 검색
	 * @param itemVO
	 * @return List<ItemVO>
	 */
	public List<ItemVO> searchItemBoard(ItemVO item);
	
	/**
	 * 상점 - 선택된 아이템 출력
	 * @param itemNo
	 * @return ItemVO
	 */
	public ItemVO getItem(int itemNo);
	
	/**
	 * 상점 - 검색된 아이템 전체 개수
	 * @param item
	 * @return int
	 */
	public int getsearchItemAllCount(ItemVO item);
	
	/**
	 * 상점 - 아이템 전체 개수 가져오기
	 * @return int
	 */
	public int getItemAllCount();
	
	/**
	 * 상점 - item 테이블의 act=y 인 데이터 수 조회
	 * @return int
	 */
	public int getItemActYcount();
	
	/**
	 * 상점 - 게시판 글 수정
	 * @param itemVO
	 * @return 성공:1, 실패:0
	 */
	public int updateItemBoard(ItemVO item, FileItem fileItem);
	
	/**
	 * 상점 - 게시판 글 삭제
	 * @param itemNo
	 * @return 성공:1, 실패:0
	 */
	public int deleteItemBoard(int itemNo);
	
	/**
	 * 아이템 정보 가져오기
	 * @return
	 */
	public List<ItemVO> getItemList();
	
}
