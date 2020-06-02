package org.grapheco.elfinder.impl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.grapheco.elfinder.service.FsService;
import org.grapheco.elfinder.service.FsServiceFactory;
import org.springframework.stereotype.Component;

/**
 * A StaticFsServiceFactory always returns one FsService, despite of whatever it
 * is requested
 * 
 * @author bluejoe
 *
 */

public class StaticFsServiceFactory implements FsServiceFactory
{
	FsService _fsService;

	@Override
	public FsService getFileService(HttpServletRequest request,
			ServletContext servletContext)
	{
		/**
		 * I typically inject custom volumes here, 
		 * such as set up some specific directories based on the user's permissions.
		 * @author little-tiger 
		 */
		return _fsService;
	}

	public FsService getFsService()
	{
		return _fsService;
	}

	public void setFsService(FsService fsService)
	{
		_fsService = fsService;
	}
}
