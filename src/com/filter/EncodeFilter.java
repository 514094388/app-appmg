package com.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodeFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		/**
		 * 获取请求对象
		 */
		HttpServletRequest req = (HttpServletRequest)request;
		/**
		 * 获取请求的方式
		 */
		String method = req.getMethod();
		/**
		 * 判断请求的方式进行转编码
		 */
		if(method.equals("POST")){
			req.setCharacterEncoding("UTF-8");
			//继续访问
			chain.doFilter(req,response);
		}else{
			Map map = new HashMap();
			/**
			 * 如果是GET请求
			 * 获取请求对象的所有参数的键的集合
			 */
			Enumeration<String> names = req.getParameterNames();
			/**
			 * 遍历键的集合
			 */
			while(names.hasMoreElements()){
				String key = names.nextElement();
				String[] values = req.getParameterValues(key);
				/**
				 * 将每个参数的值进行字符串重构
				 */
				for(int i = 0; i < values.length; i++){
					values[i] = new String(values[i].getBytes("ISO-8859-1"),"UTF-8");
					/**
					 * 将重新编码后的数据放到map中
					 */
					map.put(key,values[i]);
				}
			}
			/**
			 * 通过包装类进行对req.getParameterMap() map中的数据间接设置
			 */
			ParameterRequestFilterWrapper reqWrapper = new ParameterRequestFilterWrapper(req,map);
			//继续执行过滤器
			chain.doFilter(reqWrapper, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
