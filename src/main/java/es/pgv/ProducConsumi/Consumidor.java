package es.pgv.ProducConsumi;

public class Consumidor extends Thread {
	

	private Almacen objAlmacen;
	private String Nombre;
	
	public Consumidor(String nombre, Almacen almacen) {
		this.objAlmacen = almacen;
		this.Nombre = nombre;
	}
	
	@Override
	public void run() {
		this.objAlmacen.retirar(this.Nombre);
	}

}
