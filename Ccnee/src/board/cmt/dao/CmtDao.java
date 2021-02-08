package board.cmt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.BaseDao;
import vo.CmtVO;

public class CmtDao extends BaseDao implements ICmtDao{
	private SqlMapClient smc;
	
	public CmtDao() {
		smc = super.getSqlMapClient();
	}

	private static ICmtDao cmtDao;
	
	
	public static ICmtDao getInstance() {
		if(cmtDao == null) {
			cmtDao = new CmtDao();
		}
		return cmtDao;
	}
	
	@Override
	public List<CmtVO> cmtListAll(SqlMapClient smc, int boardNo) throws SQLException {
		
		List<CmtVO> commentList = new ArrayList<>();
		
		commentList = smc.queryForList("cmt.cmtListAll", boardNo);
		
		return commentList;
	}

	@Override
	public int insertCmt(SqlMapClient smc, CmtVO cv) throws SQLException {
		int cnt = 0;
		
		Object obj = smc.insert("cmt.insertCmt",cv);
		
		if(obj ==null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public int deleteCmt(SqlMapClient smc, int commentNo) throws SQLException {
		int cnt = 0;

		cnt = smc.delete("cmt.deleteCmt",commentNo);
	
		return cnt;
		
		
	}

	@Override
	public int updateCmt(SqlMapClient smc, CmtVO cmtVO) throws SQLException{
		int cnt = 0;
		
		Object obj = smc.insert("cmt.updateCmt",cmtVO);
		
		if(obj ==null) {
			cnt = 1;
		}
		
		return cnt;
	}
	
	
	
}
