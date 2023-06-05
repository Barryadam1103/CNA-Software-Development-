"use strict";
const scores = [];
const scoresString = [];

const displayScores = (scores, scoresString) => {

    let scoreArrayAverage = 0;
    let scoresStringArray = "";

    for(let s = 0; s < scores.length; s++){
        scoreArrayAverage += parseFloat(scores[x]);
    }

    for(let x = 0; x < scoresString.length; x++){
        scoresStringArray += scoresString[x] + "\n";
    }
  

    $("#average_score").text(scoreArrayAverage / scores.length);
    $("#scores").text(scoresStringArray);
    
};

	


$(document).ready( () => {
    $("#add_button").click( () => {

        scoresString.push($("#scores").val() + "," +  $("#last_name").val() + "," + $("#first_name").val() + ":" + $("#score").val() );
        displayScores();
        
        
        
        // get the add form ready for next entry
        $("#first_name").val( "" );
        $("#last_name").val( "" );
        $("#score").val( "" );
        $("#first_name").focus();
        
    }); // end click()



    
    $("#clear_button").click( () => {

        scores.length = 0;
        scoresString.length = 0;


        // remove the score data from the web page
        $("#average_score").val( "" );
        $("#scores").val( "" );

        $("#first_name").focus();
    }); // end click()


       
    $("#sort_button").click( () => {
        scoresString.sort()

    }); // end click()
    
    $("#first_name").focus();
}); // end ready()

