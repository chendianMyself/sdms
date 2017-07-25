package com.oracle.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.domain.user;


//拦截请求路径以 .do结尾的所有请求

//如果需要配置过滤器参数,则建议使用配置文件式,而不适用注解
public class PurviewFilter implements Filter{

	public void destroy() {
		
	}

	//执行过滤
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//当我们确认请求一定是Http或https协议时,可以使用强制转换,转换为HttpServletRequest对象
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)resp;
		
		user users=(user) request.getSession().getAttribute("user");
		
		System.out.println(users);
		//首次准备登录时,用户为空
		//所以,如果请求的是登录操作,则不予判断session,如果是其他操作,才判断session
		String uri=request.getRequestURI();
		System.out.println(uri);
		
		String purview=null;
		//路径包含参数
		if(uri.indexOf('?')>0){
			//获取请求路径的核心路径的起始点和终止点
			int begin=uri.lastIndexOf("/");
			int end=uri.indexOf("?");
			
			purview=uri.substring(begin, end);
		}else{
			//如果不包含参数,则获取最后一段路径
			int begin=uri.lastIndexOf("/");
			purview=uri.substring(begin);
		}
		System.out.println(purview);
		
		//如果请求的路径是登录操作,则放行
		if("/login.do".equals(purview)){
			chain.doFilter(req, resp);
			return;
		}else if(users!=null){
			//如果执行的不是登录请求,但是属于合法用户,则放行
			chain.doFilter(req, resp);
			return;
		}else{
			//不是登录请求,也不是合法用户,拒绝访问,返回登录
			response.sendRedirect("login.do");
			return;
		}
		
		//执行后面的请求链(进入其他过滤器或者servlet)
		//chain.doFilter(req, resp);
		
		
	}

	//获取过滤器配置参数
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}
