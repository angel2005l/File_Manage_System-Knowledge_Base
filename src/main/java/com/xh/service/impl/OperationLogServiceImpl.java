/**
 * 
 */
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
import com.xh.dao.KbOperationLogMapper;
import com.xh.dao.KbUserAdviceMapper;
import com.xh.entity.KbOperationLog;
import com.xh.entity.KbUserAdvice;
import com.xh.service.IOperationLogService;
import com.xh.uitl.Result;

/**
 * @author 陈专懂
 * @date 2018年7月4日下午4:28:18
 * @version 1.0
 */
@Service("OperationLogServiceImpl")
public class OperationLogServiceImpl extends BaseService implements IOperationLogService{
	private static final Logger log = LoggerFactory.getLogger(OperationLogServiceImpl.class);// 日志对象

	@Autowired
	private KbOperationLogMapper kbLog;//日志表数据接口
	@Autowired
	private KbUserAdviceMapper kbAdvice;//通知表数据接口
	
	@Transactional(rollbackFor = Exception.class)
	public int addLog(KbOperationLog kblog) {
		int ret=kbLog.addLog(kblog);//添加日志的返回值
		if(ret==0){
			log.error("日志添加失败");
		}
		return ret;
	}

	@Override
	public int updateLogByRead(String logUserCode) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 根据projectCode去projectUser表查询关联userCode
	 * @throws Exception 
	 */
	public Result<List<String>> parentUserCodeByCode(String parentProjectCode) throws Exception {
		try{
			List<String> userCodeList=kbAdvice.parentUserCodeByCode(parentProjectCode);
			if(null!=userCodeList&&!userCodeList.isEmpty()){
				return rtnSuccessResult("查询项目父类下所有的相关人员的code成功", userCodeList);
			}
			return rtnErrorResult(Result.ERROR_6000, "查询项目父类下所有的相关人员的接口异常");
		}catch(SQLException e){
			log.error("通知功能：根据projectCode去projectUser表查询关联userCode的接口异常，异常原因【"+e.toString()+"】");
			return rtnErrorResult(Result.ERROR_6000, "查询项目父类下所有的相关人员的接口异常");
		}
	}


	/**
	 * 添加相关的数据到通知表中
	 * @throws Exception 
	 */
	@Transactional(rollbackFor = { Exception.class })
	public Result<Object> addUserAdvice(List<KbUserAdvice> kbUserAdviceList) throws Exception {
		try{
		for (KbUserAdvice kbUserAdvice : kbUserAdviceList) {
			if(null!=kbUserAdvice){
				int ret=kbAdvice.addUserAdvice(kbUserAdvice);//添加的返回值
				if(ret==0){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
					return rtnErrorResult(Result.ERROR_6000, "通知表信息写入接口异常:新增异常,请联系系统管理员");
				}
			}else{
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
				return rtnErrorResult(Result.ERROR_6000, "通知表信息写入接口异常：数据传入异常,请联系系统管理员");
			}
		}
		return rtnSuccessResult("通知信息表数据写入成功");
		}catch(SQLException e){
			log.error("添加相关数据到通知表中的接口异常，异常信息【"+e.toString()+"】");
			return rtnErrorResult(Result.ERROR_6000, "通知写入系统异常,请联系管理员");
		}
	}
}
