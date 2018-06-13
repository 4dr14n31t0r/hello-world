function clickAnadirPregunta(boton){

    let p = document.createElement('p');

    let pregunta = document.createElement('input');
    pregunta.setAttribute('type','text');
    pregunta.setAttribute('placeholder','Pregunta');
    pregunta.setAttribute('name','pregunta');

    let botonX = document.createElement('input');
    botonX.setAttribute('type','button');
    botonX.setAttribute('value','X');
    botonX.setAttribute('onclick','clickEliminarPregunta(this)');

    p.appendChild(pregunta);
    p.appendChild(botonX);

    boton.parentElement.insertBefore(p,boton);



    p = document.createElement('p');

    let numRespuestas = document.createElement('input');
    numRespuestas.setAttribute('type','hidden');
    numRespuestas.setAttribute('name','cantidadRespuestas');
    numRespuestas.setAttribute('value','3');

    p.appendChild(numRespuestas);

    let respuesta, br;

    for (let i = 0; i < 3; i++) {
        respuesta = document.createElement('input');
        respuesta.setAttribute('type','text');
        respuesta.setAttribute('placeholder','respuesta');
        respuesta.setAttribute('name','respuesta');

        botonX = document.createElement('input');
        botonX.setAttribute('type', 'button');
        botonX.setAttribute('value', 'X');
        botonX.setAttribute('onclick', 'clickEliminarOpcion(this)');

        br = document.createElement('br');
        
        p.appendChild(respuesta);
        p.appendChild(botonX);
        p.appendChild(br);
    }

    let anadirOpcion = document.createElement('input');
    anadirOpcion.setAttribute('type','button');
    anadirOpcion.setAttribute('value','Añadir opcion');
    anadirOpcion.setAttribute('onclick','clickAnadirOpcion(this)');

    br = document.createElement('br');

    p.appendChild(anadirOpcion);
    p.appendChild(br);

    boton.parentElement.insertBefore(p,boton);
}

function clickEliminarPregunta(boton){
    boton.parentElement.nextElementSibling.remove();
    boton.parentElement.remove();
}

function clickAnadirOpcion(boton){
    let texto = document.createElement('input');
    texto.setAttribute('type','text');
    texto.setAttribute('placeholder','respuesta');
    texto.setAttribute('name','respuesta');

    let botonX = document.createElement('input');
    botonX.setAttribute('type', 'button');
    botonX.setAttribute('value', 'X');
    botonX.setAttribute('onclick', 'clickEliminarOpcion(this)');

    let br = document.createElement('br');
    
    boton.parentElement.insertBefore(texto,boton);
    boton.parentElement.insertBefore(botonX,boton);
    boton.parentElement.insertBefore(br,boton);

    boton.parentElement.firstElementChild.setAttribute(
        "value",parseInt(boton.parentElement.firstElementChild.getAttribute("value"))+1
    );
}

function clickEliminarOpcion(boton){
    boton.previousElementSibling.remove();
    boton.nextElementSibling.remove();
    boton.parentElement.firstElementChild.setAttribute(
        "value",parseInt(boton.parentElement.firstElementChild.getAttribute("value"))-1
    );
    boton.remove();
}