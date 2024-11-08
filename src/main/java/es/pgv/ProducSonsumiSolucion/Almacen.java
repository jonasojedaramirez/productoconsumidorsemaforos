package es.pgv.ProducSonsumiSolucion;

import java.util.concurrent.Semaphore;

public class Almacen {

	public final int maxManzanas = 20;
	
	public int Manzanas;
	
	//Por cada recurso a garantizar la exclusión mutua se necesita un semaforo binario o tipo mutex.
	Semaphore SemMutexManzanas;
	//Como hay que controlar que los productores no desborden el almacen, necesito un semaforo para ellos.
	Semaphore SemProducManzanas;
	//Como hay que controlar que los consumidores no intenten retirar manzanas si no hay, necesito un semaforo para ellos.
	Semaphore SemConsumManzanas;
	
	public Almacen() {
		this.Manzanas = 0;
		//Unsemaforo binario o tipo mutex siempre se inicializa a 1.
		this.SemMutexManzanas = new Semaphore(1);
		//Inicialmente como en el problema nos indican que hay 0 manzanas, pues le indico al semaforo
		//de los productores que inicialmente está disponible depositar hasta el máximo de manzanas posibles.
		this.SemProducManzanas = new Semaphore(this.maxManzanas);
		//Como en el problema nos indican que iniciarlmente hay 0 manzanas, entonces el semaforo de los consumidores
		//lo inicializo a 0, consiguiendo que por defecto los consumidores deben esperar hasta que los productores
		//depositen manzanas en el almacen.
		this.SemConsumManzanas = new Semaphore(0);
		
	}
	
	public void entregar(String Nombre) {
		
		try {
			//El productor solicita depositar una manzana
			this.SemProducManzanas.acquire();
			//En este punto, ya sabemos que puede depositar una manzana pero ahora a la hora de modificar debemos
			//garantizar que solo él pueda hacerlo. Es decir, no puede existir más de un hilo intentando modificar el recurso.
			this.SemMutexManzanas.acquire();
			this.Manzanas++;
			System.out.println(Nombre + " ha añadido una manzana. NumManzanas=" + this.Manzanas);
			//Salgo de la región crítica y la libero
			this.SemMutexManzanas.release();
			//Informo al semaforo de los consumidores que ya hay una manzana más.
			this.SemConsumManzanas.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 

	}
	
	public void retirar(String Nombre) {
		try {
			//Un consumidor solicita retirar una manzana. Si el semaforo se queda en 0 o ya estaba en 0 esperará.
			this.SemConsumManzanas.acquire();
			//En este punto ya sabemos que podemos retirar una manzana pero debemos entrar en la región crítica.
			this.SemMutexManzanas.acquire();
			this.Manzanas--;
			System.out.println(Nombre + " ha retirado una manzana. NumManzanas=" + this.Manzanas);
			//Salimos de la región crítica.
			this.SemMutexManzanas.release();
			//Informamos al semaforo de los productores que ya hay un hueco para otra manzana.
			this.SemProducManzanas.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
}
