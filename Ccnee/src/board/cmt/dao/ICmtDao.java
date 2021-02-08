package board.cmt.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import vo.CmtVO;

public interface ICmtDao {

	List<CmtVO> cmtListAll(SqlMapClient smc, int boardNo) throws SQLException;

	int insertCmt(SqlMapClient smc, CmtVO cv) throws SQLException;

	int deleteCmt(SqlMapClient smc, int commentNo) throws SQLException;

	int updateCmt(SqlMapClient smc, CmtVO cmtVO) throws SQLException;
	
}
