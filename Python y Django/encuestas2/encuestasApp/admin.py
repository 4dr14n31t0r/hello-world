from django.contrib import admin

from .models import *

#Aqui es donde se le indica a Django
#que modelos deben poder administrarse
#desde el sitio de administracion que
#django crea automaticamente en /admin

admin.site.register(Usuario)
admin.site.register(Encuesta)
admin.site.register(Pregunta)
admin.site.register(Respuesta)
admin.site.register(Comentario)