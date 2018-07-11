/**
 * 
 */
package com.xh.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xh.entity.KbOperationLog;
import com.xh.entity.KbUserAdvice;
import com.xh.service.IOperationLogService;
import com.xh.uitl.DateUtil;
import com.xh.uitl.Result;
import com.xh.uitl.StrUtil;

/**
 * 切点类
 * 
 * @author 陈专懂
 * @date 2018年7月4日下午4:26:34
 * @version 1.0
 */

@Aspect
@Component
public class SystemLogAspect {
	// 注入Service用于把日志保存数据库
	@Resource
	private IOperationLogService kolService;
	@Autowired
	KbOperationLog log;
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

	// //Service层切点
	// @Pointcut("@annotation(com.annotation.SystemServiceLog)")
	// public void serviceAspect() {
	// }

	// Controller层切点
	@Pointcut("@annotation(com.xh.aop.SystemControllerLog)")
	public void controllerAspect() {
	}

	/**
	 * 环绕通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 * @throws Throwable
	 */
	@AfterReturning(pointcut="controllerAspect()",returning="result")
	public void doAround(JoinPoint jp,Result<Object> result) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		String logUserCode = session.getAttribute("user_code").toString();
		String logUserName = session.getAttribute("user_name").toString();
		// 请求的IP
		String ip = request.getRemoteAddr(); // 暂时没用
		// 设立变量
		String logStatus = "error";
		String projectCode = "";
		String parentProjectCode = "";// 父类项目的父类项目编码
		int parentProjectLevel = 0;// 新建项目的父类等级
		int code =result.getCode();
		String logCode = "L" + DateUtil.curDateYMDHMSForService() + StrUtil.getRandom((int) (Math.random() * 10000), 4);// 日志编号
		String logMsg = logUserName + ",操作了:" + getControllerMethodDescription(jp).get("description");// 日志信息
		String errorLogMsg = "";// 异常的信息抓取
		String isAdvice = getControllerMethodDescription(jp).get("isAdvice");
		// 获取request值
		Object[] obj = jp.getArgs();
		for (Object object : obj) {
			if (object instanceof HttpServletRequest) {
				request = (HttpServletRequest) object;
				projectCode = (String) request.getAttribute("project_code");
				parentProjectCode = request.getParameter("project_parent_code");
				parentProjectLevel = Integer.parseInt(request.getParameter("project_level"));
				errorLogMsg = logUserName + ",操作时出现异常，异常信息为：" + result.getMsg();
			}
		}
		try {
			if (code == 0) {
				logStatus = "success";
			} else {
				logMsg = errorLogMsg;
				isAdvice = "false";
			}
			// *========控制台输出=========*//
			System.out.println("=====后置returning通知开始=====");
			System.out.println(
					"请求方法:" + (jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + "()"));
			System.out.println("方法描述:" + logUserName + ",操作了:" + getControllerMethodDescription(jp).get("description"));
			System.out.println("请求人:" + logUserName);
			System.out.println("请求IP:" + ip);
			// *========数据库通知=========*//
			if (("true").equals(isAdvice) || parentProjectLevel != 0) {
				List<KbUserAdvice> kbUserAdviceList = new ArrayList<KbUserAdvice>();
				// 若该level==0，则无上级，不需要通知任何人了，否则通知上级项目的所有组员
				if (null != parentProjectCode && !parentProjectCode.isEmpty()) {
					List<String> userCodeList = kolService.parentUserCodeByCode(parentProjectCode);
					for (String userCode : userCodeList) {
						KbUserAdvice kbUserAdvice = new KbUserAdvice();
						kbUserAdvice.setAdviceCode("ADV" + DateUtil.curDateYMDHMSForService()
								+ StrUtil.getRandom((int) (Math.random() * 100), 2));// 通知编号
						kbUserAdvice.setLogCode(logCode);
						kbUserAdvice.setLogMsg(logMsg);
						kbUserAdvice.setAdviceStatus("N");
						kbUserAdvice.setCreateTime(DateUtil.curDateYMDHMS());
						kbUserAdvice.setUserCode(userCode);
						kbUserAdviceList.add(kbUserAdvice);
					}
					kolService.addUserAdvice(kbUserAdviceList);
				}
			}
			;
			// *========数据库日志=========*//
			log.setLogCode(logCode);
			log.setLogMsg(logMsg);
			log.setLogType(getControllerMethodDescription(jp).get("logType"));
			log.setLogStatus(logStatus);
			log.setProjectCode(projectCode);
			log.setLogIsRead("N");
			log.setLogUserCode(logUserCode);
			log.setLogUserName(logUserName);
			log.setCreateUserCode(logUserCode);
			log.setCreateTime(DateUtil.curDateYMDHMS());
			// 保存数据库
			kolService.addLog(log);
			System.out.println("=====后置returning通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==通知异常==");
			logger.error("异常信息:{}", e.getMessage());
		}
	}

//	 @AfterReturning(pointcut="controllerAspect()",returning="result")
//	 public void doAfterReturning(JoinPoint point, Result<Object> result) {
//	 System.out.println("====后置Return通知开始=====");
//	 System.out.println("获取@After中的返回值："+result);
//	 System.out.println("=====后置Return通知结束=====");
//	
//	 }
	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static Map<String, String> getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		String logType = "";
		String isAdvice = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemControllerLog.class).description();
					logType = method.getAnnotation(SystemControllerLog.class).logType();
					isAdvice = method.getAnnotation(SystemControllerLog.class).isAdvice();
					map.put("description", description);
					map.put("logType", logType);
					map.put("isAdvice", isAdvice);
					break;
				}
			}
		}
		return map;
	}
}
