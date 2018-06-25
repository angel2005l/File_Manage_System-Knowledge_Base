package com.xh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xh.entity.KbProject;
import com.xh.entity.KbUser;

public interface KbProjectMapper {
    KbProject selectByPrimaryKey(Integer id);
    //添加项目信息
	public int insertProject(@Param("kb") KbProject kbObj,@Param("formName") String formName) throws SQLException;

	//查询所有的项目信息
	public List<KbProject> selectProject(@Param("formName")String formName,@Param("projectParentCode")String projectParentCode);
}