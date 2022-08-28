package com.revature.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForRedrController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
	
	String queryParam = request.getParameter("location");	
	
	if("monsters".equals(queryParam)) {
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("monsters");
		
		reqDispatcher.forward(request, response);
	
	}else {
		
		response.sendRedirect("http://www.yahoo.com");	
		
	}
		
	
		
	}
	
}
