class StockViewModel {
    constructor() {
        this.symbol = ko.observable();
        this.company = ko.observable();
        this.description = ko.observable();
        this.price = ko.observable();
        this.stocks = ko.observableArray([]);
        this.stockLookup = {};
        this.toJson = () => {
            return JSON.stringify({
                symbol: this.symbol(),
                company: this.company(),
                description: this.description(),
                price: Number(this.price())
            });
        };
         // Websocket
        this.wsBase = 'ws://localhost:8080/stockmarket/changes';
        this.websocket = new WebSocket(this.wsBase);

        this.websocket.onopen = () => {
            console.log('Connected!');
            $.ajax({
                method: 'GET',
                url: 'http://localhost:8080/stockmarket/api/v1/stocks?page=0&size=25',
                success: (stocks) => {
                    stocks.forEach(stock => this.stockLookup[stock.symbol] = stock);
                }
            });
        }
        this.websocket.onmessage = (event) => {
            let e = JSON.parse(event.data);
            let stock = {
                symbol: e.symbol,
                description: this.stockLookup[e.symbol].description,
                company: this.stockLookup[e.symbol].company,
                price: e.old_price.toFixed(3),
                newPrice: e.new_price.toFixed(3)
            };
            let stocks = this.stocks().filter(source => source.symbol != e.symbol);
            stocks.push(stock);
    		this.stocks([]);
			this.stocks(stocks);
        };
        // SSE
        this.eventSource = new EventSource("http://localhost:8080/stockmarket/api/v1/stocks/subscribe");
        this.eventSource.addEventListener("stockPriceChangedEvent", (event) => {
          console.log(event)
        });
    }
   findStock() {
        $.ajax({
            method: 'GET',
            url: 'http://localhost:8080/stockmarket/api/v1/stocks/' + this.symbol(),
            success:  (stock) => {
                this.company(stock.company);
                this.price(stock.price);
                this.description(stock.description);
            }
        });
    };

    findAllStocks() {
        $.ajax({
            method: 'GET',
            url: 'http://localhost:8080/stockmarket/api/v1/stocks?page=0&size=25',
            success: (stocks) => {
                stocks.map(stock => stock.newPrice = '');
                this.stocks(stocks);
            }
        });
    };

    removeStock(){
        $.ajax({
            method: 'DELETE',
            url: 'http://localhost:8080/stockmarket/api/v1/stocks/' + this.symbol(),
            success: (stock) => {
                this.company(stock.company);
                this.price(stock.price);
            }
        });
    };

    addStock(){
        $.ajax({
            method: 'POST',
            url: 'http://localhost:8080/stockmarket/api/v1/stocks',
            contentType: 'application/json',
            data: this.toJson(),
            success: (stock) => {
               console.log(stock)
            }
        });
    };

    updateStock() {
        $.ajax({
            method: 'PUT',
            url:  `http://localhost:8080/stockmarket/api/v1/stocks/${this.symbol()}`,
            contentType: 'application/json',
            data: this.toJson(),
            success: (stock) => {
               console.log(stock);
            }
        });
    }
}    