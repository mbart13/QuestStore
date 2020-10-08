let OSName="Default OS";
if (navigator.appVersion.indexOf("Linux")!==-1) OSName="Linux";

let font = 'the_blame.ttf'
if (OSName === 'Linux'){
    font = 'the_blame2.ttf'
}

let newStyle = document.createElement('style');
newStyle.appendChild(document.createTextNode("\
@font-face {\
    font-family: 'BLAME Regular';\
    font-style: normal;\
    font-weight: normal;\
    src: url('../../static/assets/" + font + "') format('truetype');\
}\
"))

document.head.appendChild(newStyle);