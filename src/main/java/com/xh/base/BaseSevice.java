package com.xh.base;

import com.xh.uitl.Result;

public class BaseSevice {
	/**
	 * 返回结果集合
	 */
	/**
	 * 
	 * @Title: rtnFailResult
	 * @Description: 返回失败结果 并携带信息
	 * @param code
	 * @param msg
	 * @return
	 * @return Result<T>
	 * @author 黄官易
	 * @date 2018.04.13
	 */
	public <T> Result<T> rtnFailResult(int code, String msg) {
		return new Result<T>(code, msg);
	}

	/**
	 * 
	 * @Title: rtnFailResult
	 * @Description: 返回失败结果 并携带信息和数据
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 * @return Result<T>
	 * @author 黄官易
	 * @date 2018.04.13
	 */
	public <T> Result<T> rtnFailResult(int code, String msg, T data) {
		return new Result<T>(code, msg, data);
	}

	/**
	 * 
	 * @Title: rtnSuccessResult
	 * @Description: 返回成功结果
	 * @return
	 * @return Result<T>
	 * @author 黄官易
	 * @date 2018.04.13
	 */
	public <T> Result<T> rtnSuccessResult() {
		return new Result<T>(Result.SUCCESS_0);
	}

	/**
	 * 
	 * @Title: rtnSuccessResult
	 * @Description: 返回成功结果 并携带信息
	 * @param msg
	 * @return
	 * @return Result<T>
	 * @author 黄官易
	 * @date 2018.04.13
	 */
	public <T> Result<T> rtnSuccessResult(String msg) {
		return new Result<>(Result.SUCCESS_0, msg);
	}

	/**
	 * 
	 * @Title: rtnSuccessResult
	 * @Description: 返回成功结果 并携带信息和数据
	 * @param msg
	 * @param data
	 * @return
	 * @return Result<T>
	 * @author 黄官易
	 * @date 2018.04.13
	 */
	public <T> Result<T> rtnSuccessResult(String msg, T data) {
		return new Result<T>(Result.SUCCESS_0, msg, data);
	}

	/**
	 * 
	 * @Title: rtnErrorResult
	 * @Description: 返回异常结果 并携带信息
	 * @param code
	 * @param msg
	 * @return
	 * @return Result<T>
	 * @author 黄官易
	 * @date 2018.04.13
	 */
	public <T> Result<T> rtnErrorResult(int code, String msg) {
		return new Result<T>(code, msg);
	}

	/**
	 * 
	 * @Title: rtnErrorResult  
	 * @Description: 返回异常结果 并携带信息和数据 
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 * @return Result<T>
	 * @author 黄官易
	 * @date 2018.04.13
	 */
	public <T> Result<T> rtnErrorResult(int code, String msg, T data) {
		return new Result<T>(code, msg, data);
	}

	/**
	 * 
	 * @Title: rtnDefault  
	 * @Description: 返回默认状态
	 * @param code
	 * @return
	 * @return Result<T>
	 * @author 黄官易
	 * @date 2018.04.13
	 */
	public <T> Result<T> rtnDefault(int code) {
		return new Result<T>(code);
	}

	/**
	 * 
	 * @Title: rtnDefaultMsg  
	 * @Description: 返回默认状态 并携带信息
	 * @param code
	 * @param msg
	 * @return
	 * @return Result<T>
	 * @author 黄官易
	 * @date 2018.04.13
	 */
	public <T> Result<T> rtnDefaultMsg(int code, String msg) {
		return new Result<T>(code, msg);
	}

	/**
	 * 
	 * @Title: rtnDefaultMsg  
	 * @Description: 返回默认状态 并携带信息和数据
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 * @return Result<T>
	 * @author 黄官易
	 * @date 2018.04.13
	 */
	public <T> Result<T> rtnDefaultMsg(int code, String msg, T data) {
		return new Result<T>(code, msg, data);
	}

	/**
	 * 
	 * @Title: rtnPageWithCount
	 * @Description: 返回数据结果
	 * @param totalResult
	 * @param data
	 * @return
	 * @return Result<Page<T>>
	 * @author 黄官易
	 * @date 2018.03.21
	 */
	// public <T> Result<Page<T>> rtnPageWithCount(int showCount, int totalResult,
	// List<T> data) {
	// return new Result<Page<T>>(Result.SUCCESS, "", new Page<T>(showCount,
	// totalResult, data));
	// }

}
