package game_mng.playtime.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.SqlMapClientFactory;
import game_mng.playtime.dao.PlaytimeDao;
import game_mng.playtime.dao.PlaytimeDaoImpl;
import vo.PlaytimeVO;

public class PlaytimeServiceImpl implements PlaytimeService{
	
	private static PlaytimeService playtimeService;
	private PlaytimeDao playtimeDao;
	
	public PlaytimeServiceImpl() {
		playtimeDao = PlaytimeDaoImpl.getInstance();
	}
	
	public static PlaytimeService getInstance() {
		if(playtimeService == null) {
			playtimeService = new PlaytimeServiceImpl();
		}
		return playtimeService;
	}
	
	@Override
	public int insertPlaytime(PlaytimeVO pv) throws SQLException {
		return playtimeDao.insertPlaytime(pv);
	}

	@Override
	public List<PlaytimeVO> displayAll() {
		
		List<PlaytimeVO> playtimeList = Collections.emptyList();
		
		try {
			playtimeList = playtimeDao.displayAll();
		}catch(SQLException e) {
			throw new RuntimeException("플레이기록 조회중 예외 발생", e);
		}
		
		return playtimeList;
	}

	@Override
	public List<String> getPlayMemId(String teamCode) {
		
		List<String> memIdList = Collections.emptyList();
		
		System.out.println("playtimeDao :" + memIdList.size());
		
		try {
			memIdList = playtimeDao.getPlayMemId(teamCode);
		}catch(SQLException e) {
			throw new RuntimeException("팀코드에 속한 멤버 ID 가져오는중 예외 발생", e);
		}
		
		return memIdList;
	}
}
