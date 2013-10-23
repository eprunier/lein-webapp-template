/**
 * Returns the context root of the application which is stored in
 * a hidden field in all pages.
 */
function getContextRoot() {
    return $("#context-root").val();
}

/**
 * Execute the fn only if the session is valid.
 * @param fn the function to execute if the session is valid.
 */
function requireSession(fn) {
    document.body.style.cursor = "progress";
    $.ajax({
	type : "GET",
	url : getContextRoot() + "check-session",
	success : function(result) {
	    if (result == "valid") {
		fn();
	    } else {
		redirect(getContextRoot() + "/");
	    }
	},
	error : function(e) {
	    redirect(getContextRoot() + "/");
	}
    });
}

/**
 * Redirect to the specified path.
 * @param path destination
 */
function redirect(path) {
    location.assign(getContextRoot() + path);
}

/**
 * Reload the specified path.
 * @param path destination
 */
function reloadPage(path) {
    redirect(path);
    location.reload(true);
}
