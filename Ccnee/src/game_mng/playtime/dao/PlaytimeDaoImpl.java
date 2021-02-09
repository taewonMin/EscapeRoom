package game_mng.playtime.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.BaseDao;
import vo.MemberVO;
import vo.PlaytimeVO;

public class PlaytimeDaoImpl extends BaseDao implements PlaytimeDao{
	
	private static PlaytimeDao playtimeDao;
	private SqlMapClient smc;
	
	public PlaytimeDaoImpl() {
		smc = getSqlMapClient();
	}
	
	public static PlaytimeDao getInstance() {
		if(playtimeDao == null) {
			playtimeDao = new PlaytimeDaoImpl();
		}
		return playtimeDao;
	}

	@Override
	public List<PlaytimeVO> displayAll() throws SQLException {
		
		List<PlaytimeVO> playtimeList = new ArrayList<PlaytimeVO>();
		
		playtimeList = smc.queryForList("playtime.displayAll");
		
		return playtimeList;
	}

	@Override
	public List<String> getPlayMemId(String teamCode) throws SQLException {
		
		List<String> memIdList = new ArrayList<String>();
		
		memIdList = smc.queryForList("member.getPlayMemId", teamCode);
		
		return memIdList;
	}

	@Override
	public int insertPlaytime(PlaytimeVO pv) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.insert("playtime.insertPlaytime", pv);
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}
}
