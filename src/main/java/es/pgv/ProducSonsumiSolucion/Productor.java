package es.pgv.ProducSonsumiSolucion;

public class Productor extends Thread {
	

	private Almacen objAlmacen;
	private String Nombre;
	
	public Productor(String nombre, Almacen almacen) {
		this.objAlmacen = almacen;
		this.Nombre = nombre;
	}
	
	@Override
	public void run() {
	
		this.objAlmacen.entregar(this.Nombre);
	}

}
