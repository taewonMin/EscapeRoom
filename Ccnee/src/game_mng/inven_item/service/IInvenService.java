package game_mng.inven_item.service;

import java.sql.SQLException;

import vo.InvenVO;

public interface IInvenService {
	/**
	 * 인벤토리 생성
	 * @param iv
	 * @throws SQLException
	 */
	public void insertInven(InvenVO iv) throws SQLException;
	
	/**
	 * 인벤토리 코드 생성
	 * @throws SQLException
	 */
	public String createInvenCode() throws SQLException;
}
