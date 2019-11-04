package com.ahom.aerMedsOrders.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ahom.aerMedsOrders.am.AerMedsService;
import com.ahom.aerMedsOrders.model.SearchAerMedsFilter;
import com.ahom.commonUI.ajax.RestResponse;
import com.ahom.commonUI.views.ViewBuilder;
import com.ahom.commonUtil.fnd.FndLovService;
import com.ahom.commonUtil.logging.GrepFriendlyLogger;
import com.ahom.commonUtil.security.*;
import com.ahom.commonUtil.util.LovMap;



@Controller
public class Search_AerMeds {
	
	private static final Logger LOGGER = new GrepFriendlyLogger(Search_AerMeds.class);
	private static final ObjectMapper jsonMapper = new ObjectMapper();
	

	private LovMap buildLov() throws JsonGenerationException, JsonMappingException, IOException, SQLException{
		LovMap map = new LovMap();
		Map<String, String> branchOptions = new LinkedHashMap<String, String>();
		List<String> orderedBranchList = new ArrayList<String>(FndLovService.getBranchOrDepotLov().values());
		Collections.sort(orderedBranchList);
		for(String org : orderedBranchList) {
			String location = org.substring(7); 
			String gl = org.substring(0, 4);
			branchOptions.put(gl, gl+" "+location);
		}
		map.put("branchOptions", jsonMapper.writeValueAsString(branchOptions));
		return map;
	}
	
	
	@RequestMapping({"/", "/AerMeds_File"})
	public ModelAndView searchAerMedsFile(HttpServletRequest request) throws SQLException, JsonGenerationException, JsonMappingException, IOException {
		UserWeblogic user = UserWeblogic.login(request);	
		ViewBuilder view = ViewBuilder.baseView(user, "searchFile");

		try {
			 LOGGER.debug("searchFileAerMeds;  userName: "+user.getUserName()); 
			 view.putLov( buildLov());
			}catch(Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return view.getModelAndView();
	}
	
	@RequestMapping("/search/AerMedsFileFilter")
	@ResponseBody
	public RestResponse searchFile(@RequestBody SearchAerMedsFilter filter, HttpServletRequest request)
	{
		try {
			LOGGER.debug("searchList= filter: " + jsonMapper.writeValueAsString(filter));
			UserWeblogic.login(request);
			
			String server = request.getServerName();
			LOGGER.debug("Server Name"+server);
			LOGGER.debug("Filter Parameter"+filter);
			if (filter.isEmpty() && !((server.startsWith("appdev") || server.startsWith("local") || server.startsWith("dev") || server.equals("test"))))
				return RestResponse.errorResponse("Filter is empty.  Please add filter criteria");
		 
		return RestResponse.response(AerMedsService.searchAerMeds(filter));
		} catch(Exception e) {
			LOGGER.error(e.getMessage(), e);
			return RestResponse.errorResponse(e);
		}
	}
	


}
