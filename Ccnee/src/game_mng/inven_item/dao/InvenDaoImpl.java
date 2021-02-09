package game_mng.inven_item.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.BaseDao;
import vo.InvenVO;

public class InvenDaoImpl extends BaseDao implements IInvenDao {

	private SqlMapClient smc;
	private static IInvenDao ivd = null;
	
	private InvenDaoImpl() {
		smc = getSqlMapClient();
	}
	
	public static IInvenDao getInstance() {
		if(ivd == null) {
			ivd = new InvenDaoImpl();
		}
		return ivd;
	}
	
	@Override
	public int getAllInvenCnt() throws SQLException {
		return (int)smc.queryForObject("invenItem.getAllInvenCnt");
	}
	
	@Override
	public void insertInven(InvenVO iv) throws SQLException {
		smc.insert("invenItem.insertInven", iv);
	}

	

}
