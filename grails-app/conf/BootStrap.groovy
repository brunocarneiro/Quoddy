import grails.util.Environment

import org.fogbeam.quoddy.EventType
import org.fogbeam.quoddy.ShareTarget
import org.fogbeam.quoddy.User
import org.fogbeam.quoddy.profile.Profile

class BootStrap {

	def ldapTemplate;
	def userService;
	
	def init = { servletContext ->
     
		
		
		 switch( Environment.current )
	     {
	         case Environment.DEVELOPMENT:
	             createSomeUsers();
				 createShareTargets();
				 createEventTypes();
	             break;
	         case Environment.PRODUCTION:
	             println "No special configuration required";
				 createSomeUsers();
				 createShareTargets();
				 createEventTypes();
				 break;
	     }     
     
	     
	     
	     // getClass().classLoader.rootLoader.URLs.each { println it };
	     
     }
     
     def destroy = {
    		 
    	// nothing, yet...
    	
     }

	 void createEventTypes()
	 {
	 	
		EventType calendarEventType = EventType.findByName( "CalendarEvent" );
		if( calendarEventType == null )
		{
			calendarEventType = new EventType( name:"CalendarEvent" );
			calendarEventType.save();
		}
		
		EventType activityEventType = EventType.findByName( "Activity" );
		if( activityEventType == null )
		{
			activityEventType = new EventType( name:"Activity" );
			activityEventType.save();
		}
		
		EventType subscriptionEventType = EventType.findByName( "SubscriptionEvent" );
		if( subscriptionEventType == null )
		{
			subscriptionEventType = new EventType( name:"SubscriptionEvent" );
			subscriptionEventType.save();
		}
	 }
	 
	 void createShareTargets()
	 {
		 ShareTarget streamPublicTarget = ShareTarget.findByName( ShareTarget.STREAM_PUBLIC );
		 if( !streamPublicTarget ) {
			 println "Creating new ${ShareTarget.STREAM_PUBLIC} ShareTarget";
			 streamPublicTarget = new ShareTarget();
			 streamPublicTarget.name = ShareTarget.STREAM_PUBLIC;
			 streamPublicTarget.save();
		 }
		 else 
		 {
		 	println "Found existing ${ShareTarget.STREAM_PUBLIC} ShareTarget"; 
		 }
	 }
	 
     void createSomeUsers()
     {
	 	println "Creating some users!";
     
		 boolean prhodesFound = false;
 
		 User user = userService.findUserByUserId( "prhodes" );

		 if( user != null )
		 {
			  println "Found existing prhodes user!";

		 }
		 else
	 	 {	
			  println "Could not find prhodes";
			  println "Creating new prhodes user";
			  User prhodes = new User();
			  prhodes.uuid = "abc123";
			  prhodes.displayName = "Phillip Rhodes";
			  prhodes.firstName = "Phillip";
			  prhodes.lastName = "Rhodes";
			  prhodes.email = "motley.crue.fan@gmail.com";
			  prhodes.userId = "prhodes";
			  prhodes.password = "secret";
			  prhodes.bio = "bio";
			  
			  Profile profile = new Profile();
			  // profile.userUuid = "abc123";
			  profile.setOwner( prhodes );
			  prhodes.profile = profile;
			  
			  userService.createUser( prhodes );
			 
			  println "bound user prhodes into LDAP"; 
		  }
		  
		  for( int i = 0; i < 20; i++ )
		  {
			  if( userService.findUserByUserId( "testuser${i}" ) == null )
			  {
				  println "Fresh Database, creating TESTUSER ${i} user";
				  def testUser = new User(
								  userId: "testuser${i}",
								password: "secret",
								firstName: "Test",
								lastName: "User${i}",
								email: "testuser${i}@example.com",
								bio:"stuff",
								displayName: "Test User${i}" );
				  
					Profile profile = new Profile();
					// profile.userUuid = testUser.uuid;
					profile.setOwner( testUser );
					testUser.profile = profile;
							
					userService.createUser( testUser );
			  }
			  else
			  {
				  println "Existing TESTUSER ${i} user, skipping...";
			  }
		  }
		  
	 }
} 