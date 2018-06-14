package com.edu.uitl;

import java.io.File;
import java.io.FileInputStream;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

public class AsposeUtil {

	public static void word2Pdf() throws Exception {
		File file = new File("D:\\123.docx");
		FileInputStream docFileStream = new FileInputStream(file);
		Document document = new Document(docFileStream);
		document.save("D:\\123.pdf", SaveFormat.PDF);
		docFileStream.close();
	}
	
	public static void main(String[] arg) {
		try {
			AsposeUtil.word2Pdf();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
