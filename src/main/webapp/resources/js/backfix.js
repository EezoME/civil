history.pushState(null, document.title, location.pathname);

window.addEventListener("popstate", function() {
    setTimeout(function(){
        location.replace(document.referrer);
    },0);
}, false);

setInterval(function() {
    history.pushState(null, document.title, location.pathname);
}, 1000);