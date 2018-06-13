from django.shortcuts import render, redirect
from django.utils import timezone
from .models import Usuario, Encuesta, Pregunta, Respuesta, Comentario

def index(request):
	if 'logout' in request.POST:
		del request.session['usuario']
	encuestas = Encuesta.objects.all()
	if 'ordenacion' in request.POST:
		encuestas = encuestas.order_by(request.POST['ordenacion'])
	if 'buscar' in request.POST:
		encuestas = encuestas.filter(titulo__contains=request.POST['textoBuscar'])
	datosEncuestas = []
	for encuesta in encuestas:
		datosEncuestas.append({
			'pk':encuesta.pk,
			'titulo':encuesta.titulo,
			'fechaPub':encuesta.fechaPub,
			'visitas':encuesta.visitas,
			'numPreguntas':encuesta.pregunta_set.count(),
			'numComentarios':encuesta.comentario_set.count(),
			'usuario':encuesta.usuario.nombre
		})
	login = 'usuario' in request.session
	contexto = {
		'datosEncuestas' : datosEncuestas,
		'login' : login
	}
	return render(request, 'encuestasApp/index.html', contexto)

def login(request):
	camposLlenos = 'usuario' in request.POST and 'contrasena' in request.POST and request.POST['usuario'] and request.POST['contrasena']
	#Si no se han dejado campos vacios...
	if camposLlenos:
		nombreUsuario = request.POST['usuario']
		cont = request.POST['contrasena']
		#Si el usuario esta en la BD...
		if Usuario.objects.filter(nombre=nombreUsuario, contrasena=cont).exists():
			#...entonces lo cogemos de la BD y lo guardamos en la sesion
			usuario = Usuario.objects.get(nombre=nombreUsuario, contrasena=cont)
			request.session['usuario'] = usuario
			return redirect('/')
		#Si el usuario no esta en la BD...
		else:
			hayAlgunError = True
			mensajeDeError = "Usuario y/o contraseña incorrectos"
	#Si 'usuario' in request.POST = False puede ser porque
	#o bien se ha dejado el campo de usuario vacio o porque
	#se acaba de acceder a esta pagina para hacer login.
	#Si 'hacerLogin' in request.POST = True entonces se ha
	#enviado el formulario y por lo tanto hay campos vacios
	elif 'hacerLogin' in request.POST:
		hayAlgunError = True
		mensajeDeError = "No pueden quedar campos vacíos"
	#Este else se ejecutara si acabamos de entrar para hacer login
	else:
		hayAlgunError = False
		mensajeDeError = ""
	return render(request,'encuestasApp/login.html',{
		'hayAlgunError':hayAlgunError,
		'mensajeDeError':mensajeDeError
	})

def registrarse(request):
	camposLlenos = 'nombre' in request.POST and 'contrasena' in request.POST and 'contrasena2' in request.POST and request.POST['nombre'] and request.POST['contrasena'] and request.POST['contrasena2']
	#Si no se han dejado campos vacios...
	if camposLlenos:
		nombre = request.POST['nombre']
		contrasena = request.POST['contrasena']
		contrasena2 = request.POST['contrasena2']
		#Si las contrasenas no coinciden...
		if contrasena != contrasena2:
			hayAlgunError = True
			mensajeDeError = "Las contraseñas no coinciden"
		#Si coinciden pero el usuario esta en la BD...
		elif Usuario.objects.filter(nombre=nombre).exists():
			hayAlgunError = True
			mensajeDeError = "El nombre de usuario no esta disponible"
		#Si todo va bien...
		else:
			usuario = Usuario()
			usuario.nombre = nombre
			usuario.contrasena = contrasena
			usuario.save()
			return redirect('/login')
	#Si 'usuario' in request.POST = False puede ser porque
	#o bien se ha dejado el campo de usuario vacio o porque
	#se acaba de acceder a esta pagina para crear la cuenta.
	#Si 'hacerRegistro' in request.POST = True entonces se ha
	#enviado el formulario y por lo tanto hay campos vacios
	elif 'hacerRegistro' in request.POST:
		hayAlgunError = True
		mensajeDeError = "No se pueden dejar campos vacios"
	#Este else se ejecutara si acabamos de entrar para hacer login
	else:
		hayAlgunError = False
		mensajeDeError = ""
	return render(request,'encuestasApp/registrarse.html',{
		'hayAlgunError':hayAlgunError,
		'mensajeDeError':mensajeDeError
	})

def verEncuesta(request, idEncuesta):
	#Si hemos seleccionado los checkboxes de cada pregunta...
	if 'enviar' in request.POST:
		#...entonces aumentamos en 1 el numero
		#de votos de cada respuesta seleccionada
		for respuestaId in request.POST.getlist('respuesta'):
			r = Respuesta.objects.get(pk=respuestaId)
			r.numVotos += 1
			r.save()
		return redirect('/')
	#Si por el contrario queremos publicar un comentario...
	elif 'publicar' in request.POST:
		usuario = request.session['usuario']
		texto = request.POST['comentario']
		comentario = Comentario()
		comentario.texto = texto
		comentario.usuario = usuario
		comentario.encuesta = Encuesta.objects.get(pk=idEncuesta)
		comentario.fechaPub = timezone.now()
		comentario.save()
	#La variable login indica si hemos hecho login o no
	login = 'usuario' in request.session
	encuesta = Encuesta.objects.get(pk=idEncuesta)
	#Si hemos publicado un comentario no tiene sentido
	#aumentar el numero de visitas cuando la pagina se recarge
	#con el comentario
	if 'publicar' not in request.POST:
		encuesta.visitas += 1
		encuesta.save()
	#Todo lo que viene a continuacion es para recoger las
	#preguntas y respuestas de cada pregunta de la encuesta
	#de la BD para cargarlas en la pantalla
	preguntas = encuesta.pregunta_set.all()
	listaPreguntas = []
	for pregunta in preguntas:
		p = {}
		p['id'] = pregunta.pk
		p['texto'] = pregunta.texto
		listaRespuestas = []
		for respuesta in pregunta.respuesta_set.all():
			r = {}
			r['id'] = respuesta.pk
			r['texto'] = respuesta.texto
			r['votos'] = respuesta.numVotos
			listaRespuestas.append(r)
		p['respuestas'] = listaRespuestas
		listaPreguntas.append(p)
	#Los comentarios deben aparecer en orden cronologico para que tengan mas sentido
	comentarios = Comentario.objects.filter(encuesta=encuesta).order_by('fechaPub')
	contexto = {
		'login':login,
		'encuesta':encuesta,
		'preguntas':listaPreguntas,
		'comentarios':comentarios
	}
	return render(request, 'encuestasApp/verEncuesta.html', contexto)

def crearEncuesta(request):
	#TODO: Comprobar que el titulo no se envia vacio (solo el titulo)
	#Si hemos creado la encuesta...
	if 'enviar' in request.POST:
		if not request.POST['titulo']:
			return render(request, 'encuestasApp/crearEncuesta.html', {
				'hayAlgunError':True,
				'mensajeDeError':'El titulo no puede quedar vacio'
			})
		e = Encuesta()
		e.titulo = request.POST['titulo']
		e.descripcion = request.POST['descripcion']
		e.fechaPub = timezone.now()
		e.usuario = request.session['usuario']
		#Con el metodo save lo guardamos y/o actualizamos en la BD
		e.save()
		indice = 0
		indice2 = 0
		contador = 0
		#Con getlist tomo todos los values de los inputs que tienen el mismo name
		for pregunta in request.POST.getlist('pregunta'):
			p = Pregunta(texto=pregunta)
			p.encuesta = e
			p.save()
			numRespuestas = int(request.POST.getlist('cantidadRespuestas')[indice])
			while numRespuestas > indice2:
				r = Respuesta()
				r.texto=request.POST.getlist('respuesta')[indice2+contador]
				r.pregunta = p
				r.save()
				indice2 += 1
			contador += numRespuestas
			indice2 = 0
			indice += 1
		return redirect('/')
	#Si acabamos de acceder a la pagina y vamos a crear una
	#encuesta simplemente renderizamos la pagina sin mas
	else:
		return render(request, 'encuestasApp/crearEncuesta.html', {'hayAlgunError':False})

def misEncuestas(request):
	#Si hemos seleccionado las encuestas que queremos eliminar...
	if 'eliminar' in request.POST:
		for enc in request.POST.getlist('encuesta'):
			#...entonces las eliminamos
			Encuesta.objects.get(pk=enc).delete()
	usuario = request.session['usuario']
	encuestas = usuario.encuesta_set.all()
	return render(request, 'encuestasApp/misEncuestas.html', {'encuestas':encuestas})