package com.xh.service;

import java.util.List;
import java.util.Map;

import com.xh.entity.KbDic;
import com.xh.uitl.Result;

public interface IDicService {
	/**
	 * 
	 * @Title: selFileEventTypeForAll
	 * @Description: 全部的文件事件类型
	 * @author 黄官易
	 * @return
	 * @throws Exception
	 * @return Result<List<KbDic>>
	 * @date 2018年7月25日
	 * @version 1.0
	 */
	public Result<List<KbDic>> selFileEventTypeForAll() throws Exception;

	/**
	 * 
	 * @Title: selFileEventTypeValueByCode
	 * @Description: 根据编码查询文件事件类型的值
	 * @author 黄官易
	 * @param code
	 * @return
	 * @throws Exception
	 * @return Result<KbDic>
	 * @date 2018年7月25日
	 * @version 1.0
	 */
	public Result<String> selFileEventTypeValueByCode(String code) throws Exception;

	/**
	 * 
	 * @Title: selFileEventLevelForAll
	 * @Description: 全部的文件事件等级
	 * @author 黄官易
	 * @return
	 * @throws Exception
	 * @return Result<List<KbDic>>
	 * @date 2018年7月25日
	 * @version 1.0
	 */
	public Result<List<KbDic>> selFileEventLevelForAll() throws Exception;

	/**
	 * 
	 * @Title: selFileEventLevelValueForAll
	 * @Description: 根据编码查询文件事件等级的值
	 * @author 黄官易
	 * @return
	 * @throws Exception
	 * @return Result<KbDic>
	 * @date 2018年7月25日
	 * @version 1.0
	 */
	public Result<String> selFileEventLevelValueByCode(String Code) throws Exception;

	/**
	 * 
	 * @Title: selDicKvForAll
	 * @Description: 获得特定的数据字典集合
	 * @author 黄官易
	 * @param dicParentCode
	 * @return
	 * @throws Exception
	 * @return Result<String>
	 * @date 2018年7月26日
	 * @version 1.0
	 */
	public Result<List<Map<String, String>>> selDicKvForAll(String dicParentCode) throws Exception;

	/**
	 * 
	 * @Title: selDicValueByCode
	 * @Description: 根据编码查询数据字典值
	 * @author 黄官易
	 * @param dicParentCode
	 * @param code
	 * @return
	 * @throws Exception
	 * @return Result<String>
	 * @date 2018年7月26日
	 * @version 1.0
	 */
	public Result<String> selDicValueByCode(String dicParentCode, String code) throws Exception;
}
