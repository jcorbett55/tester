package com.ahom.aerMedsOrders.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ahom.aerMedsOrders.am.AerMedsService;
import com.ahom.aerMedsOrders.model.AerMedsDisplay;
import com.ahom.aerMedsOrders.model.SearchAerMedsFilter;
import com.ahom.aerMedsOrders.model.SearchAerMedsResults;
import com.ahom.commonUtil.databaseX.proc.CallCheck;
import com.ahom.commonUtil.fnd.model.to.User;
import com.ahom.commonUtil.logging.GrepFriendlyLogger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class AerMedsServiceTest {

	private static final Logger log = new GrepFriendlyLogger(AerMedsServiceTest.class);

	private final User testUser; {
		testUser = new User();
		testUser.setUserName("JUNIT TEST");
		testUser.setAdRoleSet(new HashSet<String>(Arrays.asList(new String[]{"TEST"})));
	}
	
	@Test public void testCalls() { CallCheck.checkService(AerMedsServiceTest.class); }
	
/***************\
**[ Search ]**
\***************/
	
	@Test //WEB_Get_Search_List
	public void searchAerMedsTest() throws Exception {
		try {
			//Empty filter test
			SearchAerMedsFilter filter = new SearchAerMedsFilter();
			List<SearchAerMedsResults> results = AerMedsService.searchAerMeds(filter);
			log.debug("Empty filter test results: "+results.size());
			Assert.assertFalse(results.isEmpty());
		}
		catch(Exception e) { log.error(e.getMessage(), e); throw e; }
	}
	
	
/**************\
**[ Detail ]**
\**************/
	

	@Test //WEB_Get_Details
	public void getAerMedsDetailTest() throws Exception {
		Random rand = new Random();
		try {
			
			List<SearchAerMedsResults> list = AerMedsService.searchAerMeds(new SearchAerMedsFilter());
			Assert.assertFalse(list.isEmpty());
			
			SearchAerMedsResults result = null;
			while(!list.isEmpty()) {
				SearchAerMedsResults test = list.get(rand.nextInt(list.size()));
				if (test != null) {
					result = test;
					break;
				}
			}
			Assert.assertNotNull(result);
			
			int testId = result.getId();
			
			//Get AerMedsDisplay information.
			AerMedsDisplay display = AerMedsService.getAerMedsDetail(testId, testUser);
			Assert.assertNotNull(display);
		}
		catch(Exception e) { log.error(e.getMessage(), e); throw e; }
	}

	
	

}
