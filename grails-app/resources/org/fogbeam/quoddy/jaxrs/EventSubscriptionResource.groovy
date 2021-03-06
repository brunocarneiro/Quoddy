package org.fogbeam.quoddy.jaxrs

import static org.grails.jaxrs.response.Responses.*

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Produces
import javax.ws.rs.core.Response

import org.fogbeam.quoddy.EventSubscription
import org.grails.jaxrs.provider.DomainObjectNotFoundException

@Consumes(['application/xml','application/json'])
@Produces(['application/xml','application/json'])
class EventSubscriptionResource
{
	def id
	
	@GET
	Response read() {
		def obj = EventSubscription.get(id)
		if (!obj) {
			throw new DomainObjectNotFoundException(Person.class, id)
		}
		ok obj
	}
	
	@PUT
	Response update(EventSubscription dto) {
		def obj = EventSubscription.get(id)
		if (!obj) {
			throw new DomainObjectNotFoundException(Person.class, id)
		}
		obj.properties = dto.properties
		ok obj
	}
	
	@DELETE
	void delete() {
		def obj = EventSubscription.get(id)
		if (obj) {
			obj.delete()
		}
	}
	
}
