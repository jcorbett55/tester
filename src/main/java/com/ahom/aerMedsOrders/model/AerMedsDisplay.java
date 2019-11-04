package com.ahom.aerMedsOrders.model;

import com.ahom.commonUtil.databaseX.annotation.AdapterIgnore;
import com.ahom.commonUtil.databaseX.annotation.FieldName;
import com.ahom.commonUtil.databaseX.annotation.SecurityType;
import com.ahom.commonUtil.databaseX.types.SecurityMap;
import com.ahom.commonUtil.databaseX.types.TransferObjectPostInit;
import com.ahom.commonUtil.error.ErrorModelContainer;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AerMedsDisplay extends ErrorModelContainer implements TransferObjectPostInit{
	
	
	private static final long serialVersionUID = 1L;
	private static final String SQL_TYPE_IDENTIFIER = "AHOMOE.OE_AERMEDS_ORDERS_DISPLAY_T";
	
	@AdapterIgnore
	private Integer id;

	@FieldName("OE_AERMEDS_ORDERS_T")
	private AerMedsOrder aermedsOrder;
	
	@FieldName("OE_AERMEDS_ORDERS_ST")
	@SecurityType(AerMedsOrder.class)
	private SecurityMap<AerMedsOrder> aerMedsSecurity;
	
	public AerMedsDisplay() { super("aermedsOrder"); }
	
	public AerMedsOrder getAermedsOrder() {return aermedsOrder;}
	public void setAermedsOrder(AerMedsOrder aermedsOrder) {this.aermedsOrder = aermedsOrder; postInit();}
	
	public Integer getId() {return id; }
	public void setId(Integer id) {this.id = id;}
	
	public SecurityMap<AerMedsOrder> getAerMedsSecurity() {return aerMedsSecurity;}
	public void setAerMedsSecurity(SecurityMap<AerMedsOrder> aerMedsSecurity) {this.aerMedsSecurity = aerMedsSecurity;}

	@JsonIgnore @Override public String getTransferObjectName() {return SQL_TYPE_IDENTIFIER; }

	@Override public void postInit() {id = aermedsOrder == null ? null : aermedsOrder.getId();}

	
}
