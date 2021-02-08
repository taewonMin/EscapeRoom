package board.item.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.BaseDao;
import vo.BuyItemVO;
import vo.BuyVO;
import vo.MemberVO;

public class BuyItemDaoImpl extends BaseDao implements BuyItemDao {
	private SqlMapClient smc;
	private static BuyItemDao BuyItemDao;
	
	public BuyItemDaoImpl() {
		smc = super.getSqlMapClient();
	}
	
	public static BuyItemDao getInstance() {
		if(BuyItemDao == null) {
			BuyItemDao = new BuyItemDaoImpl();
		}
		return BuyItemDao;
	}
	
	@Override
	public int insertBuy(BuyVO buy) throws SQLException {
		int cnt = 0;
		
		Object obj = smc.insert("buyItem.insertBuy", buy);
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public int insertBuyItem(BuyItemVO buyItem) throws SQLException {
		int cnt = 0;
		
		Object obj = smc.insert("buyItem.insertBuyItem", buyItem);
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public int checkMemberPoint(String memId) throws SQLException {
		int memPoint = (int) smc.queryForObject("buyItem.checkMemberPoint", memId);
		return memPoint;
	}

	@Override
	public int updateBuy(MemberVO member) throws SQLException {
		int cnt = 0;
		
		cnt = smc.update("buyItem.memPoint", member);
		
		return cnt;
	}

}
