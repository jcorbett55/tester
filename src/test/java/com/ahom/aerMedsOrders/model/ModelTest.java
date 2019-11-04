package com.ahom.aerMedsOrders.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ahom.commonUtil.databaseX.test.StandardTransferObjectTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class ModelTest extends StandardTransferObjectTest
{
	@Test public void modelTest() {
		test(AerMedsDisplay.class);
		test(AerMedsOrder.class);
		test(SearchAerMedsFilter.class);
		test(SearchAerMedsResults.class);
	}
}
