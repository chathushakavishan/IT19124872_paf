package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class ProjectAPI
 */
@WebServlet("/ProjectAPI")
public class ProjectAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Project projectObj = new Project();

    /**
     * Default constructor. 
     */
    public ProjectAPI() {
        // TODO Auto-generated constructor stub
    }

    /**
   	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   	 */
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		// TODO Auto-generated method stub
   		response.getWriter().append("Served at: ").append(request.getContextPath());
   	}

   		
   	/**
   	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   	 */
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		// TODO Auto-generated method stub
   		System.out.println("Got insert");

   		String output = projectObj.insertProject(
   				request.getParameter("name"), 
   				request.getParameter("budget"), 
   				request.getParameter("category"), 
   				request.getParameter("description")); 
   				response.getWriter().write(output);
   				
   				String jspName = request.getParameter("hidProjectIDSave");
   				RequestDispatcher rd = request.getRequestDispatcher(jspName);

   				rd.forward(request, response);
   		}

   	
   	/**
   	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
   	 */
   	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		// TODO Auto-generated method stub
   		System.out.println("Got here");
   		Map paras = getParasMap(request); 
   		 String output = projectObj.updateProject(
    		 paras.get("Name").toString(), 
   		 paras.get("Category").toString(), 
   		 paras.get("Budget").toString(), 
   		 paras.get("Description").toString(),
   		 Integer.parseInt((String) paras.get("hidProjectID")));
   		 response.getWriter().write(output); 
   	}

   		
   	/**
   	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
   	 */
   	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		// TODO Auto-generated method stub
   		Map paras = getParasMap(request); 
   		 String output = projectObj.deleteProject(paras.get("ProjectID").toString()); 
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
   	 } 
   	return map; 
   	}


   }
