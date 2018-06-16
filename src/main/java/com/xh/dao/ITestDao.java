package com.xh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xh.entity.Test;

@Repository
public interface ITestDao {
	public List<Test> selInfoByCol(String userName);
}
