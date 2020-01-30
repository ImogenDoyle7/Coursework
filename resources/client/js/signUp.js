function pageLoad() {

    if(window.location.search === '?logout') {
        document.getElementById('content').innerHTML = '<h1>Logging out, please wait...</h1>';
        logout();
    } else {
        document.getElementById("signUpButton").addEventListener("click", signUp);
    }

}
function signUp(event) {

    event.preventDefault();

    const form = document.getElementById("SignUpForm");
    const formData = new FormData(form);

    fetch("/Users/signUp", {method: 'post', body: formData}
    ).then(response => response.json()
    ).then(responseData => {

        if (responseData.hasOwnProperty('error')) {
            alert(responseData.error);
        } else {
            if (responseData.password === responseData.checkPassword) {
                alert("user created");
                Cookies.set("username", responseData.username);
                Cookies.set("token", responseData.token);
            } else {
                alert("Passwords do not match");
            }


            window.location.href = '/client/userHome.html';
        }
    });
}