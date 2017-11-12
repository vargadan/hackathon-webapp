package com.vargadaniel.hackathon.filter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.vargadaniel.hackathon.util.HackUtil;

public class LogFilter implements Filter {
	
	public static HackUtil hackUtil = new HackUtil();
	
	private static File accessLogFile;
	private static FileWriter accessLogWriter;
	
	static {
		initAccessLogFile();	
	}

	public static void initAccessLogFile() {
		try {
			accessLogFile = new File("/var/tmp/tomcatAccessLog");
			accessLogWriter = new FileWriter(accessLogFile, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String requestSimpleString = hackUtil.getRequestString((HttpServletRequest) request);
		if (!accessLogFile.exists()) {
			initAccessLogFile();
		} 
		accessLogWriter.write(requestSimpleString + "\n");
		accessLogWriter.flush();
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
