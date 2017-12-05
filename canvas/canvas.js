function dibujar(){

	let lienzo = getLienzo();
	let buffer = crearBuffer(lienzo);
	let contexto = buffer.getContext('2d');

	let array = [
		dibujarTexto,

		dibujarTrianguloGrande,
		dibujarTrianguloMediano,
		dibujarTrianguloPequeno,
		dibujarTrianguloEnano,

		dibujarCuadradoAzul,
		dibujarCuadradoRojo,
		dibujarCuadradoVerde,

		dibujarCirculo,
		dibujarCorazon,
		dibujarÑordo,
		dibujarRaton
	];

	redibujar(array,contexto);
	actualizar(lienzo, buffer);

	let tiempo = 10000;
	setTimeout(() => moverCuadradoAzul			(buffer,lienzo		),tiempo			);
	setTimeout(() => sombrearTexto				(buffer,lienzo		),tiempo += 2000	);
	setTimeout(() => rotarTrianguloMayor		(buffer,lienzo,array),tiempo += 2000	);
	setTimeout(() => escalarCirculo				(buffer,lienzo		),tiempo += 2000	);
	setTimeout(() => transformarTrianguloMorado	(buffer,lienzo		),tiempo + 2000		);

	function redibujar(array,contexto){
		array.forEach(f=>f(contexto));
	}

	function getLienzo(){
		return document.getElementById('lienzo');
	}

	function crearBuffer(canvas){
		let buffer = document.createElement('canvas');
		buffer.width = canvas.width;
		buffer.height = canvas.height;
		return buffer;
	}

	function dibujarTexto(contexto){
		contexto.fillStyle="#000000";
		contexto.font="bold 26px Times New Roman";
		contexto.fillText("Ejemplo de Lienzo",5,25);
	}

	function dibujarTrianguloGrande(contexto){
		contexto.strokeStyle="#CAC7D7";
		contexto.beginPath();
		contexto.moveTo(53,377);
		contexto.lineTo(167,377);
		contexto.lineTo(167,190);
		contexto.closePath();
		contexto.stroke();
	}

	function dibujarTrianguloMediano(contexto){
		contexto.fillStyle="#BD7FBE";
		contexto.beginPath();
		contexto.moveTo(251,53);
		contexto.lineTo(188,53);
		contexto.lineTo(188,208);
		contexto.fill();
	}

	function dibujarTrianguloPequeno(contexto){
		contexto.strokeStyle="#C3E6C7";
		contexto.beginPath();
		contexto.moveTo(329,353);
		contexto.lineTo(329,377);
		contexto.lineTo(215,377);
		contexto.closePath();
		contexto.stroke();
	}

	function dibujarTrianguloEnano(contexto){
		contexto.strokeStyle="#E1E4C3";
		contexto.beginPath();
		contexto.moveTo(216,296);
		contexto.lineTo(248,296);
		contexto.lineTo(248,353);
		contexto.closePath();
		contexto.stroke();
	}

	function dibujarCuadradoAzul(contexto){
		contexto.beginPath();
		contexto.fillStyle="#0100C9";
		contexto.fillRect(41,73,28,81);
	}

	function dibujarCuadradoRojo(contexto){
		contexto.beginPath();
		contexto.fillStyle="#C70000";
		contexto.fillRect(61,81,53,89);
	}

	function dibujarCuadradoVerde(contexto){
		contexto.beginPath();
		contexto.fillStyle="#04AF15";
		contexto.fillRect(98,89,77,73);
	}

	function dibujarCirculo(contexto){
		contexto.beginPath();
		contexto.fillStyle="#C5CBD1";
		contexto.arc(309,271,40,2*Math.PI,0,true);
		contexto.stroke();
	}

	function dibujarCorazon(contexto){
		contexto.beginPath();
        contexto.moveTo(80,200);
        contexto.bezierCurveTo(15,145,0,260,80,285);
        contexto.bezierCurveTo(160,260,145,145,80,200);
        contexto.fillStyle="pink";
        contexto.fill();
	}

	function dibujarÑordo(contexto){
		contexto.strokeStyle = "#90A0AD";
		contexto.beginPath();
		contexto.moveTo(285, 170);
		contexto.quadraticCurveTo(285, 205, 230, 205);
		contexto.closePath();
		contexto.stroke();
		contexto.beginPath();
		contexto.moveTo(285, 170);
		contexto.quadraticCurveTo(285, 135, 235, 140);
		contexto.quadraticCurveTo(190, 143, 285, 170);
		contexto.stroke();
	}

	function dibujarRaton(contexto){
		contexto.strokeStyle="#A4A4A6";
		contexto.beginPath();
		contexto.moveTo(298,229); contexto.stroke();
		contexto.lineTo(298,250); contexto.stroke();
		contexto.lineTo(302,246); contexto.stroke();
		contexto.lineTo(306,253); contexto.stroke();
		contexto.lineTo(309,251); contexto.stroke();
		contexto.lineTo(305,244); contexto.stroke();
		contexto.lineTo(312,244); contexto.stroke();
		contexto.closePath();
		contexto.stroke();
	}

	function actualizar(canvas,buffer){
		canvas.getContext('2d').drawImage(buffer,0,0);
	}

	function limpiarBuffer(buffer){
		let contexto = buffer.getContext('2d');
		contexto.save();
		contexto.fillStyle="#FFFFFF";
		buffer.getContext('2d').fillRect(0,0,buffer.width,buffer.height);
		contexto.restore();
	}

	function moverCuadradoAzul(buffer,lienzo){
		let contexto = buffer.getContext('2d');

		array.splice(array.indexOf(dibujarCuadradoAzul),1);
		limpiarBuffer(buffer);

		array.push(()=>{
			contexto.save();
			contexto.globalCompositeOperation="source-atop";
			dibujarCuadradoAzul(contexto);
			contexto.restore();
		});
		redibujar(array,contexto);
		actualizar(lienzo, buffer);
	}

	function sombrearTexto(buffer,lienzo){
		let contexto = buffer.getContext('2d');

		array.splice(array.indexOf(dibujarTexto),1);
		limpiarBuffer(buffer);

		array.push(()=>{
			contexto.save();
			contexto.shadowColor="rgba(0,0,255,1)";
			contexto.shadowOffsetX=2;
			contexto.shadowOffsetY=4;
			dibujarTexto(contexto);
			contexto.restore();
		});
		redibujar(array,contexto);
		actualizar(lienzo, buffer);
	}

	function rotarTrianguloMayor(buffer,lienzo,array){
		let contexto = buffer.getContext('2d');

		array.splice(array.indexOf(dibujarTrianguloGrande),1);
		limpiarBuffer(buffer);

		array.push(()=>{
			let centroTrianguloX = (53+167)/2;
			let centroTrianguloY = (377+190)/2;
			let anguloDelCentroDelTriangulo = Math.PI/2-Math.atan(centroTrianguloY/centroTrianguloX);
			let baseTriangulo = 167-53;
			let alturaTriangulo = 377-190;
			let anguloAGirar = Math.PI-Math.atan(alturaTriangulo/baseTriangulo);
			let anguloNuevo = anguloDelCentroDelTriangulo+anguloAGirar-Math.PI/2;
			let moduloVector = Math.sqrt(Math.pow(centroTrianguloX,2)+Math.pow(centroTrianguloY,2));
			let nuevoCentroTrianguloX = moduloVector*Math.cos(anguloNuevo);
			let nuevoCentroTrianguloY = -moduloVector*Math.sin(anguloNuevo);

			contexto.save();
			contexto.translate(
				centroTrianguloX-nuevoCentroTrianguloX,
				centroTrianguloY-nuevoCentroTrianguloY
			);
			contexto.rotate(-anguloAGirar);
			dibujarTrianguloGrande(contexto);
			contexto.restore();
		});

		redibujar(array,contexto);
		actualizar(lienzo, buffer);
	}

	function escalarCirculo(buffer,lienzo){
		let contexto = buffer.getContext('2d');

		array.splice(array.indexOf(dibujarCirculo),1);
		limpiarBuffer(buffer);

		array.push(()=>{
			let centroCirculoX = 309;
			let centroCirculoY = 271;
			contexto.save();
			contexto.scale(0.5,2);
			contexto.translate(centroCirculoX,-centroCirculoY/2);
			dibujarCirculo(contexto);
			contexto.restore();
		});
		
		redibujar(array,contexto);
		actualizar(lienzo, buffer);
	}

	function transformarTrianguloMorado(buffer,lienzo){
		let contexto = buffer.getContext('2d');

		array.splice(array.indexOf(dibujarTrianguloMediano),1);
		limpiarBuffer(buffer);

		array.push(()=>{
			contexto.save();
			contexto.scale(0.5,0.5);
			contexto.translate(50,50);
			dibujarTrianguloMediano(contexto);
			contexto.restore();
		});

		redibujar(array,contexto);
		actualizar(lienzo, buffer);
	}

}

window.addEventListener("load", dibujar, false);
