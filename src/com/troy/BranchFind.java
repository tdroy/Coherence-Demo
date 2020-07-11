package com.troy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class BranchFind
 */
public class BranchFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BranchFind() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Context context;
		Connection conn = null;
		ResultSet rs = null;
		ResultSetMetaData rsm = null;
		DataSource dataSource = null;
		Statement stmt = null;
		String query;
		String sql = request.getParameter("branchState");
   	    if( sql.isEmpty())
   	    {
   	    	query = "select * from branch";
   	    }
   	    else{
   	    	query = "select * from branch where state_name = '"+sql+"'";
   	    }
		
		
		response.setContentType("text/html");
	    java.io.PrintWriter out = response.getWriter();
	    out.println("<html><head><title>Weblogic Coherence Test, created by Tanmoy</title></head><body>");
	    out.println("<h2><center>Weblogic Coherence Demo</h2> <hr>");
	    out.println("<table border='1'><tr>");
	    
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("jdbc/Mysql");
		    conn = dataSource.getConnection();
		    stmt = conn.createStatement(); 
		    rs = stmt.executeQuery(query);
		    rsm = rs.getMetaData();
		    int colCount = rsm.getColumnCount();
		    //print column names
		    for (int i = 1; i <= colCount; ++i) 
		        out.println("<th>" + rsm.getColumnName(i) + "</th>");
		    
		    out.println("</tr>");
		    
		    while (rs.next()) {
		        out.println("<tr>");
	        	out.println("<td>" + rs.getInt(1) + "</td> <td>" + rs.getString(2) + "</td> <td>" + rs.getInt(3) + "</td> <td>" + rs.getString(4) + "</td>");
		        out.println("</tr>");
		    }	    
		} catch (Exception e) {
		e.printStackTrace();
		} finally {
			if(conn != null)
			{	try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				conn = null;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
