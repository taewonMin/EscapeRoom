package vo;

import java.sql.Date;

public class PlaytimeVO {
	private int playNo;
	private String teamCode;
	private String playRecord;
	private Date playDate;
	private String playStatus;
	private int rank;
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getPlayNo() {
		return playNo;
	}
	public void setPlayNo(int playNo) {
		this.playNo = playNo;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getPlayRecord() {
		return playRecord;
	}
	public void setPlayRecord(String playRecord) {
		this.playRecord = playRecord;
	}
	public Date getPlayDate() {
		return playDate;
	}
	public void setPlayDate(Date playDate) {
		this.playDate = playDate;
	}
	public String getPlayStatus() {
		return playStatus;
	}
	public void setPlayStatus(String playStatus) {
		this.playStatus = playStatus;
	}
	
}
