package com.xh.uitl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

public final class IOUtil {
	private static final Logger log = LoggerFactory.getLogger(IOUtil.class);
	private static String realPath = ContextLoader.getCurrentWebApplicationContext().getServletContext()
			.getRealPath("/");

	/**
	 * 
	 * @Title: uploadFile
	 * @Description: 使用spring方法 上传文件
	 * @param mf
	 * @param filePath
	 * @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static final String uploadFile(MultipartFile mf, String filePath, String newFileName) {
		// 判断文件是否为空
		File fileObj = new File(realPath + filePath, newFileName);
		if (!fileObj.getParentFile().exists()) {
			fileObj.getParentFile().mkdirs();
		}
		try {
			mf.transferTo(new File(realPath + filePath + File.separator + newFileName));// 将文件写到磁盘上
		} catch (IllegalStateException | IOException e) {
			log.error("IO工具类【uploadFile】方法异常,异常原因:" + e.toString());
			return "";
		}
		return realPath + filePath + File.separator + newFileName;
	}

	/**
	 * 
	 * @Title: downloadFile
	 * @Description: 文件下载
	 * @param path
	 * @param fileName
	 * @return
	 * @author: MR.H
	 * @return: ResponseEntity<byte[]>
	 *
	 */
	public static final ResponseEntity<byte[]> downloadFile(String path, String fileName) {
		// 下载文件路径
		File file = new File(realPath + path + File.separator + fileName);
		HttpHeaders headers = new HttpHeaders();
		// 下载显示的文件名，解决中文名称乱码问题
		try {
			String downloadFielName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			// String downloadFielName = new String(fileName.getBytes("UTF-8"),
			// "UTF-8");
			// 通知浏览器以attachment（下载方式）打开图片
			headers.setContentDispositionFormData("attachment", downloadFielName);
			// application/octet-stream ： 二进制流数据（最常见的文件下载）。
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		} catch (IOException e) {
			log.error("IO工具类【downloadFile】方法异常,异常原因:" + e.toString());
			return null;
		}
	}

	/**
	 * 
	 * @Title: downloadFileTrue
	 * @Description: 文件下载servlet
	 * @author 陈专懂
	 * @return HttpServletResponse
	 * @date 2018年6月19日
	 * @version 1.0
	 */
	public static final HttpServletResponse downloadFileServlet(String filePath, String fileName,
			HttpServletResponse resp) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(realPath + filePath + File.separator + fileName);
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			resp.reset();
			// 设置response的Header
			String downloadFielName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			resp.addHeader("Content-Disposition", "attachment;filename=" + downloadFielName);
			resp.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(resp.getOutputStream());
			resp.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			log.error("IO工具类【downloadFile】方法异常,异常原因:" + ex.getMessage());
			return null;
		}
		return resp;
	}

	/**
	 * 
	 * @Title: getFileNname
	 * @Description: 获得子路径名
	 * @param file
	 * @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static final String getFileNname(File file) {
		return file.getName();
	}

	/**
	 * 
	 * @Title: renameFileName
	 * @Description: 重命名文件 注意：全路径相同
	 * @param oldPath
	 * @param newPath
	 * @return
	 * @author: MR.H
	 * @return: boolean
	 *
	 */
	public static final boolean renameFileName(String oldPath, String newPath) {
		if (!oldPath.equals(newPath)) {
			File oldfile = new File(oldPath);
			File newfile = new File(newPath);
			return oldfile.renameTo(newfile);
		}
		return true;
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除文件通用
	 * @param fileName
	 * @return
	 * @return boolean
	 * @date 2018年6月20日
	 * @version 1.0
	 */
	public static boolean deleteFiles(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			log.error("IO工具类【downloadFile】方法失败,失败原因:删除文件失败:" + fileName + "不存在！");
			return false;
		} else {
			if (file.isFile())
				return deleteFile(fileName);
			else
				return deleteDirectory(fileName);
		}
	}

	/**
	 * 
	 * @Title: deleteFile
	 * @Description: 删除单个文件
	 * @author 黄官易
	 * @param fileName
	 * @return
	 * @return boolean
	 * @date 2018年6月20日
	 * @version 1.0
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				log.error("IO工具类【downloadFile】方法失败,失败原因:删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			log.error("IO工具类【downloadFile】方法失败,失败原因:删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	/**
	 * 
	 * @Title: deleteDirectory
	 * @Description: 删除文件夹及子文件
	 * @author 黄官易
	 * @param dir
	 * @return
	 * @return boolean
	 * @date 2018年6月20日
	 * @version 1.0
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			log.error("IO工具类【downloadFile】方法失败,失败原因:删除目录失败：" + dir + "不存在");
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = IOUtil.deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = IOUtil.deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			log.error("IO工具类【downloadFile】方法失败,异常失败:删除目录失败！");
			return false;
		}
		// 删除当前目录
		return dirFile.delete();
	}

	/**
	 * 
	 * @Title: deleteNoDirectory
	 * @Description: 删除文件夹下的所有文件夹及文件
	 * @author 黄官易
	 * @param dir
	 * @return
	 * @return boolean
	 * @date 2018年6月20日
	 * @version 1.0
	 */
	public static boolean deleteNoDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			log.error("IO工具类【deleteNoDirectory】方法错误,错误原因:删除目录失败：" + dir + "不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = IOUtil.deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = IOUtil.deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		return true;
	}

	/**
	 * 
	 * @Title: deepClone
	 * @Description: 深度克隆对象
	 * @param obj
	 * @return
	 * @author: MR.H
	 * @return: T
	 *
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deepClone(Object obj) {
		if (null == obj) {
			return null;
		}
		// 将对象写入流中
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);
			// 将对象从流中读出来
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			ObjectInputStream oi = new ObjectInputStream(bi);
			return (T) oi.readObject();
		} catch (IOException | ClassNotFoundException e) {
			log.error("IO工具类【deepClone】方法异常,异常原因:" + e.toString());
			return null;
		}
	}

	/**
	 * 
	 * @Title: displayPDF
	 * @Description: TODO(pdf.js)
	 * @author 陈专懂
	 * @return void
	 * @date 2018年6月19日
	 * @version 1.0
	 */
	public static void displayPDF(HttpServletResponse response, HttpServletRequest request, String path,
			String fileName) {
		try {
			File file = new File(realPath + path + File.separator + fileName);
			FileInputStream fileInputStream = new FileInputStream(file);
			byte[] b = new byte[fileInputStream.available()];
			fileInputStream.read(b);
			response.setHeader("Content-Disposition", "attachment;fileName=预览文件.pdf");
			ServletOutputStream out = response.getOutputStream();
			out.write(b);
			out.flush();
			out.close();
			IOUtil.clearTempPdf(fileInputStream, fileName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: closeInputStream
	 * @Description: 关闭输入流对象
	 * @author 黄官易
	 * @param is
	 * @return void
	 * @date 2018年6月20日
	 * @version 1.0
	 */
	public static void closeInputStream(InputStream is) {
		try {
			if (null != is) {
				// 关闭输入流
				is.close();
			}
		} catch (IOException e) {
			log.error("IO工具类【closeInputStream】方法异常,异常原因:" + e.toString());
		}
	}

	/**
	 * 
	 * @Title: closeOutputStream
	 * @Description: 关闭输出流对象
	 * @author 黄官易
	 * @param os
	 * @return void
	 * @date 2018年6月20日
	 * @version 1.0
	 */
	public static void closeOutputStream(OutputStream os) {
		try {
			if (null != os) {
				os.close();
			}
		} catch (IOException e) {
			log.error("IO工具类【closeOutputStream】方法异常,异常原因:" + e.toString());
		}
	}

	/**
	 * 
	 * @Title: clearTempPdf
	 * @Description: 关闭输入流对象并删除pdf临时文件
	 * @author 黄官易
	 * @param is
	 * @return void
	 * @date 2018年6月20日
	 * @version 1.0
	 */
	public static void clearTempPdf(InputStream is, String fileName) {
		closeInputStream(is);
		if(StrUtil.notBlank(fileName)) {
			IOUtil.deleteFile(realPath + "pdf" + File.separator + fileName);
		}
		// IOUtil.deleteNoDirectory(realPath + "pdf/");//太过于危险
	}

}
