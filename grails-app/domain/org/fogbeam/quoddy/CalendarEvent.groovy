package org.fogbeam.quoddy


class CalendarEvent extends EventBase implements Serializable
{
	
	public CalendarEvent()
	{
		this.uuid = java.util.UUID.randomUUID().toString();
	}
	
	static constraints =
	{
		endDate(nullable:true);
		status(nullable:true);
		summary( nullable:true);
		description(nullable:true,maxSize:1000);
		location(nullable:true);
		url(nullable:true);
		lastModified(nullable:true);
	}

	static transients = ['templateName'];
	
	Date startDate;
	Date endDate;
	String status;
	String summary;
	String description;
	Date dateEventCreated;
	float geoLat = 999.0;
	float geoLong = 999.0;
	String location;
	String url;
	Date lastModified;
	String uid;
	CalendarFeed owningFeed;

	String uuid;
	Date dateCreated;

	
	public String getTemplateName()
	{
		return "/renderCalendarEvent";
	}
		
}
