/**
 *
 */

package edu.wustl.catissuecore.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.bizlogic.SOPBizLogic;
import edu.wustl.catissuecore.domain.sop.SOP;
import edu.wustl.catissuecore.processor.SPPEventProcessor;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.action.SecureAction;

/**
 * @author suhas_khot
 *
 */
public class DisplaySOPEventsFromDashboardAction extends SecureAction
{
	/**
	 * Overrides the execute method of Action class. Initializes the various
	 * fields in SpecimenEventParameters.jsp Page.
	 *
	 * @param mapping
	 *            object of ActionMapping
	 * @param form
	 *            object of ActionForm
	 * @param request
	 *            object of HttpServletRequest
	 * @param response
	 *            object of HttpServletResponse
	 * @throws IOException
	 *             I/O exception
	 * @throws ServletException
	 *             servlet exception
	 * @return value for ActionForward object
	 */
	@Override
	public ActionForward executeSecureAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long sopId = Long.parseLong(request.getParameter("sopId"));
		edu.wustl.dao.DAO dao = null;
		dao = edu.wustl.catissuecore.util.global.AppUtility.openDAOSession(null);
		SOP processingSOP = (SOP) dao.retrieveById(SOP.class.getName(), sopId);; // TODO - Retrieve SOP object based on
		// sop_id from request.

		request.setAttribute("selectedAll", request.getParameter("selectedAll"));
		request.setAttribute("sppId", sopId);
		SPPEventProcessor sppEventProcessor = new SPPEventProcessor();
		if (request.getParameter("specimenId") != null)
		{
			request.setAttribute("specimenId", request.getParameter("specimenId"));
		}
		else
		{
			request.setAttribute("scgId", request.getParameter("scgId"));
		}
		request.setAttribute("nameOfSelectedSop", processingSOP.getName());
		request.setAttribute("selectedSopId", processingSOP.getId());
		List<Map<String, Object>> sppEventDataCollection = sppEventProcessor
				.populateSPPEventsData(processingSOP);

		Map<String, Long> dynamicEventMap = new HashMap<String, Long>();
		new SOPBizLogic().getAllSOPEventFormNames(dynamicEventMap);
		if (request.getSession().getAttribute("dynamicEventMap") == null)
		{
			request.getSession().setAttribute("dynamicEventMap", dynamicEventMap);
		}
		request.setAttribute(Constants.SPP_EVENTS, sppEventDataCollection);
		return mapping.findForward("pageOfSopData");
	}

}
