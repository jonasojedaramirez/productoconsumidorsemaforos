package es.pgv.ProducConsumi;

import java.util.concurrent.Semaphore;

public class Almacen {

	public final int maxManzanas = 20; // hueco maximo en la tienda
	public final int minManzanas = 0; // hueco minimo manzanas que pueden coger los consumidores
	
	private Semaphore mutexAlmacen = new Semaphore(1);  // para proteger el recurso
	private Semaphore semProd = new Semaphore(20); // actor recuerso
	private Semaphore semConsumidores = new Semaphore(minManzanas); // actor recuerso
	
	public int Manzanas;
	
	public Almacen() {
		this.Manzanas = 0;
	}
	
	public void entregar(String Nombre) {
		
		try {
			semProd.acquire();
			mutexAlmacen.acquire();
			this.Manzanas++;
			System.out.println(Nombre + " ha añadido una manzana. NumManzanas=" + this.Manzanas);
			mutexAlmacen.release();
			semProd.release();
			semConsumidores.release();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public void retirar(String Nombre) {
		try {
			semConsumidores.acquire();
			mutexAlmacen.acquire();
			this.Manzanas--;
			System.out.println(Nombre + " ha retirado una manzana. NumManzanas=" + this.Manzanas);
			mutexAlmacen.release();
			semConsumidores.release();
			 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
