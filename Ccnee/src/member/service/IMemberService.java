package member.service;

import java.sql.SQLException;
import java.util.List;

import vo.BuyVO;
import vo.ItemVO;
import vo.MemberVO;
import vo.PlaytimeVO;
import vo.SearchVO;

public interface IMemberService {
	
	/**
	 * 회원가입 메서드
	 * @param mv
	 * @return
	 */
	int newMember(MemberVO mv);
	
	/**
	 * 로그인 ID 체크 메서드
	 * @param memId
	 * @return
	 */
	MemberVO getMemNo(String memId);
	
	/**
	 * 멤버 출력 리스트
	 * @return
	 */
	public List<MemberVO> memberList();
	
	/**
	 * 멤버의 모든 정보 가져옴
	 * @return MemberVO
	 */
	public MemberVO getMember(String memId);
	
	/**
	 * 멤버가 구매한 아이템 리스트 출력
	 * @param memId
	 * @return List
	 */
	public List<ItemVO> getMemHasItemList(String memNo);
	
	/**
	 * 아이템 status 확인
	 * @param memNo
	 * @return int
	 */
	public List<BuyVO> itemStatusCheck(String memNo);
	
	/**
	 * 멤버의 게임 기록
	 * @param memNo
	 * @return PlaytimeVO
	 * @throws SQLException
	 */
	public List<PlaytimeVO> memberRanking(String memNo);
	
	/**
	 * 아이디찾기시 입력한 vo에 일치하는 vo가져오기
	 * @param memName
	 * @param memEmail
	 * @return
	 */
	public MemberVO findNameAndEmail(MemberVO mv);
	
	/**
	 * 비번찾기시 입력한 vo에 일치하는 vo가져오기
	 * @param mv
	 * @return
	 */
	public MemberVO findIdAndTel(MemberVO mv);
	
	/**
	 * 비번수정
	 * @param updateMemPss
	 * @return
	 */
	int updatePass(MemberVO mv);
	
	/**
	 * 회원정보 수정
	 * @param memVO
	 * @return 성공:1, 실패:0
	 */
	public int updateMember(MemberVO memId);
	
	/**
	 * 회원 탈퇴
	 * @param memId
	 * @return 성공:1, 실패:0
	 */
	public int outMember(String memId);
	
	/**
	 * 아이템 status 변경
	 * @param memId
	 * @return 성공:1, 실패:0
	 */
	public int updateItemStauts(String buyNo);
	
	/**
	 * 아이템 적용 - point plus
	 * @param MemberVO
	 * @return 성공:1, 실패:0
	 */
	int plusPoint(MemberVO member);
	
	/**
	 * 회원가입할때 아이디 중복체크
	 * @param memId
	 * @return 성공 1:, 실패 : 0
	 */
	public int newIdChk(String memId);
	

	/**
	 * 로그인시 회원비밀번호 체크
	 * @param memPass
	 * @return
	 * @throws SQLException 
	 */
	public int loginPassChk(String memPass) throws SQLException;

	/**
	 * 회원관리시 페이징해서 가져올 리스트정보
	 * @param sv
	 * @return
	 * @throws SQLException 
	 */
	List<MemberVO> managementList(SearchVO sv) throws SQLException;
	
	/**
	 * 테이블 전체 갯수가져오기
	 * @param sv
	 * @return
	 * @throws SQLException
	 */
	public int getMemberAllCount(SearchVO sv) throws SQLException;
	
	/**
	 * 상세페이지조회
	 * @param memNo
	 * @return
	 */
	public MemberVO selectMember(String memNo);
	
	/**
	 * 이메일 중복체크
	 * @param memEmail
	 * @return
	 * @throws SQLException
	 */
	public int newEmailChk(String memEmail)throws SQLException;
	
	/**
	 * 회원블랙리스트변경
	 * @param mv
	 */
	public void updateManagment(MemberVO mv) throws SQLException;
	
	/**
	 * 핸드폰 중복체크
	 * @param memTel
	 * @return
	 * @throws SQLException
	 */
	public int newHpChk(String memTel) throws SQLException;
}
