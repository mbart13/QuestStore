document.getElementById("sign-out").addEventListener("click", logout);
document.getElementById("profile").addEventListener("click", goToProfile);
document.getElementById("users-icon").addEventListener("click", goToUsers);

function logout() {
    window.location.replace("/logout");
}

function goToProfile() {
    window.location.replace("/loginRedirect");
}

function goToUsers() {
    window.location.replace("/users");
}