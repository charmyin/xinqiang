package com.charmyin.cmstudio.basic.initial;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 
 * @author charmyin
 * @since 2013-7-8
 *
 */
public class ContextLoadedInitial implements ServletContextListener {
	Logger logger = Logger.getLogger(ContextLoadedInitial.class);
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		//存入全局web url路径，
		String urlpath = "http://localhost:8080/cmstudio/";
		sce.getServletContext().setAttribute("basepath", urlpath);
		logger.debug("Basepath has been set to "+ urlpath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
