
<%@ page import="org.grails.samples.Vet" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vet.label', default: 'Vet')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		%{--<a href="#show-vet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--}%
		%{--<div class="nav" role="navigation">--}%
			%{--<ul>--}%
				%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
				%{--<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--}%
				%{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
			%{--</ul>--}%
		%{--</div>--}%
		<div id="show-vet" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list vet">
			
				<g:if test="${vetInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="vet.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${vetInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vetInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="vet.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${vetInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vetInstance?.specialities}">
				<li class="fieldcontain">
					<span id="specialities-label" class="property-label"><g:message code="vet.specialities.label" default="Specialities" /></span>
					
						<g:each in="${vetInstance.specialities}" var="s">
						%{--<span class="property-value" aria-labelledby="specialities-label"><g:link controller="speciality" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>--}%
                        <span class="property-value" aria-labelledby="specialities-label">${s}</span>
                        </g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${vetInstance?.id}" />
					%{--<g:link class="edit" action="edit" id="${vetInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>--}%
                    <g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Modifica')}"/>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    <g:actionSubmit class="edit" action="list" value="${message(code: 'default.button.list.label', default: 'Elenco')}"/>
                    %{--<g:link class="edit" action="list"><g:message code="default.button.list.label" default="Elenco" /></g:link>--}%
                </fieldset>
			</g:form>
		</div>
	</body>
</html>
