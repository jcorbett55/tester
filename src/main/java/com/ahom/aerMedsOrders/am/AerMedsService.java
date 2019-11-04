package com.ahom.aerMedsOrders.am;

import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;

import com.ahom.aerMedsOrders.model.AerMedsDisplay;
import com.ahom.aerMedsOrders.model.SearchAerMedsFilter;
import com.ahom.aerMedsOrders.model.SearchAerMedsResults;
import com.ahom.commonUtil.databaseX.proc.EasyCall;
import com.ahom.commonUtil.databaseX.proc.OutMap;
import com.ahom.commonUtil.databaseX.proc.Param;
import com.ahom.commonUtil.fnd.model.to.Error;
import com.ahom.commonUtil.fnd.model.to.User;
import com.ahom.commonUtil.logging.GrepFriendlyLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class AerMedsService {
	
	private AerMedsService() {}
	private static final Logger log = new GrepFriendlyLogger(AerMedsService.class);
	private static final ObjectMapper jsonMapper = new ObjectMapper();
	
/**************\
 **[ Search ]**
\**************/
	
	private static final EasyCall searchAerMedsProc = EasyCall.procedure("AHOMOE.OE_AERMEDS_ORDERS_PKG.WEB_Get_Search_List",
		Param.inTO(SearchAerMedsFilter.class),
		Param.outNT(SearchAerMedsResults.class)
	);

	public static final List<SearchAerMedsResults> searchAerMeds(SearchAerMedsFilter filter) {
		log.info("AerMeds_Orders search: "+filter);
		List<SearchAerMedsResults> results = searchAerMedsProc.execute(filter).getList(SearchAerMedsResults.class);
		log.debug("AerMeds_Orders search result size: "+results.size());
		return results;
	}
	
/**************\
**[ Detail ]**
\**************/
		
	private static final EasyCall getAerMedsDetailsProc = EasyCall.procedure("AHOMOE.OE_AERMEDS_ORDERS_PKG.WEB_Get_Details",
		Param.in(Types.INTEGER),
		Param.inTO(User.class),
		Param.outTO(AerMedsDisplay.class)
			
		);
		
	/**
	 * Gets AerMeds information.
	 * @param id The AerMeds Orders id to lookup.
	 * @param user The user object to base security on.
	 * @return The AerMedsDisplay object.
	 */
	public static final AerMedsDisplay getAerMedsDetail(int id, User user) {
		AerMedsDisplay aerMeds = getAerMedsDetailsProc.execute(id, user).get(AerMedsDisplay.class);
		try { 
			log.info("GET aerMeds Orders (i/o) "+jsonMapper.writeValueAsString(aerMeds)); 
			}
		catch (JsonProcessingException e)
		{ log.error("GET AerMeds Orders (i/o) failed", e);
		}
		return aerMeds;
	}

/**********************\
**[ Detail-Report ]**
\**********************/
	
	private static final EasyCall getAerMedsOrderNoProc = EasyCall.procedure("AHOMOE.OE_AERMEDS_ORDERS_PKG.WEB_Get_Details_By_Order_Num",
			Param.in(Types.INTEGER),
			Param.inTO(User.class),
			Param.outTO(AerMedsDisplay.class)
				
			);
	
	/**
	 * Gets AerMeds information.
	 * @param orderNo The AerMeds Orders no to lookup.
	 * @param user The user object to base security on.
	 * @return The AerMedsDisplay object.
	 */
	
	public static final AerMedsDisplay getAerMedsOrderNo(Long mestaOrderNo, User user) {
		AerMedsDisplay aerMedsOrder = getAerMedsOrderNoProc.execute(mestaOrderNo, user).get(AerMedsDisplay.class);
		try { 
			log.info("GET aerMeds Orders No (i/o) "+jsonMapper.writeValueAsString(aerMedsOrder)); 
			}
		catch (JsonProcessingException e)
		{ log.error("GET AerMeds Orders No (i/o) failed", e);
		}
		return aerMedsOrder;
	}
		
/***************\
**[ Save ]**
\***************/
		
	/**
	 * Saves AerMeds information.
	 * @param user The AerMeds Orders display to lookup.
	 * @param user The user object to base security on.
	 * @return The updated AerMedsDisplay object.
	 */

	private static final EasyCall saveAerMedsOrderProc = EasyCall.procedure("AHOMOE.OE_AERMEDS_ORDERS_PKG.WEB_Save_Details",
		Param.inTO(User.class),
		Param.inoutTO(AerMedsDisplay.class),
		Param.outNT(Error.class)
		);
			
	public static final AerMedsDisplay saveAerMedsOrder(AerMedsDisplay order, User user) {
		try { log.info("SAVE order (i/o) "+jsonMapper.writeValueAsString(order)); }
		catch (Exception e) { log.error("Error logging save order json", e); }
				
		OutMap map = saveAerMedsOrderProc.execute(user, order);
		AerMedsDisplay results = map.get(AerMedsDisplay.class);
		GrepFriendlyLogger.setKey("ORDER", String.valueOf(results.getId()));
		List<Error> errorList = map.getList(Error.class);
		if (!errorList.isEmpty())
			for(Error error : errorList)
				log.info("Error:" + error);

		results.addError(errorList);
				
		try { log.info("RETURN order (i/o) errors( "+errorList.size()+" ) "+jsonMapper.writeValueAsString(results)); }
		catch (Exception e) { log.error("Error logging return order json", e); }

		return results;
		};

}
