package org.grapheco.web.servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.grapheco.elfinder.controller.ConnectorController;

/**
 * ConnectorServlet is an example servlet it creates a ConnectorController on
 * init() and use it to handle requests on doGet()/doPost()
 * 
 * users should extend from this servlet and customize required protected
 * methods
 * 
 * @author bluejoe
 *
 */
@WebServlet(urlPatterns = "/userfiles-servlet/*", loadOnStartup = 1)
public class ElfinderConnectorServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2966366562551631275L;
	// core member of this Servlet
	@Resource
	ConnectorController _connectorController;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		_connectorController.connector(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		_connectorController.connector(req, resp);
	}

}
