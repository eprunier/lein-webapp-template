$(document).ready(function() {
    $("#reset").on("click", reset);
});

/**
 * Send a reset request to the server with an Ajax call
 * and display the result in a modal popup.
 */
function reset() {
    var requestData = "?username=" + $("#username").val();

    $.ajax({
	type : "POST",
	url : getContextRoot() + "/reset-pass",
	data : requestData,
	success : function(result) {
	    if (result == "ok") {
		$("#modal-message").html("Password reseted.");
		$("#modal-message").removeClass("alert alert-danger");
	    } else {
		$("#modal-message").html("An error occurred. Please try again in a few moment.");
		$("#modal-message").addClass("alert alert-danger");
	    }
	    $('#modal-reset').modal("show");
	},
	error : function() {
	    $("#error-message").html("An error occurred. Please try again in a few moment.");
	    $('#modal-signup-error').modal("show");
	},
	complete : function() {
	    document.body.style.cursor = "default";
	}
    });
}
