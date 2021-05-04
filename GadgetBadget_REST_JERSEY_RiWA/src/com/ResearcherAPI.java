package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ResearcherAPI") 
public class ResearcherAPI extends HttpServlet 
{ 
	
	ResearcherManagement researcherManagementObj = new ResearcherManagement();
	//Code goes here....
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		String output = researcherManagementObj.insertResearcher(
		request.getParameter("resCode"), 
		request.getParameter("resName"), 
		request.getParameter("resEmail"), 
		request.getParameter("resAge"),
		request.getParameter("resAddress"), 
		request.getParameter("resRole"), 
		request.getParameter("joinDate"));
		
		response.getWriter().write(output); 
	}

	
	
	
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
		Map<String, String> map = new HashMap<String, String>(); 
		try
		 { 
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			 String queryString = scanner.hasNext() ? 
			 scanner.useDelimiter("\\A").next() : ""; 
			 scanner.close(); 
			 String[] params = queryString.split("&"); 
			 for (String param : params) 
			 {
				 String[] p = param.split("=");
				 map.put(p[0], p[1]); 
			 } 
		 } 
		catch (Exception e) 
		 { 
			//catch..
		 } 
		return map; 
	 }
	
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		 Map paras = getParasMap(request); 
		 
		 String output = researcherManagementObj.updateResearcher(paras.get("hidItemIDSave").toString(), 
		 paras.get("resCode").toString(), 
		 paras.get("resName").toString(), 
		 paras.get("resEmail").toString(), 
		 paras.get("resAge").toString(), 
		 paras.get("resAddress").toString(), 
		 paras.get("resRole").toString(), 
		 paras.get("joinDate").toString()); 
		 response.getWriter().write(output); 
	}
	
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		 Map paras = getParasMap(request); 
		 
		 String output = researcherManagementObj.deleteResearcher(paras.get("resID").toString()); 
		 response.getWriter().write(output); 
	}
	
	
	
	
}