package game_mng.inven_item.service;

import java.sql.SQLException;
import java.util.List;

import game_mng.inven_item.dao.IInvenItemDao;
import game_mng.inven_item.dao.InvenItemDaoImpl;
import vo.Inven_ItemVO;

public class InvenItemServiceImpl implements IInvenItemService {
	private static IInvenItemService iis = null;
	private IInvenItemDao iid;
	
	private InvenItemServiceImpl() {
		iid = InvenItemDaoImpl.getInstance();
	}
	
	public static IInvenItemService getInstance() {
		if(iis==null) {
			iis = new InvenItemServiceImpl();
		}
		return iis;
	}

	@Override
	public int insertInvenItem(Inven_ItemVO invenItemVO) throws SQLException{
		return iid.insertInvenItem(invenItemVO);
	}

	@Override
	public int deleteInvenItem(String itemNo) throws SQLException {
		return iid.deleteInvenItem(itemNo);
	}
	
	@Override
	public List<Inven_ItemVO> getInvenItem(String invenCode) throws SQLException {
		return iid.getInvenItem(invenCode);
	}

}
