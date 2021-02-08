package board.item.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.BaseDao;
import vo.ItemVO;
import vo.PagingVO;

public class ItemDaoImpl extends BaseDao implements ItemDao{
	private SqlMapClient smc;
	private static ItemDao itemDao;
	
	public ItemDaoImpl() {
		smc = super.getSqlMapClient();
	}
	
	public static ItemDao getInstance() {
		if(itemDao == null) {
			itemDao = new ItemDaoImpl();
		}
		return itemDao;
	}
	

	@Override
	public int insertItemBoard(ItemVO item) throws SQLException {
		int cnt = 0;
		
		Object obj = smc.insert("itemBoard.insertItemBoard", item);
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public int updateItemBoard(ItemVO item) throws SQLException {
		int cnt = 0;
		
		cnt = smc.update("itemBoard.updateItemBoard", item);
		
		return cnt;
	}

	@Override
	public int deleteItemBoard(int itemNo) throws SQLException {
		int cnt = 0;
		
		cnt = smc.delete("itemBoard.deleteItemBoard", itemNo);
		
		return cnt;
	}

	@Override
	public List<ItemVO> searchItemBoard(ItemVO item) throws SQLException {
		List<ItemVO> searchItemList = new ArrayList<>();
		
		searchItemList = smc.queryForList("itemBoard.searchItemBoard",item);
		
		return searchItemList;
	}
	
	@Override
	public List<ItemVO> displayItemBoardList(PagingVO pv) throws SQLException {
		List<ItemVO> itemList = new ArrayList<>();
		
		itemList = smc.queryForList("itemBoard.displayItemPagingBoardList",pv);
		
		return itemList;
	}
	
	@Override
	public List<ItemVO> displayItemActYpagingBoardList(PagingVO pv) throws SQLException {
		List<ItemVO> itemList = new ArrayList<>();
		
		itemList = smc.queryForList("itemBoard.displayItemActYpagingBoardList",pv);
		
		return itemList;
	}

	@Override
	public int getItemAllCount() throws SQLException {
		int cnt = (int) smc.queryForObject("itemBoard.getItemAllCount");
		
		return cnt;
	}
	
	@Override
	public int getItemActYcount() throws SQLException {
		int cnt = (int) smc.queryForObject("itemBoard.getItemActYcount");
		
		return cnt;
	}

	@Override
	public int getsearchItemAllCount(ItemVO item) throws SQLException {
		int cnt = (int) smc.queryForObject("itemBoard.getSearchItemAllCount", item);
		
		return cnt;
	}
	
	@Override
	public List<ItemVO> displayItemBoardList(ItemVO item) throws SQLException {
		List<ItemVO> itemList = new ArrayList<>();
		
		itemList = smc.queryForList("itemBoard.displayItemBoardList", item);
		
		return itemList;
	}
	
	@Override
	public ItemVO getItem(int itemNo) throws SQLException {
		
		Object obj = smc.queryForObject("itemBoard.getItemBoard", itemNo);
		
		return (ItemVO) obj;
	}

	@Override
	public int getItemNo() throws SQLException {
		int itemNo = (int) smc.queryForObject("itemBoard.getItemNo");
		
		return itemNo;
	}

	@Override
	public List<ItemVO> getItemList() throws SQLException {
		
		List<ItemVO> itemList = new ArrayList<>();
		
		itemList = smc.queryForList("itemBoard.getItemList");
		
		return itemList;
	}

	
}
