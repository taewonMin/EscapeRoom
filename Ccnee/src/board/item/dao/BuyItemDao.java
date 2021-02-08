package board.item.dao;

import java.sql.SQLException;

import vo.BuyItemVO;
import vo.BuyVO;
import vo.MemberVO;

public interface BuyItemDao {
	/**
	 * 아이템 구매시 BUY테이블에 INSERT
	 * @param BuyVO
	 * @return 성공:1, 실패:0
	 * @throws SQLException
	 */
	public int insertBuy(BuyVO buy) throws SQLException;
	
	/**
	 * 아이템 구매시 BUY_ITEM테이블에 INSERT
	 * @param buyItemVO
	 * @return 성공:1, 실패:0
	 * @throws SQLException
	 */
	public int insertBuyItem(BuyItemVO buyItem) throws SQLException;
	
	/**
	 * 아이템 구매시 MEMBER 테이블에서 해당 멤버의 현재 MEM_POINT 체크
	 * @param memNo
	 * @return int, member의 현재 포인트
	 * @throws SQLException
	 */
	public int checkMemberPoint(String memId) throws SQLException;
	
	/**
	 * 아이템 구매시 MEMBER테이블의 MEM_POINT UPDATE
	 * @param member
	 * @return 성공:1, 실패:0
	 * @throws SQLException
	 */
	public int updateBuy(MemberVO member) throws SQLException;
	
}
