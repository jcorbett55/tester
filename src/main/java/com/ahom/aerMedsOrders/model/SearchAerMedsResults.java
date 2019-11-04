package com.ahom.aerMedsOrders.model;

import java.util.Date;

import com.ahom.commonUtil.databaseX.annotation.FieldName;
import com.ahom.commonUtil.databaseX.types.CollectionObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

public final class  SearchAerMedsResults implements CollectionObject{
	

	private static final long serialVersionUID = 1L;
	private static final String SQL_TYPE_NAME = "AHOMOE.OE_AERMEDS_SEARCH_FILTER_T";
	private static final String SQL_ARRAY_NAME = "AHOMOE.OE_AERMEDS_SEARCH_FILTER_NT";	
	
	@FieldName("ID")
	private Integer id;
	
	@FieldName("CUSTOMER_FIRST")
	private String customerFirstName;
	
	@FieldName("CUSTOMER_LAST")
	private String customerLastName;

	@FieldName("DATE_OF_SERVICE")
	private Date dos;
	
	@FieldName("COMPLETION_DT")
	private Date completionDate;
	
	@FieldName("GLCODE")
	private String glCode;
	
	@FieldName("MESTA_ORDER_NUMBER")
	private Long mestaOrderNo;
	
	@FieldName("SALESMAN_NUMBER")
	private Integer salesmanNo;
	
	@FieldName("SALESMAN_NAME")
	private String salesmanNameDSP;
	
	@FieldName("FACILITY_NAME")
	private String facilityNameDSP;
	
	@FieldName("CUSTOMER_NUMBER")
	private String customerNo;
	
	public Integer getId() {return id;}
	public void setId(Integer id) {this.id = id;}
	
	public String getCustomerFirstName() {return customerFirstName;}
	public void setCustomerFirstName(String customerFirstName) {this.customerFirstName = customerFirstName;}
	
	public String getCustomerLastName() {return customerLastName;}
	public void setCustomerLastName(String customerLastName) {this.customerLastName = customerLastName;}
	
	public Date getDos() {return dos;}
	public void setDos(Date dos) {this.dos = dos;}
	
	public Date getCompletionDate() {return completionDate;}
	public void setCompletionDate(Date completionDate) {this.completionDate = completionDate;}
	
	public String getGlCode() {return glCode;}
	public void setGlCode(String glCode) {this.glCode = glCode;}
	
	public Long getMestaOrderNo() {return mestaOrderNo;}
	public void setMestaOrderNo(Long mestaOrderNo) {this.mestaOrderNo = mestaOrderNo;}
	
	public Integer getSalesmanNo() {return salesmanNo;}
	public void setSalesmanNo(Integer salesmanNo) {this.salesmanNo = salesmanNo;}
	
	public String getSalesmanNameDSP() {return salesmanNameDSP;}
	public void setSalesmanNameDSP(String salesmanNameDSP) {this.salesmanNameDSP = salesmanNameDSP;}
	
	public String getFacilityNameDSP() {return facilityNameDSP;}
	public void setFacilityNameDSP(String facilityNameDSP) {this.facilityNameDSP = facilityNameDSP;}
	
	public String getCustomerNo() {return customerNo;}
	public void setCustomerNo(String customerNo) {this.customerNo = customerNo;}
	
	@JsonIgnore @Override public String getTransferObjectName() {return SQL_TYPE_NAME;}
	@JsonIgnore @Override public String getCollectionObjectName() {return SQL_ARRAY_NAME;}
	

}
