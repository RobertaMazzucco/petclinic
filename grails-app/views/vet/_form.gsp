<%@ page import="org.grails.samples.Vet" %>



<div class="fieldcontain ${hasErrors(bean: vetInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="vet.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${vetInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: vetInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="vet.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${vetInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: vetInstance, field: 'specialities', 'error')} ">
	<label for="specialities">
		<g:message code="vet.specialities.label" default="Specialities" />
		
	</label>
	<g:select name="specialities" from="${org.grails.samples.Speciality.list()}" multiple="multiple" optionKey="id" size="5" value="${vetInstance?.specialities*.id}" class="many-to-many"/>
</div>

