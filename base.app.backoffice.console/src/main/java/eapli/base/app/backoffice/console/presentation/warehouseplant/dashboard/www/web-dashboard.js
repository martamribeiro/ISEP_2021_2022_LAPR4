function getMatrix() {
    var request = new XMLHttpRequest();
    var vBoard = document.getElementById("matrix");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="black";
        setTimeout(getMatrix, 5000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(getMatrix, 1000);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(getMatrix, 10000);
    };

    request.open("GET", "/matrix", true);
    request.timeout = 10000;
    request.send();
}

function refreshPositions() {
    var request = new XMLHttpRequest();
    var vBoard = document.getElementById("agvs");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="black";
        setTimeout(refreshPositions, 5000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshPositions, 1000);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshPositions, 10000);
    };

    request.open("GET", "/agvs", true);
    request.timeout = 10000;
    request.send();
}
