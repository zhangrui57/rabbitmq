/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.rabbitmq.aspect;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.rabbitmq.annotation.SysLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * 系统日志，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.3.0 2017-03-08
 */
@Aspect
@Component
public class SysLogAspect {

	@Pointcut("@annotation(com.example.rabbitmq.annotation.SysLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysLog(point, time);

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SysLog syslog = method.getAnnotation(SysLog.class);

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();

		//请求的参数
		Object[] args = joinPoint.getArgs();
		String param = "";
		try {
			JSONObject params = (JSONObject)  JSONObject.toJSON(args[0]);
			param = params.toJSONString();
		}catch(Exception e){
			try{
				JSONArray params = (JSONArray)  JSONArray.toJSON(args[0]);
				param = params.toJSONString();
			}catch(Exception x){
				try{
					param = args[0].toString();
				}catch(Exception t){

				}
			}
		}
//		//获取request
//		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//		//设置IP地址
//		sysLog.setIp(IPUtils.getIpAddr(request));
//		sysLog.setUsername(shiroTokenUtils.getUserName());
//		sysLog.setTime(time);
//		sysLog.setCreateDate(new Date());
//		//保存系统日志
//		sysLogService.insert(sysLog);
		System.out.println(syslog.value()+className + "." + methodName + "()"+param);
	}
}
