package game_mng.team.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.BaseDao;
import vo.TeamVO;

public class TeamDaoImpl extends BaseDao implements ITeamDao {

	private SqlMapClient smc;
	private static ITeamDao teamDao = null;
	
	private TeamDaoImpl() {
		smc = getSqlMapClient();
	}
	
	public static ITeamDao getInstance() {
		if(teamDao == null) {
			teamDao = new TeamDaoImpl();
		}
		return teamDao;
	}
	
	@Override
	public void insertTeam(TeamVO tv) throws SQLException {
		smc.insert("team.insertTeam", tv);
	}

	@Override
	public void insertTeamMem(HashMap<String, String> teamMember) throws SQLException {
		smc.insert("team.insertTeamMem", teamMember);
	}
	
	@Override
	public int getAllTeamCnt() throws SQLException {
		return (int)smc.queryForObject("team.getAllTeamCnt");
	}


}
