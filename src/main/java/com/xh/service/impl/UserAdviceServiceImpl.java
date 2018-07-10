/**
 * 
 */
package com.xh.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.xh.base.BaseService;
import com.xh.dao.KbUserAdviceMapper;
import com.xh.entity.KbUserAdvice;
import com.xh.service.IUserAdviceService;
import com.xh.uitl.Result;

/**
 * @author 陈专懂
 * @date 2018年7月9日下午1:05:04
 * @version 1.0
 */
@Service("userAdviceServiceImpl")
public class UserAdviceServiceImpl extends BaseService implements IUserAdviceService{
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class); // 日志对象
	
	@Autowired
	private KbUserAdviceMapper kbAdviceMapper;

	/**
	 * 获取用户未读的通知消息
	 */
	public List<KbUserAdvice> getAdviceMsgByUser(String userCode) {
		if(null!=userCode&&!userCode.isEmpty()){
			List<KbUserAdvice> adviceList=kbAdviceMapper.getAdviceMsgByUser(userCode);
			if(null!=adviceList){
				return adviceList;
			}else{
				log.error("通知功能：查询未读通知的接口出错，请联系管理员!");
				return null;
			}
		}
		log.error("通知功能：userCode输入为空，请联系管理员!");
		return null;
	}

	/**
	 * 用户已读通知，修改数据库通知的状态
	 */
	@Transactional(rollbackFor = { Exception.class })
	public Result<Object> updateAdviceStatusByAdviceCode(List<String> adviceCode) {
		if(null==adviceCode||adviceCode.isEmpty()){
			return rtnFailResult(Result.ERROR_4000, "用户已读传入数据错误，数据为空");
		}
		for (String code : adviceCode) {
			int ret=kbAdviceMapper.updateAdviceStatusByAdviceCode(code);
			if(ret==0){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚
				return rtnFailResult(Result.ERROR_4000, "通知已读状态修改失败！");
			}
		}
		return rtnSuccessResult("已读状态修改成功");
	}
	
}
