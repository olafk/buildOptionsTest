<%@ include file="init.jsp" %>

<p>
	<b><liferay-ui:message key="compileonlytest.caption"/></b>
</p>

<portlet:resourceURL var="downloadURL"/>
<a href="${downloadURL}">Download Pdf</a>