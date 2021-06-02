package com.example.lottery.rs;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.example.lottery.service.LotteryService;

@Path("/numbers")
public class LotteryRestService {
	@Inject
	private LotteryService lotteryService;
	// Resource -> List<List<Integer>>
	// (1) GET -> query resource ? 
	// (2) URL: http://localhost:8001/lottery-rest/api/v1/numbers?max=60&size=6&column=10
	// (3) Representation ? @Produces(MediaType.APPLICATION_JSON) -> "application/json"
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<List<Integer>> getLotteryNumbers(
			@QueryParam("max") Integer max, 
			@QueryParam("size") Integer size, 
			@QueryParam("column") Integer column){
		return lotteryService.draw(max, size, column);
	}
}
