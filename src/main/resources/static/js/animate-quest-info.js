document.getElementById("more-inf-button").addEventListener("click", expandInfo);

function expandInfo() {
    document.getElementById("more-inf-content").style.display="flex";
    document.getElementById("more-inf-button").removeEventListener("click", expandInfo);
    document.getElementById("more-inf-button").addEventListener("click", hideInfo);
    document.getElementById("more-inf-button").innerHTML = "Hide info";
}

function hideInfo() {
    document.getElementById("more-inf-content").style.display="none";
    document.getElementById("more-inf-button").removeEventListener("click", hideInfo);
    document.getElementById("more-inf-button").addEventListener("click", expandInfo);
    document.getElementById("more-inf-button").innerHTML = "More info...";
}
