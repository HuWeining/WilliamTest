
function notify(message, type, dismiss) {
	var delay;
	if (type === 'success') {
		delay = 5000;
	} else {
		delay = 0;
	}
	$.notify({
		message : message
	}, {
		allow_dismiss : dismiss,
		delay : delay,
		type : type,
		placement : {
			from : 'bottom',
			algin : 'right'
		},
		animate : {
			enter : 'animated fadeInUp',
			exit : 'animated fadeOutDown'
		}
	});
}

function notifyError(response){
	var error = "HTTP error! <br>";
	if (response.status !== undefined && response.status!== null && response.status.length > 0){
		error += "Error Status: ";
		error += response.status; 
	}
	if (response.statusText !== undefined && response.statusText!== null && response.statusText.length > 0){
		error += "<br> Status Text:";
		error += response.statusText; 
	}
	if (response.data!== undefined && response.data!== null && response.data.message !== undefined && response.data.message!== null && response.data.message.length > 0){
		error += "<br> message: ";
		error += response.data.message; 
	}
	if (response.data!== undefined && response.data!== null && response.data.path !== undefined && response.data.path!== null && response.data.path.length > 0){
		error += "<br> path: ";
		error += response.data.path; 
	}
	if (response.data!== undefined && response.data!== null && response.data.exception !== undefined && response.data.exception!== null && response.data.exception.length > 0){
		error += "<br> exception: ";
		error += response.data.exception; 
	}
	notify( error , "danger", true);
}
