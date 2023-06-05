"use strict";

$(document).ready( () => {
	// display data from session storage
	//$("#profile").text(sessionStorage.profile);
	//$("#email").text( sessionStorage.email );
	//$("#phone").text( sessionStorage.phone );
	//$("#zip").text( sessionStorage.zip );
	//$("#dob").text( sessionStorage.dob );

	const json = sessionStorage.sess_storage;

	const sess_storage = JSON.parse(sessionStorage.getItem("profile"));

	for(let x=0; x < sess_storage.length; x++){
		

		if (sess_storage[x][0] == "email") {
			$("#email").text( sess_storage[x][1]);

		} else if (sess_storage[x][0] == "phone") {
			$("#phone").text( sess_storage[x][1]);

		} else if (sess_storage[x][0] == "zip"){
			$("#zip").text( sess_storage[x][1]);

		} else if (sess_storage[x][0] == "dob"){
			$("#dob").text( sess_storage[x][1]);

		}


	}

	
	$("#back").click( () => {
		history.back();
	}); // end of click()
	
}); // end of ready()