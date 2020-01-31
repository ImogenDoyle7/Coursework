function pageLoad() {

    if(window.location.search === '?logout') {
        document.getElementById('content').innerHTML = '<h1>Logging out, please wait...</h1>';
        logout();
    } else {
        document.getElementById("loginButton").addEventListener("click", login);
    }

}
function login(event) {

   // event.preventDefault();
let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    /*const form = document.getElementById("loginForm");
    const formData = new FormData(form);*/
let formData = new FormData();
formData.append("email", email);
    formData.append("password", password);
    fetch("/Users/login", {method: 'post', body: formData}
    ).then(response => response.json()
    ).then(responseData => {
        alert("yeah");
if (responseData.hasOwnProperty('status')){
    alert("logged in");

}
        if (responseData.hasOwnProperty('error')) {
            alert(responseData.error);
        } else {
            alert("logged in");
            Cookies.set("username", responseData.username);
            Cookies.set("password", responseData.password);
            // Cookies.set("token", responseData.token);

            window.location.href = '/client/userHome.html';
        }
    });
}
function logout() {

    fetch("/Users/logout", {method: 'post'}
    ).then(response => response.json()
    ).then(responseData => {
        if (responseData.hasOwnProperty('error')) {

            alert(responseData.error);

        } else {

            Cookies.remove("username");
            Cookies.remove("token");

            window.location.href = '/client/index.html';

        }
    });

}


