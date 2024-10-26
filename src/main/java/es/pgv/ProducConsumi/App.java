package es.pgv.ProducConsumi;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Almacen objAlmacen = new Almacen();
        
        for (int i = 1; i<=20; i++) {
        	new Consumidor("Consumidor " + i, objAlmacen).start();
        }
        
        for (int i = 1; i<=20; i++) {
        	new Productor("Productor " + i, objAlmacen).start();
        }
        
    }
}
