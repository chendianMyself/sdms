package com.oracle.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class charsetFilter implements Filter {

	//定义默认字符类型和是否默认启动过滤器
		private String encoding="utf-8";
		private String forceEncoding="true";
		
		public void destroy() {
			// TODO Auto-generated method stub
			
		}

		public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
				throws IOException, ServletException {
			//当且仅当是否启用由  true 4个字母组成,才启用过滤器
			Boolean flag=new Boolean(forceEncoding);
			if(flag){
				req.setCharacterEncoding(encoding);
				resp.setCharacterEncoding(encoding);
			}
			//不论是否使用过滤器,都要继续执行后面的代码
			chain.doFilter(req, resp);
		}

		public void init(FilterConfig config) throws ServletException {
			try {
				//读取配置文件中的参数
				encoding=config.getInitParameter("encoding");
				forceEncoding=config.getInitParameter("forceEncoding");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
