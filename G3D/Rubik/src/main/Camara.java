package main;

public class Camara {
	
	private float[] posicion;
	
	public Camara() {
		System.out.println("CREAR CAMARA ID_CAMARA");
		System.out.println("USAR CAMARA ID_CAMARA");
		System.out.println("PERSPECTIVA CONICA");
		posicion = new float[3];
	}
	
	public void mover(float x, float y, float z){
		posicion[0] += x;
		posicion[1] += y;
		posicion[2] += z;
		System.out.println("MOVER CAMARA "+ x +" "+ y +" "+ z);
	}
	
	public void rotar(Eje eje, float radianes){
		System.out.println("ROTAR CAMARA EJE_"+eje.toString()+" "+radianes);
	}
	
	public void restaurar(){
		System.out.println("RESTAURAR CAMARA");
		posicion = new float[3];
		mover(0.0f, 0.0f, -3.0f);
	}
	
	public float getPosicion(Eje eje){
		return posicion[eje.ordinal()];
	}
}
