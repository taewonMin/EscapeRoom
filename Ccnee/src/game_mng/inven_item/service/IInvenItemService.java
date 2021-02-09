package game_mng.inven_item.service;

import java.sql.SQLException;
import java.util.List;

import vo.Inven_ItemVO;

public interface IInvenItemService {

	/**
	 * 인벤토리에 아이템 추가하는 메서드
	 * @param invenItemVO
	 * @return
	 */
	int insertInvenItem(Inven_ItemVO invenItemVO) throws SQLException;
	
	/**
	 * 인벤토리에 아이템 삭제하는 메서드
	 * @param itemNo
	 * @return
	 */
	int deleteInvenItem(String itemNo) throws SQLException;

	/**
	 * 인벤토리 아이템 읽어오는 메서드
	 * @param invenCode
	 * @return
	 */
	List<Inven_ItemVO> getInvenItem(String invenCode) throws SQLException;

}
