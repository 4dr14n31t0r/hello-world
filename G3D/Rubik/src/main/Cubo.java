package main;

import java.awt.Color;
import java.util.Map;
import java.util.Map.Entry;

import static main.Rubik.*;

public class Cubo {
	
	private static int ultimoId = 0;
	
	public final int idCubo;
	
	private Map<Entry<Eje, Boolean>, Color> colores;
	
	float[] posicion;
	
	public Cubo(Map<Entry<Eje, Boolean>, Color> colores) {
		this.colores = colores;
		this.posicion = new float[3];
		this.idCubo = ultimoId;
		System.out.println(
			"CREAR OBJETO TRIANGULOS CUBO_"+(ultimoId++)+" "
					+(-0.3)+" "+(-0.3)+" "+(-0.3)+" "+	this.colores.get(xNegativo).getRed()/255.0f +" "+ this.colores.get(xNegativo).getGreen()/255.0f +" "+ this.colores.get(xNegativo).getBlue()/255.0f +" "
					+(-0.3)+" "+(-0.3)+" "+(+0.3)+" "+	this.colores.get(xNegativo).getRed()/255.0f +" "+ this.colores.get(xNegativo).getGreen()/255.0f +" "+ this.colores.get(xNegativo).getBlue()/255.0f +" "
					+(-0.3)+" "+(+0.3)+" "+(+0.3)+" "+	this.colores.get(xNegativo).getRed()/255.0f +" "+ this.colores.get(xNegativo).getGreen()/255.0f +" "+ this.colores.get(xNegativo).getBlue()/255.0f +" "

					+(-0.3)+" "+(+0.3)+" "+(+0.3)+" "+	this.colores.get(xNegativo).getRed()/255.0f +" "+ this.colores.get(xNegativo).getGreen()/255.0f +" "+ this.colores.get(xNegativo).getBlue()/255.0f +" "
					+(-0.3)+" "+(+0.3)+" "+(-0.3)+" "+	this.colores.get(xNegativo).getRed()/255.0f +" "+ this.colores.get(xNegativo).getGreen()/255.0f +" "+ this.colores.get(xNegativo).getBlue()/255.0f +" "
					+(-0.3)+" "+(-0.3)+" "+(-0.3)+" "+	this.colores.get(xNegativo).getRed()/255.0f +" "+ this.colores.get(xNegativo).getGreen()/255.0f +" "+ this.colores.get(xNegativo).getBlue()/255.0f +" "



					+(-0.3)+" "+(-0.3)+" "+(-0.3)+" "+	this.colores.get(yNegativo).getRed()/255.0f +" "+ this.colores.get(yNegativo).getGreen()/255.0f +" "+ this.colores.get(yNegativo).getBlue()/255.0f +" "
					+(-0.3)+" "+(-0.3)+" "+(+0.3)+" "+	this.colores.get(yNegativo).getRed()/255.0f +" "+ this.colores.get(yNegativo).getGreen()/255.0f +" "+ this.colores.get(yNegativo).getBlue()/255.0f +" "
					+(+0.3)+" "+(-0.3)+" "+(+0.3)+" "+	this.colores.get(yNegativo).getRed()/255.0f +" "+ this.colores.get(yNegativo).getGreen()/255.0f +" "+ this.colores.get(yNegativo).getBlue()/255.0f +" "
					
					+(+0.3)+" "+(-0.3)+" "+(+0.3)+" "+	this.colores.get(yNegativo).getRed()/255.0f +" "+ this.colores.get(yNegativo).getGreen()/255.0f +" "+ this.colores.get(yNegativo).getBlue()/255.0f +" "
					+(+0.3)+" "+(-0.3)+" "+(-0.3)+" "+	this.colores.get(yNegativo).getRed()/255.0f +" "+ this.colores.get(yNegativo).getGreen()/255.0f +" "+ this.colores.get(yNegativo).getBlue()/255.0f +" "
					+(-0.3)+" "+(-0.3)+" "+(-0.3)+" "+	this.colores.get(yNegativo).getRed()/255.0f +" "+ this.colores.get(yNegativo).getGreen()/255.0f +" "+ this.colores.get(yNegativo).getBlue()/255.0f +" "



					+(-0.3)+" "+(-0.3)+" "+(-0.3)+" "+	this.colores.get(zNegativo).getRed()/255.0f +" "+ this.colores.get(zNegativo).getGreen()/255.0f +" "+ this.colores.get(zNegativo).getBlue()/255.0f +" "
					+(-0.3)+" "+(+0.3)+" "+(-0.3)+" "+	this.colores.get(zNegativo).getRed()/255.0f +" "+ this.colores.get(zNegativo).getGreen()/255.0f +" "+ this.colores.get(zNegativo).getBlue()/255.0f +" "
					+(+0.3)+" "+(+0.3)+" "+(-0.3)+" "+	this.colores.get(zNegativo).getRed()/255.0f +" "+ this.colores.get(zNegativo).getGreen()/255.0f +" "+ this.colores.get(zNegativo).getBlue()/255.0f +" "

					+(+0.3)+" "+(+0.3)+" "+(-0.3)+" "+	this.colores.get(zNegativo).getRed()/255.0f +" "+ this.colores.get(zNegativo).getGreen()/255.0f +" "+ this.colores.get(zNegativo).getBlue()/255.0f +" "
					+(+0.3)+" "+(-0.3)+" "+(-0.3)+" "+	this.colores.get(zNegativo).getRed()/255.0f +" "+ this.colores.get(zNegativo).getGreen()/255.0f +" "+ this.colores.get(zNegativo).getBlue()/255.0f +" "
					+(-0.3)+" "+(-0.3)+" "+(-0.3)+" "+	this.colores.get(zNegativo).getRed()/255.0f +" "+ this.colores.get(zNegativo).getGreen()/255.0f +" "+ this.colores.get(zNegativo).getBlue()/255.0f +" "






					+(+0.3)+" "+(-0.3)+" "+(-0.3)+" "+	this.colores.get(xPositivo).getRed()/255.0f +" "+ this.colores.get(xPositivo).getGreen()/255.0f +" "+ this.colores.get(xPositivo).getBlue()/255.0f +" "
					+(+0.3)+" "+(-0.3)+" "+(+0.3)+" "+	this.colores.get(xPositivo).getRed()/255.0f +" "+ this.colores.get(xPositivo).getGreen()/255.0f +" "+ this.colores.get(xPositivo).getBlue()/255.0f +" "
					+(+0.3)+" "+(+0.3)+" "+(+0.3)+" "+	this.colores.get(xPositivo).getRed()/255.0f +" "+ this.colores.get(xPositivo).getGreen()/255.0f +" "+ this.colores.get(xPositivo).getBlue()/255.0f +" "

					+(+0.3)+" "+(+0.3)+" "+(+0.3)+" "+	this.colores.get(xPositivo).getRed()/255.0f +" "+ this.colores.get(xPositivo).getGreen()/255.0f +" "+ this.colores.get(xPositivo).getBlue()/255.0f +" "
					+(+0.3)+" "+(+0.3)+" "+(-0.3)+" "+	this.colores.get(xPositivo).getRed()/255.0f +" "+ this.colores.get(xPositivo).getGreen()/255.0f +" "+ this.colores.get(xPositivo).getBlue()/255.0f +" "
					+(+0.3)+" "+(-0.3)+" "+(-0.3)+" "+	this.colores.get(xPositivo).getRed()/255.0f +" "+ this.colores.get(xPositivo).getGreen()/255.0f +" "+ this.colores.get(xPositivo).getBlue()/255.0f +" "



					+(-0.3)+" "+(+0.3)+" "+(-0.3)+" "+	this.colores.get(yPositivo).getRed()/255.0f +" "+ this.colores.get(yPositivo).getGreen()/255.0f +" "+ this.colores.get(yPositivo).getBlue()/255.0f +" "
					+(-0.3)+" "+(+0.3)+" "+(+0.3)+" "+	this.colores.get(yPositivo).getRed()/255.0f +" "+ this.colores.get(yPositivo).getGreen()/255.0f +" "+ this.colores.get(yPositivo).getBlue()/255.0f +" "
					+(+0.3)+" "+(+0.3)+" "+(+0.3)+" "+	this.colores.get(yPositivo).getRed()/255.0f +" "+ this.colores.get(yPositivo).getGreen()/255.0f +" "+ this.colores.get(yPositivo).getBlue()/255.0f +" "

					+(+0.3)+" "+(+0.3)+" "+(+0.3)+" "+	this.colores.get(yPositivo).getRed()/255.0f +" "+ this.colores.get(yPositivo).getGreen()/255.0f +" "+ this.colores.get(yPositivo).getBlue()/255.0f +" "
					+(+0.3)+" "+(+0.3)+" "+(-0.3)+" "+	this.colores.get(yPositivo).getRed()/255.0f +" "+ this.colores.get(yPositivo).getGreen()/255.0f +" "+ this.colores.get(yPositivo).getBlue()/255.0f +" "
					+(-0.3)+" "+(+0.3)+" "+(-0.3)+" "+	this.colores.get(yPositivo).getRed()/255.0f +" "+ this.colores.get(yPositivo).getGreen()/255.0f +" "+ this.colores.get(yPositivo).getBlue()/255.0f +" "



					+(-0.3)+" "+(-0.3)+" "+(+0.3)+" "+	this.colores.get(zPositivo).getRed()/255.0f +" "+ this.colores.get(zPositivo).getGreen()/255.0f +" "+ this.colores.get(zPositivo).getBlue()/255.0f +" "
					+(-0.3)+" "+(+0.3)+" "+(+0.3)+" "+	this.colores.get(zPositivo).getRed()/255.0f +" "+ this.colores.get(zPositivo).getGreen()/255.0f +" "+ this.colores.get(zPositivo).getBlue()/255.0f +" "
					+(+0.3)+" "+(+0.3)+" "+(+0.3)+" "+	this.colores.get(zPositivo).getRed()/255.0f +" "+ this.colores.get(zPositivo).getGreen()/255.0f +" "+ this.colores.get(zPositivo).getBlue()/255.0f +" "

					+(+0.3)+" "+(+0.3)+" "+(+0.3)+" "+	this.colores.get(zPositivo).getRed()/255.0f +" "+ this.colores.get(zPositivo).getGreen()/255.0f +" "+ this.colores.get(zPositivo).getBlue()/255.0f +" "
					+(+0.3)+" "+(-0.3)+" "+(+0.3)+" "+	this.colores.get(zPositivo).getRed()/255.0f +" "+ this.colores.get(zPositivo).getGreen()/255.0f +" "+ this.colores.get(zPositivo).getBlue()/255.0f +" "
					+(-0.3)+" "+(-0.3)+" "+(+0.3)+" "+	this.colores.get(zPositivo).getRed()/255.0f +" "+ this.colores.get(zPositivo).getGreen()/255.0f +" "+ this.colores.get(zPositivo).getBlue()/255.0f +" "
		);
	}
	
	public float getPosicion(Eje eje){
		return posicion[eje.ordinal()];
	}
	
	public void mover(float x, float y, float z){
		posicion[0] += x;
		posicion[1] += y;
		posicion[2] += z;
		System.out.println("USAR OBJETO CUBO_"+this.idCubo);
		System.out.println("MOVER OBJETO "+ x +" "+ y +" "+ z);
	}
	
	public void rotar(Eje eje, float radianes){
		System.out.println("USAR OBJETO CUBO_"+this.idCubo);
		System.out.println("ROTAR OBJETO EJE_"+eje.toString()+" "+radianes);
	}
	
	public void establecerRotacion(Eje eje, boolean signo){
		int n = signo ? 1 : -1;
		double radianes = Math.PI/2;
		float[] p = {posicion[0],posicion[1],posicion[2]};
		switch(eje){
		case X:
			posicion[1] = (float)(Math.hypot(p[1],p[2])*Math.cos(Math.atan2(p[2],p[1])+n*radianes));
			posicion[2] = (float)(Math.hypot(p[1],p[2])*Math.sin(Math.atan2(p[2],p[1])+n*radianes));
			break;
		case Y:
			posicion[2] = (float)(Math.hypot(p[2],p[0])*Math.cos(Math.atan2(p[0],p[2])+n*radianes));
			posicion[0] = (float)(Math.hypot(p[2],p[0])*Math.sin(Math.atan2(p[0],p[2])+n*radianes));
			break;
		case Z:
			posicion[0] = (float)(Math.hypot(p[0],p[1])*Math.cos(Math.atan2(p[1],p[0])+n*radianes));
			posicion[1] = (float)(Math.hypot(p[0],p[1])*Math.sin(Math.atan2(p[1],p[0])+n*radianes));
			break;
		}
	}
	
	public void restaurar(){
		System.out.println("USAR OBJETO CUBO_"+this.idCubo);
		System.out.println("RESTAURAR OBJETO");
		posicion = new float[3];
		
	}
	
	public void dibujar() {
		System.out.println("USAR OBJETO CUBO_"+this.idCubo);
		System.out.println("DIBUJAR");
	}
}
