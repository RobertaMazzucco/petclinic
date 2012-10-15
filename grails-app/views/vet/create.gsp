<%@ page import="org.grails.samples.Vet" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vet.label', default: 'Vet')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		%{--<a href="#create-vet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--}%
		%{--<div class="nav" role="navigation">--}%
			%{--<ul>--}%
				%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
				%{--<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--}%
			%{--</ul>--}%
		%{--</div>--}%
		<div id="create-vet" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${vetInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${vetInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
        <g:form action="save">
            <table>
                <tr>
                    <th>
                        <g:render template="/common/formField"
                                  model="[name:'vet', bean:vetInstance, field:'firstName', label:'First Name']" />
                    </th>
                </tr>
                <tr>
                    <th>
                        <g:render template="/common/formField"
                                  model="[name:'vet', bean:vetInstance, field:'lastName', label:'Last Name']" />
                    </th>
                </tr>
                <tr>
                    <td>
                    %{--<div class="fieldcontain ${hasErrors(bean: vetInstance, field: 'specialities', 'error')} ">--}%
                    <label for="vet.specialities">
                        <g:message code="vet.specialities.label" default="Specialities" />

                    </label>
                    <g:select name="vet.specialities" from="${org.grails.samples.Speciality.list()}" multiple="multiple" optionKey="id" size="5" value="${vetInstance?.specialities*.id}" class="many-to-many"/>
                    %{--</div>--}%
                    </td>
                </tr>
                <tr>
                    <td>
                        <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                        %{--<p class="submit"><input type="submit" value="${ vetInstance.id ? 'Update' : 'Add'} Vet"/></p>--}%
                    </td>
                </tr>
            </table>
        </g:form>
			%{--<g:form action="save" >--}%
				%{--<fieldset class="form">--}%
					%{--<g:render template="form"/>--}%
				%{--</fieldset>--}%
				%{--<fieldset class="buttons">--}%
					%{--<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />--}%
				%{--</fieldset>--}%
			%{--</g:form>--}%
		</div>
	</body>
</html>
