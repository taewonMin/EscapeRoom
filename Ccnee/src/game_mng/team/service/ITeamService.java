package game_mng.team.service;

import java.sql.SQLException;
import java.util.HashMap;

import vo.TeamVO;

public interface ITeamService {
	/**
	 * 팀 등록
	 * @param tv
	 * @throws SQLException
	 */
	public void insertTeam(TeamVO tv) throws SQLException;
	
	/**
	 * 팀원 등록
	 * @throws SQLException
	 */
	public void insertTeamMember(HashMap<String,Object> teamInfo) throws SQLException;
	
	/**
	 * 팀 코드 생성
	 * @throws SQLException
	 */
	public String createTeamCode() throws SQLException;
}
