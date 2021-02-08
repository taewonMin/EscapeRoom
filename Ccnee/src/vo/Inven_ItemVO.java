package vo;

public class Inven_ItemVO {

	private String invenItemNo;		//아이템인벤 넘버(PK)
	private String invenCode;			//인벤코드(FK)
	private String gameNo;				//게임넘버(FK)
	private String invenItemInfo;		//아이템이름
	private String invenItemSrc;		//아이템 파일이름
	private String sessionData;			//세션에 저장될 데이터(key=value 형태)
	
	public String getSessionData() {
		return sessionData;
	}





	public void setSessionData(String sessionData) {
		this.sessionData = sessionData;
	}





	public String getInvenItemNo() {
		return invenItemNo;
	}





	public void setInvenItemNo(String invenItemNo) {
		this.invenItemNo = invenItemNo;
	}





	public String getInvenCode() {
		return invenCode;
	}





	public void setInvenCode(String invenCode) {
		this.invenCode = invenCode;
	}





	public String getGameNo() {
		return gameNo;
	}





	public void setGameNo(String gameNo) {
		this.gameNo = gameNo;
	}





	public String getInvenItemInfo() {
		return invenItemInfo;
	}





	public void setInvenItemInfo(String invenItemInfo) {
		this.invenItemInfo = invenItemInfo;
	}





	public String getInvenItemSrc() {
		return invenItemSrc;
	}





	public void setInvenItemSrc(String invenItemSrc) {
		this.invenItemSrc = invenItemSrc;
	}





	public Inven_ItemVO toInvenItemVO() {
		Inven_ItemVO iiv = new Inven_ItemVO();
		iiv.setInvenItemNo(invenItemNo);
		iiv.setInvenCode(invenCode);
		iiv.setGameNo(gameNo);
		iiv.setInvenItemInfo(invenItemInfo);
		iiv.setInvenItemSrc(invenItemSrc);
		
		return iiv;
	}
}
