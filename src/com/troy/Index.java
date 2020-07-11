package com.troy;

import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Index
 */
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Index() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//List<String> stateNames = new ArrayList<String>();
		TroyCache cache = TroyCache.getInstance();
		
		System.out.println("[TROY] Getting cache instance...");
		List<String> stateNames = cache.stateNames;
		
		try {
            if (stateNames.isEmpty()) {
            	System.out.println("[TROY] Empty list ...");
            }
            else {
            	System.out.println("[TROY] setting cache to request object.");
            	request.setAttribute("stateNames", stateNames);
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
		    
		    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
