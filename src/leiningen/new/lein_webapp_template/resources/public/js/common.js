/**
 * Execute the fn only if the session is active.
 */
function requireSession(fn) {
    document.body.style.cursor = "progress";
    $.ajax({
	type : "GET",
	url : "check-session",
	success : function(result) {
	    if (result == "active") {
		fn();
	    } else {
		location.assign("/");
	    }
	},
	error : function(e) {
	    location.assign("/");
	}
    });
}
