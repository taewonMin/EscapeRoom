package vo;

public class SearchVO extends PagingVO {
	private String boardGrNo;
	private String criteria;
	private String search;

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getBoardGrNo() {
		return boardGrNo;
	}

	public void setBoardGrNo(String boardGrNo) {
		this.boardGrNo = boardGrNo;
	}
	
	
}
