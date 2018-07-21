package com.xh.uitl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;

import com.aspose.cells.Workbook;
import com.aspose.slides.Presentation;
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
		if (getLicenseWithExcel()) {
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
		if (getLicenseWithExcel()) {
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
		if (getLicenseWithExcel()) {
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

	/**
	 * 
	 * @Title: getLicenseWithPpt
	 * @Description: 获得许可证Ppt
	 * @author 黄官易
	 * @return
	 * @return boolean
	 * @date 2018年7月14日
	 * @version 1.0
	 */
	public static boolean getLicenseWithPpt() {
		boolean result = false;
		try {
			InputStream is = Resources.getResourceAsStream("license/license_ppt.xml");
			com.aspose.slides.License license = new com.aspose.slides.License();
			license.setLicense(is);
			result = true;
		} catch (Exception e) {
			log.error("加载ppt许可失败,失败原因：【" + e.toString() + "】");
		}
		return result;
	}

	/**
	 * 
	 * @Title: excel2PDFStr
	 * @Description: 根据文件路径 转化ppt文件返回路径
	 * @param filePath
	 * @return
	 * @author: MR.H
	 * @date 2018年6月22日
	 * @return: String
	 *
	 */
	public static String ppt2PDFStr(String filePath) {
		String pdfPath = "";
		FileInputStream is = null;
		try {
			if (getLicenseWithPpt()) {
				File file = new File(realPath + filePath);
				is = new FileInputStream(file);
				Presentation pst = new Presentation(is);
				pdfPath = realPath + "pdf" + File.separator + DateUtil.curDateYMDHMSSForService() + ".pdf";
				pst.save(pdfPath, com.aspose.slides.SaveFormat.Pdf);
			}
		} catch (Exception e) {
			log.error("Aspose工具类【ppt2PDFStr(String)】方法异常,异常原因:【" + e.toString() + "】");
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					log.error("Aspose工具类【ppt2PDFStr(String)】方法关闭输入流异常,异常原因:【" + e.toString() + "】");
				}
			}
		}
		return pdfPath;
	}

	/**
	 * 
	 * @Title: ppt2PDFtream
	 * @Description: 根据文件路径 转化ppt文件返回输入流对象
	 * @param filePath
	 * @return
	 * @author: MR.H
	 * @return: InputStream
	 *
	 */
	public static InputStream ppt2PDFtream(String filePath) {
		FileInputStream is = null;
		FileInputStream pdfFileIs = null;
		// 加载许可证
		if (getLicenseWithPpt()) {
			// 获得文件对象
			try {
				File file = new File(realPath + filePath);
				is = new FileInputStream(file);
				Presentation pst = new Presentation(is);
				String pdfPath = realPath + "pdf" + File.separator + DateUtil.curDateYMDHMSSForService() + ".pdf";
				pst.save(pdfPath, com.aspose.slides.SaveFormat.Pdf);
				File filePdf = new File(pdfPath);
				pdfFileIs = new FileInputStream(filePdf);
			} catch (Exception e) {
				log.error("Aspose工具类【ppt2PDFtream(String)】方法异常,异常原因:【" + e.toString() + "】");
			} finally {
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						log.error("Aspose工具类【ppt2PDFtream(String)】方法关闭输入流异常,异常原因:【" + e.toString() + "】");
					}
				}
			}
		}
		return pdfFileIs;
	}

	/**
	 * 
	 * @Title: ppt2PDFStr
	 * @Description: 根据输入流 转化ppt文件返回路径
	 * @param is
	 * @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static String ppt2PDFStr(InputStream is) {
		String pdfPath = "";
		// 加载许可证
		if (getLicenseWithPpt()) {
			// 获得文件对象
			try {
				Presentation pst = new Presentation(is);
				pdfPath = realPath + "pdf" + File.separator + DateUtil.curDateYMDHMSSForService() + ".pdf";
				pst.save(pdfPath, com.aspose.slides.SaveFormat.Pdf);
			} catch (Exception e) {
				log.error("Aspose工具类【ppt2PDFStr(InputStream)】方法异常,异常原因:【" + e.toString() + "】");
			} finally {
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						log.error("Aspose工具类【ppt2PDFStr(InputStream)】方法关闭输入流异常,异常原因:【" + e.toString() + "】");
					}
				}
			}
		}
		return pdfPath;
	}

	/**
	 * 
	 * @Title: ppt2PDFStream
	 * @Description: 根据输入流 转化ppt文件返回输入流对象
	 * @param is
	 * @return
	 * @author: MR.H
	 * @return: InputStream
	 *
	 */
	public static InputStream ppt2PDFStream(InputStream is) {
		FileInputStream pdfFileIs = null;
		// 加载许可证
		if (getLicenseWithPpt()) {
			// 获得文件对象
			try {
				Presentation pst = new Presentation(is);
				String pdfPath = realPath + "pdf" + File.separator + DateUtil.curDateYMDHMSSForService() + ".pdf";
				pst.save(pdfPath, com.aspose.slides.SaveFormat.Pdf);
				File filePdf = new File(pdfPath);
				pdfFileIs = new FileInputStream(filePdf);
			} catch (Exception e) {
				log.error("Aspose工具类【ppt2PDFStream(InputStream)】方法异常,异常原因:【" + e.toString() + "】");
			} finally {
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						log.error("Aspose工具类【ppt2PDFStream(InputStream)】方法关闭输入流异常,异常原因:【" + e.toString() + "】");
					}
				}
			}
		}
		return pdfFileIs;
	}

	/**
	 * 
	 * @Title: showPDFStr
	 * @Description: 将pdf文件写入到临时文件 获得文件路径
	 * @author 黄官易
	 * @param filePath
	 * @return
	 * @return String
	 * @date 2018年7月19日
	 * @version 1.0
	 */
	public static String showPDFStr(String filePath) {
		String pdfPath = "";
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			pdfPath = realPath + "pdf" + File.separator + DateUtil.curDateYMDHMSSForService() + ".pdf";// 临时文件名称
			System.err.println(filePath);
			// 使用fileChannel
			File inputFile = new File(realPath + filePath);
			System.err.println(inputFile.exists());
			if (inputFile.exists()) {
				// 文件是否存在
				inputChannel = new FileInputStream(inputFile).getChannel();
				outputChannel = new FileOutputStream(new File(pdfPath)).getChannel();
				outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
			}
		} catch (IOException e) {
			log.error("Aspose工具类【showPDFStr(String)】方法异常,异常原因:【" + e.toString() + "】");
			return "";
		} finally {
			try {
				if (null != inputChannel) {
					inputChannel.close();
				}
				if (null != outputChannel) {
					outputChannel.close();
				}
			} catch (IOException e) {
				log.error("Aspose工具类【showPDFStr(String)】方法关闭输入/输出流异常,异常原因:【" + e.toString() + "】");
			}
		}
		return pdfPath;
	}

	/**
	 * 
	 * @Title: showImgStr
	 * @Description: 将图片文件写入到临时文件 获得文件路径
	 * @author 黄官易
	 * @param filePath
	 * @return
	 * @return String
	 * @date 2018年7月21日
	 * @version 1.0
	 */
	@SuppressWarnings("resource")
	public static String showImgStr(String filePath, String suffix) {
		String imgPath = "";
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			imgPath = "pdf" + File.separator + DateUtil.curDateYMDHMSSForService() + suffix;// 临时文件名称
			File imgFile = new File(realPath + filePath);
			if (imgFile.exists()) {
				inputChannel = new FileInputStream(imgFile).getChannel();
				outputChannel = new FileOutputStream(new File(realPath + imgPath)).getChannel();
				// 移动到临时文件下
				outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
				return imgPath;
			}
		} catch (IOException e) {
			log.error("Aspose工具类【showImgStr(String)】方法异常,异常原因:【" + e.toString() + "】");
		} finally {
			try {
				if (null != inputChannel) {
					inputChannel.close();
				}
				if (null != outputChannel) {
					outputChannel.close();
				}
			} catch (IOException e) {
				log.error("Aspose工具类【showImgStr(String)】方法关闭输入/输出流异常,异常原因:【" + e.toString() + "】");
			}
		}
		return "";
	}
}
