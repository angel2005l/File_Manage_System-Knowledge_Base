package com.xh.pair;

public enum FileTypeImgEnum {

	ASP(".asp","file_extension_asp.png"),
	C(".c","file_extension_c.png"),
	DOC(".doc","file_extension_doc"),
	DOCX(".docx","file_extension_docx.png"),
	DWG(".dwg","file_extension_dwg.png"),
	EXE(".exe","file_extension_exe.png"),
	FILE(".file","file_extension_file.png"),
	GIF(".gif","file_extension_gif.png"),
	HTML(".html","file_extension_html.png"),
	JAVA(".java","file_extension_java.png"),
	JPG(".jpg","file_extension_jpg.png"),
	PHP(".php","file_extension_php.png"),
	PNG(".png","file_extension_png.png"),
	PPT(".ppt","file_extension_ppt.png"),
	PPTX(".pptx","file_extension_pptx.png"),
	RAR(".rar","file_extension_rar.png"),
	TXT(".txt","file_extension_txt.png"),
	XLS(".xls","file_extension_xls.png"),
	XLSX(".xlsx","file_extension_xlsx.png"),
	ZIP(".zip","file_extension_zip.png");
	
	
	
	private String code;
	private String text;

	private FileTypeImgEnum(String code, String text) {
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
