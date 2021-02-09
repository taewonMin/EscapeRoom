package member.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.BaseDao;
import vo.BuyVO;
import vo.ItemVO;
import vo.MemberVO;
import vo.PlaytimeVO;
import vo.SearchVO;

public class MemberDaoImpl extends BaseDao implements IMemberDao {

	private static IMemberDao memDao = null;
	private SqlMapClient smc;
	
	private MemberDaoImpl() {
		smc = getSqlMapClient();
	}
	
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}
	
	@Override
	public int newMember(MemberVO mv) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.insert("member.insertMember", mv);
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public MemberVO getMemNo(String memId) throws SQLException {
		
		MemberVO chk = (MemberVO)smc.queryForObject("member.getMember", memId);
		
		
		return chk;
	}

	@Override
	public List<MemberVO> memberList() throws SQLException {
		
		List<MemberVO> memberList = new ArrayList<>();
		
		memberList = smc.queryForList("member.memberList");
		
		return memberList;
	}

	@Override
	public MemberVO login(Map<String, String> params) {
		
		return null;
	}

	@Override
	public MemberVO getMember(String memId) throws SQLException {
		
		MemberVO member = (MemberVO) smc.queryForObject("member.getMember",memId);
		
		return member;
	}
	
	@Override
	public List<ItemVO> getMemHasItemList(String memNo) throws SQLException {
		List<ItemVO> itemList = smc.queryForList("member.getMemHasItemList", memNo);
		return itemList;
	}
	
	@Override
	public MemberVO findNameAndEmail(MemberVO mv) throws SQLException {
		MemberVO find = (MemberVO) smc.queryForObject("member.findNameAndEmail",mv);
		System.out.println("Dao값은"+find);

		return find;
	}
	
	@Override
	public MemberVO findIdAndTel(MemberVO mv) throws SQLException {
		MemberVO member = (MemberVO) smc.queryForObject("member.findIdAndTel",mv);
		System.out.println("Dao값은"+member);
		
		return member;
	}
	
	@Override
	public int updatePass(MemberVO mv) throws SQLException {
		int cnt = smc.update("member.updatePass", mv);
		System.out.println("Dao="+cnt);
		return cnt;
	}
	
	@Override
	public int updateMember(MemberVO memId) throws SQLException {
		int cnt = smc.update("member.updateMember", memId);
		
		return cnt;
	}

	@Override
	public int outMember(String memId) throws SQLException {
		int cnt = smc.update("member.outMember", memId);
		
		return cnt;
	}

	@Override
	public int newIdChk(String memId) throws SQLException {
		int cnt = (int)smc.queryForObject("member.newIdChk", memId);
		
		return cnt;
	}

	@Override
	public List<MemberVO> managementList(SearchVO sv) throws SQLException  {

		List<MemberVO> managementList = new ArrayList<>();
		
		managementList = smc.queryForList("member.memberManagementList", sv);
		
		return managementList;
	}

	@Override
	public int getMemberAllCount(SearchVO sv) throws SQLException {
		int cnt = (int)smc.queryForObject("member.getMemberAllCount", sv);
		
		return cnt;
	}

	@Override
	public int loginPassChk(String memPass) throws SQLException {
		
		int cnt = (int)smc.queryForObject("member.loginPassChk", memPass);
		
		return cnt;
	}

	@Override
	public MemberVO selectMember(String memNo) throws SQLException {
		MemberVO memberVO = (MemberVO) smc.queryForObject("member.selectMember", memNo);
		System.out.println("다오vo="+memberVO);
		return memberVO;
	}

	@Override
	public int newEmailChk(String memEmail) throws SQLException {
		int cnt = (int)smc.queryForObject("member.newEmailChk", memEmail);
		
		return cnt;
	}

	@Override
	public List<PlaytimeVO> memberRanking(String memNo) throws SQLException {
		List<PlaytimeVO> playList = new ArrayList<>();
		
		playList = smc.queryForList("member.memberRanking", memNo);
		
		return playList;
	}

	@Override
	public void updateManagment(MemberVO mv) throws SQLException {
		smc.update("member.updateManagment",mv);
	}

	@Override
	public int newHpChk(String memTel) throws SQLException {
		int cnt = (int)smc.queryForObject("member.newHpChk", memTel);
		
		return cnt;
	}

	@Override
	public List<BuyVO> buyItemStatus(String memNo) throws SQLException {
		List<BuyVO> buyItemStatusList = new ArrayList<>();
		
		buyItemStatusList = smc.queryForList("member.buyItemStatus", memNo);
		
		return buyItemStatusList;
	}

	@Override
	public int updateItemStauts(String buyNo) throws SQLException {
		int cnt = (int)smc.update("member.updateItemStauts", buyNo);
		
		return cnt;
	}

	@Override
	public int plusPoint(MemberVO member) throws SQLException {
		int cnt = (int)smc.update("member.plusPoint", member);
		
		return cnt;
	}

	
}
