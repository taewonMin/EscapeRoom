package game_mng.playtime.service;

import java.sql.SQLException;
import java.util.List;

import vo.PlaytimeVO;

public interface PlaytimeService {
	
	/**
	 * 플레이기록 추가
	 * @param pv
	 * @return
	 * @throws SQLException
	 */
	public int insertPlaytime(PlaytimeVO pv) throws SQLException;
	
	/**
	 * 플레이기록 랭킹 전체를 조회하는 메서드
	 * @param playNo 
	 * @return
	 */
	public List<PlaytimeVO> displayAll();
	
	/**
	 * 파라미터에 해당하는 팀코드에 속한 멤버 ID 가져오기
	 * @param teamCode
	 * @return memId
	 */
	public List<String> getPlayMemId(String teamCode);
	

}
