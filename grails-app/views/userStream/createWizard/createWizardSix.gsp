<html>
	
	<head>
		<title>Quoddy: Edit User Stream</title>
		<meta name="layout" content="main" />
	     <nav:resources />	
	</head>
	
	<body>
	  <div class="hero-unit span6">	
		<h2>Select subscriptions to include in this stream.</h2>
        <g:form controller="userStream" action="createWizard" method="POST">
			<g:select optionKey="uuid" optionValue="name" 
        		name="eventSubscriptions" 
        		from="${eventSubscriptions}"
        		value="${selectedEventSubscriptions}" 
        		multiple="true" />
			<g:submitButton name="finishWizard" value="Finish" class="btn" />
		</g:form>
	</div>	
	</body>
</html>