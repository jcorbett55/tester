package com.ahom.aerMedsOrders.model;


import java.util.Date;

import com.ahom.commonUtil.databaseX.annotation.FieldName;
import com.ahom.commonUtil.databaseX.annotation.SecurityIgnore;
import com.ahom.commonUtil.databaseX.types.SecurityObject;
import com.ahom.commonUtil.error.ErrorModel;
import com.ahom.commonUtil.fnd.model.to.AuditInfo;
import com.ahom.commonUtil.fnd.model.to.Name;
import com.ahom.commonUtil.miscModel.ActionCode;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AerMedsOrder extends ErrorModel implements SecurityObject{
	
	private static final long serialVersionUID = 1L;
	private static final String SQL_TYPE_NAME = "AHOMOE.OE_AERMEDS_ORDERS_T";
	private static final String SQL_SECURITY_NAME = "AHOMOE.OE_AERMEDS_ORDERS_ST";

	@SecurityIgnore
	private ActionCode actionCode;
	private Integer id;
	@FieldName("AUDIT_INFO")
	private AuditInfo auditInfo;
	@FieldName("GLCODE")
	private String glCode;
	@FieldName("CM_CUSTOMER_HDR_ID")
	private Integer customerHdrId;
	@FieldName("MESTA_ORDER_NUMBER")
	private Long mestaOrderNo;
	@FieldName("AHOM_SALESMAN_ID")
	private Integer salesmanId;
	@FieldName("DATE_OF_SERVICE")
	private Date dos;
	@FieldName("COMPLETION_DT")
	private Date completionDate;	
	@FieldName("CUSTOMER_NAME_DSP")
	private Name customerNameDisp;
	@FieldName("SALESMAN_NAME_DSP")
	private String salesmanNameDisp;
	@FieldName("SALESMAN_NUMBER_DSP")
	private Integer salesmanNo;
	@FieldName("FACILITY_NAME_DSP")
	private String facilityNameDSP;
	@FieldName("CUSTOMER_NUMBER_DSP")
	private String customerNo;
	

	public ActionCode getActionCode() {
		return actionCode;
	}
	public void setActionCode(ActionCode actionCode) {
		this.actionCode = actionCode;
	}
	public AuditInfo getAuditInfo() {return auditInfo;}
	public void setAuditInfo(AuditInfo auditInfo) {this.auditInfo = auditInfo;}

	public String getGlCode() {return glCode;}
	public void setGlCode(String glCode) {this.glCode = glCode;}

	public Integer getCustomerHdrId() {return customerHdrId;}
	public void setCustomerHdrId(Integer customerHdrId) {this.customerHdrId = customerHdrId;}
	
	public Long getMestaOrderNo() {return mestaOrderNo;}
	public void setMestaOrderNo(Long mestaOrderNo) {this.mestaOrderNo = mestaOrderNo;}

	public Integer getSalesmanId() {return salesmanId;}
	public void setSalesmanId(Integer salesmanId) {this.salesmanId = salesmanId;}

	public Date getDos() {return dos;}
	public void setDos(Date dos) {this.dos = dos;}

	public Date getCompletionDate() {return completionDate;}
	public void setCompletionDate(Date completionDate) {this.completionDate = completionDate;}

	public Name getCustomerNameDisp() {return customerNameDisp;}
	public void setCustomerNameDisp(Name customerNameDisp) {this.customerNameDisp = customerNameDisp;}

	public String getSalesmanNameDisp() {return salesmanNameDisp;}
	public void setSalesmanNameDisp(String salesmanNameDisp) {this.salesmanNameDisp = salesmanNameDisp;}

	public Integer getSalesmanNo() {return salesmanNo;}
	public void setSalesmanNo(Integer salesmanNo) {this.salesmanNo = salesmanNo;}
	
	public Integer getId() {return id;}
	public void setId(Integer id) {this.id = id;}
	
	public String getCustomerNo() {return customerNo;}
	public void setCustomerNo(String customerNo) {this.customerNo = customerNo;}
	
	public String getFacilityNameDSP() {return facilityNameDSP;}
	public void setFacilityNameDSP(String facilityNameDSP) {this.facilityNameDSP = facilityNameDSP;}
	
	@JsonIgnore @Override public String getTransferObjectName() {return SQL_TYPE_NAME;}

	@JsonIgnore @Override public String getSecurityObjectName() { return SQL_SECURITY_NAME;}
	
	

}
