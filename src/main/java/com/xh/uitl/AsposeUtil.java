package com.xh.uitl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;

import com.aspose.cells.Workbook;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

public class AsposeUtil {
	private static final Logger log = LoggerFactory.getLogger(AsposeUtil.class);
	private static final String realPath = ContextLoader.getCurrentWebApplicationContext().getServletContext()
			.getRealPath("/");

	/**
	 * 
	 * @Title: getLicenseWithWord
	 * @Description: 获得许可证word
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
		FileInputStream is = null;
		// 加载许可证
		if (getLicenseWithWord()) {
			// 获得文件对象
			try {
				File file = new File(realPath + filePath);
				is = new FileInputStream(file);
				Document doc = new Document(is);
				pdfPath = realPath + "pdf" + File.separator + DateUtil.curDateYMDHMSSForService() + ".pdf";
				doc.save(pdfPath, SaveFormat.PDF);
			} catch (Exception e) {
				log.error("Aspose工具类【word2PDFStr(String)】方法异常,异常原因:【" + e.toString() + "】");
			} finally {
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						log.error("Aspose工具类【word2PDFStr(String)】方法关闭输入流异常,异常原因:【" + e.toString() + "】");
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
		FileInputStream is = null;
		FileInputStream pdfFileIs = null;
		// 加载许可证
		if (getLicenseWithWord()) {
			// 获得文件对象
			try {
				File file = new File(realPath + filePath);
				is = new FileInputStream(file);
				Document doc = new Document(is);
				String pdfPath = realPath + "pdf" + File.separator + DateUtil.curDateYMDHMSSForService() + ".pdf";
				doc.save(pdfPath, SaveFormat.PDF);
				File filePdf = new File(pdfPath);
				pdfFileIs = new FileInputStream(filePdf);
			} catch (Exception e) {
				log.error("Aspose工具类【word2PDFStream(String)】方法异常,异常原因:【" + e.toString() + "】");
			} finally {
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						log.error("Aspose工具类【word2PDFStream(String)】方法关闭输入流异常,异常原因:【" + e.toString() + "】");
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
				pdfPath = realPath + "pdf" + File.separator + DateUtil.curDateYMDHMSSForService() + ".pdf";
				doc.save(pdfPath, SaveFormat.PDF);
			} catch (Exception e) {
				log.error("Aspose工具类【word2PDFStr(InputStream)】方法异常,异常原因:【" + e.toString() + "】");
			} finally {
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						log.error("Aspose工具类【word2PDFStr(InputStream)】方法关闭输入流异常,异常原因:【" + e.toString() + "】");
					}
				}
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
		FileInputStream pdfFileIs = null;
		// 加载许可证
		if (getLicenseWithWord()) {
			// 获得文件对象
			try {
				Document doc = new Document(is);
				String pdfPath = realPath + "pdf" + File.separator + DateUtil.curDateYMDHMSSForService() + ".pdf";
				doc.save(pdfPath, SaveFormat.PDF);
				File filePdf = new File(pdfPath);
				pdfFileIs = new FileInputStream(filePdf);
			} catch (Exception e) {
				log.error("Aspose工具类【word2PDFStream(InputStream)】方法异常,异常原因:【" + e.toString() + "】");
			} finally {
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						log.error("Aspose工具类【word2PDFStream(InputStream)】方法关闭输入流异常,异常原因:【" + e.toString() + "】");
					}
				}
			}
		}
		return pdfFileIs;
	}

	/**
	 * 
	 * @Title: getLicenseWithExcel
	 * @Description: 获得许可证Excel
	 * @author 黄官易
	 * @return
	 * @return boolean
	 * @date 2018年6月20日
	 * @version 1.0
	 */
	public static boolean getLicenseWithExcel() {
		boolean result = false;
		try {
			InputStream is = Resources.getResourceAsStream("license/license_excel.xml");
			com.aspose.cells.License license = new com.aspose.cells.License();
			license.setLicense(is);
			result = true;
		} catch (Exception e) {
			log.error("加载excel许可失败,失败原因：【" + e.toString() + "】");
		}
		return result;
	}

	/**
	 * 
	 * @Title: excel2PDFStr
	 * @Description: 根据文件路径 转化excel文件返回路径
	 * @param filePath
	 * @return
	 * @author: MR.H
	 * @date 2018年6月22日
	 * @return: String
	 *
	 */
	public static String excel2PDFStr(String filePath) {
		String pdfPath = "";
		FileInputStream is = null;
		try {
			if (getLicenseWithExcel()) {
				File file = new File(realPath + filePath);
				is = new FileInputStream(file);
				Workbook wb = new Workbook(is);
				pdfPath = realPath + "pdf" + File.separator + DateUtil.curDateYMDHMSSForService() + ".pdf";
				wb.save(pdfPath, com.aspose.cells.SaveFormat.PDF);
			}
		} catch (Exception e) {
			log.error("Aspose工具类【excel2PDFStr(String)】方法异常,异常原因:【" + e.toString() + "】");
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					log.error("Aspose工具类【excel2PDFStr(String)】方法关闭输入流异常,异常原因:【" + e.toString() + "】");
				}
			}
		}
		return pdfPath;
	}

	/**
	 * 
	 * @Title: excel2PDFtream
	 * @Description: 根据文件路径 转化excel文件返回输入流对象
	 * @param filePath
	 * @return
	 * @author: MR.H
	 * @return: InputStream
	 *
	 */
	public static InputStream excel2PDFtream(String filePath) {
		FileInputStream is = null;
		FileInputStream pdfFileIs = null;
		// 加载许可证
		if (getLicenseWithWord()) {
			// 获得文件对象
			try {
				File file = new File(realPath + filePath);
				is = new FileInputStream(file);
				Workbook wk = new Workbook(is);
				String pdfPath = realPath + "pdf" + File.separator + DateUtil.curDateYMDHMSSForService() + ".pdf";
				wk.save(pdfPath, com.aspose.cells.SaveFormat.PDF);
				File filePdf = new File(pdfPath);
				pdfFileIs = new FileInputStream(filePdf);
			} catch (Exception e) {
				log.error("Aspose工具类【excel2PDFtream(String)】方法异常,异常原因:【" + e.toString() + "】");
			} finally {
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						log.error("Aspose工具类【excel2PDFtream(String)】方法关闭输入流异常,异常原因:【" + e.toString() + "】");
					}
				}
			}
		}
		return pdfFileIs;
	}

	/**
	 * 
	 * @Title: excel2PDFStr
	 * @Description: 根据输入流 转化excel文件返回路径
	 * @param is
	 * @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static String excel2PDFStr(InputStream is) {
		String pdfPath = "";
		// 加载许可证
		if (getLicenseWithWord()) {
			// 获得文件对象
			try {
				Workbook wk = new Workbook(is);
				pdfPath = realPath + "pdf" + File.separator + DateUtil.curDateYMDHMSSForService() + ".pdf";
				wk.save(pdfPath, com.aspose.cells.SaveFormat.PDF);
			} catch (Exception e) {
				log.error("Aspose工具类【excel2PDFStr(InputStream)】方法异常,异常原因:【" + e.toString() + "】");
			} finally {
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						log.error("Aspose工具类【excel2PDFStr(InputStream)】方法关闭输入流异常,异常原因:【" + e.toString() + "】");
					}
				}
			}
		}
		return pdfPath;
	}

	/**
	 * 
	 * @Title: excel2PDFStream
	 * @Description: 根据输入流 转化excel文件返回输入流对象
	 * @param is
	 * @return
	 * @author: MR.H
	 * @return: InputStream
	 *
	 */
	public static InputStream excel2PDFStream(InputStream is) {
		FileInputStream pdfFileIs = null;
		// 加载许可证
		if (getLicenseWithWord()) {
			// 获得文件对象
			try {
				Workbook wk = new Workbook(is);
				String pdfPath = realPath + "pdf" + File.separator + DateUtil.curDateYMDHMSSForService() + ".pdf";
				wk.save(pdfPath, com.aspose.cells.SaveFormat.PDF);
				File filePdf = new File(pdfPath);
				pdfFileIs = new FileInputStream(filePdf);
			} catch (Exception e) {
				log.error("Aspose工具类【excel2PDFStream(InputStream)】方法异常,异常原因:【" + e.toString() + "】");
			} finally {
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						log.error("Aspose工具类【excel2PDFStream(InputStream)】方法关闭输入流异常,异常原因:【" + e.toString() + "】");
					}
				}
			}
		}
		return pdfFileIs;
	}
}
