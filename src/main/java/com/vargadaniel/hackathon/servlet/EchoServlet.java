package com.vargadaniel.hackathon.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vargadaniel.hackathon.util.HackUtil;

/**
 * Servlet implementation class TestServlet
 */
public class EchoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HackUtil hackUtil = new HackUtil();

    /**
     * Default constructor. 
     */
    public EchoServlet() {
    }

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.getOutputStream().write(hackUtil.getRequestString(req).getBytes());
		res.getOutputStream().flush();
	}
	




}
