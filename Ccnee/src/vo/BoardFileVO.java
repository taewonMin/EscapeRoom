package vo;

import java.sql.Date;
import java.util.List;

public class BoardFileVO {
	private int boardFileNo;
	private int boardNo;
	private String boardFileName;
	private String boardFileOrigin;
	private String boardStreCours;
	private String boardFileExtsn;
	private Date boardFileCreat;
	private String isThumnail;
	
	public int getBoardFileNo() {
		return boardFileNo;
	}
	public void setBoardFileNo(int boardFileNo) {
		this.boardFileNo = boardFileNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardFileName() {
		return boardFileName;
	}
	public void setBoardFileName(String boardFileName) {
		this.boardFileName = boardFileName;
	}
	public String getBoardFileOrigin() {
		return boardFileOrigin;
	}
	public void setBoardFileOrigin(String boardFileOrigin) {
		this.boardFileOrigin = boardFileOrigin;
	}
	public String getBoardStreCours() {
		return boardStreCours;
	}
	public void setBoardStreCours(String boardStreCours) {
		this.boardStreCours = boardStreCours;
	}
	public String getBoardFileExtsn() {
		return boardFileExtsn;
	}
	public void setBoardFileExtsn(String boardFileExtsn) {
		this.boardFileExtsn = boardFileExtsn;
	}
	public Date getBoardFileCreat() {
		return boardFileCreat;
	}
	public void setBoardFileCreat(Date boardFileCreat) {
		this.boardFileCreat = boardFileCreat;
	}
	
	
	
}
