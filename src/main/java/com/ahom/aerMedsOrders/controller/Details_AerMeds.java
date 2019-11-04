package com.ahom.aerMedsOrders.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ahom.aerMedsOrders.am.AerMedsService;
import com.ahom.aerMedsOrders.model.AerMedsDisplay;
import com.ahom.commonUI.ajax.RestResponse;
import com.ahom.commonUI.views.ViewBuilder;
import com.ahom.commonUtil.logging.GrepFriendlyLogger;
import com.ahom.commonUtil.security.UserWeblogic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class Details_AerMeds {
	
	private static final Logger LOGGER = new GrepFriendlyLogger(Details_AerMeds.class);
	
		@RequestMapping("/order/{id}")
	public ModelAndView getAerMedsPage(@PathVariable int id, HttpServletRequest request) throws JsonProcessingException {
		
		GrepFriendlyLogger.setKey("AERMEDS", id);
		
		UserWeblogic user = UserWeblogic.login(request);
		ViewBuilder view = ViewBuilder.baseView(user, "editAerMeds");
		
		LOGGER.debug("inside Details_ aermeds controller");
		
		AerMedsDisplay order = AerMedsService.getAerMedsDetail(id, user.toUser());
		LOGGER.debug("JSON OUTPUT: "+new ObjectMapper().writeValueAsString(order));
		
		String initTab = request.getParameter("tab");
		view.put("initTab", initTab == null ? "null" : "\""+initTab+"\""); 
		view.putJson("aermeds", order);
		if(order.getId() == null){
			throw new RuntimeException("Invalid Id.Please enter a valid one");
		}
		
		return view.getModelAndView();
		
	}
		@RequestMapping("/orderNumber/{mestaOrderNo}")
		public ModelAndView getAerMedsOrderNo(@PathVariable Long mestaOrderNo, HttpServletRequest request) throws JsonProcessingException {
			
			UserWeblogic user = UserWeblogic.login(request);
			ViewBuilder view = ViewBuilder.baseView(user, "editAerMeds");
			
			try
			{
				GrepFriendlyLogger.setKey("AERMEDS", mestaOrderNo);
				
				LOGGER.debug("inside getAerMeds OrderNo");
				
				AerMedsDisplay orderNo = AerMedsService.getAerMedsOrderNo(mestaOrderNo, user.toUser());
				LOGGER.debug("JSON OUTPUT: "+new ObjectMapper().writeValueAsString(orderNo));
				
				String initTab = request.getParameter("tab");
				view.put("initTab", initTab == null ? "null" : "\""+initTab+"\""); 
				view.putJson("aermeds", orderNo);
				if(orderNo.getAermedsOrder().getMestaOrderNo() == null){
					throw new RuntimeException("Invalid Order Number.Please enter a valid one");
				}
			}

			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			
			return view.getModelAndView();
		}
		
		@RequestMapping("/save")
		@ResponseBody
	public RestResponse saveAerMedsOrder(@RequestBody AerMedsDisplay aerMedsOrder, HttpServletRequest request) {
		try {
			LOGGER.debug("saveAerMedsOrder");
			GrepFriendlyLogger.setKey("ORDER", aerMedsOrder.getId());
			UserWeblogic user = UserWeblogic.login(request);
			return RestResponse.response(AerMedsService.saveAerMedsOrder(aerMedsOrder, user.toUser()));
		}
		catch(Exception e) { return RestResponse.errorResponse(e); }
	}



}
