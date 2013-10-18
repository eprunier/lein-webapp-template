$(document).ready(function() {
    $("#login").on("click", login);
});

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
		$('#modal-login').modal("show");
	    }
	},
	error: function() {
	    $("#modal-message").html("An error occurred. Please try again in a few moment.");
	    $('#modal-login').modal("show");
	},
	complete : function() {
	    document.body.style.cursor = "default";
	}
    });
}
