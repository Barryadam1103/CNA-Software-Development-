//The underscore Filter function looks through each value in the list, returning an array of all the values that pass a truth test 
const nums = [10, 50, 3, 1, 40, 4, 70, 899, 90, 14, 19, 50, 21, 25 ]; 

var _ = require('underscore'); 
var underscoreNumbers = _.filter(nums, function (x) { return x <= 25 }); 
console.log(underscoreNumbers); 