/**
 * 
 */
package com.xh.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xh.base.BaseService;
import com.xh.dao.KbOperationLogMapper;
import com.xh.entity.KbOperationLog;
import com.xh.service.IKbOperationLogService;

/**
 * @author 陈专懂
 * @date 2018年7月4日下午4:28:18
 * @version 1.0
 */
@Service("kbOperationLogServiceImpl")
public class KbOperationLogServiceImpl extends BaseService implements IKbOperationLogService{
	private static final Logger log = LoggerFactory.getLogger(KbOperationLogServiceImpl.class);// 日志对象

	@Autowired
	private KbOperationLogMapper kbLog;//日志表数据接口
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int addLog(KbOperationLog kblog) {
		int ret=kbLog.addLog(kblog);//添加日志的返回值
		if(ret==0){
			log.error("日志添加失败");
		}
		return ret;
	}

}
