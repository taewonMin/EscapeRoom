package vo;

public class MemberVO {

	private String memNo;		//멤버코드 (PK)
	private String memId;		//회원 ID
	private String memName;		//회원이름
	private String memPass;		//회원 비밀번호
	private String memTel;		//회원전화번호
	private String memEmail;	//이메일
	private int memBir;			//생년월일
	private int memPoint;		//포인트
	private String memBlack;	//블랙여부
	private String activate;	//탈퇴여부
	private String memAlias;	//칭호
	private int memCnt;			//신고누적횟수
	
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public int getMemBir() {
		return memBir;
	}
	public void setMemBir(int memBir) {
		this.memBir = memBir;
	}
	public int getMemPoint() {
		return memPoint;
	}
	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}
	public String getMemBlack() {
		return memBlack;
	}
	public void setMemBlack(String memBlack) {
		this.memBlack = memBlack;
	}
	public String getActivate() {
		return activate;
	}
	public void setActivate(String activate) {
		this.activate = activate;
	}
	public String getMemAlias() {
		return memAlias;
	}
	public void setMemAlias(String memAlias) {
		this.memAlias = memAlias;
	}
	public int getMemCnt() {
		return memCnt;
	}
	public void setMemCnt(int memCnt) {
		this.memCnt = memCnt;
	}
	
	
	public MemberVO toMemberVO() {
		MemberVO mv= new MemberVO();
		mv.setActivate(activate);
		mv.setMemAlias(memAlias);
		mv.setMemBir(memBir);
		mv.setMemBlack(memBlack);
		mv.setMemBlack(memBlack);
		mv.setMemCnt(memCnt);
		mv.setMemEmail(memEmail);
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemNo(memNo);
		mv.setMemPass(memPass);
		mv.setMemPoint(memPoint);
		mv.setMemTel(memTel);
		return mv;
	}
	
	
	
	
}
