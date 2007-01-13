package edu.wustl.catissuecore.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.ViewSurgicalPathologyReportForm;
import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.bizlogic.IdentifiedSurgicalPathologyReportBizLogic;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenCollectionGroup;
import edu.wustl.catissuecore.domain.User;
import edu.wustl.catissuecore.domain.pathology.IdentifiedSurgicalPathologyReport;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.action.BaseAction;
import edu.wustl.common.beans.SessionDataBean;
import edu.wustl.common.bizlogic.DefaultBizLogic;
import edu.wustl.common.security.SecurityManager;
import edu.wustl.common.security.exceptions.SMException;
import edu.wustl.common.util.dbManager.DAOException;
import edu.wustl.common.util.logger.Logger;
import gov.nih.nci.security.authorization.domainobjects.Role;

/**
 * @author vijay_pande
 * Action class to show Surgical Pathology  Report
 */
public class ViewSurgicalPathologyReportAction extends BaseAction
{

	private String forward;
	private ViewSurgicalPathologyReportForm viewSPR;
	/**
	 * @see edu.wustl.common.action.BaseAction#executeAction(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		viewSPR=(ViewSurgicalPathologyReportForm)form;
		String pageOf = request.getParameter(Constants.PAGEOF);
		String operation = (String)request.getParameter(Constants.OPERATION);
		String submittedFor="";
		Long id=new Long((String)request.getParameter(Constants.SYSTEM_IDENTIFIER));
		viewSPR.setId(id);
		boolean isAuthorized;
		if(operation.equalsIgnoreCase(Constants.VIEW_SURGICAL_PATHOLOGY_REPORT))
		{
			isAuthorized=isAuthorized(getSessionBean(request));
			retrieveAndSetObject(pageOf,id,isAuthorized);
		}
		request.setAttribute(Constants.PAGEOF, pageOf);
		request.setAttribute(Constants.OPERATION, Constants.VIEW_SURGICAL_PATHOLOGY_REPORT);
		request.setAttribute(Constants.REQ_PATH, "");
		request.setAttribute(Constants.SUBMITTED_FOR, submittedFor);
		
		return mapping.findForward(forward);
	}
	
	/**
	 * @param pageOf pageOf variable to find out domain object 
	 * @param id Identifier of the domain object
	 * @throws DAOException exception occured while DB handling
	 * This method retrives the appropriate SurgicalPathologyReport object and set values of ViewSurgicalPathologyReportForm object
	 */
	private void retrieveAndSetObject(String pageOf,long id,boolean isAuthorized) throws DAOException
	{
		String className;
		String colName=new String(Constants.SYSTEM_IDENTIFIER);
		long colValue=id;	
		DefaultBizLogic defaultBizLogic=new DefaultBizLogic();		
		//if page is of Specimen Collection group then the domain object is SpecimenCollectionGroup
		if(pageOf.equalsIgnoreCase(Constants.PAGEOF_SPECIMEN_COLLECTION_GROUP))
		{
			forward=new String(Constants.SPECIMEN_COLLECTION_GROUP);
			className=SpecimenCollectionGroup.class.getName();
			List scgList=defaultBizLogic.retrieve(className, colName, colValue);
			SpecimenCollectionGroup scg=(SpecimenCollectionGroup)scgList.get(0);
			if(isAuthorized)
			{
				viewSPR.setAllValues(scg.getIdentifiedSurgicalPathologyReport());
				viewSPR.setParticipant(scg.getParticipant());
				viewSPR.setDeIdentifiedReport(scg.getDeIdentifiedSurgicalPathologyReport());
			}
			else
			{
				viewSPR.setIdentifiedReportTextContent("You are not authorized to view this report");
				viewSPR.setParticipant(scg.getParticipant());
				viewSPR.setDeIdentifiedReport(scg.getDeIdentifiedSurgicalPathologyReport());
			}
		}
		//if page is of Specimen then the domain object is Specimen
		if(pageOf.equalsIgnoreCase(Constants.PAGEOF_SPECIMEN))
		{
			forward=new String(Constants.SPECIMEN);
			className=Specimen.class.getName();
			List specimenList=defaultBizLogic.retrieve(className, colName, colValue);
			Specimen specimen=(Specimen)specimenList.get(0);
			SpecimenCollectionGroup scg=specimen.getSpecimenCollectionGroup();
			viewSPR.setAllValues(scg.getIdentifiedSurgicalPathologyReport());
			if(isAuthorized)
			{
				viewSPR.setAllValues(scg.getIdentifiedSurgicalPathologyReport());
				viewSPR.setParticipant(scg.getParticipant());
				viewSPR.setDeIdentifiedReport(scg.getDeIdentifiedSurgicalPathologyReport());
			}
			else
			{
				viewSPR.setParticipant(scg.getParticipant());
				viewSPR.setDeIdentifiedReport(scg.getDeIdentifiedSurgicalPathologyReport());
			}
		}
		// if page is of Participant then the domain object is Participant
		// Also needs to retrieve a list of SurgicalPathologyReport objects (One-to-Many relationship)
		if(pageOf.equalsIgnoreCase(Constants.PAGEOF_PARTICIPANT))
		{
			forward=new String(Constants.SPECIMEN);
			className=Participant.class.getName();
			List participantList=defaultBizLogic.retrieve(className, colName, colValue);
			Participant participant=(Participant)participantList.get(0);
			viewSPR.setParticipant(participant);
			Collection scgCollection=participant.getSpecimenCollectionGroupCollection();
			
			Iterator iter=scgCollection.iterator();
			if(iter.hasNext())
			{
				colValue=(Long)iter.next();
				className=SpecimenCollectionGroup.class.getName();
				List scgList=defaultBizLogic.retrieve(className, colName, colValue);
				SpecimenCollectionGroup scg=(SpecimenCollectionGroup)scgList.get(0);
				if(isAuthorized)
				{
					viewSPR.setAllValues(scg.getIdentifiedSurgicalPathologyReport());
				}
				else
				{
					viewSPR.setParticipant(scg.getParticipant());
					viewSPR.setDeIdentifiedReport(scg.getDeIdentifiedSurgicalPathologyReport());
				}
			}
			viewSPR.setReportId(getReportIdList(scgCollection));
		}
	}
	
	/**
	 * @param scgCollection A collection of SpecimenCollectionGroup Id
	 * @return List of SurgicalPathologyReport Id
	 * @throws DAOException Exception occured while handling DB
	 */
	private List getReportIdList(Collection scgCollection)throws DAOException
	{
		Long[] scg=null;
		List reportIDList=null;
		
		String sourceObjectName=IdentifiedSurgicalPathologyReport.class.getName();
		String[] displayNameFields=new String[] {Constants.SYSTEM_IDENTIFIER};
		String valueField=new String(Constants.SYSTEM_IDENTIFIER);
		String[] whereColumnName = new String[]{"specimenCollectionGroup"};
		String[] whereColumnCondition = new String[]{"="};
		Object[] whereColumnValue = null;
		String joinCondition = null;
		String separatorBetweenFields = Constants.SEPARATOR;
		
		if(scgCollection!=null)
		{
			Iterator iter=scgCollection.iterator();
			scg=new Long[scgCollection.size()];
			int i=0;
			while(iter.hasNext())
			{
				scg[i++]=(Long)iter.next();
			}
			
			whereColumnValue=scg;
			BizLogicFactory bizLogicFactory = BizLogicFactory.getInstance();
			IdentifiedSurgicalPathologyReportBizLogic bizLogic =(IdentifiedSurgicalPathologyReportBizLogic) bizLogicFactory.getBizLogic(IdentifiedSurgicalPathologyReport.class.getName());
			reportIDList = bizLogic.getList(sourceObjectName, displayNameFields, valueField, whereColumnName, whereColumnCondition, whereColumnValue, joinCondition, separatorBetweenFields, false);
		}
		return reportIDList;
	}
	
	private SessionDataBean getSessionBean(HttpServletRequest request)
	{
		try
		{
			SessionDataBean sessionBean = (SessionDataBean) request.getSession().getAttribute(Constants.SESSION_DATA);
			return sessionBean;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	private boolean isAuthorized(SessionDataBean sessionBean) throws Exception
	{
		SecurityManager sm=SecurityManager.getInstance(this.getClass());
		try
		{
			Role role=sm.getUserRole(sessionBean.getUserId());
			if(role.getName().equalsIgnoreCase(Constants.ROLE_ADMINISTRATOR))
			{
				return true;
			}
			
		}
		catch(SMException ex)
		{
			Logger.out.info("Reviewer's Role not found!");
		}
		return false;
	}
	
}


