document.getElementById("quests-link").addEventListener("click", goToQuests)
document.getElementById("profile-link").addEventListener("click", goToProfile)

function goToQuests() {
    window.location.replace("/quests")
}

function goToProfile() {
    window.location.replace("/loginRedirect")
}