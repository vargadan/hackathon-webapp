package com.vargadaniel.hackathon.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

public class HackUtil {
	
	public static String SEPARATOR = "; ";
	
	DateFormat dtf =  new SimpleDateFormat("HH:mm:ss.SSS");
	
	public String getRequestString(HttpServletRequest req) {
		StringBuffer sb = new StringBuffer();
		sb.append(dtf.format(new Date())).append(" ").append(req.getMethod()).append(" ").append(req.getRequestURL()).append(SEPARATOR);
		sb.append("parameters:");
		for (Object paramName : req.getParameterMap().keySet()) {
			Object paramValue = req.getParameterMap().get(paramName);
			if (paramValue instanceof Object[])  {
				paramValue = Arrays.toString((Object[]) paramValue);
			}
			sb.append(paramName).append("=").append(paramValue).append(",");
		}
		sb.append(SEPARATOR).append("headers:");
		Enumeration<String> headerNames = req.getHeaderNames(); 
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = req.getHeader(headerName);
			sb.append(headerName).append("=").append(headerValue).append(",");
		}
		sb.append(SEPARATOR);
		sb.append("remoteaddr:").append(req.getRemoteAddr()).append(SEPARATOR);
		sb.append("remotehost:").append(req.getRemoteHost()).append(SEPARATOR);
		return sb.toString();
	}
	
	public static String runProcess(String[] command, String[] envp, File dir) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		if (envp == null) {
			envp = new String[0];
		}
		Process process = null;
		if (dir == null) {
			process = runtime.exec(command, envp);
		} else {
			process = runtime.exec(command, envp, dir);
		}
		try {
			process.waitFor(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		}
		InputStream pis = process.getInputStream();
		int available = pis.available();
		byte[] bytes = new byte[available];
		pis.read(bytes);
		return new String(bytes);
	}
	
	public static void main(String... args) throws Exception {
//		System.out.println(runProcess(new String[]{"/bin/bash", "-c", "less /var/tmp/tomcatAccessLog | grep echo"}, null, null));
		System.out.println(runProcess(new String[]{"/bin/bash", "-c", "ls -la"}, null, null));
	}

}
