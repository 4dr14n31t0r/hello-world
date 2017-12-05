/*
 * main.h
 *
 *  Created on: 31 mar. 2017
 *      Author: adrian
 */

#ifndef MAIN_H_
#define MAIN_H_

#include <vector>
#include <string>

class Entrada;

void crear			(std::string* s, std::vector<Entrada> vector);
void usar			(std::string* s, std::vector<Entrada> vector);
void dibujar		(std::string* s, std::vector<Entrada> vector);
void eliminar		(std::string* s, std::vector<Entrada> vector);
void mover			(std::string* s, std::vector<Entrada> vector);
void redimensionar	(std::string* s, std::vector<Entrada> vector);
void zoom			(std::string* s, std::vector<Entrada> vector);
void rotar			(std::string* s, std::vector<Entrada> vector);
void restaurar		(std::string* s, std::vector<Entrada> vector);
void actualizar		(std::string* s, std::vector<Entrada> vector);
void limpiar		(std::string* s, std::vector<Entrada> vector);
void perspectiva	(std::string* s, std::vector<Entrada> vector);

#endif /* MAIN_H_ */
