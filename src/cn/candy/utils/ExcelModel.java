package cn.candy.utils;

public class ExcelModel {

	private String sheetName;
	/**
	 * 是否设置链接
	 */
	private boolean haveLink;
	/**
	 * 设置链接的列
	 */
	private Integer linkCellIndex;
	/**
	 * 链接到地址
	 */
	private String[] linkAddress;

	/**
	 * 表头
	 */
	private String[] header;

	/**
	 * 数据
	 */
	private String[][] data;

	public String[][] getData() {
		return data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public boolean isHaveLink() {
		return haveLink;
	}

	public void setHaveLink(boolean haveLink) {
		this.haveLink = haveLink;
	}

	public String[] getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String[] linkAddress) {
		this.linkAddress = linkAddress;
	}

	public Integer getLinkCellIndex() {
		return linkCellIndex;
	}

	public void setLinkCellIndex(Integer linkCellIndex) {
		this.linkCellIndex = linkCellIndex;
	}

}
