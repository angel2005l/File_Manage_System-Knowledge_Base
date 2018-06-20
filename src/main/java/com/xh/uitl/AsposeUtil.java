package com.xh.uitl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

public class AsposeUtil {
	private static final Logger log = LoggerFactory.getLogger(AsposeUtil.class);

	/**
	 * 
	 * @Title: getLicenseWithWord
	 * @Description: 获得许可证
	 * @author 黄官易
	 * @return
	 * @return boolean
	 * @date 2018年6月20日
	 * @version 1.0
	 */
	public static boolean getLicenseWithWord() {
		boolean result = false;
		try {
			InputStream is = Resources.getResourceAsStream("license/license_word.xml");
			License aposeLic = new License();
			aposeLic.setLicense(is);
			result = true;
		} catch (Exception e) {
			log.error("加载word许可失败,失败原因：【" + e.toString() + "】");
		}
		return result;
	}

	/**
	 * 
	 * @Title: word2PDF
	 * @Description: 根据文件路径 转化word文件返回路径
	 * @author 黄官易
	 * @param filePath
	 * @param userPath
	 * @return
	 * @return String
	 * @date 2018年6月20日
	 * @version 1.0
	 */
	public static String word2PDFStr(String filePath) {
		String pdfPath = "";
		FileInputStream pdfFileOS = null;
		// 加载许可证
		if (getLicenseWithWord()) {
			// 获得文件对象
			try {
				File file = new File(filePath);
				pdfFileOS = new FileInputStream(file);
				Document doc = new Document(pdfFileOS);
				pdfPath = "src//main//pdf//" + DateUtil.curDateYMDHMSSForService() + ".pdf";
				doc.save(pdfPath, SaveFormat.PDF);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (null != pdfFileOS) {
					try {
						pdfFileOS.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return pdfPath;
	}

	/**
	 * 
	 * @Title: word2PDFStream
	 * @Description: 根据文件路径 转化word文件返回输入流对象
	 * @author 黄官易
	 * @param filePath
	 * @return
	 * @return InputStream
	 * @date 2018年6月20日
	 * @version 1.0
	 */
	public static InputStream word2PDFStream(String filePath) {
		String pdfPath = "";
		FileInputStream pdfFileOS = null;
		FileInputStream pdfFileIs = null;
		// 加载许可证
		if (getLicenseWithWord()) {
			// 获得文件对象
			try {
				File file = new File(filePath);
				pdfFileOS = new FileInputStream(file);
				Document doc = new Document(pdfFileOS);
				pdfPath = "src//main//pdf//" + DateUtil.curDateYMDHMSSForService() + ".pdf";
				doc.save(pdfPath, SaveFormat.PDF);
				File filePdf = new File(pdfPath);
				pdfFileIs = new FileInputStream(filePdf);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != pdfFileOS) {
					try {
						pdfFileOS.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return pdfFileIs;
	}

	/**
	 * 
	 * @Title: word2PDF
	 * @Description: 根据输入流 转化word文件返回文件路径
	 * @author 黄官易
	 * @param is
	 * @return
	 * @return String
	 * @date 2018年6月20日
	 * @version 1.0
	 */
	public static String word2PDFStr(InputStream is) {
		String pdfPath = "";
		// 加载许可证
		if (getLicenseWithWord()) {
			// 获得文件对象
			try {
				Document doc = new Document(is);
				pdfPath = "src//main//pdf//" + DateUtil.curDateYMDHMSSForService() + ".pdf";
				doc.save(pdfPath, SaveFormat.PDF);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pdfPath;
	}

	/**
	 * 
	 * @Title: word2PDFStream
	 * @Description: 根据输入流 转化word文件返回输入流对象
	 * @author 黄官易
	 * @param is
	 * @return
	 * @return InputStream
	 * @date 2018年6月20日
	 * @version 1.0
	 */
	public static InputStream word2PDFStream(InputStream is) {
		String pdfPath = "";
		FileInputStream pdfFileOS = null;
		FileInputStream pdfFileIs = null;
		// 加载许可证
		if (getLicenseWithWord()) {
			// 获得文件对象
			try {
				Document doc = new Document(is);
				pdfPath = "src//main//pdf//" + DateUtil.curDateYMDHMSSForService() + ".pdf";
				doc.save(pdfPath, SaveFormat.PDF);
				File filePdf = new File(pdfPath);
				pdfFileIs = new FileInputStream(filePdf);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != pdfFileOS) {
					try {
						pdfFileOS.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return pdfFileIs;
	}
	
	

	public static void main(String[] arg) throws IOException {
		// D:\\123测试.docx
		// InputStream word2pdfStream = word2PDFStream("D:\\123xin是.doc");

		// String word2pdf = word2PDF(new FileInputStream("D:\\123xin是.doc"));
		// File file = new File(word2pdf);

		// word2pdfStream.close();

		// InputStream word2pdfStream = word2PDFStream(new
		// FileInputStream("D:\\123xin是.doc"));
		// System.err.println(word2pdfStream);

	}
}
