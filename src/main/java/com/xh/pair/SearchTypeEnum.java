package com.xh.pair;

public enum SearchTypeEnum {

	COLLECT("collect", "收藏"), SELF("self", "我的项目"), PARTICIPATION("participation", "参与的项目"),LOCKED("locked","锁定的项目");

	private String code;
	private String text;

	private SearchTypeEnum(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
