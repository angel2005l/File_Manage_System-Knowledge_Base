/**
 * 
 */
package com.xh.service;


import java.util.List;
import java.util.Map;


import com.xh.entity.KbProjectTable;
import com.xh.uitl.Result;

/**
 * @author 陈专懂
 * @date 2018年6月21日下午3:24:06
 * @version 1.0
 */
public interface IProjectService {
	//动态建表
	public Result<Object> createProjectList(KbProjectTable kbpt);

	//根据项目等级查询项目的编号、名字
	public Result<Object> selectProjectTableNameByProjectLevel(int projectLevel);
	//添加项目信息
	public Result<Object> insertProject(Map<String,Object> map);
	//根据员工的编号，查找员工信息
	public Result<Object> selectUserByUserCode(List<String> strList);
	//查询所有的根层的项目信息
	public Result<Object> selectAllPro(String formName);
}
