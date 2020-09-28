function selectBasicQuests() {
    document.getElementById("type-choice").style.background="linear-gradient(90deg, #001513 5%, #550609 13%, #550609 40%, #001513 47%)"
}


function selectExtraQuests() {
    document.getElementById("type-choice").style.background="linear-gradient(90deg, #001513 53%, #550609 60%, #550609 87%, #001513 95%)"
}

document.getElementById("basic-quests").addEventListener("click", selectBasicQuests);
document.getElementById("extra-quests").addEventListener("click", selectExtraQuests);
