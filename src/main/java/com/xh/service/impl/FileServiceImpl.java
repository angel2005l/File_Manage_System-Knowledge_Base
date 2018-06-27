package com.xh.service.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.xh.base.BaseService;
import com.xh.base.Constant;
import com.xh.dao.KbFileMapper;
import com.xh.dao.KbFileTableMapper;
import com.xh.dao.KbFileUserMapper;
import com.xh.dao.KbProjectMapper;
import com.xh.dao.KbProjectTableMapper;
import com.xh.dao.KbUserMapper;
import com.xh.entity.KbFile;
import com.xh.entity.KbFileTable;
import com.xh.entity.KbFileUser;
import com.xh.entity.KbProject;
import com.xh.entity.KbUser;
import com.xh.service.IFileService;
import com.xh.service.IUserService;
import com.xh.uitl.AsposeUtil;
import com.xh.uitl.DateUtil;
import com.xh.uitl.IOUtil;
import com.xh.uitl.Result;
import com.xh.uitl.StrUtil;

@Service("fileServiceImpl")
public class FileServiceImpl extends BaseService implements IFileService {
	private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);// 日志对象
	private static final String FILETAG = Constant.FILETAG; // 文件标识头
	private static final String FILEPATH = "../upload";// 将文件保存在服务器根目录

	@Autowired
	private KbFileTableMapper kftm;// 文件表数据接口
	@Autowired
	private KbFileMapper kfm;// 文件数据接口
	@Autowired
	private KbFileUserMapper kfum;// 文件用户关联接口
	@Autowired
	private KbUserMapper kum; // 用户接口
	@Autowired
	private KbProjectTableMapper kptm;// 项目表接口
	@Autowired
	private KbProjectMapper kpm;// 项目接口

	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService us;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result<Object> insFile(KbFile kf, String projectLevel, List<KbFileUser> kfus) throws Exception {
		if (!StrUtil.isNaturalNumber(projectLevel)) {
			return rtnFailResult(Result.ERROR_4000, "项目层级参数不合法");
		}
		String fileTableName = "";
		try {
			fileTableName = kftm.selectFileTableNameByFileLevel(Integer.parseInt(projectLevel));
		} catch (SQLException e) {
			log.error("获得文件表信息异常,异常原因【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "获得文件表信息异常,请联系系统管理员");
		}
		if (StrUtil.notBlank(fileTableName)) {
			try {
				// 保存文件主信息
				int fileNum = kfm.insertFile(kf, fileTableName);
				// 保存文件与用户的关联关系
				int fileUserNum = kfum.batchInsertFileUser(kfus);
				if (fileNum > 0 && fileUserNum == kfus.size()) {
					return rtnSuccessResult("文件保存成功");
				} else {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
					return rtnFailResult(Result.ERROR_4300, "文件保存失败");
				}
			} catch (SQLException e) {
				log.error("新增文件信息数据接口异常,异常原因:【" + e.toString() + "】");
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
				return rtnErrorResult(Result.ERROR_6000, "新增文件信息数据接口异常,请联系系统管理员");
			}
		}
		return rtnFailResult(Result.ERROR_4300, "无相关联数据表信息/该层级未开放,请联系系统管理员");
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Result<Object> insFileTable(KbFileTable kft) throws Exception {
		if (null == kft) {
			return rtnFailResult(Result.ERROR_4000, "文件表数据为空");
		}
		try {
			// 判断是否越级插入
			int fileLevelMax = kftm.selectFileLevelMax();
			if (fileLevelMax == 0 && kft.getFileLevel() != 0) {
				// 初始化 排除边界值为0
				return rtnFailResult(Result.ERROR_4000, "初始化失败,初始层级应该为0");
			} else if (kft.getFileLevel() - fileLevelMax > 1) {
				return rtnFailResult(Result.ERROR_4000, "文件层级越级");
			} else if (kft.getFileLevel() - fileLevelMax <= 0) {
				return rtnFailResult(Result.ERROR_4000, "该文件层级已存在");
			}
			// 判断文件是否存在
			if (kftm.isExistFileDataTable(kft.getFtName())) {
				return rtnFailResult(Result.ERROR_4000, "文件表名重复");
			}
			// 新增文件表信息
			int fileTableInfoNum = kftm.insertFileTable(kft);
			if (fileTableInfoNum != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
				return rtnFailResult(Result.ERROR_4000, "文件表新增失败");
			}
			// 创建文件表
			kftm.createFileDataTable(kft.getFtName(), kft.getFileLevel());
			return rtnSuccessResult("文件表新增成功");
		} catch (SQLException e) {
			log.error("新增表文件数据接口异常,异常原因：+【" + e.toString() + "】");
			kftm.dropFileDataTable(kft.getFtName());
			kftm.deleteFileTabel(kft.getFtCode());
			return rtnErrorResult(Result.ERROR_6000, "新增表文件数据接口异常,请联系系统管理员");
		}
	}

	@Override
	public Result<Map<String, String>> uploadFile(MultipartFile mf) throws Exception {
		if (!mf.isEmpty()) {
			String oldFileName = mf.getOriginalFilename();
			String suffix = StrUtil.strToLower(oldFileName.substring(oldFileName.lastIndexOf(".")));
			String newFileName = FILETAG + DateUtil.curDateYMDHMSForService()
					+ StrUtil.getRandom((int) (Math.random() * 10000), 4);
			Map<String, String> result = new HashMap<String, String>();
			// 现支持的文件类型
			switch (suffix) {
			case ".xls":
			case ".xlsx":
			case ".doc":
			case ".docx":
			case ".ppt":
			case ".pptx":
			case ".jpe":
			case ".jpeg":
			case ".jpg":
			case ".png":
				IOUtil.uploadFile(mf, FILEPATH, newFileName + suffix);
				result.put("fileCode", newFileName);
				result.put("fileName", oldFileName);
				result.put("fileType", suffix);
				break;
			default:
				return rtnFailResult(Result.ERROR_4300, "暂时不支持【" + suffix + "】该类型文件格式");
			}
			return rtnSuccessResult("", result);
		}
		return rtnFailResult(Result.ERROR_4000, "文件数据为空,上传失败");
	}

	@Override
	public Result<KbFile> selFileByFileCode(int fileLevel, String fileCode) throws Exception {
		String fileTableName = kftm.selectFileTableNameByFileLevel(fileLevel);
		try {
			if (StrUtil.isBlank(fileTableName)) {
				return rtnFailResult(Result.ERROR_4000, "无效的文件层级");
			}
			KbFile fileObj = kfm.selectFileByFileCode(fileTableName, fileCode);
			return null == fileObj ? rtnFailResult(Result.ERROR_4000, "文件不存在或已被移除") : rtnSuccessResult("", fileObj);
		} catch (SQLException e) {
			log.error("根据文件编码查询文件信息数据接口异常,异常原因【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "获取文件信息异常,请联系系统管理员");
		}
	}

	@Override
	public ResponseEntity<byte[]> downloadPdf(String filePath, String fileCode, String fileName) throws Exception {
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		String pdfFileName = "";
		String fileRootPath = filePath + File.separator + fileCode + suffix;// 获得文件路径
		switch (suffix) {
		case ".xls":
		case ".xlsx":
			pdfFileName = AsposeUtil.excel2PDFStr(fileRootPath);
			break;
		case ".doc":
		case ".docx":
			pdfFileName = AsposeUtil.word2PDFStr(fileRootPath);
			break;
		case ".ppt":
		case ".pptx":
			// 暂未开放，敬请期待
			break;
		default:
			log.error("未知文件转换PDF错误,错误文件类型【" + suffix + "】");
			return null;
		}
		if (StrUtil.isBlank(pdfFileName)) {
			return null;
		}
		pdfFileName = pdfFileName.substring(pdfFileName.lastIndexOf(File.separator) + 1);
		ResponseEntity<byte[]> downloadFile = IOUtil.downloadFile("pdf", pdfFileName);
		IOUtil.clearTempPdf(null, pdfFileName);
		return downloadFile;
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public Result<Object> insSuperiorUserFileWithOnlyRead(KbFile kf, String userDeptCode) throws Exception {

		List<KbUser> superiorUsers = kum.selectSuperiorUserByUserDeptCode(userDeptCode);
		if (null == superiorUsers || superiorUsers.isEmpty()) {
			return rtnSuccessResult();
		}
		List<KbFileUser> kfuList = new ArrayList<KbFileUser>();
		for (KbUser ku : superiorUsers) {
			KbFileUser kfu = new KbFileUser();
			kfu.setFileCode(kf.getFileCode());
			kfu.setFileName(kf.getFileName());
			kfu.setFileType(kf.getFileType());
			kfu.setUserCode(ku.getUserCode());
			kfu.setFilePermission("onlyread");
			kfu.setCreateUserCode(kf.getCreateUserCode());
			kfu.setCreateTime(DateUtil.curDateYMDHMS());
			kfuList.add(kfu);
		}
		try {
			int insNum = kfm.insertSuperiorUserFileWithOnlyRead(kfuList);
			if (insNum == kfuList.size()) {
				return rtnSuccessResult("默认添加上级部门领导层文件预览成功");
			} else {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
				return rtnFailResult(Result.ERROR_4000, "默认添加上级部门领导层文件预览成功");
			}
		} catch (SQLException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
			log.error("默认添加上级部门领导层文件预览接口异常,异常原因:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "默认添加上级部门领导层文件预览接口异常");
		}
	}

	@Override
	public Result<List<Map<String, Object>>> selectFile(int projectLevel, String userCode, String projectCode)
			throws Exception {
		try {
			String fileTableName = kftm.selectFileTableNameByFileLevel(projectLevel);
			List<Map<String, Object>> fileInfoList = kfm.selectFileByUserCode(fileTableName, projectCode, userCode);
			for (Map<String, Object> map : fileInfoList) {
				System.err.println(map.toString());
			}
			return rtnSuccessResult("", fileInfoList);
		} catch (SQLException e) {
			log.error("根据部门编码查询上级部门的领导层用户,异常信息:【" + e.toString() + "】");
			return rtnErrorResult(Result.ERROR_6000, "查询系统异常,请联系系统管理员");
		}
	}

	@Override
	public Map<String, Object> getShareFile(String fileCode, int fileLevel, String projectCode) throws Exception {
		// 因为想在项目表层级与文件表层级相同 fileLevel/projectLevel 相同
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 查询项目信息
		String projectTableName = kptm.selectProjectTableNameByProjectLevel(fileLevel);
		KbProject shareProject = kpm.selectProjectByProjectCode(projectTableName, projectCode);
		// 查询文件信息
		String fileTableName = kftm.selectFileTableNameByFileLevel(fileLevel);
		KbFile shareFile = kfm.selectFileByFileCode(fileTableName, fileCode);
		if (null != shareProject && null != shareFile) {
			resultMap.put("shareProject", shareProject);
			resultMap.put("shareFile", shareFile);
		}
		return resultMap;
	}

}
