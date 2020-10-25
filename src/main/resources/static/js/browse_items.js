const basicItems = document.querySelector('.basic-items');
const magicItems = document.querySelector('.magic-items');

function selectBasicItems() {
    document.querySelector('.header').classList.add('basic-items-selected');
    document.querySelector('.header').classList.remove('extra-items-selected');
    changeClassDisplay('basic', 'block');
    changeClassDisplay('magic', 'none');
}

function selectMagicItems() {
    document.querySelector('.header').classList.add('extra-items-selected');
    document.querySelector('.header').classList.remove('basic-items-selected');
    changeClassDisplay('basic', 'none');
    changeClassDisplay('magic', 'block');
}

function changeClassDisplay(elementClass, display) {
    const elements = document.getElementsByClassName(elementClass);
    for(let i = 0; i < elements.length; i++) {
        elements[i].style.display = display;
    }
}

basicItems.addEventListener("click", selectBasicItems);
magicItems.addEventListener("click", selectMagicItems);
