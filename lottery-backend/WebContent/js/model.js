class LotteryViewModel {
   constructor(){
       this.numbers = ko.observableArray([]);
       this.column = ko.observable(1);
       this.draw = this.draw.bind(this);
       this.reset = this.reset.bind(this);
   }
   
   draw(){
       fetch(`http://localhost:8001/lottery-rest/api/v1/numbers?max=60&size=6&column=${this.column()}`)
           .then( res => res.json() )
           .then( lotteryNumbers => lotteryNumbers.forEach( nums => this.numbers.push(nums) ) );
   }
   
   reset() {
      this.numbers([]);
   }
}

let model = new LotteryViewModel();

window.onload = function(){
    ko.applyBindings(model);
}