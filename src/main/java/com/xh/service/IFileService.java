package com.xh.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.xh.entity.KbBatchShare;
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
	 * @Title: getImgUrlStr
	 * @Description: 获得图片的临时路径
	 * @author 黄官易
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2018年7月20日
	 * @version 1.0
	 */
	public Result<String> getImgUrlStr(String filePath, String fileCode, String fileName) throws Exception;

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
	public Result<Map<String, Object>> getProjectDetailData(String projectCode, int projectLevel, String userCode,
			Map<String, String> fileSelMap) throws Exception;

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

	/**
	 * 
	 * @Title: insBatchProject
	 * @Description: 新增批量分享
	 * @author 黄官易
	 * @param kbs
	 * @return
	 * @throws Exception
	 * @return Result<String>
	 * @date 2018年7月12日
	 * @version 1.0
	 */
	public Result<String> insBatchProject(KbBatchShare kbs) throws Exception;

	/**
	 * 
	 * @Title: selectShareFilesData
	 * @Description: 根据分享编码查询分享信息
	 * @author 黄官易
	 * @param shareCode
	 * @return
	 * @throws Exception
	 * @return Result<Map<String,Object>>
	 * @date 2018年7月12日
	 * @version 1.0
	 */
	public Result<Map<String, Object>> selectShareFilesData(String shareCode) throws Exception;

	/**
	 * 
	 * @Title: deleteTempImg
	 * @Description: 删除临时图片文件
	 * @author 黄官易
	 * @param filePath
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年7月21日
	 * @version 1.0
	 */
	public Result<Object> deleteTempImg(String filePath) throws Exception;

	/**
	 * 
	 * @Title: uptlockFile
	 * @Description: 锁定文件 锁定的文件不能被预览和下载
	 * @author 黄官易
	 * @param fileLevel
	 * @param fileCode
	 * @param userCode
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年7月25日
	 * @version 1.0
	 */
	public Result<Object> uptLockFile(int fileLevel, String fileCode, String userCode) throws Exception;

	/**
	 * 
	 * @Title: deleteFile
	 * @Description: 删除文件 删除的文件无法再回复 但在后台只是更新字段
	 * @author 黄官易
	 * @param fileLevel
	 * @param fileCode
	 * @param userCode
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年7月25日
	 * @version 1.0
	 */
	public Result<Object> deleteFile(int fileLevel, String fileCode, String userCode) throws Exception;

	/**
	 * 
	 * @Title: uptUnLockFile
	 * @Description: 解锁文件  解锁必须要延迟1小时方可解除
	 * @author 黄官易
	 * @param fileLevel
	 * @param fileCode
	 * @param userCode
	 * @return
	 * @throws Exception
	 * @return Result<Object>
	 * @date 2018年8月1日
	 * @version 1.0
	 */
	public Result<Object> uptUnLockFile(int fileLevel, String fileCode, String userCode) throws Exception;
}
