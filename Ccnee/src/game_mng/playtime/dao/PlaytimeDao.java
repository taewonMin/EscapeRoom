package game_mng.playtime.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import vo.MemberVO;
import vo.PlaytimeVO;

public interface PlaytimeDao {
	
	/**
	 * 플레이기록 추가
	 * @param pv
	 * @return
	 * @throws SQLException
	 */
	public int insertPlaytime(PlaytimeVO pv) throws SQLException;
	
	/**
	 * 플레이기록 랭킹을 전체 조회하는 메서드
	 * @param smc
	 * @param playNo 
	 * @return
	 */
	public List<PlaytimeVO> displayAll() throws SQLException;
	
	/**
	 * 파라미터에 해당하는 팀코드에 속한 멤버 ID 가져오기
	 * @param teamCode
	 * @return
	 * @throws SQLException
	 */
	public List<String> getPlayMemId(String teamCode) throws SQLException;
	
}
