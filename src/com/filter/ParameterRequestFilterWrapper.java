package com.filter;


import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 
 * @author 林敏忠
 * 主要修改req.getParameterMap()中上锁的数据，
 * 直接通过req.getParameterMap().put(key,value)会报异常java.langIllegalStateException:No modifications are allowed to a locked ParameterMap
 * 所以通过这种方式间接修改数据
 *
 */
//为了让编译器不报警告
@SuppressWarnings("unchecked")
public class ParameterRequestFilterWrapper extends HttpServletRequestWrapper {
	//接收传过来的新的参数
	private Map params;
	
	public ParameterRequestFilterWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 传入间接修改传入参数集合的构造函数
	 */
	public ParameterRequestFilterWrapper(HttpServletRequest request,Map params){
		this(request);
		this.params = params;
	}
	
	/**
	 * 重写获取key的方法
	 */
	@Override
	public Enumeration getParameterNames(){
		Vector names = new Vector(params.keySet());
		return names.elements();
	}
	
	/**
	 * 重写获取多值values的方法
	 */
	@Override
	public String getParameter(String name){
		Object v = params.get(name);
		if(v == null){
			return null;
		}else if(v instanceof String[]){
			String[] strArr = (String[])v;
			if(strArr.length > 0){
				return strArr[0];
			}else{
				return null;
			}
		}else if(v instanceof String){
			return (String)v;
		}else{
			return v.toString();
		}
	}
}
