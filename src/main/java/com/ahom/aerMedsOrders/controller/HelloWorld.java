package com.ahom.aerMedsOrders.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ahom.commonUI.views.ViewBuilder;
import com.ahom.commonUtil.security.UserWeblogic;

//@Controller
public class HelloWorld
{
//	private static final Logger log = new GrepFriendlyLogger(HelloWorld.class);

//	@RequestMapping("/")
	public ModelAndView getPaymentPage(HttpServletRequest request) {
		UserWeblogic user = UserWeblogic.login(request);
		
		ViewBuilder view = ViewBuilder.baseView(user, "helloWorld");
		
		return view.getModelAndView();
	}
}

