function pageLoad() {

    let now = new Date();

    let myHTML = '<div style="text-align:center;">'
        + '<h1>Student Revision Tool</h1>'
        + '<button type="button">Log In</button>'
        + '<button type="button">Sign Up</button>'
        + '<img src="/client/img/logo.png"  alt="Logo"/>'
        + '<div style="font-style: italic;">'
        + 'Generated at ' + now.toLocaleTimeString()
        + '</div>'
        + '</div>';

    document.getElementById("testDiv").innerHTML = myHTML;
}
function checkLogin() {

    let now = new Date();

    let myHTML = '<div style="text-align:center;">'
        + '<h1>Student Revision Tool</h1>'
        + '<button type="button">Log In</button>'
        + '<button type="button">Sign Up</button>'
        + '<img src="/client/img/logo.png"  alt="Logo"/>'
        + '</div>'
        + '</div>';

    document.getElementById("testDiv").innerHTML = myHTML;
}
