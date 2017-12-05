LIBS=-lGLEW -lglfw3 -lGL -lX11 -lXi -lXrandr -lXxf86vm -lXinerama -lXcursor -ldl -lpthread
ARGS=-std=c++11 -g
CC=g++

all: .o/Camara.o .o/Color.o .o/Contexto.o .o/Coordenada.o .o/Eje.o .o/Entrada.o .o/Graficos.o .o/Objeto.o .o/Teclado.o .o/Util.o .o/Ventana.o .o/Vertice.o libreria.a G3D

G3D: libreria.a .h/Entrada.h .h/Ventana.h .h/Vertice.h .h/Util.h .h/Contexto.h .h/Graficos.h .h/Camara.h .h/Objeto.h .h/Teclado.h main.cpp main.h
	$(CC) main.cpp $(ARGS) libreria.a $(LIBS) -o G3D
	chmod 777 G3D

libreria.a: .o/Camara.o .o/Color.o .o/Contexto.o .o/Coordenada.o .o/Eje.o .o/Entrada.o .o/Graficos.o .o/Objeto.o .o/Teclado.o .o/Util.o .o/Ventana.o .o/Vertice.o
	ar rv libreria.a $?

.o/Camara.o: .h/Util.h .h/Camara.h .cpp/Camara.cpp
	$(CC) $(ARGS) -c .cpp/Camara.cpp -o .o/Camara.o

.o/Color.o: .h/Color.h
	$(CC) $(ARGS) -c .h/Color.h -o .o/Color.o

.o/Contexto.o: .h/Ventana.h .h/Util.h .h/Objeto.h .h/Camara.h .h/Contexto.h .cpp/Contexto.cpp
	$(CC) $(ARGS) -c .cpp/Contexto.cpp -o .o/Contexto.o

.o/Coordenada.o: .h/Coordenada.h
	$(CC) $(ARGS) -c .h/Coordenada.h -o .o/Coordenada.o

.o/Eje.o: .h/Eje.h
	$(CC) $(ARGS) -c .h/Eje.h -o .o/Eje.o

.o/Entrada.o: .h/Util.h .h/TipoDeEntrada.h .h/Entrada.h .cpp/Entrada.cpp
	$(CC) $(ARGS) -c .cpp/Entrada.cpp -o .o/Entrada.o

.o/Graficos.o: .h/Contexto.h .h/Ventana.h .h/Util.h .h/Objeto.h .h/Camara.h .h/Graficos.h .cpp/Graficos.cpp
	$(CC) $(ARGS) -c .cpp/Graficos.cpp -o .o/Graficos.o

.o/Objeto.o: .h/Vertice.h .h/Util.h .h/Objeto.h .cpp/Objeto.cpp
	$(CC) $(ARGS) -c .cpp/Objeto.cpp -o .o/Objeto.o

.o/Teclado.o: .h/Entrada.h .h/Teclado.h .cpp/Teclado.cpp
	$(CC) $(ARGS) -c .cpp/Teclado.cpp -o .o/Teclado.o

.o/Util.o: .h/Util.h .cpp/Util.cpp
	$(CC) $(ARGS) -c .cpp/Util.cpp -o .o/Util.o

.o/Ventana.o: .h/Util.h .h/Contexto.h .h/Ventana.h .cpp/Ventana.cpp
	$(CC) $(ARGS) -c .cpp/Ventana.cpp -o .o/Ventana.o

.o/Vertice.o: .h/Vertice.h
	$(CC) $(ARGS) -c .h/Vertice.h -o .o/Vertice.o
	
clean:
	-rm .o/*.o libreria.a

.PHONY: all clean
