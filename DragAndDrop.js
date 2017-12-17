/*
dragstart
drag
dragend

dragenter
dragover
dragleave
drop
*/

window.addEventListener('load',function(){
    var dragstart   = document.getElementById('dragstart',false);
    var drag        = document.getElementById('drag',false);
    var dragend     = document.getElementById('dragend',false);

    var dragenter   = document.getElementById('dragenter',false);
    var dragover    = document.getElementById('dragover',false);
    var dragleave   = document.getElementById('dragleave',false);
    var drop        = document.getElementById('drop',false);

    var imagen = document.getElementById('imagen');
    var destino = document.getElementById('destino');
    
    imagen.addEventListener('dragstart',function(ev){
        //ev.preventDefault();
        dragstart.innerText++;
    });
    imagen.addEventListener('drag',function(ev){
        ev.preventDefault();
        drag.innerText++;
    });
    imagen.addEventListener('dragend',function(ev){
        ev.preventDefault();
        dragend.innerText++;
    });

    destino.addEventListener('dragenter',function(ev){
        ev.preventDefault();
        dragenter.innerText++;
    });
    destino.addEventListener('dragover',function(ev){
        ev.preventDefault();
        dragover.innerText++;
    });
    destino.addEventListener('dragleave',function(ev){
        ev.preventDefault();
        dragleave.innerText++;
    });
    destino.addEventListener('drop',function(ev){
        ev.preventDefault();
        drop.innerText++;
    });
});