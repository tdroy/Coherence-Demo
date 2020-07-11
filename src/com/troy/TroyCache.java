package com.troy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import org.apache.log4j.Logger;

public class TroyCache {
	
	final static Logger logger = Logger.getLogger(TroyCache.class);
	
    // static variable single_instance of type Singleton
    private static TroyCache single_instance = null;
    
	Statement stmt = null;
	ResultSet rs = null;
 
    // variable of type String
    //public String stateNames;
    List<String> stateNames = new ArrayList<String>();
 
    // private constructor restricted to this class itself
    private TroyCache()
    {
    	Context context;
		try {
			context = new InitialContext();
			DataSource dataSource = (javax.sql.DataSource) context.lookup("jdbc/Mysql");
			Connection conn = dataSource.getConnection();
			
			System.out.println("[TROY] database call");
		    stmt = conn.createStatement(); 
		    rs = stmt.executeQuery("select distinct state_name from branch");
		    
		    System.out.println("[TROY] Join cache cluster...");
            CacheFactory.ensureCluster();
            NamedCache cache = CacheFactory.getCache("state-example");
		    
            System.out.println("[TROY] Adding delay of 10sec...");
            Thread.sleep(10000);
            while (rs.next()) {
                stateNames.add(rs.getString("state_name"));
            }
            System.out.println("[TROY] Adding list to cache.");
            cache.put("key", stateNames);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 
    // static method to create instance of Singleton class
    public static TroyCache getInstance()
    {
        if (single_instance == null)
            single_instance = new TroyCache();
 
        
        for (int i = 0; i<10 ; i++)
        	logger.error("Log4j Test : " + i);
        return single_instance;
    }

}
