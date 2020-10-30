function logout() {
    window.location.replace("/logout");
}

function goToProfile() {
    window.location.replace("/loginRedirect");
}

function goToUsers() {
    window.location.replace("/users/page/1?sortField=id&sortDir=asc");
}

function sortTable(currentPage, columnHeader, reverseSortDir) {
    window.location.replace(`/users/page/${currentPage}?sortField=${columnHeader}&sortDir=${reverseSortDir}`);
}

function goToUsersNew() {
    window.location.replace("/users/new");
}

function changePassword() {
    window.location.replace('/admin/profile-page/edit');
}

function uploadImage() {
    window.location.replace('/admin/profile-page/image-form');
}