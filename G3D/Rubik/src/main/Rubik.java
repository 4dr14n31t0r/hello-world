package main;

import java.awt.Color;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Rubik {
	public final static Entry<Eje, Boolean> xNegativo = new SimpleEntry<>(Eje.X, false);
	public final static Entry<Eje, Boolean> yNegativo = new SimpleEntry<>(Eje.Y, false);
	public final static Entry<Eje, Boolean> zNegativo = new SimpleEntry<>(Eje.Z, false);
	
	public final static Entry<Eje, Boolean> xPositivo = new SimpleEntry<>(Eje.X, true);
	public final static Entry<Eje, Boolean> yPositivo = new SimpleEntry<>(Eje.Y, true);
	public final static Entry<Eje, Boolean> zPositivo = new SimpleEntry<>(Eje.Z, true);
	
	private Cubo[][][] cubos;
	
	private Camara camara;
	
	public Rubik(Camara camara) {
		this.camara = camara;
		cubos = new Cubo[3][3][3];
		Map<Entry<Eje, Boolean>, Color> colores = new HashMap<>();
		colores.put(xNegativo, Color.RED);
		colores.put(yNegativo, Color.GREEN);
		colores.put(zNegativo, Color.BLUE);
		colores.put(xPositivo, Color.ORANGE);
		colores.put(yPositivo, Color.YELLOW);
		colores.put(zPositivo, Color.CYAN);
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				for(int k = 0; k < 3; k++) {
					cubos[i][j][k] = new Cubo(colores);
					cubos[i][j][k].mover((i-1)*0.7f, (j-1)*0.7f, (k-1)*0.7f);
				}
		actualizar();
	}
	
	public Rubik(Rubik rubik){
		
	}
	
	public Cubo getCubo(int x, int y, int z) {
		return cubos[x+1][y+1][z+1];
	}
	
	public void rotar(Eje eje, float radianes) {
		float x = camara.getPosicion(Eje.X);
		float y = camara.getPosicion(Eje.Y);
		float z = camara.getPosicion(Eje.Z);
		camara.mover(-x, -y, -z);
		camara.rotar(eje, -radianes);
		camara.mover(x, y, z);
	}
	
	public void girarCara(Entry<Eje, Boolean> cara, float radianes) {
			 if(cara.equals(xNegativo)){
				for(Cubo[][] c1 : cubos)
					for(Cubo[] c2 : c1)
						for(Cubo c3 : c2)
							if(c3.getPosicion(Eje.X) < -0.1)
								c3.rotar(Eje.X, radianes);
			}

		else if(cara.equals(yNegativo)){
			for(Cubo[][] c1 : cubos)
				for(Cubo[] c2 : c1)
					for(Cubo c3 : c2)
						if(c3.getPosicion(Eje.Y) < -0.1)
							c3.rotar(Eje.Y, radianes);
		}

		else if(cara.equals(zNegativo)){
			for(Cubo[][] c1 : cubos)
				for(Cubo[] c2 : c1)
					for(Cubo c3 : c2){
						if(c3.getPosicion(Eje.Z) < -0.1)
							c3.rotar(Eje.Z, radianes);
					}
		}

		else if(cara.equals(xPositivo)){
			for(Cubo[][] c1 : cubos)
				for(Cubo[] c2 : c1)
					for(Cubo c3 : c2)
						if(c3.getPosicion(Eje.X) > 0.1)
							c3.rotar(Eje.X, radianes);
		}

		else if(cara.equals(yPositivo)){
			for(Cubo[][] c1 : cubos)
				for(Cubo[] c2 : c1)
					for(Cubo c3 : c2)
						if(c3.getPosicion(Eje.Y) > 0.1)
							c3.rotar(Eje.Y, radianes);
		}

		else if(cara.equals(zPositivo)){
			for(Cubo[][] c1 : cubos)
				for(Cubo[] c2 : c1)
					for(Cubo c3 : c2)
						if(c3.getPosicion(Eje.Z) > 0.1)
							c3.rotar(Eje.Z, radianes);
		}
	}
	
	public void establecerRotacionCara(Entry<Eje, Boolean> cara, boolean signo){
		 if(cara.equals(xNegativo)){
			for(Cubo[][] c1 : cubos)
				for(Cubo[] c2 : c1)
					for(Cubo c3 : c2)
						if(c3.getPosicion(Eje.X) < -0.1)
							c3.establecerRotacion(Eje.X, signo);
		}

		else if(cara.equals(yNegativo)){
			for(Cubo[][] c1 : cubos)
				for(Cubo[] c2 : c1)
					for(Cubo c3 : c2)
						if(c3.getPosicion(Eje.Y) < -0.1)
							c3.establecerRotacion(Eje.Y, signo);
		}
	
		else if(cara.equals(zNegativo)){
			for(Cubo[][] c1 : cubos)
				for(Cubo[] c2 : c1)
					for(Cubo c3 : c2)
						if(c3.getPosicion(Eje.Z) < -0.1)
							c3.establecerRotacion(Eje.Z, signo);
		}
	
		else if(cara.equals(xPositivo)){
			for(Cubo[][] c1 : cubos)
				for(Cubo[] c2 : c1)
					for(Cubo c3 : c2)
						if(c3.getPosicion(Eje.X) > 0.1)
							c3.establecerRotacion(Eje.X, signo);
		}
	
		else if(cara.equals(yPositivo)){
			for(Cubo[][] c1 : cubos)
				for(Cubo[] c2 : c1)
					for(Cubo c3 : c2)
						if(c3.getPosicion(Eje.Y) > 0.1)
							c3.establecerRotacion(Eje.Y, signo);
		}
	
		else if(cara.equals(zPositivo)){
			for(Cubo[][] c1 : cubos)
				for(Cubo[] c2 : c1)
					for(Cubo c3 : c2)
						if(c3.getPosicion(Eje.Z) > 0.1)
							c3.establecerRotacion(Eje.Z, signo);
		}
	}
	
	
	public void actualizar() {
		System.out.println("LIMPIAR");
		for(Cubo[][] c1 : cubos)
			for(Cubo[] c2 : c1)
				for(Cubo c3 : c2)
					c3.dibujar();
		System.out.println("ACTUALIZAR");
	}
	
	public void restaurar(){
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				for(int k = 0; k < 3; k++) {
					cubos[i][j][k].restaurar();
					cubos[i][j][k].mover((i-1)*0.7f, (j-1)*0.7f, (k-1)*0.7f);
				}
	}
}
