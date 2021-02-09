package game_mng.inven_item.dao;

import java.sql.SQLException;

import vo.InvenVO;

public interface IInvenDao {
	/**
	 * 인벤토리 기본키 생성
	 * @param 최대값 리턴
	 * @throws SQLException
	 */
	public int getAllInvenCnt() throws SQLException;
	
	/**
	 * 인벤토리 생성
	 * @param iv
	 * @throws SQLException
	 */
	public void insertInven(InvenVO iv) throws SQLException;
}
