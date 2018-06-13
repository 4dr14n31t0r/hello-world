from django.urls import path

from . import views

app_name="encuestas2"

urlpatterns = [
	path('', views.index, name="index"),
	path('login', views.login, name="login"),
	path('registrarse', views.registrarse, name="registrarse"),
	path('misEncuestas', views.misEncuestas, name="misEncuestas"),
	path('crearEncuesta', views.crearEncuesta, name="crearEncuesta"),
	#Ejemplo de uso de urls limpias
	path('encuestas/<int:idEncuesta>/', views.verEncuesta, name="encuestas"),
]
