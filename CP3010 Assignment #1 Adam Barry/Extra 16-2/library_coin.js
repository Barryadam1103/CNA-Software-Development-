"use strict";
class Coins{
    constructor(quarters, dimes, nickels, pennies){
        this.quarters = quarters = 0;
        this.dimes = dimes = 0;
        this.nickels = nickels = 0;
        this.pennies = pennies = 0;
        
    }

    makeChange(cents){
        //let cents = Math.floor( parseInt( $("#cents").val() ) );

        if (isNaN(cents) || cents < 0 || cents > 99) {
            alert("Please enter a valid number between 0 and 99");
        } else {
            //this.cents = cents;
            // calculate the number of quarters
            this.quarters = cents / 25;  // get number of quarters
            this.quarters = Math.floor(this.quarters);
            cents = cents % 25;         // assign the remainder to the cents variable

            // calculate the number of dimes
            this.dimes = cents / 10;     // get number of dimes
            this.dimes = Math.floor(this.dimes);
            cents = cents % 10;         // assign the remainder to the cents variable

            // calculate the number of nickels
            this.nickels = cents / 5;
            this.nickels = Math.floor(this.nickels);
            cents = cents % 5;

            // calculate the number of nickels and pennies
            this.pennies = cents;


        }

    }

}
