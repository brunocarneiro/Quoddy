<html>
	
	<head>
		<title>Quoddy: Edit User Stream</title>
		<meta name="layout" content="main" />
	     <nav:resources />		
	</head>
	
	<body>
	
		<h3>Edit User Stream</h3>
		<p />
		<g:form controller="userStream" action="editWizard" method="POST">
			<g:hiddenField name="streamId" value="${streamToEdit.id}" />
			<label for="streamName">Name:</label> <g:textField name="streamName" value="${streamToEdit.name}"/>
			<br />
			<label for="streamDescription">Description:</label> <g:textField name="streamDescription" value="${streamToEdit.description}"/>
			<br />
			<g:submitButton name="stage2" value="Next" />
		</g:form>
	</body>
</html>