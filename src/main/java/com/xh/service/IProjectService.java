/**
 * 
 */
package com.xh.service;

import com.xh.entity.KbProject;
import com.xh.uitl.Result;

/**
 * @author 陈专懂
 * @date 2018年6月21日下午3:24:06
 * @version 1.0
 */
public interface IProjectService {
	//动态建表
	public Result<Object> createProjectList(KbProject kbproject);

	
}
