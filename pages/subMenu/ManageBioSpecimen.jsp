<td class="subMenuPrimaryItemsWithBorder" onmouseover="changeMenuStyle(this,'subMenuPrimaryItemsWithBorderOver')" onmouseout="changeMenuStyle(this,'subMenuPrimaryItemsWithBorder')">		
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<tr>
	<td class="subMenuPrimaryTitle" height="22">
		<a href="#content">
    		<img src="images/shim.gif" alt="Skip Menu" width="1" height="1" border="0" />
    	</a>
	</td>
</tr>

<tr>
	<td class="subMenuPrimaryItemsWithBorder" onmouseover="changeMenuStyle(this,'subMenuPrimaryItemsWithBorderOver')" onmouseout="changeMenuStyle(this,'subMenuPrimaryItemsWithBorder')">				
		<div>
			<!--img src="images/subMenuArrow.gif" width="7" height="7" alt="" /--> 
				<b> <bean:message key="app.participant" /> </b>
		</div>		
		<div>
			<a class="subMenuPrimary" href="Participant.do?operation=add"><bean:message key="app.add" /></a> | 
			<a class="subMenuPrimary" <%--href="Participant.do?operation=search"--%> >
				<bean:message key="app.edit" />
			</a>
		</div>
	</td>
</tr>
<tr>
	<td class="subMenuPrimaryItemsWithBorder" onmouseover="changeMenuStyle(this,'subMenuPrimaryItemsWithBorderOver')" onmouseout="changeMenuStyle(this,'subMenuPrimaryItemsWithBorder')">				
		<div>
			<!--img src="images/subMenuArrow.gif" width="7" height="7" alt="" /--> 
				<b> <bean:message key="app.participantregister" /> </b>
		</div>
		<div>
			<a class="subMenuPrimary" href="CollectionProtocolRegistration.do?operation=add"><bean:message key="app.add" /></a> | 
			<a class="subMenuPrimary" <%--href="CollectionProtocolRegistration.do?operation=search"--%> >
				<bean:message key="app.edit" />
			</a>
		</div>
	</td>
</tr>
</tr>
<tr>
	<td class="subMenuPrimaryItemsWithBorder" onmouseover="changeMenuStyle(this,'subMenuPrimaryItemsWithBorderOver')" onmouseout="changeMenuStyle(this,'subMenuPrimaryItemsWithBorder')">				
		<div>
			<!--img src="images/subMenuArrow.gif" width="7" height="7" alt="" /--> 
				<b> <bean:message key="app.specimencollectiongroup" /></b>
		</div>
		<div>
		<a class="subMenuPrimary" href="SpecimenCollectionGroup.do?operation=add"><bean:message key="app.add" /></a> | 
		<a class="subMenuPrimary" <%--href="SpecimenCollectionGroup.do?operation=search"--%> >
			<bean:message key="app.edit" />
		</a>
		</div>
	</td>
</tr>
<tr>
	<td class="subMenuPrimaryItemsWithBorder" onmouseover="changeMenuStyle(this,'subMenuPrimaryItemsWithBorderOver')" onmouseout="changeMenuStyle(this,'subMenuPrimaryItemsWithBorder')">		

		<div>
			<!--img src="images/subMenuArrow.gif" width="7" height="7" alt="" /--> 
				<b><bean:message key="app.newSpecimen" /> </b>
		</div>
		<div>
			<a class="subMenuPrimary" href="NewSpecimen.do?operation=add">
				<bean:message key="app.add" />
			</a> | 
			<a class="subMenuPrimary" <%--href="NewSpecimen.do?operation=search"--%> ><bean:message key="app.edit" /></a>
			 | <a class="subMenuPrimary" href="CreateSpecimen.do?operation=add&amp;pageOf=">
				<bean:message key="app.createSpecimen" />	</a> 
		</div>
	</td>
</tr>

<tr>
	<td class="subMenuPrimaryItemsWithBorder" onmouseover="changeMenuStyle(this,'subMenuPrimaryItemsWithBorderOver')" onmouseout="changeMenuStyle(this,'subMenuPrimaryItemsWithBorder')">				
		
		<div>
			<%--img src="images/subMenuArrow.gif" width="7" height="7" alt="" /--%> 
				<b><bean:message key="app.distribution" /></b>
		</div>
		<div>
			<a class="subMenuPrimary" href="Distribution.do?operation=add"><bean:message key="app.add" /></a> | 
			<a class="subMenuPrimary" href="Distribution.do?operation=search">
				<bean:message key="app.edit" />
			</a>
		</div>
	</td>
</tr>

<tr>
	<td class="subMenuPrimaryItemsWithBorder" onmouseover="changeMenuStyle(this,'subMenuPrimaryItemsWithBorderOver')" onmouseout="changeMenuStyle(this,'subMenuPrimaryItemsWithBorder')">				
		
		<div>
			<!--img src="images/subMenuArrow.gif" width="7" height="7" alt="" /--> 
				<a class="subMenuPrimary" href="SpecimenEventParameters.do">
					<b><bean:message key="app.specimeneventparameters" /></b>
				</a>
		</div>
	</td>
</tr>

