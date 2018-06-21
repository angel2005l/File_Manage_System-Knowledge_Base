package com.xh.service;

import java.util.List;

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
	public Result<Object> inseFileTable(KbFileTable kft) throws Exception;
}
