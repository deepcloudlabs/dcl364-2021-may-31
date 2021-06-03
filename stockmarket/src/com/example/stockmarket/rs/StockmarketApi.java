package com.example.stockmarket.rs;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.example.stockmarket.boundary.StockRequest;
import com.example.stockmarket.boundary.StockResponse;
import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.repository.StockRepository;

// curl -X POST "http://localhost:8080/stockmarket/api/v1/stocks" -d "{\"price\": 300, \"symbol\": \"msft\", \"company\": \"microsoft inc.\", \"description\": \"no comment\" }" -H "Accept: application/json" -H "Content-Type: application/json"
// curl -X GET  "http://localhost:8080/stockmarket/api/v1/stocks?page=0&size=10"
// curl -X GET  "http://localhost:8080/stockmarket/api/v1/stocks/orcl"
// curl -X PUT  "http://localhost:8080/stockmarket/api/v1/stocks/msft" -d "{\"price\": 301, \"symbol\": \"msft\", \"company\": \"microsoft inc.\", \"description\": \"no comment\" }" -H "Accept: application/json" -H "Content-Type: application/json"
// curl -X DELETE  "http://localhost:8080/stockmarket/api/v1/stocks/msft"

@Path("/stocks")
public class StockmarketApi {
	private static final Function<Stock,StockResponse> STOCK_TO_STOCK_RESPONSE = stock -> new StockResponse("ok", stock.getSymbol(), stock.getPrice());
	// Notes on Dependency Injection
	// Java EE -> CDI -> @Inject, 
    // Java EE -> JPA -> @PersistenceContext -> EntityManager (Connection)/EntityManagerFactory (Connection Pool)
    // Java EE -> Managed Resource -> @Resource
                         // Connection Pool -> @Resource private DataSource dataSource;
                         // JMS -> Topic/Queue -> @Resource private Queue queue;
                         // ManagedExecutorService -> @Resource private ManagedExecutorService es;
						 // JavaMail
    // Spring -> @Autowired, @Inject, @Resource
	@Inject
	private StockRepository stockRepository;
	
	// http://localhost:8080/stockmarket/api/v1/stocks/orcl
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{symbol}")
	// ACL: Anti-Corruption Layer/OHS: Open Host Service
	public StockResponse findBySymbol(@PathParam("symbol") String symbol) {
		 var stock = stockRepository.findByIdentity(symbol);
		 if (stock.isEmpty())
			 return new StockResponse("not found");
		 return new StockResponse("ok", symbol, stock.get().getPrice());
	} 
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// ACL: Anti-Corruption Layer/OHS: Open Host Service
	public List<StockResponse> findAll(@QueryParam("page") int page,@QueryParam("size") int size) {
		return stockRepository.findAll(page, size)
				     .stream()
				     .map(STOCK_TO_STOCK_RESPONSE)
				     .collect(toList());
	} 
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StockResponse addStock(StockRequest stockRequest) {
		var createdStock = stockRepository.create(stockRequest.toStock());
		return new StockResponse("ok", createdStock.getSymbol(), createdStock.getPrice());
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{symbol}")
	public StockResponse updateStock(StockRequest stockRequest) {
		var createdStock = stockRepository.update(stockRequest.toStock());
		return new StockResponse("ok", createdStock.getSymbol(), createdStock.getPrice());
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{symbol}")
	public StockResponse removeBySymbol(@PathParam("symbol") String symbol) {
		 var stock = stockRepository.removeByIdentity(symbol);
		 if (stock.isEmpty())
			 return new StockResponse("not found");
		 return new StockResponse("ok", symbol, stock.get().getPrice());
	}
}
