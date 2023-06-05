//const { argv } = require('process');

const fs = require('fs').promises;

const scriptName = process.argv[1];
const fileName = process.argv[2];

fs.readFile(fileName, "utf8")
    .then(console.log("Script:", scriptName))
    .then(console.log("Filename:", fileName))
    .then(list =>  console.log(list)) 
    .catch(error => console.log(error));
