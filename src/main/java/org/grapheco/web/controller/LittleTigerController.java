package org.grapheco.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.grapheco.web.config.VolumesProperties;

/**
 * @date：2020年05月01日 上午11:43:26
 * @author little-tiger
 * @version 1.0
 * @since JDK 1.8 @description：
 */
@Controller
public class LittleTigerController {

	@RequestMapping("/hello")
	@ResponseBody
	public String toTest() {
		return "Hello World";
	}

	@Autowired
	VolumesProperties volumes;

	@RequestMapping("/")
	public String index(ModelMap map) {
		map.addAttribute("host", "http://zoo.fe.com");
		map.addAttribute("title", "Zoo");
		return "index";
	}

	@Autowired
	private HttpServletRequest request;

	
	
	@RequestMapping("/elfinder")
	public String elfinder(ModelMap map) {

		map.addAttribute("host", "http://zoo.fe.com");
		map.addAttribute("start", request.getParameter("start"));
		map.addAttribute("type", request.getParameter("type"));
		map.addAttribute("userFilePath", volumes.getUserFilePath());
		map.addAttribute("elfinder_entrance", "/userfiles-servlet/connector");
		map.addAttribute("func", request.getParameter("func"));
		String action = request.getParameter("action");
		if (!StringUtils.isEmpty(action) && action.indexOf("pop") > -1) {
			map.addAttribute("isPopup", true);
		} else {
			map.addAttribute("isPopup", false);
		}
		return "elfinder";
	}
}
