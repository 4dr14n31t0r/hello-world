#include "main.h"

#define GLEW_STATIC
#include <GL/glew.h>

#include <GLFW/glfw3.h>

#include <vector>
#include <string>
#include <iostream>
#include <utility>
#include <stdexcept>

#include ".h/Entrada.h"
#include ".h/Ventana.h"
#include ".h/Vertice.h"
#include ".h/Util.h"
#include ".h/Contexto.h"
#include ".h/Graficos.h"
#include ".h/Camara.h"
#include ".h/Objeto.h"
#include ".h/Teclado.h"

int main(void)
{
	std::vector<Entrada> vector;
	std::string* s;
	Graficos::prepararGraficos();
	GLboolean continuar = GL_TRUE;
	do{
		try{
			vector = Teclado::leerLinea();
			s = vector.at(0).getString();

				 if(!s->compare("CREAR"			)){delete s;crear			(s, vector);}
			else if(!s->compare("ELIMINAR"		)){delete s;eliminar		(s, vector);}
			else if(!s->compare("USAR"			)){delete s;usar			(s, vector);}
			else if(!s->compare("DIBUJAR"		)){delete s;dibujar			(s, vector);}
			else if(!s->compare("MOVER"			)){delete s;mover			(s, vector);}
			else if(!s->compare("REDIMENSIONAR"	)){delete s;redimensionar	(s, vector);}
			else if(!s->compare("ZOOM"			)){delete s;zoom			(s, vector);}
			else if(!s->compare("ROTAR"			)){delete s;rotar			(s, vector);}
			else if(!s->compare("RESTAURAR"		)){delete s;restaurar		(s, vector);}
			else if(!s->compare("ACTUALIZAR"	)){delete s;actualizar		(s, vector);}
			else if(!s->compare("LIMPIAR"		)){delete s;limpiar			(s, vector);}
			else if(!s->compare("PERSPECTIVA"	)){delete s;perspectiva		(s, vector);}
			else if(!s->compare("TERMINAR"		)){delete s;continuar = false;}
			else throw Util::ERROR_OPCION_NO_VALIDA;
		} catch(char const* ex) {
			std::cerr << "Error: " << ex << std::endl;
		} catch(const std::out_of_range& ex) {
			std::cerr << "Error: Se han otorgado menos argumentos de los necesarios" << std::endl;
		} catch(...) {
			std::cerr << "Error" << std::endl;
		}
	}while(continuar);
	Graficos::terminarGraficos();
	return 0;
}

void crear(std::string* s, std::vector<Entrada> vector)
{
	s = vector.at(1).getString();
	if(!s->compare("VENTANA")) {
		delete s;
		std::string* id		= vector.at(2).getString();
		GLint ancho			= vector.at(3).getNumero();
		GLint alto			= vector.at(4).getNumero();
		std::string* titulo	= vector.at(5).getString();
		Ventana::crearVentana(ancho, alto, *titulo, *id);
		delete titulo;
		delete id;
	} else if(!s->compare("OBJETO")) {
		delete s;
		s = vector.at(2).getString();
		//size es el numero de elementos del vector sobre vertices
		//Se le resta 4 porque tambien hay que tener en cuenta el id
		GLuint size = vector.size()-4;
		if(!s->compare("PUNTOS")) {
			delete s;
			//3 componentes * 2 datos(coordenada y color) * 1 vertice (punto)
			if(size%(3*2*1))
				throw Util::ERROR_NUM_ARGS_NO_VALIDO;
			if (!Ventana::getVentana())
				throw Util::ERROR_VENTANA_EN_USO;
			GLuint numVertices = size/(3*2);//3 componentes * 2 datos(coordenada y color)
			Vertice vertices[numVertices];
			for(GLint i = 0; i < numVertices; i++)
			{
				vertices[i] = {
						//4 (1º posicion en el vector) + n (componente del vertice) + 3*2*i (numero de componentes de cada vertice)
						Coordenada{
							(GLfloat)vector.at(4+0+3*2*i).getNumero(),
							(GLfloat)vector.at(4+1+3*2*i).getNumero(),
							(GLfloat)vector.at(4+2+3*2*i).getNumero()
						},
						Color{
							(GLfloat)vector.at(4+3+3*2*i).getNumero(),
							(GLfloat)vector.at(4+4+3*2*i).getNumero(),
							(GLfloat)vector.at(4+5+3*2*i).getNumero()
					}
				};
			}
			std::string* id = vector.at(3).getString();
			Contexto::crearObjeto(GL_POINTS, numVertices, vertices, *id);
			delete id;
		} else if(!s->compare("LINEAS")) {
			delete s;
			//3 componentes * 2 datos(coordenada y color) * 2 vertices (linea)
			if(size%(3*2*2)) throw Util::ERROR_NUM_ARGS_NO_VALIDO;
			if (!Ventana::getVentana())
				throw Util::ERROR_VENTANA_EN_USO;
			GLuint numVertices = size/(3*2);//3 componentes * 2 datos(coordenada y color)
			Vertice vertices[numVertices];
			for(GLint i = 0; i < numVertices; i++)
			{
				vertices[i] = {
						//4 (1º posicion en el vector) + n (componente del vertice) + 3*2*i (numero de componentes de cada vertice)
						Coordenada{
							(GLfloat)vector.at(4+0+3*2*i).getNumero(),
							(GLfloat)vector.at(4+1+3*2*i).getNumero(),
							(GLfloat)vector.at(4+2+3*2*i).getNumero()
						},
						Color{
							(GLfloat)vector.at(4+3+3*2*i).getNumero(),
							(GLfloat)vector.at(4+4+3*2*i).getNumero(),
							(GLfloat)vector.at(4+5+3*2*i).getNumero()
						}
				};
			}
			std::string* id = vector.at(3).getString();
			Contexto::crearObjeto(GL_LINES, numVertices, vertices, *id);
			delete id;
		} else if(!s->compare("CADENA_LINEAS")) {
			delete s;
			//3 componentes * 2 datos(coordenada y color) * 2 vertices (linea)
			if(size < 3*2*2)
				throw Util::ERROR_NUM_ARGS_NO_VALIDO;
			if (!Ventana::getVentana())
				throw Util::ERROR_VENTANA_EN_USO;
			GLuint numVertices = size/(3*2);//3 componentes * 2 datos(coordenada y color)
			Vertice vertices[numVertices];
			for(GLint i = 0; i < numVertices; i++)
			{
				vertices[i] = {
						//4 (1º posicion en el vector) + n (componente del vertice) + 3*2*i (numero de componentes de cada vertice)
						Coordenada{
							(GLfloat)vector.at(4+0+3*2*i).getNumero(),
							(GLfloat)vector.at(4+1+3*2*i).getNumero(),
							(GLfloat)vector.at(4+2+3*2*i).getNumero()
						},
						Color{
							(GLfloat)vector.at(4+3+3*2*i).getNumero(),
							(GLfloat)vector.at(4+4+3*2*i).getNumero(),
							(GLfloat)vector.at(4+5+3*2*i).getNumero()
						}
				};
			}
			std::string* id = vector.at(3).getString();
			Contexto::crearObjeto(GL_LINE_STRIP, numVertices, vertices, *id);
			delete id;
		} else if(!s->compare("BUCLE_LINEAS")) {
			delete s;
			//3 componentes * 2 datos(coordenada y color) * 2 vertices (linea)
			if(size < 3*2*2)
				throw Util::ERROR_NUM_ARGS_NO_VALIDO;
			if (!Ventana::getVentana())
				throw Util::ERROR_VENTANA_EN_USO;
			GLuint numVertices = size/(3*2);//3 componentes * 2 datos(coordenada y color)
			std::string* id = vector.at(3).getString();
			Vertice vertices[numVertices];
			for(GLint i = 0; i < numVertices; i++)
			{
				vertices[i] = {
						//4 (1º posicion en el vector) + n (componente del vertice) + 3*2*i (numero de componentes de cada vertice)
						Coordenada{
							(GLfloat)vector.at(4+0+3*2*i).getNumero(),
							(GLfloat)vector.at(4+1+3*2*i).getNumero(),
							(GLfloat)vector.at(4+2+3*2*i).getNumero()
						},
						Color{
							(GLfloat)vector.at(4+3+3*2*i).getNumero(),
							(GLfloat)vector.at(4+4+3*2*i).getNumero(),
							(GLfloat)vector.at(4+5+3*2*i).getNumero()
						}
				};
			}
			Contexto::crearObjeto(GL_LINE_LOOP, numVertices, vertices, *id);
			delete id;
		} else if(!s->compare("TRIANGULOS")) {
			delete s;
			//3 componentes * 2 datos(coordenada y color) * 3 vertices (triangulo)
			if(size%(3*2*3))
				throw Util::ERROR_NUM_ARGS_NO_VALIDO;
			if (!Ventana::getVentana())
				throw Util::ERROR_VENTANA_EN_USO;
			GLuint numVertices = size/(3*2);//3 componentes * 2 datos(coordenada y color)
			std::string* id = vector.at(3).getString();
			Vertice vertices[numVertices];
			for(GLint i = 0; i < numVertices; i++)
			{
				vertices[i] = {
						//4 (1º posicion en el vector) + n (componente del vertice) + 3*2*i (numero de componentes de cada vertice)
						Coordenada{
							(GLfloat)vector.at(4+0+3*2*i).getNumero(),
							(GLfloat)vector.at(4+1+3*2*i).getNumero(),
							(GLfloat)vector.at(4+2+3*2*i).getNumero()
						},
						Color{
							(GLfloat)vector.at(4+3+3*2*i).getNumero(),
							(GLfloat)vector.at(4+4+3*2*i).getNumero(),
							(GLfloat)vector.at(4+5+3*2*i).getNumero()
						}
				};
			}
			Contexto::crearObjeto(GL_TRIANGLES, numVertices, vertices, *id);
			delete id;
		} else if(!s->compare("CADENA_TRIANGULOS")) {
			delete s;
			//3 componentes * 2 datos(coordenada y color) * 3 vertices (triangulo)
			if(size < 3*2*3)
				throw Util::ERROR_NUM_ARGS_NO_VALIDO;
			if (!Ventana::getVentana())
				throw Util::ERROR_VENTANA_EN_USO;
			GLuint numVertices = size/(3*2);//3 componentes * 2 datos(coordenada y color)
			std::string* id = vector.at(3).getString();
			Vertice vertices[numVertices];
			for(GLint i = 0; i < numVertices; i++)
			{
				vertices[i] = {
						//4 (1º posicion en el vector) + n (componente del vertice) + 3*2*i (numero de componentes de cada vertice)
						Coordenada{
							(GLfloat)vector.at(4+0+3*2*i).getNumero(),
							(GLfloat)vector.at(4+1+3*2*i).getNumero(),
							(GLfloat)vector.at(4+2+3*2*i).getNumero()
						},
						Color{
							(GLfloat)vector.at(4+3+3*2*i).getNumero(),
							(GLfloat)vector.at(4+4+3*2*i).getNumero(),
							(GLfloat)vector.at(4+5+3*2*i).getNumero()
						}
				};
			}
			Contexto::crearObjeto(GL_TRIANGLE_STRIP, numVertices, vertices, *id);
			delete id;
		} else if(!s->compare("ABANICO_TRIANGULOS")) {
			delete s;
			//3 componentes * 2 datos(coordenada y color) * 3 vertices (triangulo)
			if(size < 3*2*3)
				throw Util::ERROR_NUM_ARGS_NO_VALIDO;
			if (!Ventana::getVentana())
				throw Util::ERROR_VENTANA_EN_USO;
			GLuint numVertices = size/(3*2);//3 componentes * 2 datos(coordenada y color)
			std::string* id = vector.at(3).getString();
			Vertice vertices[numVertices];
			for(GLint i = 0; i < numVertices; i++)
			{
				vertices[i] = {
						//4 (1º posicion en el vector) + n (componente del vertice) + 3*2*i (numero de componentes de cada vertice)
						Coordenada{
							(GLfloat)vector.at(4+0+3*2*i).getNumero(),
							(GLfloat)vector.at(4+1+3*2*i).getNumero(),
							(GLfloat)vector.at(4+2+3*2*i).getNumero()
						},
						Color{
							(GLfloat)vector.at(4+3+3*2*i).getNumero(),
							(GLfloat)vector.at(4+4+3*2*i).getNumero(),
							(GLfloat)vector.at(4+5+3*2*i).getNumero()
						}
				};
			}
			Contexto::crearObjeto(GL_TRIANGLE_FAN, numVertices, vertices, *id);
			delete id;
		} else throw Util::ERROR_OPCION_NO_VALIDA;
	} else if(!s->compare("CAMARA")) {
		delete s;
		s = vector.at(2).getString();
		Contexto::crearCamara(*s, GL_FALSE);
		delete s;
	} else throw Util::ERROR_OPCION_NO_VALIDA;
}

void usar(std::string* s, std::vector<Entrada> vector)
{
	s = vector.at(1).getString();
	if (!s->compare("VENTANA")) {
		delete s;
		s = vector.at(2).getString();
		Ventana::usarVentana(Ventana::getVentana(*s));
		delete s;
	} else if (!Ventana::getVentana()) {
		throw Util::ERROR_VENTANA_EN_USO;
	} else if (!s->compare("CAMARA")) {
		delete s;
		s = vector.at(2).getString();
		Contexto::usarCamara(Contexto::getCamara(*s));
		delete s;
	} else if (!s->compare("OBJETO")) {
		delete s;
		s = vector.at(2).getString();
		Contexto::usarObjeto(Contexto::getObjeto(*s));
		delete s;
	} else throw Util::ERROR_OPCION_NO_VALIDA;
}

/**Este metodo dibuja un objeto pero para mostrarlo tambien
 * hay que llamar a "actualizar". Asi se evita el parpadeo
 * que aparece cada vez que se limpia la pantalla*/
void dibujar(std::string* s, std::vector<Entrada> vector)
{
	if(vector.size() == 1)
		s = new std::string("OBJETO");
	else
		s = vector.at(1).getString();
	if (!s->compare("OBJETO")) {
		delete s;
		if (!Ventana::getVentana())
			throw Util::ERROR_VENTANA_EN_USO;
		Objeto* objeto = Contexto::getObjeto();
		if(objeto)
			Graficos::dibujar(Contexto::getObjeto());
		else
			throw Util::ERROR_OBJETO_EN_USO;
	} else throw Util::ERROR_OPCION_NO_VALIDA;
}

void eliminar(std::string* s, std::vector<Entrada> vector)
{
	s = vector.at(1).getString();
	if (!s->compare("VENTANA")) {
		delete s;
		Ventana* ventana = Ventana::getVentana();
		if(!ventana)
			throw Util::ERROR_VENTANA_EN_USO;
		Ventana::eliminarVentana(Ventana::getVentana());
	} else if (!Ventana::getVentana())
		throw Util::ERROR_VENTANA_EN_USO;
	else if (!s->compare("OBJETO")) {
		delete s;
		Objeto* objeto = Contexto::getObjeto();
		if(!objeto)
			throw Util::ERROR_OBJETO_EN_USO;
		Contexto::eliminarObjeto(objeto);
	} else if (!s->compare("CAMARA")) {
		delete s;
		Camara* camara = Contexto::getCamara();
		if(!camara)
			throw Util::ERROR_CAMARA_EN_USO;
		Contexto::eliminarCamara(camara);
	} else throw Util::ERROR_OPCION_NO_VALIDA;
}

void mover(std::string* s, std::vector<Entrada> vector)
{
	s = vector.at(1).getString();
	if (!s->compare("OBJETO")) {
		delete s;
		Objeto* objeto = Contexto::getObjeto();
		if(!objeto)
			throw Util::ERROR_OBJETO_EN_USO;
		Contexto::getObjeto()->mover(
				(GLfloat)vector.at(2).getNumero(),
				(GLfloat)vector.at(3).getNumero(),
				(GLfloat)vector.at(4).getNumero()
		);
	} else if (!s->compare("CAMARA")) {
		delete s;
		Camara* camara = Contexto::getCamara();
		if(!camara)
			throw Util::ERROR_CAMARA_EN_USO;
		camara->mover(
				(GLfloat)vector.at(2).getNumero(),
				(GLfloat)vector.at(3).getNumero(),
				(GLfloat)vector.at(4).getNumero()
		);
	} else throw Util::ERROR_OPCION_NO_VALIDA;
}

void redimensionar(std::string* s, std::vector<Entrada> vector)
{
	s = vector.at(1).getString();
	if (!s->compare("VENTANA")) {
		delete s;
		Ventana* ventana = Ventana::getVentana();
		if(!ventana)
			throw Util::ERROR_VENTANA_EN_USO;
		Ventana::getVentana()->setDimensiones(
				vector.at(2).getNumero(),
				vector.at(3).getNumero()
		);
	} else if (!s->compare("OBJETO")) {
		delete s;
		Objeto* objeto = Contexto::getObjeto();
		if(!objeto)
			throw Util::ERROR_OBJETO_EN_USO;
		objeto->redimensionar(
				vector.at(2).getNumero(),
				vector.at(3).getNumero(),
				vector.at(4).getNumero()
		);
	} else throw Util::ERROR_OPCION_NO_VALIDA;
}

void zoom(std::string* s, std::vector<Entrada> vector)
{
	Camara* camara = Contexto::getCamara();
	if(!camara)
		throw Util::ERROR_CAMARA_EN_USO;
	camara->ampliar((GLfloat)vector.at(1).getNumero());
}

void rotar(std::string* s, std::vector<Entrada> vector)
{
	s = vector.at(1).getString();
	if (!s->compare("OBJETO")) {
		delete s;
		s = vector.at(2).getString();
		if(!s->compare("EJE_X")) {
			delete s;
			Objeto* objeto = Contexto::getObjeto();
			if(!objeto)
				throw Util::ERROR_OBJETO_EN_USO;
			objeto->rotar(Eje::X, (GLfloat)vector.at(3).getNumero());
		} else if(!s->compare("EJE_Y")) {
			delete s;
			Objeto* objeto = Contexto::getObjeto();
			if(!objeto)
				throw Util::ERROR_OBJETO_EN_USO;
			objeto->rotar(Eje::Y, (GLfloat)vector.at(3).getNumero());
		} else if(!s->compare("EJE_Z")) {
			delete s;
			Objeto* objeto = Contexto::getObjeto();
			if(!objeto)
				throw Util::ERROR_OBJETO_EN_USO;
			objeto->rotar(Eje::Z, (GLfloat)vector.at(3).getNumero());
		} else throw Util::ERROR_OPCION_NO_VALIDA;
	} else if (!s->compare("CAMARA")) {
		delete s;
		s = vector.at(2).getString();
		if(!s->compare("EJE_X")) {
			delete s;
			Camara* camara = Contexto::getCamara();
			if(!camara)
				throw Util::ERROR_CAMARA_EN_USO;
			camara->rotar(Eje::X, (GLfloat)vector.at(3).getNumero());
		} else if(!s->compare("EJE_Y")) {
			delete s;
			Camara* camara = Contexto::getCamara();
			if(!camara)
				throw Util::ERROR_CAMARA_EN_USO;
			camara->rotar(Eje::Y, (GLfloat)vector.at(3).getNumero());
		} else if(!s->compare("EJE_Z")) {
			delete s;
			Camara* camara = Contexto::getCamara();
			if(!camara)
				throw Util::ERROR_CAMARA_EN_USO;
			camara->rotar(Eje::Z, (GLfloat)vector.at(3).getNumero());
		} else throw Util::ERROR_OPCION_NO_VALIDA;
	} else throw Util::ERROR_OPCION_NO_VALIDA;
}

void restaurar(std::string* s, std::vector<Entrada> vector)
{
	s = vector.at(1).getString();
	if (!s->compare("OBJETO")) {
		delete s;
		Objeto* objeto = Contexto::getObjeto();
		if(!objeto)
			throw Util::ERROR_OBJETO_EN_USO;
		objeto->restaurar();
	} else if (!s->compare("CAMARA")) {
		delete s;
		Camara* camara = Contexto::getCamara();
		if(!camara)
			throw Util::ERROR_CAMARA_EN_USO;
		camara->restaurar();
	} else throw Util::ERROR_OPCION_NO_VALIDA;
}

void actualizar(std::string* s, std::vector<Entrada> vector)
{
	if (!Ventana::getVentana())
		throw Util::ERROR_VENTANA_EN_USO;
	Graficos::actualizar();
}

void limpiar(std::string* s, std::vector<Entrada> vector)
{
	if (!Ventana::getVentana())
		throw Util::ERROR_VENTANA_EN_USO;
	Graficos::limpiar();
}

void perspectiva(std::string* s, std::vector<Entrada> vector)
{
	s = vector.at(1).getString();
	if (!Ventana::getVentana())
	{
		delete s;
		throw Util::ERROR_VENTANA_EN_USO;
	}
	if (!Contexto::getCamara())
	{
		delete s;
		throw Util::ERROR_CAMARA_EN_USO;
	}
	if(!s->compare("ISOMETRICA"))
		Contexto::getCamara()->perspectiva = GL_FALSE;
	else if(!s->compare("CONICA"))
		Contexto::getCamara()->perspectiva = GL_TRUE;
	delete s;
}
