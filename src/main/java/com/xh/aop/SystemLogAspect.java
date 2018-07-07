/**
 * 
 */
package com.xh.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.NotFoundException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xh.entity.KbOperationLog;
import com.xh.service.IKbOperationLogService;
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
	private IKbOperationLogService kolService;
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
	@Around("controllerAspect()")
	public void doAround(ProceedingJoinPoint jp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		String logUserCode = session.getAttribute("user_code").toString();
		String logUserName = session.getAttribute("user_name").toString();
		// 请求的IP
		String ip = request.getRemoteAddr(); // 暂时没用
		// 设立变量
		String logStatus = "false";
		String projectCode = "";
		String logCode = "L" + DateUtil.curDateYMDHMSForService() + StrUtil.getRandom((int) (Math.random() * 10000), 4);
		Object[] obj = jp.getArgs();
		for (Object object : obj) {
			if (object instanceof HttpServletRequest) {
				request = (HttpServletRequest) object;
				projectCode = request.getParameter("project_code");
			}
		}
		try {
			Object resultObj = jp.proceed(); // 获取被切函数的 返回值
			@SuppressWarnings("unchecked")
			Result<Object> res = (Result<Object>) resultObj;
			if (null != res) {
				if (res.getCode() == 0) {
					logStatus = "success";
				}
				;
			}
			// *========控制台输出=========*//
			System.out.println("=====环绕通知开始=====");
			System.out.println(
					"请求方法:" + (jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + "()"));
			System.out.println("方法描述:" + logUserName + ",操作了:" + getControllerMethodDescription(jp).get("description"));
			System.out.println("请求人:" + logUserName);
			System.out.println("请求IP:" + ip);
			// *========数据库日志=========*//
			log.setLogCode(logCode);
			log.setLogMsg(logUserName + ",操作了:" + getControllerMethodDescription(jp).get("description"));
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
			System.out.println("=====环绕通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==环绕通知异常==");
			logger.error("异常信息:{}", e.getMessage());
		}
	}

	// @Before("controllerAspect()")
	// public void doBefore(JoinPoint point) {
	// HttpServletRequest request =
	// ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	// HttpSession session = request.getSession();
	// System.out.println("====前置通知开始=====");
	// session.setAttribute("test", "session测试");
	// System.out.println("====前置通知结束=====");
	// }

	// @After("controllerAspect()")
	// public void doAfter(JoinPoint point) {
	// HttpServletRequest request =
	// ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	// HttpSession session = request.getSession();
	// String projecrCode=request.getAttribute("project_code").toString();
	// System.out.println("====后置通知开始=====");
	// System.out.println("----projectCode:"+projecrCode);
	// System.out.println("-------------ing...-------------------");
	// System.out.println("=====后置通知结束=====获取session测试的值:"+session.getAttribute("test").toString());
	// }

	//// @AfterReturning(pointcut="controllerAspect",returning="logCode")
	// @AfterReturning(
	// pointcut="execution(* com.xh.aop.SystemControllerLog.*(..))",
	// returning="logCode")
	// public void doAfterReturning(JoinPoint point, Object logCode) {
	// HttpServletRequest request =
	//// ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	// HttpSession session = request.getSession();
	// System.out.println("====后置Return通知开始=====");
	// System.out.println("获取@Around中的返回值："+logCode);
	// System.out.println("=====后置Return通知结束=====获取session测试的值:"+session.getAttribute("test").toString());
	//
	// }
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
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemControllerLog.class).description();
					logType = method.getAnnotation(SystemControllerLog.class).logType();
					map.put("description", description);
					map.put("logType", logType);
					break;
				}
			}
		}

		return map;
	}
}
