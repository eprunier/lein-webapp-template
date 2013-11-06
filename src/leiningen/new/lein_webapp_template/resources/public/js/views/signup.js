$(document).ready(function() {
    configureSignupActions();
    configurePopinActions();
});

/**
 * Configure actions associated with account creation.
 */
function configureSignupActions() {
    $("#signup").on("click", signup);

    $("input").keypress(function(e) {
	if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
	    signup();
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
function signup() {
    var requestData = "username=" + $("#username").val();
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
		$("#popin-signup-success").modal("show");
	    } else {
		$("#error-message").html(result);
		$("#popin-signup-error").modal("show");
	    }
	},
	error: function() {
	    $("#error-message").html("An error occurred. Please try again in a few moment.");
	    $("#popin-signup-error").modal("show");
	},
	complete : function() {
	    document.body.style.cursor = "default";
	}
    });
}

/** 
 * Configure actions associated with confirmation/error popin.
 */
function configurePopinActions() {
    $("#success-close-modal").on("click", closePopinSuccess);
}

/**
 * Close the popin after success and redirect user to login page.
 */
function closePopinSuccess() {
    $("#popin-signup-success").modal("show");
    redirect("/login");
}
