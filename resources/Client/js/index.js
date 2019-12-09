function pageLoad() {

    let now = new Date();

    let myHTML = '<div style="text-align:center;">'
        + '<h1>Student Revision Tool</h1>'
        + '<button type="LogInButton">Log In</button>'
        + '<button type="signUpButton">Sign Up</button>'
        + '<img src="/client/img/logo.png"  alt="Logo"/>'
        + '<div style="font-style: italic;">'
        + 'Generated at ' + now.toLocaleTimeString()
        + '</div>'
        + '</div>';

    document.getElementById("testDiv").innerHTML = myHTML;
}
function checkLogin() {

    let myHTML = '<div style="text-align:center;">'
        + '<h1>Student Revision Tool</h1>'
        + '<button type="LogInButton">Log In</button>'
        + '<button type="signUpButton">Sign Up</button>'
        + '<img src="/client/img/logo.png"  alt="Logo"/>'
        + '</div>'
        + '</div>';

    document.getElementById("testDiv").innerHTML = myHTML;
}
function checkLogin() {

    let email = Cookies.get("email");

    let logInHTML = '';

    if (email === undefined) {

        let LogInButton = document.getElementsByClassName("Log In");
        for (let button of LogInButton) {
            button.style.visibility = "hidden";
        }

        let signUpButton = document.getElementsByClassName("Sign Up");
        for (let button of signUpButton) {
            button.style.visibility = "hidden";
        }

        logInHTML = "Not logged in. <a href='/client/login.html'>Log in</a>";
    } else {

        let LogInButton = document.getElementsByClassName("Log In");
        for (let button of LogInButton) {
            button.style.visibility = "visible";
        }

        let signUpButton = document.getElementsByClassName("Sign Up");
        for (let button of signUpButton) {
            button.style.visibility = "visible";
        }
        checkLogin();
        logInHTML = "Logged in as " + email + ". <a href='/client/login.html?logout'>Log out</a>";

    }

    document.getElementById("loggedInDetails").innerHTML = logInHTML;

}
