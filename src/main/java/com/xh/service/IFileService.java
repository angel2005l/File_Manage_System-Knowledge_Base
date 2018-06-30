package com.xh.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.xh.entity.KbFile;
import com.xh.entity.KbFileTable;
import com.xh.entity.KbFileUser;
import com.xh.uitl.Result;

public interface IFileService {

	/**
	 * 
	 * @Title: insFile
	 * @Description: 新增文件信息
	 * @author 黄官易
	 * @param kf
	 * @param kfb
	 * @param kfus
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年6月21日
	 * @version 1.0
	 */
	public Result<Object> insFile(KbFile kf, String projectLevel, List<KbFileUser> kfus) throws Exception;

	/**
	 * 
	 * @Title: inseFileTable
	 * @Description: 新增文件表信息及文件表业务方法
	 * @author 黄官易
	 * @param kft
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年6月21日
	 * @version 1.0
	 */
	public Result<Object> insFileTable(KbFileTable kft) throws Exception;

	/**
	 * 
	 * @Title: uploadFile
	 * @Description: 文件上传
	 * @author 黄官易
	 * @param mf
	 * @return
	 * @throws Exception
	 * @return Result<Map<String,String>>
	 * @date 2018年6月22日
	 * @version 1.0
	 */
	public Result<Map<String, String>> uploadFile(MultipartFile mf) throws Exception;

	/**
	 * 
	 * @Title: selFileByFileCode
	 * @Description: 根据文件编码查询文件信息业务方法
	 * @author 黄官易
	 * @param projectLevel
	 * @param fileCode
	 * @return
	 * @throws Exception
	 * @return Result<KbFile>
	 * @date 2018年6月22日
	 * @version 1.0
	 */
	public Result<KbFile> selFileByFileCode(int fileLevel, String fileCode) throws Exception;

	/**
	 * 
	 * @Title: downloadPdf
	 * @Description: 下载临时PDF文件
	 * @author 黄官易
	 * @param fileCode
	 * @param fileName
	 * @return
	 * @throws Exception
	 * @return ResponseEntity<byte[]>
	 * @date 2018年6月23日
	 * @version 1.0
	 */
	public ResponseEntity<byte[]> downloadPdf(String filePath, String fileCode, String fileName) throws Exception;

	/**
	 * 
	 * @Title: insSuperiorUserFileWithOnlyRead
	 * @Description: 新增文件上级预览权限
	 * @author 黄官易
	 * @param kf
	 * @param kbUser
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年6月25日
	 * @version 1.0
	 */
	public Result<Object> insSuperiorUserFileWithOnlyRead(KbFile kf, String userDeptCode) throws Exception;

	/**
	 * 
	 * @Title: selectFile
	 * @Description: 查询当前项目中 用户有权限的文件
	 * @author 黄官易
	 * @param projectLevel
	 * @param UserCode
	 * @param projectCode
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年6月25日
	 * @version 1.0
	 */
	public List<Map<String, Object>> selectFile(int projectLevel, String userCode, String projectCode) throws Exception;

	/**
	 * 
	 * @Title: getShare
	 * @Description: 文件分享
	 * @author 黄官易
	 * @param fileCode
	 * @param fileLevel
	 * @param projectCode
	 * @return
	 * @throws Exception
	 * @return Map<String,Object>
	 * @date 2018年6月27日
	 * @version 1.0
	 */
	public Map<String, Object> getShareFile(String fileCode, int fileLevel, String projectCode) throws Exception;

	/**
	 * 
	 * @Title: getProjectDetailData
	 * @Description: 统一方法 显示详细页所有数据
	 * @author 黄官易
	 * @param projectCode
	 * @param projectLevel
	 * @param userCode
	 * @return
	 * @throws Exception
	 * @return Result<Map<String,Object>>
	 * @date 2018年6月30日
	 * @version 1.0
	 */
	public Result<Map<String, Object>> getProjectDetailData(String projectCode, int projectLevel, String userCode)
			throws Exception;

	/**
	 * 
	 * @Title: selectSuperiorProjectCodeByProjectCode
	 * @Description: 根据项目编码查询父类的项目编码
	 * @author 黄官易
	 * @param projectLevel
	 * @param projectCode
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2018年6月30日
	 * @version 1.0
	 */
	public String selectSuperiorProjectCodeByProjectCode(int projectLevel, String projectCode) throws Exception;

}
