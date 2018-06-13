from django.db import models

class Usuario(models.Model):
	nombre = models.CharField(max_length=200)
	contrasena = models.CharField(max_length=200)
	def __str__(self):
		#El metodo __str__ se utiliza para que
		#django sepa que mostrar en la pagina
		#de administracion de los comentarios
		return self.nombre

class Encuesta(models.Model):
	titulo = models.CharField(max_length=200)
	descripcion = models.CharField(max_length=1000)
	fechaPub = models.DateTimeField('fecha de publicacion')
	visitas = models.IntegerField(default=0)
	usuario = models.ForeignKey(Usuario, on_delete=models.CASCADE, null=False)
	def __str__(self):
		#El metodo __str__ se utiliza para que
		#django sepa que mostrar en la pagina
		#de administracion de los comentarios
		return self.titulo

class Pregunta(models.Model):
	texto = models.CharField(max_length=200)
	encuesta = models.ForeignKey(Encuesta, on_delete=models.CASCADE, null=False)
	def __str__(self):
		#El metodo __str__ se utiliza para que
		#django sepa que mostrar en la pagina
		#de administracion de los comentarios
		return self.texto

class Respuesta(models.Model):
	texto = models.CharField(max_length=200)
	numVotos = models.IntegerField(default=0)
	pregunta = models.ForeignKey(Pregunta, on_delete=models.CASCADE, null=False)
	def __str__(self):
		#El metodo __str__ se utiliza para que
		#django sepa que mostrar en la pagina
		#de administracion de los comentarios
		return self.texto

class Comentario(models.Model):
	texto = models.CharField(max_length=1000)
	fechaPub = models.DateTimeField('fecha de publicacion')
	usuario = models.ForeignKey(Usuario, on_delete=models.CASCADE, null=False)
	encuesta = models.ForeignKey(Encuesta, on_delete=models.CASCADE, null=False)
	#El metodo __str__ se utiliza para que
	#django sepa que mostrar en la pagina
	#de administracion de los comentarios
	def __str__(self):
		return self.usuario.texto + ": " + self.texto
