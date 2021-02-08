package vo;

import java.sql.Date;

public class BoardVO {

	private int boardNo;			//게시글 번호(PK)
	private String boardTitle;		//게시글 제목
	private String boardContent;	//게시글 내용
	private int boardGrNo;			//게시판그룹번호(FK)
	private String memNo;			//멤버코드(FK)
	private Date boardDate;			//게시글 작성일
	private int boardCnt;			//게시글 조회수
	private String memId;			// 작성자의 아이디
	private String boardNum;		// 글번호 (NO.000형식)
	
	public String getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(String boardNum) {
		this.boardNum = boardNum;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getBoardCnt() {
		return boardCnt;
	}
	public void setBoardCnt(int boardCnt) {
		this.boardCnt = boardCnt;
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public int getBoardGrNo() {
		return boardGrNo;
	}
	public void setBoardGrNo(int boardGrNo) {
		this.boardGrNo = boardGrNo;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	
	
	
}
