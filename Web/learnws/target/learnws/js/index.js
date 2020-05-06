var loc = window.location;
var uri;
if (loc === 'https:') {
    uri = 'wss:'
} else {
    uri = 'ws:'
}
uri += ('//' + loc.host);
uri += (loc.pathname + 'chat');
console.log(uri);

var ws = new WebSocket(uri);

ws.onerror = (e) => {
    $("#msg").html(e.data + ' error');
}

ws.onmessage = (event) => {
    $("#msg").html(event.data);
}

ws.onclose = (event) => {
    $("#msg").html(event.data);
}

ws.onopen = (event) => {
    $("#msg").html(event.data);
}

window.onbeforeunload = () => {
    ws.close();
}

function send() {
    var msg = $("#text").val();
    ws.send(msg);
}

function closeWebSocket() {
    ws.close();
}