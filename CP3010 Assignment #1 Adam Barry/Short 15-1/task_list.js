"use strict";

$(document).ready( () => {
    const tasks = [];

    $("#add_task").click( () => {   
        const textbox = $("#task");
        const task = textbox.val();

        if (task === "") {
            alert("Please enter a task.");
            textbox.focus();
        } else {
            //Using .split
            const thearray = task.split(",");

            //for of loop that goes through the array and then .push (a)
            for(let a of thearray){
            // add task to array
            tasks.push(a);
            }
            // clear task text box and re-display tasks
            textbox.val("");
            $("#task_list").val(tasks.join("\n"));
            textbox.focus();
        }
    });
    
    $("#clear_tasks").click( () => {
        tasks.length = 0;
        $("#task_list").val("");
        $("#task").focus();
    }); 
    
    $("#task").focus();
});