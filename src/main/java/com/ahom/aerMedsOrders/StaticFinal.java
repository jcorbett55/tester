package com.ahom.aerMedsOrders;

public class StaticFinal {

	//Security Model
		public static final int HIDDEN = 0;
		public static final int DISPLAY = 1;
		public static final int EDITABLE = 2;
		
		public static final String propFile = "/AerMedsOrdersAppInfo.prop";
		
		public static final String contextPath = "/AerMeds_Orders";
		public static final String version = "1.00.01-AM";
		
		public String getContextPath()  { return contextPath; }
		public String getVersion()  { return version; }
		public int getHIDDEN()  { return HIDDEN; }
		public int getDISPLAY()  { return DISPLAY; }
		public int getEDITABLE()  { return EDITABLE; }
		
}
