"use strict";
const coins =  {
    quarters: 0,
    dimes: 0,
    nickels: 0,
    cents: 0,
    pennies: 0,

    makeChange(cents){
        if (isNaN(cents) || cents < 0 || cents > 99) {

            alert("Please enter a valid number between 0 and 99");
        } else {
            this.cents = cents;
            //this. added to quarters/dimes/nickels/pennies due to the fact we want to access the objects properties. We want this
            //quarter and this dimes etc....
            
            // calculate the number of quarters
            let quarters = this.cents / 25;  // get number of quarters
            this.quarters = Math.floor(quarters);
            this.cents = this.cents % 25; // assign the remainder to the cents variable

            // calculate the number of dimes
            let dimes = this.cents / 10; // get number of dimes
            this.dimes = Math.floor(dimes);
            this.cents = this.cents % 10; // assign the remainder to the cents variable

            // calculate the number of nickels
            let nickels = this.cents / 5;
            this.nickels = Math.floor(nickels);
            this.cents = this.cents % 5


            // calculate the number of nickels and pennies
            // Fixed the pennies one due to the fact if I put 67 in I only got 2 quarters 1 dime and 1 nickel but i was missing the 2
            //pennies so I had to add that little line below---
            let pennies = this.cents % 5;
            this.pennies = pennies;
            

    }

}

};
