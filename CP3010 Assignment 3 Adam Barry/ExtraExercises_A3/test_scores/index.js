"use strict";

const scores = [];

const displayScores = () => scores.join(", ");

const calculateAverage = () => {
    const total = scores.reduce( (prev, curr) => prev + parseInt(curr), 0);
    return total / scores.length;
};

// load user entries in scores array
const averageScore = process.argv;

for (let x = 0; x < averageScore.length; x++) {
  //Skip the first two elements in the process.argv array.
  if (x != 0 && x != 1) {
    scores.push(averageScore[x]);
  }
}

// display all scores
console.log("All Scores: " + displayScores());


// display average score
console.log("Average Score: " + calculateAverage());
