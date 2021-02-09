package game_mng.team.dao;

import java.sql.SQLException;
import java.util.HashMap;

import vo.TeamVO;

public interface ITeamDao {
	/**
	 * 팀 등록
	 * @param tv
	 * @throws SQLException
	 */
	public void insertTeam(TeamVO tv) throws SQLException;
	
	/**
	 * 팀원 등록
	 * @param tv
	 * @throws SQLException
	 */
	public void insertTeamMem(HashMap<String, String> teamMember) throws SQLException;
	
	
	
	/**
	 * 팀 개수 조회
	 * @throws SQLException
	 */
	public int getAllTeamCnt() throws SQLException;
}
