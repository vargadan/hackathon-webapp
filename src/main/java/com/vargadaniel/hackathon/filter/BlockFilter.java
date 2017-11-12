package com.vargadaniel.hackathon.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vargadaniel.hackathon.util.HackUtil;

public class BlockFilter implements Filter {
	
	public static String[] PATTERNS = new String[] {"Runtime", "ProcessBuilder"};
	
	public static HackUtil hackUtil = new HackUtil();

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String requestSimpleString = hackUtil.getRequestString((HttpServletRequest) request); 
		for (String pattern : PATTERNS) {
			if (requestSimpleString.indexOf(pattern) >= 0) {
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.sendError(500, "The request contained forbidden input : " + pattern);
				System.err.println("The request contained forbidden input : " + pattern + " : \n" + requestSimpleString);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
