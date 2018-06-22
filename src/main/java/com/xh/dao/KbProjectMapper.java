package com.xh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import com.xh.entity.KbProject;
import com.xh.entity.KbUser;

public interface KbProjectMapper {
    KbProject selectByPrimaryKey(Integer id);
    //添加项目信息
	public int insertProject(Map<String,Object> map) throws SQLException;

}