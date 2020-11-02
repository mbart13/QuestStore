const defaultPage = 1;
const defaultSortField = "id";
const defaultSortDirection = "asc";

function goToLogin() {
    window.location.replace("/login");
}

function logout() {
    window.location.replace("/logout");
}

function goToProfile() {
    window.location.replace("/loginRedirect");
}

function goToUsers() {
    window.location.replace(`/users/page/${defaultPage}?sortField=${defaultSortField}&sortDir=${defaultSortDirection}`);
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

function goToLostPassword() {
    window.location.replace("/forgotten-password");
}