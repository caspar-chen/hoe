package com.caspar.hoe;

/**
 * HTML special character
 * @author caspar.chen
 */
public class HtmlChar {

	private String html;
	private String name;
	private String decimalCode;

	public HtmlChar() {
		super();
	}

	public HtmlChar(String html, String name, String decimalCode) {
		super();
		this.html = html;
		this.name = name;
		this.decimalCode = decimalCode;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDecimalCode() {
		return decimalCode;
	}

	public void setDecimalCode(String decimalCode) {
		this.decimalCode = decimalCode;
	}

}
