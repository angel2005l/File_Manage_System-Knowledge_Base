package com.xh.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.xh.base.BaseService;
import com.xh.dao.KbFileTableMapper;
import com.xh.entity.KbFile;
import com.xh.entity.KbFileTable;
import com.xh.entity.KbFileUser;
import com.xh.service.IFileService;
import com.xh.uitl.Result;

@Service("fileServiceImpl")
public class FileServiceImpl extends BaseService implements IFileService {
	private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);// 日志对象

	@Autowired
	private KbFileTableMapper kftm;

	@Override
	public Result<Object> insFile(KbFile kf, KbFileTable kfb, List<KbFileUser> kfus) throws Exception {
		return null;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Result<Object> inseFileTable(KbFileTable kft) throws Exception {
		if (null == kft) {
			return rtnFailResult(Result.ERROR_4000, "文件表数据为空");
		}
		try {
			// 判断文件是否存在
			if (kftm.isExistFileTable(kft.getFileLevel()) || kftm.isExistFileDataTable(kft.getFtName())) {
				return rtnFailResult(Result.ERROR_4000, "改文件层级已存在或文件表名重复");
			}
			// 新增文件表信息
			int insertFile = kftm.insertFile(kft);
			// 创建文件表
			int createFileTable = kftm.createFileTable(kft.getFtName(), kft.getFileLevel());
			if (insertFile > 0 && createFileTable == 0)
				return rtnSuccessResult("文件表新增成功");
			else
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
			return rtnFailResult(Result.ERROR_4000, "文件表新增失败");
		} catch (SQLException e) {
			log.error("新增表文件数据接口异常,异常原因：+【" + e.toString() + "】");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
			return rtnErrorResult(Result.ERROR_6000, "新增表文件数据接口异常,请联系系统管理员");
		}
	}

}
