package common.util;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseDao {
	private SqlMapClient smc;

	protected SqlMapClient getSqlMapClient() {
		if(smc == null)
			smc = SqlMapClientFactory.getInstance();
		
		return smc;
	}
	
}
