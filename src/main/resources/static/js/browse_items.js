const basicHeader = document.querySelector('.basic-items');
const magicHeader = document.querySelector('.magic-items');
const basic_items = document.querySelectorAll(".basic");
const magic_items = document.querySelectorAll(".magic");
const header = document.querySelector('.header');

basicHeader.addEventListener('click', () => {
    basic_items.forEach(item => {
        item.classList.add("show");
        item.classList.remove("hide");
    });
    magic_items.forEach(item => {
        item.classList.add("hide");
        item.classList.remove("show");
    });
    basicHeader.classList.add('selected')
    magicHeader.classList.remove('selected')
    header.classList.add('basic-items-selected')
    header.classList.remove('extra-items-selected')
});

magicHeader.addEventListener('click', () => {
    basic_items.forEach(item => {
        item.classList.add("hide");
        item.classList.remove("show");
    });
    magic_items.forEach(item => {
        item.classList.add("show");
        item.classList.remove("hide");
    });
    basicHeader.classList.remove('selected')
    magicHeader.classList.add('selected')
    header.classList.add('extra-items-selected')
    header.classList.remove('basic-items-selected')
});


