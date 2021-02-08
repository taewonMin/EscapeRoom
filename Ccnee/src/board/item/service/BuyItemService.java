package board.item.service;

import java.sql.SQLException;

import vo.BuyItemVO;
import vo.BuyVO;
import vo.MemberVO;

public interface BuyItemService {
	/**
	 * 아이템 구매시 BUY테이블에 INSERT
	 * @param BuyVO
	 * @return 성공:1, 실패:0
	 */
	public int insertBuy(BuyVO buy);
	
	/**
	 * 아이템 구매시 BUY_ITEM테이블에 INSERT
	 * @param buyItemVO
	 * @return 성공:1, 실패:0
	 */
	public int insertBuyItem(BuyItemVO buyItem);
	
	/**
	 * 아이템 구매시 MEMBER 테이블에서 해당 멤버의 현재 MEM_POINT 체크
	 * @param memNo
	 * @return member의 현재 포인트
	 */
	public int checkMemberPoint(String memId);
	
	/**
	 * 아이템 구매시 MEMBER테이블의 MEM_POINT UPDATE
	 * @param member
	 * @return 성공:1, 실패:0
	 */
	public int updateBuy(MemberVO member);
}
