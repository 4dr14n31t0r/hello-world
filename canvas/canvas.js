var resultado;

function dibujar() {
	let lienzo = getLienzo();
	let buffer = crearBuffer(lienzo);
	let contexto = buffer.getContext('2d');

	let origen = getOrigenDeCoordenadas();
	contexto.translate(origen.x, origen.y);

	resultado = [];

	let array = [
		dibujarLineaDeTierra,
		dibujarOrigenDeCoordenadas,
		dibujarObservador,
		dibujarLineaDeHorizonte,
		dibujarPuntoDeFuga,
		dibujarCalculos,
		dibujarRectangulo,
		dibujarResultado
	];

	limpiarBuffer(buffer);
	redibujar(array, contexto);
	actualizar(lienzo, buffer);
}

function getLienzo() {
	return document.getElementById('lienzo');
}

function crearBuffer(canvas) {
	let buffer = document.createElement('canvas');
	buffer.width = canvas.width;
	buffer.height = canvas.height;
	return buffer;
}

function redibujar(array, contexto) {
	array.forEach(f => f(contexto));
}

function actualizar(canvas, buffer) {
	canvas.getContext('2d').drawImage(buffer, 0, 0);
}

function limpiarBuffer(buffer){
	let contexto = buffer.getContext('2d');
	contexto.save();
	contexto.fillStyle="#FFFFFF";
	let origen = getOrigenDeCoordenadas();
	contexto.fillRect(-origen.x,-origen.y,buffer.width,buffer.height);
	contexto.restore();
}

function dibujarLineaDeTierra(contexto) {
	dibujarRectaHorizontal(contexto, 0);
}

function dibujarOrigenDeCoordenadas(contexto) {
	const origen = {
		x: 0,
		y: 0
	};
	dibujarPunto(contexto, origen);
}

function dibujarObservador(contexto) {
	let observador = getObservador();
	dibujarPunto(contexto, observador);
}

function dibujarLineaDeHorizonte(contexto) {
	let observador = getObservador();
	dibujarRectaHorizontal(contexto, -observador.altura);
}

function dibujarPuntoDeFuga(contexto) {
	let observador = getObservador();
	let puntoDeFuga = getPuntoDeFuga(observador);
	dibujarPunto(contexto, puntoDeFuga);
}

function getPuntoDeFuga(observador) {
	return {
		x: observador.x,
		y: -observador.altura
	};
}

function dibujarRectangulo(contexto) {
	contexto.save();
	contexto.strokeStyle = getColorPrisma();
	let vertices = getVerticesPrisma();
	let dimensiones = getDimensionesPrisma();
	dibujarRectangulo0(contexto, vertices[0], dimensiones);
	contexto.restore();
}

function dibujarRectangulo0(contexto, vertice, dimensiones) {
	contexto.strokeRect(vertice.x, vertice.y, dimensiones.ancho, dimensiones.largo);
}

function dibujarCalculos(contexto) {
	contexto.save();
	contexto.strokeStyle = getColorCalculos();
	let vertices = getVerticesPrisma();
	let observador = getObservador();
	for (let vertice of vertices){
		dibujarCalculoVertice(contexto, observador, vertice);
	}
	contexto.restore();
}

function dibujarCalculoVertice(contexto, observador, vertice){
	dibujarRectaVertical(contexto, vertice.x);
	let puntoDeFuga = getPuntoDeFuga(observador);
	dibujarRectaAlturaVertice(contexto, vertice, puntoDeFuga);
	dibujarRectaObservadorAVertice(contexto, vertice, observador);
	let corteEnPlano = calcularCorteEnPlano(vertice, observador);
	dibujarRectaVertical(contexto, corteEnPlano);
	let corteEnAltura = calcularCorteEnAltura(puntoDeFuga, vertice, corteEnPlano);
	let punto = {
		x: corteEnPlano,
		y: corteEnAltura
	}
	dibujarPunto(contexto, punto);
	resultado.push(punto);
}

function dibujarRectaAlturaVertice(contexto, vertice, puntoDeFuga) {
	let punto = {
		x:vertice.x,
		y:vertice.altura
	}
	dibujarLinea(contexto,punto,puntoDeFuga);
}

function dibujarRectaObservadorAVertice(contexto, vertice, observador) {
	dibujarLinea(contexto,vertice,observador);
}

function calcularCorteEnPlano(vertice, observador) {
	return vertice.x - vertice.y * (vertice.x - observador.x) / (vertice.y - observador.y);
}

function calcularCorteEnAltura(puntoDeFuga, vertice, corteEnPlano) {
	return puntoDeFuga.y + (vertice.altura - puntoDeFuga.y) * (corteEnPlano - puntoDeFuga.x) / (vertice.x - puntoDeFuga.x);
}

function dibujarResultado(contexto) {
	let color = getColorPrisma();
	contexto.save();
	contexto.strokeStyle = color;

	dibujarLinea(contexto,resultado[0b000],resultado[0b001]);
	dibujarLinea(contexto,resultado[0b000],resultado[0b010]);
	dibujarLinea(contexto,resultado[0b000],resultado[0b100]);

	dibujarLinea(contexto,resultado[0b011],resultado[0b001]);
	dibujarLinea(contexto,resultado[0b011],resultado[0b010]);
	dibujarLinea(contexto,resultado[0b011],resultado[0b111]);

	dibujarLinea(contexto,resultado[0b101],resultado[0b100]);
	dibujarLinea(contexto,resultado[0b101],resultado[0b111]);
	dibujarLinea(contexto,resultado[0b101],resultado[0b001]);

	dibujarLinea(contexto,resultado[0b110],resultado[0b100]);
	dibujarLinea(contexto,resultado[0b110],resultado[0b111]);
	dibujarLinea(contexto,resultado[0b110],resultado[0b010]);

	contexto.restore();
}

function dibujarLinea(contexto,a,b){
	contexto.beginPath();
	contexto.moveTo(a.x,a.y);
	contexto.lineTo(b.x,b.y);
	contexto.stroke();
}

function dibujarPunto(contexto, coordenadas) {
	let tamano = 3;
	contexto.beginPath();
	contexto.arc(coordenadas.x, coordenadas.y, tamano, 0, 2 * Math.PI, false);
	contexto.fill();
}

function dibujarRectaHorizontal(contexto, y) {
	let origen = getOrigenDeCoordenadas();
	contexto.beginPath();
	let buffer = contexto.canvas;
	contexto.moveTo(-origen.x, y);
	contexto.lineTo(buffer.width - origen.x, y);
	contexto.stroke();
}

function dibujarRectaVertical(contexto, x) {
	let origen = getOrigenDeCoordenadas();
	contexto.beginPath();
	let buffer = contexto.canvas;
	contexto.moveTo(x, -origen.y);
	contexto.lineTo(x, buffer.height);
	contexto.stroke();
}

function getOrigenDeCoordenadas() {
	let datos = document.forms['datos'];
	return {
		x:parseInt(datos.origenX.value),
		y:parseInt(datos.origenY.value)
	};
}

function getObservador() {
	let datos = document.forms['datos'];
	return {
		x:parseInt(datos.observadorX.value),
		y:-parseInt(datos.observadorY.value),
		altura:parseInt(datos.observadorAltura.value)
	}
}

function getVerticesPrisma() {
	let datos = document.forms['datos'];

	let posicion = {
		x:parseInt(datos.prismaX.value),
		y:parseInt(-datos.prismaY.value),
		altura:parseInt(-datos.prismaAltura.value)
	};

	let dimensiones = {
		ancho:parseInt(datos.prismaAncho.value),
		alto:parseInt(datos.prismaAlto.value),
		largo:parseInt(datos.prismaLargo.value)
	};

	return getVerticesPrisma0(posicion,dimensiones);
}

function getPosicionPrisma(){
	let datos = document.forms['datos'];

	let posicion = {
		x:parseInt(datos.prismaX.value),
		y:parseInt(-datos.prismaY.value),
		altura:parseInt(datos.prismaAltura.value)
	};

	return posicion;
}

function getDimensionesPrisma(){
	let datos = document.forms['datos'];
	
	let dimensiones = {
		ancho:parseInt(datos.prismaAncho.value),
		alto:parseInt(datos.prismaAlto.value),
		largo:parseInt(datos.prismaLargo.value)
	};

	return dimensiones;
}

function getVerticesPrisma0(posicion,dimensiones){
	
	let vertices = [
		{	
			x:posicion.x-dimensiones.ancho/2,
			y:posicion.y-dimensiones.largo/2,
			altura:posicion.altura-dimensiones.alto/2
		},
		{	
			x:posicion.x+dimensiones.ancho/2,
			y:posicion.y-dimensiones.largo/2,
			altura:posicion.altura-dimensiones.alto/2
		},
		{	
			x:posicion.x-dimensiones.ancho/2,
			y:posicion.y+dimensiones.largo/2,
			altura:posicion.altura-dimensiones.alto/2
		},
		{	
			x:posicion.x+dimensiones.ancho/2,
			y:posicion.y+dimensiones.largo/2,
			altura:posicion.altura-dimensiones.alto/2
		},
		{	
			x:posicion.x-dimensiones.ancho/2,
			y:posicion.y-dimensiones.largo/2,
			altura:posicion.altura+dimensiones.alto/2
		},
		{	
			x:posicion.x+dimensiones.ancho/2,
			y:posicion.y-dimensiones.largo/2,
			altura:posicion.altura+dimensiones.alto/2
		},
		{	
			x:posicion.x-dimensiones.ancho/2,
			y:posicion.y+dimensiones.largo/2,
			altura:posicion.altura+dimensiones.alto/2
		},
		{	
			x:posicion.x+dimensiones.ancho/2,
			y:posicion.y+dimensiones.largo/2,
			altura:posicion.altura+dimensiones.alto/2
		}
	];

	return vertices;
}

function getColorCalculos(){
	let datos = document.forms['datos'];
	let color = datos.colorCalculos.value;
	return color;
}

function getColorPrisma(){
	let datos = document.forms['datos'];
	let color = datos.colorResultado.value;
	return color;
}

window.addEventListener("load", function(){setInterval(dibujar, 100);}, false);
