package member.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import vo.BuyVO;
import vo.MemberVO;
import vo.PlaytimeVO;
import vo.SearchVO;

public interface IMemberDao {
	
	/**
	 * 회원 가입 메서드
	 * @param smc
	 * @param mv
	 * @return
	 * @throws SQLException
	 */
	int newMember(MemberVO mv) throws SQLException;
	
	/**
	 * 로그인 ID 체크 메서드
	 * @param smc
	 * @param memId
	 * @return
	 * @throws SQLException
	 */
	MemberVO getMemNo(String memId)throws SQLException;
	
	MemberVO login(Map<String, String> params);
	/**
	 * 멤버 리스트 출력
	 * @param smc
	 * @return
	 * @throws SQLException
	 */
	List<MemberVO> memberList() throws SQLException;
	
	/**
	 * 멤버의 모든 정보 가져옴
	 * @param memNo
	 * @return MemberVO
	 * @throws SQLException
	 */
	MemberVO getMember(String memId) throws SQLException;
	
	/**
	 * 멤버의 게임 기록
	 * @param memNo
	 * @return PlaytimeVO
	 * @throws SQLException
	 */
	List<PlaytimeVO> memberRanking(String memNo) throws SQLException;
	
	/**
	 * 멤버가 구매한 아이템 리스트 출력
	 * @param memNo
	 * @return List
	 * @throws SQLException
	 */
	List getMemHasItemList(String memNo) throws SQLException;
	
	/**
	 * 멤버가 가진 itemNo와 그 useStatus
	 * @param memNo
	 * @return List
	 * @throws SQLException
	 */
	List<BuyVO> buyItemStatus(String memNo) throws SQLException;
	
	/**
	 * 아이디 찾기
	 * @param mv
	 * @return
	 * @throws SQLException 
	 */
	MemberVO findNameAndEmail(MemberVO mv) throws SQLException;
	
	/**
	 * 비번찾기
	 * @param mv
	 * @return
	 * @throws SQLException
	 */
	MemberVO findIdAndTel(MemberVO mv) throws SQLException;
	
	/**
	 * 비번수정
	 * @param updateMemPss
	 * @return
	 * @throws SQLException
	 */
	int updatePass(MemberVO mv) throws SQLException;
	
	/**
	 * 회원정보 수정
	 * @param memVO
	 * @return 성공:1, 실패:0
	 * @throws SQLException
	 */
	int updateMember(MemberVO memId) throws SQLException;
	
	/**
	 * 회원 탈퇴
	 * @param memId
	 * @return 성공:1, 실패:0
	 * @throws SQLException
	 */
	int outMember(String memId) throws SQLException;
	
	/**
	 * 아이템 사용
	 * @param buyVO
	 * @return 성공:1, 실패:0
	 * @throws SQLException
	 */
	int updateItemStauts(String buyNo) throws SQLException;
	
	/**
	 * 아이템 적용 - point plus
	 * @param MemberVO
	 * @return 성공:1, 실패:0
	 * @throws SQLException
	 */
	int plusPoint(MemberVO member) throws SQLException;
	
	/**
	 * 회원가입할때 ID 중복체크
	 * @param memId
	 * @return 성공 : 1, 실패 : 0
	 * @throws SQLException
	 */
	int newIdChk(String memId) throws SQLException;
	
	/**
	 * 회원관리시 페이징해서 가져올 리스트 정보
	 * @param sv
	 * @return
	 * @throws SQLException 
	 */
	List<MemberVO> managementList(SearchVO sv) throws SQLException;
	
	/**
	 * 멤버테이블 전체 갯수 조회
	 * @param sv
	 * @return
	 */
	int getMemberAllCount(SearchVO sv) throws SQLException;
	
	/**
	 * 로그인시 비번 체크
	 * @param memPass
	 * @return
	 * @throws SQLException
	 */
	int loginPassChk(String memPass) throws SQLException;

	/**
	 * 회원관리 상세조회
	 * @param memNo
	 * @return
	 */
	MemberVO selectMember(String memNo) throws SQLException;
	
	/**
	 * 회원가입때 이메일 중복체크
	 * @param memEmail
	 * @return
	 */
	int newEmailChk(String memEmail) throws SQLException;
	
	/**
	 * 회원블랙리스트정보변경
	 * @param mv
	 */
	void updateManagment(MemberVO mv) throws SQLException;
	
	/**
	 * 회원가입때 핸드폰 중복체크
	 * @param memTel
	 * @return
	 * @throws SQLException
	 */
	int newHpChk(String memTel) throws SQLException;
	

	
}
