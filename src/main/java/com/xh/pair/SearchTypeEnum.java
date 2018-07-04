package com.xh.pair;

public enum SearchTypeEnum {

	COLLECT("COLLECT", "收藏"), SELF("SELF", "我的项目"), PARTICIPATION("PARTICIPATION", "参与的项目");

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
