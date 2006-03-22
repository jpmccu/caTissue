<%@ page import="edu.wustl.catissuecore.util.global.Constants"%>

<% 	
	String pageOf = request.getParameter(Constants.PAGEOF);
	String storageContainerType = null;
	String storageContainerID = null;
	String position = null;
	String propertyName = null, cdeName=null;
	if (pageOf.equals(Constants.PAGEOF_STORAGE_LOCATION) ||
	     pageOf.equals(Constants.PAGEOF_SPECIMEN))
	{
	   // For all storage container maps...
		storageContainerType = (String)request.getAttribute(Constants.STORAGE_CONTAINER_TYPE);
		storageContainerID = (String)request.getAttribute(Constants.STORAGE_CONTAINER_TO_BE_SELECTED);
		position = (String)request.getAttribute(Constants.STORAGE_CONTAINER_POSITION);
	}
	else if (pageOf.equals(Constants.PAGEOF_TISSUE_SITE))
	{
		propertyName = request.getParameter(Constants.PROPERTY_NAME);
		cdeName = request.getParameter(Constants.CDE_NAME);
	}
%>

<script language="javascript">
	function closeWindow()
	{
		window.close();
	}

	function setParentWindowValue(elementName,elementValue)
	{ 
		for (var i=0;i < opener.document.forms[0].elements.length;i++)
	    {
	    	if (opener.document.forms[0].elements[i].name == elementName)
			{
				opener.document.forms[0].elements[i].value = elementValue;
			}
	    }
	}
</script>

<OBJECT classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"
    width="100%" height="100%" align="top" name="<%=Constants.TREE_APPLET_NAME%>"
    codebase="http://java.sun.com/products/plugin/autodl/jinstall-1_4-windows-i586.cab#Version=1,4,0,0">
	<PARAM name="<%=Constants.PROPERTY_NAME%>" value="<%=propertyName%>">
	<PARAM name="<%=Constants.CDE_NAME%>" value="<%=cdeName%>">
    <PARAM name="code" value="<%=Constants.QUERY_TREE_APPLET%>">
	<PARAM name="archive" value="Applet/QueryTree.jar">
	<PARAM name="type" value="application/x-java-applet;jpi-version=1.4.2">
	<PARAM name="<%=Constants.PAGEOF%>" value="<%=pageOf%>">
	<PARAM name="<%=Constants.STORAGE_CONTAINER_TYPE%>" value="<%=storageContainerType%>">
	<PARAM name="<%=Constants.STORAGE_CONTAINER_TO_BE_SELECTED%>" value="<%=storageContainerID%>">
	<PARAM name="<%=Constants.STORAGE_CONTAINER_POSITION%>" value="<%=position%>">
	<PARAM name="name" value="<%=Constants.TREE_APPLET_NAME%>">
	<COMMENT>
		<EMBED type="application/x-java-applet;version=1.4.1" width="100%"
			name="<%=Constants.TREE_APPLET_NAME%>"
			height="100%" code="<%=Constants.QUERY_TREE_APPLET%>"
			codebase="<%=Constants.APPLET_CODEBASE%>"
			pluginspage="http://java.sun.com/j2se/1.4.1/download.html"
			archive="Applet/QueryTree.jar"
			pageOf="<%=pageOf%>"
			storageType="<%=storageContainerType%>"
			storageToBeSelected="<%=storageContainerID%>"
			position="<%=position%>"
			propertyName="<%=propertyName%>" 
			cdeName="<%=cdeName%>" MAYSCRIPT>
			
			<NOEMBED>
				No Java 2 SDK support for APPLET!!
					<a href="http://java.sun.com/products/plugin/">
						<code>http://java.sun.com/products/plugin/</code>
					</a>
			</NOEMBED>
		</EMBED>
	</COMMENT>
</OBJECT>