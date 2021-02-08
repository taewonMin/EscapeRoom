package vo;

import java.sql.Date;

public class CmtVO {
	private String cmtContent;	//댓글내용     
	private int cmtNo;          //댓글번호(PK)
	private int boardNo;        //게시글번호(FK)   
	private String memNo;       //멤버코드(FK)
	private Date cmtDate;		//댓글쓴날짜
	
	public Date getCmtDate() {
		return cmtDate;
	}
	public void setCmtDate(Date cmtDate) {
		this.cmtDate = cmtDate;
	}
	public String getCmtContent() {
		return cmtContent;
	}
	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}
	public int getCmtNo() {
		return cmtNo;
	}
	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}

	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	
	public CmtVO toCmtVO() {
		CmtVO cv = new CmtVO();
		cv.setBoardNo(boardNo);
		cv.setCmtNo(cmtNo);
		cv.setCmtContent(cmtContent);
		cv.setMemNo(memNo);
		cv.setCmtDate(cmtDate);
		return cv;
	}
}
