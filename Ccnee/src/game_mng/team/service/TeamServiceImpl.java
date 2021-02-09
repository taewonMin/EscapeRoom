package game_mng.team.service;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import game_mng.team.dao.ITeamDao;
import game_mng.team.dao.TeamDaoImpl;
import vo.TeamVO;

public class TeamServiceImpl implements ITeamService {

	private static ITeamService teamService = null;
	private ITeamDao teamDao;
	
	private TeamServiceImpl() {
		teamDao = TeamDaoImpl.getInstance();
	}
	
	public static ITeamService getInstance() {
		if(teamService == null) {
			teamService = new TeamServiceImpl();
		}
		return teamService;
	}
	
	@Override
	public void insertTeam(TeamVO tv) throws SQLException {
		teamDao.insertTeam(tv);
	}
	
	@Override
	public void insertTeamMember(HashMap<String, Object> teamInfo) throws SQLException {
		
		String teamCode = (String) teamInfo.get("teamCode");
		List<String> memNoList = (List<String>) teamInfo.get("memNoList");
		
		// 팀 멤버 추가
		HashMap<String, String> teamMember = new HashMap<>();
		teamMember.put("teamCode", teamCode);
		for(String memNo : memNoList) {
			teamMember.put("memNo", memNo);
			
			teamDao.insertTeamMem(teamMember);
		}
	}

	@Override
	public String createTeamCode() throws SQLException {
		
		int cnt = teamDao.getAllTeamCnt()+1;
		
		// 코드 형식 만들기
		DecimalFormat df = new DecimalFormat("00000");
		String teamCode = "T" + df.format(cnt);
		
		return teamCode;
	}


}
