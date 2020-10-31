const a = document.querySelector('#delete');
const message = document.querySelector('#confirmation');

a.addEventListener("click", (e) => {
    e.preventDefault();
    message.style.display = 'block';
})