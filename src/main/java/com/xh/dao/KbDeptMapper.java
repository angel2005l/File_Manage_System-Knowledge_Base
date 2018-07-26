package com.xh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface KbDeptMapper {

	public List<Map<String, String>> selectDeptForAll() throws SQLException;

}