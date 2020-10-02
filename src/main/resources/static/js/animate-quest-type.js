function selectBasicQuests() {
    document.getElementById("type-choice").style.background="linear-gradient(90deg, #001513 5%, #550609 13%, #550609 40%, #001513 47%)"
    changeClassDisplay('extra', 'none')
    changeClassDisplay('basic', 'flex')
}


function selectExtraQuests() {
    document.getElementById("type-choice").style.background="linear-gradient(90deg, #001513 53%, #550609 60%, #550609 87%, #001513 95%)"
    changeClassDisplay('extra', 'flex')
    changeClassDisplay('basic', 'basic')
}

function changeClassDisplay(elementClass, display) {
    let elements = document.getElementsByClassName(elementClass);
    for(let i = 0; i < elements.length; i++) {
        elements[i].style.display = display;
    }
}

document.getElementById("basic-quests").addEventListener("click", selectBasicQuests);
document.getElementById("extra-quests").addEventListener("click", selectExtraQuests);
