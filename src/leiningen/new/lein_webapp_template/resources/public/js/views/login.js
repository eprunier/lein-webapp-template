$(document).ready(function() {
    configureLoginActions();
});

/**
 * Configure actions associated with authentication.
 */
function configureLoginActions() {
    $("#login").on("click", login);

    $("input").keypress(function(e) {
	if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
	    login();
	    return false;
	} else {
	    return true;
	}
    });
}

/**
 * Send the signup request to the server with an Ajax call
 * and display a modal popup with the result.
 */
function login() {
    var requestData = "username=" + $("#username").val();
    requestData += "&password=" + $("#password").val();

    $.ajax({
	type : "POST",
	url : getContextRoot() + "/login",
	data : requestData,
	success : function(result) {
	    if (result == "ok") {
		redirect("/");
	    } else {
		$("#modal-message").html(result);
		$("#popin-login").modal("show");
	    }
	},
	error: function() {
	    $("#modal-message").html("An error occurred. Please try again in a few moment.");
	    $("#modal-login").modal("show");
	},
	complete : function() {
	    document.body.style.cursor = "default";
	}
    });
}
