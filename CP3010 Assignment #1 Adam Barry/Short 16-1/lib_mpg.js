"use strict";

class Trip{
    constructor(miles, gallons){
        this.miles = miles;
        this.gallons = gallons;
    }

    get isValid() {
        if (isNaN(this.miles) || isNaN(this.gallons)) {
            return false;
        } else if (this.miles <= 0 || this.gallons <= 0) {
            return false;
        } else {
            return true;
        }
    }
    calculateMPG() {
        return this.miles / this.gallons;
    }




}




const mpg = {
    miles: 0,
    gallons: 0,
    get isValid() {
        if (isNaN(this.miles) || isNaN(this.gallons)) {
            return false;
        } else if (this.miles <= 0 || this.gallons <= 0) {
            return false;
        } else {
            return true;
        }
    },
    calculateMPG() {
        return this.miles / this.gallons;
    }
};