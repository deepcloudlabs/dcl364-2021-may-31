package com.example.crm.rs;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.crm.boundary.AcquireCustomerRequest;
import com.example.crm.boundary.AcquireCustomerResponse;
import com.example.crm.boundary.ReleaseCustomerResponse;
import com.example.crm.service.CrmService;

// curl -X POST "http://localhost:8080/crm-backend-javaee/api/v1/customers" -d "{\"id\": \"1\", \"fullname\": \"jack bauer\", \"email\": \"jack.bauer@example.com\", \"phones\": [{\"number\": \"3341348\", \"type\": 1}], \"addresses\": [{\"value\": \"istanbul, turkey\"}]}" -H "Accept: application/json" -H "Content-Type: application/json"
// curl -X DELETE "http://localhost:8080/crm-backend-javaee/api/v1/customers/1" -H "Accept: application/json"
@Path("/customers")
@ApplicationScoped
public class CrmApi {
	@Inject
	private CrmService crmService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AcquireCustomerResponse acquireCustomer(AcquireCustomerRequest request) {
		return crmService.acquireCustomer(request);
	}
	
	@Path("{identity}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public ReleaseCustomerResponse releaseCustomer(@PathParam("identity") String identity) {
		return crmService.releaseCustomer(identity);
	}
}
