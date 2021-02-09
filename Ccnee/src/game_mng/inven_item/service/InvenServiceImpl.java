package game_mng.inven_item.service;

import java.sql.SQLException;
import java.text.DecimalFormat;

import game_mng.inven_item.dao.IInvenDao;
import game_mng.inven_item.dao.InvenDaoImpl;
import vo.InvenVO;

public class InvenServiceImpl implements IInvenService {

	private static IInvenService iis = null;
	private IInvenDao iid;
	
	private InvenServiceImpl() {
		iid = InvenDaoImpl.getInstance();
	}
	
	public static IInvenService getInstance() {
		if(iis == null) {
			iis = new InvenServiceImpl();
		}
		return iis;
	}
	
	@Override
	public void insertInven(InvenVO iv) throws SQLException {
		iid.insertInven(iv);
	}

	@Override
	public String createInvenCode() throws SQLException {
		
		int cnt = iid.getAllInvenCnt()+1;
		
		// 코드 형식 만들기
		DecimalFormat df = new DecimalFormat("00000");
		String invenCode = "I" + df.format(cnt);
		
		return invenCode;
	}

	

}
