/**
 * <p>Title: BizLogicFactory Class>
 * <p>Description:	BizLogicFactory is a factory for DAO instances of various domain objects.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Gautam Shetty
 * @version 1.00
 */

package edu.wustl.catissuecore.dao;

import edu.wustl.catissuecore.util.global.Constants;

/**
 * BizLogicFactory is a factory for DAO instances of various domain objects.
 * @author gautam_shetty
 */
public class BizLogicFactory
{
    /**
     * Returns DAO instance according to the form bean type.
     * @param FORM_TYPE The form bean type.
     * @return An AbstractDAO object.
     */
    public static AbstractBizLogic getDAO(int FORM_TYPE)
    {
        AbstractBizLogic abstractBizLogic = null;
        
        switch(FORM_TYPE)
        {
//            case Constants.USER_FORM_ID:
//                abstractBizLogic = new UserBizLogic();
//            	break;
////            case Constants.ACCESSION_FORM_ID:
////                abstractBizLogic = new AccessionBizLogic();
////            	break;
//            case Constants.INSTITUTION_FORM_ID:
//                abstractBizLogic = new InstituteBizLogic();
//            	break;
//            case Constants.REPORTEDPROBLEM_FORM_ID:
//                abstractBizLogic = new ReportedProblemBizLogic();
//            	break;
            default:
                abstractBizLogic = new AbstractBizLogic();
            	break;
        }
        return abstractBizLogic;
    }
}