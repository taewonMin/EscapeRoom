package game_mng.inven_item.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.BaseDao;
import vo.Inven_ItemVO;

public class InvenItemDaoImpl extends BaseDao implements IInvenItemDao{
	private SqlMapClient smc;
	private static IInvenItemDao iid = null;
	
	public InvenItemDaoImpl() {
		smc = getSqlMapClient();
	}
	
	public static IInvenItemDao getInstance() {
		if(iid ==null) {
			iid = new InvenItemDaoImpl();
		}
		return iid;
	}

	@Override
	public int insertInvenItem(Inven_ItemVO invenItemVO) throws SQLException {
		int cnt = 0;
		
		Object obj = smc.insert("invenItem.insertInvenItem",invenItemVO);
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public int deleteInvenItem(String itemNo) throws SQLException {
		return smc.delete("invenItem.deleteInvenItem", itemNo);
	}
	
	@Override
	public List<Inven_ItemVO> getInvenItem(String invenCode) throws SQLException {
		List<Inven_ItemVO> invenItemList = new ArrayList<Inven_ItemVO>();
		
		invenItemList = smc.queryForList("invenItem.getInvenItem",invenCode);
		
		
		return invenItemList;
	}

	
}
