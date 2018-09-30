package com.betconstruct.demo.model;

import java.util.List;

public class Group implements Item {

	private int id;

	private String title;

	//    @JsonProperty("matches")
	private List<Child> childs;

	//    @JsonProperty("matchDate")
	private String matchDate;

	//    @JsonProperty("matchCount")
	private int matchCount;

	private boolean isAllChecked = false;


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Child> getChilds() {
		return childs;
	}

	public void setChilds(List<Child> childs) {
		this.childs = childs;
	}

	public boolean isAllChecked() {
		return isAllChecked;
	}

	public void setAllChecked(boolean allChecked) {
		isAllChecked = allChecked;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchCount(int matchCount) {
		this.matchCount = matchCount;
	}

	public int getMatchCount() {
		return matchCount;
	}

	@Override
	public String toString() {
		return
				"FixtureMatches{" +
						"matchDate = '" + matchDate + '\'' +
						",matchCount = '" + matchCount + '\'' +
						",matches = '" + childs + '\'' +
						"}";
	}

	@Override
	public boolean isGroup() {
		return true;
	}

	@Override
	public int getId() {
		return id;
	}

}
