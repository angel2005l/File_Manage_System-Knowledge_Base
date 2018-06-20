package com.xh.base;

public final class Constant {
	/**
	 * 左边
	 */
	public static final String LEFT = "left";
	/**
	 * 中间
	 */
	public static final String CENTER = "center";
	/**
	 * 右边
	 */
	public static final String RIGHT = "right";
	/**
	 * 部门头标识
	 */
	public static final String DEPTTAG = "D";
	/**
	 * 项目_表映射头标识
	 */
	public static final String PROJECTTABLETAG = "PT";
	/**
	 * 项目头标识
	 */
	public static final String PROJECTTAG = "P";
	/**
	 * 文件_表映射头标识
	 */
	public static final String FILETABLETAG = "FT";
	/**
	 * 文件头标识
	 */
	public static final String FILETAG = "F";
	/**
	 * 操作日志头标识
	 */
	public static final String OPERATIONLOGTAG = "L";
	/**
	 * 职称头标识
	 */
	public static final String PROFESSIONALTAG = "PR";
	
	/**
	 * excel2007及以上文件MIME
	 */
	public static final String EXCELMIME = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	/**
	 * excel2003及以下文件MIME
	 */
	public static final String EXCELXLSMIME = "application/vnd.ms-excel";

	/**
	 * JPEG/JPG文件MIME
	 */
	public static final String JPEGMIME = "image/jpeg";

	/**
	 * PNG文件MIME
	 */
	public static final String PNGMIME = "image/png";

	/**
	 * 员工导入列对应字段 （顺序不可随意改动）
	 */
	public static final String[] EMPEXCELCOL = { "empAccount", "empName", "empPhone", "empEmail" };

	/**
	 * EXCEL2007及以上后缀名
	 */
	public static final String XLSXSUFFIX = ".xlsx";

	/**
	 * EXCEL2003及以下后缀名
	 */
	public static final String XLSSUFFIX = ".xls";

}
