package board.item.service;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import board.item.dao.BuyItemDao;
import board.item.dao.BuyItemDaoImpl;
import common.util.SqlMapClientFactory;
import vo.BuyItemVO;
import vo.BuyVO;
import vo.MemberVO;

public class BuyItemServiceImpl implements BuyItemService{
	private BuyItemDao buyItemDao;
	private SqlMapClient smc;
	private static BuyItemService buyItemService;
	
	private BuyItemServiceImpl() {
		buyItemDao = BuyItemDaoImpl.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static BuyItemService getInstance() {
		if(buyItemService == null) {
			buyItemService = new BuyItemServiceImpl();
		}
		return buyItemService;
	}
	
	@Override
	public int insertBuy(BuyVO buy) {
		
		int cnt = 0;
		try {
			smc.startTransaction();
			cnt = buyItemDao.insertBuy(buy);
			
			BuyItemVO buyItem = new BuyItemVO();
			
			buyItem.setItemNo(buy.getItemNo()); //BuyitemVO에 세팅
			int buyItemCnt = buyItemDao.insertBuyItem(buyItem);
			
		//	buyItemDao.insertBuyItem(buyItem);
			
			smc.commitTransaction();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				smc.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	@Override
	public int insertBuyItem(BuyItemVO buyItem) {
		int cnt = 0;
		try {
			smc.startTransaction();
			cnt = buyItemDao.insertBuyItem(buyItem);
			smc.commitTransaction();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				smc.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	@Override
	public int checkMemberPoint(String memId) {
		int memPoint = 0;
		
		try {
			memPoint = buyItemDao.checkMemberPoint(memId);
			
		}catch(SQLException e) {
			throw new RuntimeException("멤버 포인트 조회 중 예외발생",e);
		}
		return memPoint;
	}

	@Override
	public int updateBuy(MemberVO member) {
		int cnt = 0;
		
		try {
			cnt = buyItemDao.updateBuy(member);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

}
