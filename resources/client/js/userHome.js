function pageLoad() {
    /*if (Cookies.get('token') === ""){
        window.location.href = '../login.html'
    }
      window.onload=pageLoad();*/

    let now = new Date();

    let myHTML = '<div style="text-align:center;">'
        + '<h1>User Home</h1>'
        + '<img src="/client/img/study.gif"  alt="Logo"/>'
        + '<button id="Subjects">Subjects</button>'
        + '<button id="UserDetails">User Details</button>'
        + '<div style="font-style: italic;">'
        + 'Generated at ' + now.toLocaleTimeString()
        + '</div>'
        + '</div>';

    document.getElementById("testDiv").innerHTML = myHTML;
}