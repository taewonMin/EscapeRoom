package member.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.stylesheets.LinkStyle;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.SqlMapClientFactory;
import member.dao.IMemberDao;
import member.dao.MemberDaoImpl;
import vo.BuyVO;
import vo.ItemVO;
import vo.MemberVO;
import vo.PlaytimeVO;
import vo.SearchVO;

public class MemberServiceImpl implements IMemberService {

	private static IMemberService memService = null;
	private IMemberDao memDao;
	private SqlMapClient smc;
	
	private MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}

	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImpl();
		}
		return memService;
	}
	
	@Override
	public int newMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			smc.startTransaction();
			cnt = memDao.newMember(mv);
			
			smc.commitTransaction();
		}catch(SQLException e) {
			try {
				smc.endTransaction();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public MemberVO getMemNo(String memId) {
		
		MemberVO chk;
		
		try {
			chk = memDao.getMemNo(memId);
		}catch(SQLException e) {
			throw new RuntimeException("아이디 확인중 예외발생");
		}
		
		return chk;
	}

	@Override
	public List<MemberVO> memberList() {
		
		List<MemberVO> memberList = Collections.emptyList();
		
		try {
			memberList = memDao.memberList();
		}catch(SQLException e) {
			throw new RuntimeException("회원리스트 출력중 예외발생", e);
		}
		
		return memberList;
	}

	@Override
	public MemberVO getMember(String memId) {
		
		MemberVO member;
		
		try {
			member = memDao.getMember(memId);
		}catch(SQLException e) {
			throw new RuntimeException("회원 정보 조회 중 예외발생",e);
		}
		return member;
	}

	@Override
	public List<ItemVO> getMemHasItemList(String memNo) {
		List<ItemVO> itemList = Collections.emptyList();
		
		try {
			itemList = memDao.getMemHasItemList(memNo);
		}catch(SQLException e) {
			throw new RuntimeException("회원이 구매한 아이템 리스트 출력중 예외발생", e);
		}

		return itemList;
	}
	
	
	@Override
	public MemberVO findNameAndEmail(MemberVO mv) {
		MemberVO member;
		
		try {
			member = memDao.findNameAndEmail(mv);
			System.out.println("sv="+member);
		}catch(Exception e) {
			throw new RuntimeException("회원 정보 조회 중 예외발생",e);
		}
		return member;
	}
	
	@Override
	public MemberVO findIdAndTel(MemberVO mv) {
		MemberVO member;
		
		try {
			member = memDao.findIdAndTel(mv);
			System.out.println("sv="+member);
		}catch(Exception e) {
			throw new RuntimeException("회원 정보 조회 중 예외발생",e);
		}
		return member;
	}
	
	@Override
	public int updatePass(MemberVO mv) {
		int cnt = 0;
		
		try {
			cnt = memDao.updatePass(mv);
			System.out.println("sv="+cnt);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	@Override
	public int updateMember(MemberVO memId) {
		int cnt = 0;
		
		try {
			cnt = memDao.updateMember(memId);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int outMember(String memId) {
		int cnt = 0;
		
		try {
			cnt = memDao.outMember(memId);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int newIdChk(String memId) {
		
		int cnt = 0;
		
		try {
			cnt = memDao.newIdChk(memId);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> managementList(SearchVO sv) throws SQLException {
		
		List<MemberVO> managementList = Collections.emptyList();
		
		managementList = memDao.managementList(sv);
		
		return managementList;
	}

	@Override
	public int getMemberAllCount(SearchVO sv) throws SQLException {
		int cnt = 0;
		try {
			cnt = memDao.getMemberAllCount(sv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int loginPassChk(String memPass) throws SQLException {
		int cnt = 0;
		
		try {
			cnt = memDao.loginPassChk(memPass);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public MemberVO selectMember(String memNo) {
		MemberVO memberVO = new MemberVO(); 
		try {
			memberVO = memDao.selectMember(memNo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("게시판 검색중 예외발생",e);
		}
		return memberVO;
	}

	@Override
	public int newEmailChk(String memEmail) throws SQLException {
		int cnt = 0;
		
		try {
			cnt = memDao.newEmailChk(memEmail);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<PlaytimeVO> memberRanking(String memNo) {
		List<PlaytimeVO> playList = Collections.emptyList();
		try {
			playList = memDao.memberRanking(memNo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("플레이타임 조회 중 예외발생",e);
		}
		return playList;
	}

	@Override
	public void updateManagment(MemberVO mv) throws SQLException {
		memDao.updateManagment(mv);
	}

	@Override
	public int newHpChk(String memTel) throws SQLException {
		int cnt = 0;
		
		try {
			cnt = memDao.newHpChk(memTel);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<BuyVO> itemStatusCheck(String memNo) {
		List<BuyVO> buyList = Collections.emptyList();
		try {
			buyList = memDao.buyItemStatus(memNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("구매내역 일부 조회 중 예외발생",e);
		}
		return buyList;
	}

	@Override
	public int updateItemStauts(String buyNo) {
		int cnt = 0;
		
		try {
			cnt = memDao.updateItemStauts(buyNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("아이템 status 업데이트 중 예외발생",e);
		}
		return cnt;
	}

	@Override
	public int plusPoint(MemberVO member) {
		int cnt = 0;
		
		try {
			cnt = memDao.plusPoint(member);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("멤버 포인트 추가 중 예외발생",e);
		}
		return cnt;
	}

}
