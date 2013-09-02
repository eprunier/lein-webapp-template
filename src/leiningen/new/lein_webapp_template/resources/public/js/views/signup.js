$(document).ready(function() {
    $("#signup").on("click", signup);
    $("#success-close-modal").on("click", closeModalSuccess);
});

/**
 * Send the signup request to the server with an Ajax call
 * and display a modal popup with the result.
 */
function signup() {
    var requestData = "?username=" + $("#username").val();
    requestData += "&email=" + $("#email").val();
    requestData += "&password=" + $("#password").val();
    requestData += "&password-check=" + $("#password-check").val();

    $.ajax({
	type : "POST",
	url : getContextRoot() + "/signup",
	data : requestData,
	success : function(result) {
	    if (result == "ok") {
		$("#success-message").html("Thank you for your registration.");
		$('#modal-signup-success').modal("show");
	    } else {
		$("#error-message").html(result);
		$('#modal-signup-error').modal("show");
	    }
	},
	error: function() {
	    $("#error-message").html("An error occurred. Please try again in a few moment.");
	    $('#modal-signup-error').modal("show");
	},
	complete : function() {
	    document.body.style.cursor = "default";
	}
    });
}

/**
 * Close the modal popup and redirect user to login page.
 */
function closeModalSuccess() {
    $('#modal-signup-success').modal("show");
    redirect("/login");
}
